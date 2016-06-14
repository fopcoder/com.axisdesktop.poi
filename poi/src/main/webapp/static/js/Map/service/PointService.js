MapApp.service( 'PointService', [ '$http', function( $http ) {
	self.findPoint = function( params )	{
		return $http.post( '/trip/day/point/list', params ).then(
			function(res) {
				console.log(res);
				return res.data;
			},
			function(res) {
				console.log(res);
				return $q.reject(res);
			}
		);
	};
} ] )