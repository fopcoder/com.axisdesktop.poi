Ext.define( 'Crawler.proxy.view.Panel', {
    extend: 'Axis.ux.view.GridView',

    title: 'Proxy servers',
    enableAddButton: true,
    enableDelButton: true,

    columns: [ {
        text: 'ID',
        dataIndex: 'id',
        width: 80
    }, {
        text: 'Host',
        dataIndex: 'host',
        width: 200
    }, {
        text: 'Port',
        dataIndex: 'port',
        width: 80
    } ],

    storeConfig: {
        model: Crawler.proxy.model.Proxy,
        pageSize: 25,
        sorters: [ {
            property: 'id',
            direction: 'desc'
        } ]
    },

    constructor: function( config ) {
	    config = config || {};
	    Ext.apply( this, config );

	    this.appendToolbar = [ {
	        text: 'Add batch',
	        handler: this.addBatch,
	        scope: this
	    } ];

	    this.callParent();
    },

    addBatch: function() {
	    var win = Ext.create( 'Crawler.proxy.view.AddBatchWindow' );
    }

} )