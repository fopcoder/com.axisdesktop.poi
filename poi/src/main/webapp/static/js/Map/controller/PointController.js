MapApp.controller( 'PointController', [ 'PointService', function() {
	var self = this;

	self.pointList = function( params ) {
		PointService.findTrip( params ).then( //
		function( data ) {
			self.trips = data;
		}, function( err ) {
			console.log( err )
		} );
	};
	
	
	
} ] )