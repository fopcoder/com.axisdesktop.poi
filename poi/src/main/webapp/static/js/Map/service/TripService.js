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
	
	self.findDay = function( params )	{
		return $http.post( '/trip/day/list', params ).then(
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
	
	self.findPoint = function( params )	{
		return $http.post( '/trip/day/list', params ).then(
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