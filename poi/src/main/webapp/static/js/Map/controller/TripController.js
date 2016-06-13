MapApp.controller( 'TripController', [ 'TripService', function( TripService ) {
	var self = this;

	self.tripList = function( params ) {
		TripService.findTrip( params ).then( //
		function( data ) {
			self.trips = data;
		}, function( err ) {
			console.log( err )
		} );
	};

	self.dayList = function( params ) {
		TripService.findDays( params ).then( //
		function( data ) {
			self.days = data;
		}, function( err ) {
			console.log( err )
		} );
	};

	self.tripList();

} ] );