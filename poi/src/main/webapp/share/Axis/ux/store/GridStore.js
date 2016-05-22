Ext.define( 'Axis.ux.store.GridStore', {
    extend: 'Ext.data.Store',

    autoLoad: true,
    autoSync: true,
    remoteSort: true,

    constructor: function( config ) {
	    config = config || {};

	    Ext.apply( config, {
	    	
	    // proxy: this.createProxy()
	    } );

	    this.callParent( [ config ] );

	    this.proxy.extraParams.filters = [ {
	        property: 'port',
	        value: '8080'
	    } ];
    }

} )