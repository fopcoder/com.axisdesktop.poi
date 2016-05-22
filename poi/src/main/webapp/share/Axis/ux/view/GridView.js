Ext.define( 'Axis.ux.view.GridView', {
    extend: 'Ext.grid.Panel',
    
    title: 'Axis.ux.view.GridView',

    enablePaging: true,
    border: false,

    listeners: {
	    scope: this
    },

    dockedItems: [],

    // initComponent: function( config ) {
    // console.log(config);
    // },

    constructor: function( config ) {
	    config = config || {};
	    
	    this.storeConfig = this.storeConfig || {};
	    this.store = Ext.create( 'Axis.ux.store.GridStore', this.storeConfig );

	    // var stCfg = {
	    // };
	    //
	    // if( this.storeConfig ) {
	    // Ext.apply( stCfg, config );
	    // storeCfg.model = this.model;
	    // }

	    // var store = Ext.create( 'Ext.data.Store', storeCfg );

	    // var cfg = {
	    //
	    // };

	    // config = config || {};
	    // Ext.apply( this, config );

	    // if( config.storeConfig ) {
	    // Ext.apply( this.store, config.storeConfig );
	    // }
	    // else if( this.storeConfig ) {
	    // Ext.apply( this.store, this.storeConfig );
	    // }

	    // if( config.listenersConfig ) {
	    // Ext.apply( this.listeners, config.listenersConfig );
	    // }

	    if( this.enablePaging ) {
		    this.dockedItems.push( {
		        xtype: 'pagingtoolbar',
		        store: this.store,
		        dock: 'bottom',
		        displayInfo: true,
		    } );
	    }

	    this.callParent();
    }
} )