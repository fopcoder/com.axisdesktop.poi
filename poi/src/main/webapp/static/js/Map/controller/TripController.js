MapApp.controller( 'TripController', [ '$http', '$scope', '$routeParams', 'TripService',
	function( $http, $scope, $routeParams, TripService ) {
	    var self = this;
	    self.newTrip = '';
	    self.currentTrip = {};
	
	    $scope.$on( 'reloadDays', function() {
	        self.dayList();
	    } );
	    
	    $scope.$on( 'reloadTrips', function() {
	        self.tripList();
	    } );
	
	    self.tripList = function( params ) {
	        params = params || {};
	        params.sorters = [ {
	            property: "id",
	            direction: "desc"
	        } ];
	
	        TripService.tripList( params ).then( //
	        function( data ) {
		        self.trips = data.content;
	        }, function( err ) {
	        } );
	    };
	
	    self.dayList = function( params ) {
	        params = params || {};
	        params.tripId = $routeParams.tripId;
	        params.sorters = [ {
	            property: "torder",
	            direction: "asc"
	        } ];
	
	        TripService.dayList( params ).then( //
	        function( data ) {
		        self.days = data.content;
	        }, function( err ) {
	        } );
	    };
	
	    self.pointList = function( params ) {
	        params = params || {};
	        params.tripId = $routeParams.dayId;
	
	        TripService.pointList( params ).then( //
	        function( data ) {
		        self.days = data;
	        }, function( err ) {
	        } );
	    };
	
	    self.loadTrip = function() {
    		var params = {};
	        params.tripId = $routeParams.tripId;
	
	        TripService.load( params ).then( //
	        	function( data ) {
	        		self.currentTrip = data;
	        	}, 
	        	function() {} 
	        );	
	    };
	    
	    self.createTrip = function( form ) {
	    	if( form.$valid ){
	    		var params = {};
		        params.name = self.newTrip;
		
		        TripService.create( params ).then( //
		        function() {
			        $scope.$broadcast( 'reloadTrips' );
			        self.newTrip = '';
		        }, function() {
		        } );	
	    	}
	    };
	    
	    self.createDay = function() {
	        var params = {};
	        params.tripId = $routeParams.tripId;
	        params.name = self.newTrip;
	
	        TripService.create( params ).then( //
	        function() {
		        $scope.$broadcast( 'reloadDays' );
		        self.newTrip = '';
	        }, function() {
	        } );
	    };
	
	    self.moveUp = function( trip ) {
	        var params = {};
	        params.tripId = trip.id;
	
	        TripService.moveUp( params ).then( //
	        function() {
		        $scope.$broadcast( 'reloadDays' );
	        }, function() {
	        } );
	    };
	
	    self.moveDown = function( trip ) {
	        var params = {};
	        params.tripId = trip.id;
	
	        TripService.moveDown( params ).then( //
	        function() {
		        $scope.$broadcast( 'reloadDays' );
	        }, function() {
	        } );
	    };
	
	    self.updateTrip = function( data, trip ) {
	    	var params = {
	    		name: data.name,
	    		tripId: trip.id
	    	};
	    	
	    	if( data.name.length > 0 ) {
	    		TripService.update( params ).then( //
    		        function() {
    			        $scope.$broadcast( 'reloadTrips' );
    		        }, 
    		        function() {} 
    	        );	
	    	}
	    	else	{
	    		$scope.$broadcast( 'reloadTrips' );
	    	}
	    	
	    };
	    
	    self.updateDay = function( data, trip ) {
	    	var params = {
	    		name: data.name,
	    		tripId: trip.id
	    	};
	    	
	    	TripService.update( params ).then( //
		        function() {
			        $scope.$broadcast( 'reloadDays' );
		        }, 
		        function() {} 
	        );
	    };
	    
	    self.deleteTrip = function( trip ) {
	    	var params = {
	    		tripId: trip.id
	    	};
	    	
	    	if( confirm('Удалить полностью всю поездку?') == true )	{
	    		TripService.delete( params ).then( //
    		        function() {
    			        $scope.$broadcast( 'reloadTrips' );
    		        }, 
    		        function() {} 
    	        );
	    	}
	    };
	    
	    self.deleteDay = function( trip ) {
	    	var params = {
	    		tripId: trip.id
	    	};
	    	
	    	if( confirm('Удалить день?') == true )	{
	    		TripService.delete( params ).then( //
    		        function() {
    			        $scope.$broadcast( 'reloadDays' );
    		        }, 
    		        function() {} 
    	        );
	    	}
	    };
	
	} ] );