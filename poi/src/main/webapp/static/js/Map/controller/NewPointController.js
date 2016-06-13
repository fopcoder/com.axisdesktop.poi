MapApp.controller( "NewPointController", [ "$scope", "$http", function( $scope, $http ) {
	this.showForm = false;
	
	this.createNewPoint = function( newPoint )	{
		console.log($scope.np);
	};
	
	this.formToggle = function()	{
		this.showForm = !this.showForm;
	}
	
	this.createNewPoint = function( data )	{
		$http.post( "/point/create", data||{} );
	}
	
} ] )