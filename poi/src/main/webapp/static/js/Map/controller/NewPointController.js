MapApp.controller( "NewPointController", [ "$scope", "$http", "$routeParams", "$rootScope", function( $scope, $http, $routeParams, $rootScope ) {
	this.showForm = false;
	
	this.createNewPoint = function( newPoint )	{
		console.log($scope.np);
	};
	
	this.formToggle = function()	{
		this.showForm = !this.showForm;
	}
	
	this.createNewPoint = function( data )	{
		data = data || {};
		data.tripId = $routeParams.dayId;
		$http.post( "/point/create", data||{} ).then( function(){
			$rootScope.$broadcast('reloadUserPoints');
		},function(){});
	}
	
} ] )