Ext.define( 'Crawler.proxy.view.AddBatchWindow', {
    extend: 'Ext.Window',

    layout: 'fit',
    title: 'Add proxy server list',
    width: 500,
    autoShow: true,

    uses: [ 'Axis.ux.view.FormView' ],

    constructor: function( config ) {
	    config = config || {};

	    Ext.apply( this, config, {
		    items: [ {
		        xtype: 'tabpanel',
		        items: [ {
		            title: 'Proxy list',
		            xtype: 'axis.formview',
		            api: {
			            submit: Crawler.proxyService.batchCreate
		            },
		            paramsAsHash: true,
		            items: [ {
		                xtype: 'textarea',
		                name: 'proxyText',
		                height: 300
		            } ],
		            listeners: {
		                formclose: function() {
			                this.up('window').destroy();
		                },
		                submitsuccess: function() {
			                // this.up('window').destroy();
		                }
		            }
		        }, {
		        	xtype: 'axis.formview',
		            title: 'Result',
		            hideSaveButton: true,
		            items: [ {
			            xtype: 'textarea',
			            itemId: 'result',
			            height: 300,
			            readOnly: true
		            } ],
		            listeners: {
		                formclose: function() {
			                this.up('window').destroy();
		                }
		            }
		        } ]
		    } ]
	    } );

	    this.callParent();
    },

} )