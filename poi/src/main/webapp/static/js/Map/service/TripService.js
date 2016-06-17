'use strict';

MapApp.service( 'TripService', [ '$http', '$q', function( $http, $q ) {
	var self = this;
	
	self.listTrip = function( params )	{
		return $http.post( '/trips', params ).then(
			function(res) {
				return res.data;
			},
			function(res) {
				console.log(res);
				return $q.reject(res);
			}
		);
	};
	
	self.listDay = function( params )	{
		return $http.post( '/trip/days', params ).then(
			function(res) {
				return res.data;
			},
			function(res) {
				console.log(res);
				return $q.reject(res);
			}
		);
	};
	
	self.listPoint = function( params )	{
		return $http.post( '/trip/day/points', params ).then(
			function(res) {
				return res.data;
			},
			function(res) {
				console.log(res);
				return $q.reject(res);
			}
		);
	};
	
} ])