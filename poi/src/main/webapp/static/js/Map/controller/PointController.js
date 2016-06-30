MapApp.controller( 'PointController', [ '$http','$scope','$rootScope', '$routeParams', 'UserPointService', 
                                     function( $http,$scope, $rootScope, $routeParams, UserPointService ) {
	var self = this;
	
	self.dayId = $routeParams.dayId;

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
			console.log('kokoko KML');
			//self.points = data.content;
		}, function( err ) {
			console.log( err )
		} );
	};

	
	

} ] )