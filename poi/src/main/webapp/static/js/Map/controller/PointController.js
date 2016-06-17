MapApp.controller( 'PointController', [ '$routeParams', 'PointService', function( $routeParams, PointService ) {
	var self = this;

	self.listPoint = function( params ) {
		params = params || {};
		params.tripId = $routeParams.dayId;
		
		PointService.listPoint( params ).then( //
		function( data ) {
			self.points = data;
		}, function( err ) {
			console.log( err )
		} );
	};
	
	
	
} ] )