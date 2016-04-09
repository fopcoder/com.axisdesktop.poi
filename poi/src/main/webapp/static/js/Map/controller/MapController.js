MapApp.controller("MapController", function($scope, $http, NgMap) {
	var mc = this;
	var markers = [];
	var markerClusterer;
	var checkedPoints = {};
	
	var checkedIcon = new google.maps.MarkerImage(
	    "http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=|00D900",
	    null, /* size is determined at runtime */
	    null, /* origin is 0,0 */
	    null, /* anchor is bottom center of the scaled image */
	    null //new google.maps.Size(12, 18)
	);
	
	var freeIcon = new google.maps.MarkerImage(
	    "http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=|ff0000",
	    null, /* size is determined at runtime */
	    null, /* origin is 0,0 */
	    null, /* anchor is bottom center of the scaled image */
	    null //new google.maps.Size(12, 18)
	);
	
	NgMap.getMap().then(function(map) {
		mc.map = map;
		markerClusterer = new MarkerClusterer( map );
		
		mc.onIdle = function(event) {
			console.log("idle");
			mc.loadPointsInBBox( mc.map.getBounds() );
		}
	});

	mc.loadPointsInBBox = function( bounds )	{
		
		$http
			.post( "/poi/getdata", bounds )
			.then(
					function( res ) {
						for( var i = 0; i < markers.length; i++ ){
							markers[i].setMap(null);
						}
						markers = [];
						markerClusterer.clearMarkers();
						
						
						for( var i = 0; i < res.data.length; i++ )	{
							markers.push( createMarker( res.data[i] ) );
						}
						
						if( mc.map.getZoom() > 8 )	{
							for( var i = 0; i < markers.length; i++ )	{
								markers[i].setMap( mc.map );
							}
						}
						else	{
							markerClusterer.addMarkers( markers );
						}
					},
					function( res ) {
						console.log(res);
					}
			);
	}
	
	createMarker = function( data )	{
		var marker = new google.maps.Marker({
		    position: new google.maps.LatLng(data[1], data[2]),
		    title: data[3],
		    id: data[0],
		    icon: isChecked( data[0] ) ? checkedIcon : freeIcon
		});
		
		google.maps.event.addListener( marker, 'click', function() {
			$scope.chkp = isChecked( data[0] );
			mc.map.showInfoWindow( "info-window" , this );
		} );
		
		return marker;
	}
	
	isChecked = function( id )	{
		if( checkedPoints[id] )	{
			return true;
		}
		
		return false;
	}
	
	setChecked = function( id, checked )	{
		if( checked == false )	{
			delete checkedPoints[id];
			for( var i = 0; i < markers.length; i++ )	{
				if( markers[i].id == id )	{
					markers[i].setIcon(freeIcon);	
				}
			}
		}
		else	{
			checkedPoints[id] = 1;
			for( var i = 0; i < markers.length; i++ )	{
				if( markers[i].id == id )	{
					markers[i].setIcon( checkedIcon );	
				}
			}
		}
	}
	
	mc.checkPoint = function( id, checked )	{
		setChecked( id, checked );
		console.log( id, checked );
	}
	
})