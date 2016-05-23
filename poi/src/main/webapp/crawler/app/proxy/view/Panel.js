Ext.define( 'Crawler.proxy.view.Panel', {
    extend: 'Axis.ux.view.GridView',

    title: 'Прокси сервера',
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
        pageSize: 25
    },

    constructor: function( config ) {
	    config = config || {};
	    Ext.apply( this, config );

	    this.appendToolbar = [ {
	        text: 'Добавить список',
	        handler: this.addBatch,
	        scope: this
	    } ];

	    this.callParent();
    },

    addBatch: function() {
	    var win = Ext.create( 'Ext.Window', {
	        layout: 'fit',
	        title: 'Добавление списка прокси серверов',
	        width: 500
	    } );

	    var form = Ext.create( 'Axis.ux.view.FormView', {
	    	api: {
	    		submit: ProxyService.batchCreate
	    	},
	    	paramsAsHash: true,
	        items: [ {
	            xtype: 'textarea',
	            name: 'proxy_text',
	            height: 300
	        } ],
	        listeners: {
		        formclose: function() {
			        win.destroy();
		        },
		        submitsuccess: function()	{
		        	win.destroy();
		        }
	        }
	    } );

	    win.add( form );
	    win.show();
    }

} )