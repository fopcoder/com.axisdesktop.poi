Ext.define( 'Crawler.proxy.view.Panel', {
    extend: 'Axis.ux.view.GridView',

    title: 'Proxy servers',
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
        width: 70
    }, {
        text: 'Tries',
        dataIndex: 'tries',
        width: 70
    }, {
        text: 'Created',
        dataIndex: 'created',
        xtype: 'datecolumn',
        format: 'Y-m-d H:i',
        width: 150
    }, {
        text: 'Modified',
        dataIndex: 'modified',
        width: 150,
        format: 'Y-m-d H:i',
        xtype: 'datecolumn'
    } ],

    storeConfig: {
        model: Crawler.proxy.model.Proxy,
        listeners: {
            scope: this,
            beforeload: function( store ) {
	            // console.log(111);
	            // store.proxy.extraParams.filters = [{ property: 'id', value: 1
				// } ];
            }
        },
        filters: [ {
            property: 'active',
            value: 1,
            id: 'activeFilter'
        } ]
    },

    constructor: function( config ) {
	    config = config || {};
	    Ext.apply( this, config );

	    this.appendToolbar = [ {
	        text: 'Add batch',
	        handler: this.addBatch,
	        scope: this
	    }, '-', {
	        xtype: 'checkbox',
	        fieldLabel: 'только активные',
	        labelWidth: 110,
	        value: true,
	        listeners: {
	            scope: this,
	            change: function( obj, newValue, oldValue, eOpts ) {
		            if( newValue ) {
			            this.store.addFilter( {
			                property: 'id',
			                value: 1,
			                id: 'activeFilter'
			            } );
		            }
		            else {
			            this.store.removeFilter( 'activeFilter' );
		            }
	            }
	        }
	    } ];

	    this.callParent();
    },

    addBatch: function() {
	    var me = this;
	    var win = Ext.create( 'Crawler.proxy.view.AddBatchWindow', {
		    batchSuccessHandler: function() {
			    me.store.reload()
		    }
	    } );
    }

} )