Ext.define( 'Axis.ux.store.GridStore', {
    extend: 'Ext.data.Store',

    autoLoad: true,
    autoSync: true,
    remoteSort: true,
    remoteFilter: true,
    pageSize: 25,
    sorters: [ {
        property: 'id',
        direction: 'asc'
    } ],
    
    constructor: function( config ) {
	    config = config || {};
	    
	    this.callParent( [ config ] );
    }
} )