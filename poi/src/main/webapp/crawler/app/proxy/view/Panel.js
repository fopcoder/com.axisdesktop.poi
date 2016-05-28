Ext.define( 'Crawler.proxy.view.Panel', {
    extend: 'Axis.ux.view.GridView',

    title: 'Прокси',
    enableDelButton: true,

    columns: [ {
        text: 'Код',
        dataIndex: 'id',
        width: 80
    }, {
        text: 'Хост',
        dataIndex: 'host',
        width: 200
    }, {
        text: 'Порт',
        dataIndex: 'port',
        width: 70
    }, {
        text: 'Попыток',
        dataIndex: 'tries',
        width: 70
    }, {
        text: 'Создан',
        dataIndex: 'created',
        xtype: 'datecolumn',
        format: 'Y-m-d H:i',
        width: 150
    }, {
        text: 'Изменен',
        dataIndex: 'modified',
        width: 150,
        format: 'Y-m-d H:i',
        xtype: 'datecolumn'
    }, {
        text: 'Статус',
        dataIndex: 'statusId',
        width: 100,
        renderer: function( val, meta, rec ) {
	        var store = Ext.data.StoreManager.lookup( 'proxyStatusStore' );
	        return store.getById( val ).get( "name" );
        }
    }, {
        text: 'Лог',
        dataIndex: 'log',
        flex: 1

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
        } ],
        sorters: [ {
            property: 'id',
            direction: 'desc'
        } ]
    },

    constructor: function( config ) {
	    config = config || {};
	    Ext.apply( this, config );

	    this.appendToolbar = [ {
	        text: 'Добавить список',
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