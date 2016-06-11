Ext.define( 'Crawler.data.view.Panel', {
    extend: 'Axis.ux.grid.Panel',

    title: 'Данные',
    
    storeConfig: {
    	model: 'Crawler.data.model.Data',
    	pageSize: 100
    },

    constructor: function( config ) {
	    config = config || {};
	    Ext.apply( this, config );

	    this.callParent( arguments );
    }

} )