MapApp.controller( 'PointController', [ '$http','$scope','$rootScope', '$routeParams', 'UserPointService', 
                                     function( $http,$scope, $rootScope, $routeParams, UserPointService ) {
	var self = this;
	self.tripId = $routeParams.tripId;
	self.dayId = $routeParams.dayId;
	self.routeInfo = [];
	//self.travelMode = google.maps.TravelMode["DRIVING"];
	self.travelMode = "DRIVING";

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
		var waypts = [];
        var start, finish;
        var directionsService = new google.maps.DirectionsService();
        
        for( var i = 0; i < points.length; i++) {
            waypts.push({
                location: new google.maps.LatLng( points[i].latitude, points[i].longitude ),
                stopover: true
            });
        }
        start = (waypts.shift()).location;
        finish = (waypts.pop()).location;
        
        var request = {
            origin: start,
            destination: finish,
            waypoints: waypts,
            travelMode: google.maps.TravelMode[self.travelMode]
        };
        
        directionsService.route( request, directionResults );
        
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
        	
        
        function directionResults(result, status) {
            if (status == google.maps.DirectionsStatus.OK) {
                console.log(result);
                
                var data = result.routes[0];
                
                $scope.map.fitBounds(data.bounds);
                
                var dist = 0;
                var time = 0;
                var routeData = [];
                for( var i = 0; i < data.legs.length; i++ )	{
                	dist += data.legs[i].distance.value;
                	time += data.legs[i].duration.value;
                	
                	routeData.push( self.points[i+1].name + ": " +
                			displayDist( dist )  + " / "+
                			displayTime( time ) );
                }
                
                self.routeInfo = routeData;
                
                var render = new google.maps.DirectionsRenderer();
                render.setMap($scope.map);

                // Some unique options from the colorArray so we can see the routes
                render.setOptions({
                    preserveViewport: true,
                    suppressInfoWindows: true,
                    polylineOptions: {
                        strokeWeight: 4,
                        strokeOpacity: 0.8,
                        strokeColor: colourArray[i]
                    },
//                    markerOptions:{
//                        icon:{
//                            path: google.maps.SymbolPath.BACKWARD_CLOSED_ARROW,
//                            scale: 3,
//                            strokeColor: colourArray[i]
//                        }
//                    }
                });

                // Use this new renderer with the result
                render.setDirections(result);
                // and start the next request
                //nextRequest();
            }

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