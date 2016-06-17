'use strict';

var MapApp = angular.module( "MapApp", [ "ngMap", "ngStorage", "ngRoute", "pageslide-directive", "ui.bootstrap" ] )
        .config( [ '$routeProvider', function( $routeProvider ) {
	        $routeProvider//
	        .when( "/", {
		        template: "11111"
	        } )//
	        .when( "/trips", {
		        templateUrl: "/include/trip_list.html"
	        } )//
	        .when( "/trip/:id", {
		        template: "pppp {{id}}"
	        } )//
	        .when( "/trip/:id/day", {
		        templateUrl: "/include/trip_day_list.html"
	        } )//
	        .when( "/trip/:tripId/day/:dayId/point", {
		        templateUrl: "/include/trip_point_list.html"
	        } )//
	        .otherwise( {
		        redirectTo: "/"
	        } );
        } ] );
