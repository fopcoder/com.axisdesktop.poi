'use strict';

MapApp.service( 'TripService', [ '$http', '$q', function( $http, $q ) {
	var self = this;
	
	self.tripList = function( params )	{
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
	
	self.dayList = function( params )	{
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
	
	self.pointList = function( params )	{
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
	
	self.load = function( params ) {
		return $http.post( "/trip/load", params ).then( 
			function( res ) {
		       return res.data;
	        }, function( res ) {
	        	console.log(res);
				return $q.reject(res);
	        } 
	    );
	};
	
	self.create = function( params ) {
		return $http.post( "/trip/create", params ).then( 
			function( res ) {
		       return res.data;
	        }, function( res ) {
	        	console.log(res);
				return $q.reject(res);
	        } 
	    );
	};
	
	self.update = function( params ) {
		return $http.post( "/trip/update", params ).then( 
			function( res ) {
		       return res.data;
	        }, function( res ) {
	        	console.log(res);
				return $q.reject(res);
	        } 
	    );
	};
	
	self.moveUp = function( params ) {
		return $http.post( "/trip/moveUp", params ).then( 
			function( res ) {
		       return res.data;
	        }, function( res ) {
	        	console.log(res);
				return $q.reject(res);
	        } 
	    );
	};
	
	self.moveDown = function( params ) {
		return $http.post( "/trip/moveDown", params ).then( 
			function( res ) {
		       return res.data;
	        }, function( res ) {
	        	console.log(res);
				return $q.reject(res);
	        } 
	    );
	};
	
	self.delete = function( params ) {
		return $http.post( "/trip/delete", params ).then( 
			function( res ) {
		       return res.data;
	        }, function( res ) {
	        	console.log(res);
				return $q.reject(res);
	        } 
	    );
	};
	
} ])