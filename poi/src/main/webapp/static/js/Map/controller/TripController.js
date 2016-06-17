MapApp.controller( 'TripController', [ '$scope', '$routeParams', 'TripService', function( $scope, $routeParams, TripService ) {
	var self = this;

	self.listTrip = function( params ) {
		params = params || {};
		params.sorters = [ { property: "id", direction: "desc" } ];
		
		TripService.listTrip( params ).then( //
		function( data ) {
			self.trips = data.content;
		}, function( err ) {
			console.log( err )
		} );
	};

	self.listDay = function( params ) {
		params = params || {};
		params.tripId = $routeParams.tripId;
		params.sorters = [ { property: "torder", direction: "asc" } ];
		
		TripService.listDay( params ).then( //
		function( data ) {
			self.days = data.content;
		}, function( err ) {
			console.log( err )
		} );
	};
	
	self.listPoint = function( params ) {
		params = params || {};
		params.tripId = $routeParams.dayId;
		
		TripService.listPoint( params ).then( //
		function( data ) {
			self.days = data;
		}, function( err ) {
			console.log( err )
		} );
	};

} ] );