MapApp.controller( 'PointController', [ '$routeParams', 'PointService', function( $routeParams, PointService ) {
	var self = this;

	self.pointList = function( params ) {
		params = params || {};
		params.dayId = $routeParams.dayId;
		
		PointService.listPoint( params ).then( //
		function( data ) {
			self.points = data;
		}, function( err ) {
			console.log( err )
		} );
	};
	
	
	
} ] )