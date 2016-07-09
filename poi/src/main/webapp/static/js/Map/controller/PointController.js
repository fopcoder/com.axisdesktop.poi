MapApp.controller( 'PointController', [ '$http','$scope','$rootScope', '$routeParams', 'UserPointService', 
                                     function( $http,$scope, $rootScope, $routeParams, UserPointService ) {
	var self = this;
	self.tripId = $routeParams.tripId;
	self.dayId = $routeParams.dayId;
	self.routeInfo = [];
	//self.travelMode = google.maps.TravelMode["DRIVING"];
	self.travelMode = "DRIVING";
	var directionsService = new google.maps.DirectionsService();
	var renderers = [];
	
	//render = new google.maps.DirectionsRenderer();
    //render.setMap($scope.map);

	$scope.$on('reloadUserPoints', function() {
        self.listPoint();
    });
	
	self.listPoint = function( params ) {
		params = params || {};
		params.tripId = $routeParams.dayId;
		params.sorters = [ {
		    property: "point2trip.porder",
		    direction: "asc"
		} ];

		UserPointService.listPoint( params ).then( //
		function( data ) {
			self.points = data.content;
		}, function( err ) {
			console.log( err )
		} );
	};
	
	self.loadInfo = function( id ) {
		var params = {
			id: id
		};

		$http.get( '/point/info/'+id ).then(
				function(res) {//
					console.log( res);
				},
				function(res) {
					console.log(res);
					return $q.reject(res);
				}
			);
		
//		UserPointService.removePoint( params ).then( //
//		function( data ) {
//			$scope.$broadcast('reloadUserPoints');
//		}, function( err ) {
//			console.log( err )
//		} );
	};

	self.addPointAnchor = function( anchor ) {
		var params = {
			latitude: anchor.getPosition().lat(),
			longitude: anchor.getPosition().lng(),
			pointId: anchor.id,
			tripId: $routeParams.dayId ? $routeParams.dayId : 0,
			name: anchor.title,
			isUserPoint: anchor.isUserPoint
		};

		UserPointService.addPoint( params ).then( //
		function( data ) {
			$rootScope.$broadcast('reloadUserPoints');
		}, function( err ) {
			console.log( err )
		} );
	};
	
	self.removePoint = function( params ) {
		var params = {
			pointId: params.id,
			tripId: $routeParams.dayId
		};

		UserPointService.removePoint( params ).then( //
		function( data ) {
			$scope.$broadcast('reloadUserPoints');
		}, function( err ) {
			console.log( err )
		} );
	};
	
	self.selectPoint = function( point )	{
		$rootScope.$broadcast('selectPoint', point );
	};
	
	self.exportKml = function( params )	{
		params = params || {};
		params.tripId = $routeParams.dayId;
		params.sorters = [ {
		    property: "point2trip.porder",
		    direction: "asc"
		} ];

		UserPointService.exportKml( params ).then( //
		function( data ) {
			//self.points = data.content;
		}, function( err ) {
			console.log( err )
		} );
	};

	self.createRoute = function( points )	{
        var maxWpt = 10;
        
        var pts = angular.copy(points);
        var splitPts = [];
        
        var dist = 0;
        var time = 0;
        var routeData = [];
        
        var bounds = new google.maps.LatLngBounds();
        
        while( pts.length )	{
        	var data = pts.splice( 0, ( maxWpt - 1 ) );
        	
        	if( splitPts.length > 0 ) {
        		data.unshift( splitPts[ splitPts.length - 1 ][ maxWpt - 1 ] );
        	}
        	else {
        		if( pts[0] ) data.push( pts[0] );
        	}
        	
        	splitPts.push( data );
        }
        
        console.log(splitPts);
        
        while( renderers.length > 0 )	{
        	var r = renderers.shift();
        	r.setMap(null);
        	r = null;
        }
        
        for( var i = 0; i < splitPts.length; i++ )	{
        	var wp = [];
        	
        	for( var j = 0; j < splitPts[i].length; j++ ) {
        		var loc = new google.maps.LatLng( splitPts[i][j].latitude, splitPts[i][j].longitude );
        		
        		bounds.extend( loc );
        		
                wp.push({
                    location: loc,
                    stopover: true
                });
            }
        	
            var request = {
                origin: ( wp.shift() ).location,
                destination: ( wp.pop() ).location,
                waypoints: wp,
                travelMode: google.maps.TravelMode[self.travelMode]
            };
        	
            directionsService.route( request, directionResults ) ;
        }
        
        $scope.map.fitBounds( bounds );
        
        function displayDist( val ) {
        	if( val < 100 )	{
        		return val + " м";
        	}
        	else if( val < 10000 )	{
        		return ( val / 1000 ).toFixed(1) + " км";
        	}
        	else	{
        		return Math.round( val / 1000 ) + " км";
        	}
        }
        
        function displayTime( val ) {
        	if( val < 60 )	{
        		return 0 + " мин";
        	}
        	else if( val < 3600 )	{
        		return Math.round( val / 60 ) + " мин";
        	}
        	else	{
        		var h = Math.floor( val / 3600 );
        		var m = Math.round( ( val - h * 3600 ) / 60 );
        		return h + ":" + m;
        	}
        }
        
        
        
        function directionResults( result, status ) {
            if( status == google.maps.DirectionsStatus.OK ) {
                var data = result.routes[0];
                var rdl = routeData.length;
                
                if( rdl == 0 && maxWpt > self.points.length ) {
                  routeData.push( self.points[ rdl ].name + ": " +
        			displayDist( 0 )  + " / "+
        			displayTime( 0 ) );
                  	rdl = routeData.length;
                }
                
//                console.log(data.legs);
                
                for( var i = 0; i < data.legs.length; i++ )	{
                	dist += data.legs[i].distance.value;
                	time += data.legs[i].duration.value;
//                	console.log( displayDist(dist) );
                	//if( self.points[ i + rdl  ] ) {
                	routeData.push( self.points[ i + rdl ].name + ": " +
                			displayDist( dist )  + " / "+
                			displayTime( time ) );
                	//}
                }
                
                self.routeInfo = routeData;
                
                var render = new google.maps.DirectionsRenderer();
                render.setMap($scope.map);
                renderers.push( render );

                // Some unique options from the colorArray so we can see the routes
                render.setOptions({
                    preserveViewport: true,
                    suppressInfoWindows: true,
                    polylineOptions: {
                        strokeWeight: 4,
                        strokeOpacity: 0.8,
                        strokeColor: "blue"
                    },
//                    markerOptions:{
//                        icon:{
//                            path: google.maps.SymbolPath.BACKWARD_CLOSED_ARROW,
//                            scale: 3,
//                            strokeColor: colourArray[i]
//                        }
//                    }
                });

                render.setDirections(result);
            }
            
            return data.bounds;

        }
        
        
        
	};
	
	self.moveUp = function( point )	{
		var params = {};
		params.tripId = $routeParams.dayId;
		params.pointId = point.id;
		
		$http.post( "/userpoint/moveUp", params ).then(
			function()	{
				$scope.$broadcast('reloadUserPoints');
			},
			function()	{
				
			}
		);
	};
	
	self.moveDown = function( point )	{
		var params = {};
		params.tripId = $routeParams.dayId;
		params.pointId = point.id;
		
		$http.post( "/userpoint/moveDown", params ).then(
			function()	{
				$scope.$broadcast('reloadUserPoints');
			},
			function()	{
				
			}
		);
	}
	

} ] )