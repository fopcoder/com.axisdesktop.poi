MapApp.controller( 'PointController', [ '$scope','$rootScope', '$routeParams', 'PointService', 
                                     function( $scope, $rootScope, $routeParams, PointService ) {
	var self = this;

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

		PointService.listPoint( params ).then( //
		function( data ) {
			self.points = data.content;
		}, function( err ) {
			console.log( err )
		} );
	};

	self.addPointAnchor = function( anchor ) {
		var params = {
			latitude: anchor.getPosition().lat(),
			longitude: anchor.getPosition().lng(),
			pointId: anchor.id,
			tripId: $routeParams.dayId ? $routeParams.dayId : 0,
			name: anchor.title
		};

		PointService.addPoint( params ).then( //
		function( data ) {
			$rootScope.$broadcast('reloadUserPoints');
		}, function( err ) {
			console.log( err )
		} );
	};
	
	self.selectPoint = function( point )	{
		$rootScope.$broadcast('selectPoint', point );
	};


} ] )