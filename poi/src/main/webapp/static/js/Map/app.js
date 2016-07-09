'use strict';

var MapApp = angular.module( "MapApp", [ "ngMap", "ngStorage", "ngRoute", "pageslide-directive", "ui.bootstrap", "xeditable" ] )
        .config( [ '$routeProvider', function( $routeProvider ) {
	        $routeProvider//
	        .when( "/", {
		        template: "11111"
	        } )//
	        .when( "/trips", {
		        templateUrl: "/include/trip_list.html"
	        } )//
	        .when( "/trip/:tripId", {
		        templateUrl: "/include/trip_day_list.html"
	        } )//
	        .when( "/trip/:tripId/day/:dayId", {
		        templateUrl: "/include/trip_point_list.html"
	        } )//
	        .otherwise( {
		        redirectTo: "/"
	        } );
        } ] );
