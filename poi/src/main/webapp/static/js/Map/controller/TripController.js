MapApp.controller( 'TripController', [ '$http', '$scope', '$routeParams', 'TripService', 
                                       	function( $http, $scope, $routeParams, TripService ) {
	var self = this;
	self.newTrip = '';
	
	$scope.$on('reloadDays', function() {
        self.listDay();
    });

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
	
	self.addDay = function()	{
		var params = {};
		params.tripId = $routeParams.tripId;
		params.name = self.newTrip;
		
		$http.post( "/trip/create", params ).then(
				function()	{
					$scope.$broadcast('reloadDays');
				},
				function()	{
					
				}
			);
	};
	
	self.moveUp = function( trip )	{
		console.log(trip);
		var params = {};
		params.tripId = trip.id;
		
		$http.post( "/trip/moveUp", params ).then(
			function()	{
				$scope.$broadcast('reloadDays');
			},
			function()	{
				
			}
		);
	};
	
	self.moveDown = function( trip )	{
		var params = {};
		params.tripId = trip.id;
		
		$http.post( "/trip/moveDown", params ).then(
			function()	{
				$scope.$broadcast('reloadDays');
			},
			function()	{
				
			}
		);
	};
	
	self.updateTrip = function( trip )	{
		console.log(trip);
	};

} ] );