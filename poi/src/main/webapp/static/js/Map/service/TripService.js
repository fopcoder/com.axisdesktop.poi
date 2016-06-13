'use strict';

MapApp.service( 'TripService', [ '$http', '$q', function( $http, $q ) {
	var self = this;
	
	self.findTrip = function( params )	{
		return $http.post( '/trip/list', params ).then(
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
	
	self.findDays = function( params )	{
		return $http.post( '/trip/days', params ).then(
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
	
} ])