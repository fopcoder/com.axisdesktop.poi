MapApp.controller("MapController", [ '$scope', '$http', '$localStorage', '$controller', '$routeParams', 'TripService', 'NgMap', 
            function($scope, $http, $localStorage, $controller, $routeParams, TripService, NgMap) {
	var mc = this;
	var markers = [];
	var markerClusterer;
	var checkedPoints = {};
	var defaultZoom = 6;
	var currenrMarker;
	
	var checkedIcon = new google.maps.MarkerImage(
	    "http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=|0000ff",
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
	
	var currentIcon = new google.maps.MarkerImage(
	    "http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=|00ff00",
	    null, /* size is determined at runtime */
	    null, /* origin is 0,0 */
	    null, /* anchor is bottom center of the scaled image */
	    null //new google.maps.Size(12, 18)
	);
	
	var userIcon = new google.maps.MarkerImage(
		    "http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=|ff00ff",
		    null, /* size is determined at runtime */
		    null, /* origin is 0,0 */
		    null, /* anchor is bottom center of the scaled image */
		    null //new google.maps.Size(12, 18)
		);
	
	$scope.$on('selectPoint', function( event, data ) {
		mc.map.setCenter( { lat: data.latitude, lng: data.longitude } );
	} );
	
	$scope.newPoint = {
		value: '',
		latitude: '',
		longitude: '',
		name: '',
		description: ''
	};
	
	var runIdle = false;
	
	// slidebar
	$scope.sidebarChecked = false; 

	mc.sidebarToggle = function()	{
		$scope.sidebarChecked = !$scope.sidebarChecked;
	}
	
	NgMap.getMap().then(function(map) {
		$scope.map = map;
		mc.map = map;
		markerClusterer = new MarkerClusterer( map, null, { imagePath: '/static/js/markerclusterer/images/m' } );
		/*
		google.maps.event.addListener(map, "click", function(event) {
			if( currenrMarker )	{
				currenrMarker.setMap(null);
			}
			
			$scope.newPoint.val = event.latLng.toString().replace("(","").replace(")","");
			console.log( $scope.newPoint.val );
			
	        currenrMarker = new google.maps.Marker({
	            position: event.latLng, 
	            map: map,
	            icon: currentIcon
	        });
	        
	        google.maps.event.addListener( currenrMarker, 'click', function( event1 ) {
//	        	if( !$scope.sidebarChecked )	{
//	        		angular.element(document.querySelector('#close')).triggerHandler('click');
//	        	}
	        	//console.log(event1.latLng.toString().replace("(","").replace(")",""));
	        	$scope.newPoint.val = event1.latLng.toString().replace("(","").replace(")","");
			} );
	        
	    });
	    */
		
		restoreCheckedPoints();
		restoreZoom();
		restoreCenterPoint();
		
		if( markers.length == 0 )	{
			mc.loadPointsInBBox( mc.map.getBounds() );
		}
		
		mc.onIdle = function(event) {
			if( runIdle )	{
				mc.loadPointsInBBox( mc.map.getBounds() );
				saveCenterPoint();
				saveZoom();
			}
			runIdle = true; // грязній хак
		}
		
		mc.onClick = function(event) {
			if( currenrMarker )	{
				currenrMarker.setMap(null);
			}
			
			currenrMarker = new google.maps.Marker({
	            position: event.latLng, 
	            map: map,
	            icon: currentIcon
	        });
			
			if( !$scope.sidebarChecked )	{
        		angular.element(document.querySelector('#panel-button')).triggerHandler('click');
        	}
			
			$scope.newPoint.value = event.latLng.lat().toFixed(6) + ", " + event.latLng.lng().toFixed(6);
			$scope.newPoint.latitude = event.latLng.lat();
			$scope.newPoint.longitude = event.latLng.lng();
			$scope.newPoint.name = '';
			$scope.newPoint.description = '';
			
			$scope.tab = 1;
			
		}
//		TripService.findTrip().then(
//			function( data ) {
//				console.log(data);
//			},
//			function( data ) {
//				console.log(data);
//			}
//		);
		
//		$http.get( '/trip/list' ).then( 
//				function( data )	{
//					console.log('success  '+arguments);
//				},
//				function( data )	{
//					console.log('failure  '+arguments);
//				}
//		);
		
		
		var directionsService = new google.maps.DirectionsService();
	    var num, data;
	    var requestArray = [], renderArray = [];

	    // A JSON Array containing some people/routes and the destinations/stops
	    var jsonArray = {
	        "Person 1": ["Torquay", "Teignmouth", "Dawlish", "Exeter"],
	        "Person 2": ["Exmouth", "Sidmouth", "Taunton", "Crediton", "Okehampton"],
	        "Person 3": ["Penzance", "Truro", "Bodmin", "Falmouth"]
	    }
	        
	    // 16 Standard Colours for navigation polylines
	    var colourArray = ['navy', 'grey', 'fuchsia', 'black', 'white', 'lime', 'maroon', 'purple', 'aqua', 'red', 'green', 'silver', 'olive', 'blue', 'yellow', 'teal'];

	    // Let's make an array of requests which will become individual polylines on the map.
	    function generateRequests(){

	        requestArray = [];

	        for (var route in jsonArray){
	            // This now deals with one of the people / routes

	            // Somewhere to store the wayoints
	            var waypts = [];
	            
	            // 'start' and 'finish' will be the routes origin and destination
	            var start, finish
	            
	            // lastpoint is used to ensure that duplicate waypoints are stripped
	            var lastpoint

	            data = jsonArray[route]

	            limit = data.length
	            for (var waypoint = 0; waypoint < limit; waypoint++) {
	                if (data[waypoint] === lastpoint){
	                    // Duplicate of of the last waypoint - don't bother
	                    continue;
	                }
	                
	                // Prepare the lastpoint for the next loop
	                lastpoint = data[waypoint]

	                // Add this to waypoint to the array for making the request
	                waypts.push({
	                    location: data[waypoint],
	                    stopover: true
	                });
	            }

	            // Grab the first waypoint for the 'start' location
	            start = (waypts.shift()).location;
	            // Grab the last waypoint for use as a 'finish' location
	            finish = waypts.pop();
	            if(finish === undefined){
	                // Unless there was no finish location for some reason?
	                finish = start;
	            } else {
	                finish = finish.location;
	            }

	            // Let's create the Google Maps request object
	            var request = {
	                origin: start,
	                destination: finish,
	                waypoints: waypts,
	                travelMode: google.maps.TravelMode.DRIVING
	            };

	            // and save it in our requestArray
	            requestArray.push({"route": route, "request": request});
	        }

	        processRequests();
	    }

	    function processRequests(){

	        // Counter to track request submission and process one at a time;
	        var i = 0;

	        // Used to submit the request 'i'
	        function submitRequest(){
	            directionsService.route(requestArray[i].request, directionResults);
	        }

	        // Used as callback for the above request for current 'i'
	        function directionResults(result, status) {
	            if (status == google.maps.DirectionsStatus.OK) {
	                
	                // Create a unique DirectionsRenderer 'i'
	                renderArray[i] = new google.maps.DirectionsRenderer();
	                renderArray[i].setMap(map);

	                // Some unique options from the colorArray so we can see the routes
	                renderArray[i].setOptions({
	                    preserveViewport: true,
	                    suppressInfoWindows: true,
	                    polylineOptions: {
	                        strokeWeight: 4,
	                        strokeOpacity: 0.8,
	                        strokeColor: colourArray[i]
	                    },
	                    markerOptions:{
	                        icon:{
	                            path: google.maps.SymbolPath.BACKWARD_CLOSED_ARROW,
	                            scale: 3,
	                            strokeColor: colourArray[i]
	                        }
	                    }
	                });

	                // Use this new renderer with the result
	                renderArray[i].setDirections(result);
	                // and start the next request
	                nextRequest();
	            }

	        }

	        function nextRequest(){
	            // Increase the counter
	            i++;
	            // Make sure we are still waiting for a request
	            if(i >= requestArray.length){
	                // No more to do
	                return;
	            }
	            // Submit another request
	            submitRequest();
	        }

	        // This request is just to kick start the whole process
	        submitRequest();
	    }
		
		
		
		
		
		//generateRequests()
		
	});

	
	
	
	mc.loadPointsInBBox = function( bounds )	{
		
		$http
			.post( "/point/list", bounds )
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
		    //label: 111,
		    id: data[0],
		    isUserPoint: data[4] == 1 ? true : false,
		    icon: isChecked( data[0] ) ? checkedIcon : data[4] == 1 ? userIcon : freeIcon
		});
		
		google.maps.event.addListener( marker, 'click', function() {
			$scope.chkp = isChecked( data[0] );
			
			if( data[4] == 1 ) {
				$http.get( '/userpoint/info/'+data[0] ).then(
						function(res) {//
							$scope.infoData = res.data;
							//console.log( res);
						},
						function(res) {
							console.log(res);
							//return $q.reject(res);
						}
					);
			}
			else	{
				$http.get( '/point/info/'+data[0] ).then(
						function(res) {//
							$scope.infoData = res.data;
							//console.log( res);
						},
						function(res) {
							console.log(res);
							//return $q.reject(res);
						}
					);
			}
			
			
			//data-ng-init="pc.loadInfoAnchor( anchor.id )"
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
	
	saveCheckedPoints = function()	{
		$localStorage.checkedPoints = angular.toJson( checkedPoints );
	}
	
	restoreCheckedPoints = function()	{
		if( $localStorage.checkedPoints )	{
			checkedPoints = angular.fromJson( $localStorage.checkedPoints );
		}
	}
	
	saveZoom = function()	{
		$localStorage.zoom = mc.map.getZoom();
	}
	
	restoreZoom = function()	{
		mc.map.setZoom( $localStorage.zoom ? $localStorage.zoom : defaultZoom );
	}
	
	saveCenterPoint = function()	{
		$localStorage.centerPoint = angular.toJson( mc.map.getCenter() );
	}
	
	restoreCenterPoint = function()	{
		if( $localStorage.centerPoint )	{
			mc.map.setCenter( angular.fromJson( $localStorage.centerPoint ) );
		}
		else	{
			// TODO
		}
	}
	
	mc.checkPoint = function( id, checked )	{
		setChecked( id, checked );
		saveCheckedPoints();
		console.log( id, checked );
	}
	
	mc.addToList = function( anchor )	{
		var params = {};
		params.latitude = anchor.getPosition().lat();
		params.longitude = anchor.getPosition().lng();
		
		if( $routeParams.tripId )	{
			params.tripId = $routeParams.id;
		}
		
		if( anchor.id )	{
			params.pointId = anchor.id;
		}
		
		$http.post( "/trip/add/point", params ).then(
			function()	{
				
			},
			function()	{
				
			}
		);
	}
	
}])