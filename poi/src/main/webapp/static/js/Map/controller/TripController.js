MapApp.controller( 'TripController', [ '$scope', '$routeParams', 'TripService', function( $scope, $routeParams, TripService ) {
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
		params = params || {};
		params.tripId = $routeParams.id;
		
		TripService.findDay( params ).then( //
		function( data ) {
			self.days = data;
		}, function( err ) {
			console.log( err )
		} );
	};

} ] );