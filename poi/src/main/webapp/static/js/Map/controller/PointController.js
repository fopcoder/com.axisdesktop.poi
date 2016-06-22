MapApp.controller( 'PointController', [ '$routeParams', 'PointService', function( $routeParams, PointService ) {
	var self = this;

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
			//self.points = data.content;
		}, function( err ) {
			console.log( err )
		} );
	}

} ] )