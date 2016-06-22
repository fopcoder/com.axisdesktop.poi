MapApp.service( 'PointService', [ '$http', function( $http ) {
	var self = this;
	
	self.listPoint = function( params )	{
		return $http.post( '/userpoints', params ).then(
			function(res) {
				return res.data;
			},
			function(res) {
				console.log(res);
				return $q.reject(res);
			}
		);
	};
	
	self.addPoint = function( params )	{
		return $http.post( '/userpoint/add', params ).then(
				function(res) {//
					return res.data;
				},
				function(res) {
					console.log(res);
					return $q.reject(res);
				}
			);
	};
	
	
///////	
	self.loadPoint = function( params )	{
		return $http.get( '/userpoint/load/' + params.id ).then(
				function(res) {
					return res.data;
				},
				function(res) {
					console.log(res);
					return $q.reject(res);
				}
			);
	};
	
	self.createPoint = function( params )	{
		return $http.post( '/userpoint/create', params ).then(
				function(res) {
					return res.data;
				},
				function(res) {
					console.log(res);
					return $q.reject(res);
				}
			);
	};
	
	self.updatePoint = function( params )	{
		return $http.post( '/userpoint/update', params ).then(
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
	
	self.deletePoint = function( params )	{
		return $http.get( '/userpoint/delete/' + params.id ).then(
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