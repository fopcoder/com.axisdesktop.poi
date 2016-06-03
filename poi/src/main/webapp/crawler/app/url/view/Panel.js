Ext.define( 'Crawler.url.view.Panel', {
    extend: 'Axis.ux.view.GridPanel',

    title: 'Ссылки',

    uses: [ 'Axis.ux.form.field.Combo' ],

    // plugins: 'gridfilters',

    columns: [ {
        xtype: 'rownumberer',
        width: 50,
        text: '#'
    }, {
        text: 'Код',
        dataIndex: 'id',
        width: 80
    }, {
        text: 'Источник',
        dataIndex: 'providerId',
        width: 80
    }, {
        text: 'Тип',
        dataIndex: 'typeId',
        width: 80,
        renderer: function( val, meta, rec ) {
	        var store = Ext.data.StoreManager.lookup( 'dataTypeStore' );
	        return store.getById( val ).get( "name" );
        }
    }, {
        text: 'URL',
        dataIndex: 'url',
        flex: 1
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
	        var store = Ext.data.StoreManager.lookup( 'urlStatusStore' );
	        return store.getById( val ).get( "name" );
        }
    }, {
        text: 'Лог',
        dataIndex: 'log',
        flex: 1

    }, {
        text: 'params',
        dataIndex: 'params',
        width: 80
    }, {
        text: 'parentId',
        dataIndex: 'parentId',
        width: 80
    }

    ],

    storeConfig: {
        model: Crawler.url.model.Url,
        pageSize: 100,
        listeners: {
            scope: this,
            beforeload: function( store ) {

            }
        },
        sorters: [ {
            property: 'id',
            direction: 'desc'
        } ]
    },

    constructor: function( config ) {
	    config = config || {};
	    Ext.apply( this, config );

	    this.appendToolbar = [ '-', {
	        xtype: 'axis.combo',
	        fieldLabel: 'Тип',
	        labelWidth: 30,
	        width: 150,
	        storeId: 'dataTypeStore',
	        listeners: {
	            scope: this,
	            select: function( obj, rec ) {
		            if( rec && rec.get( 'id' ) ) {
			            this.store.addFilter( {
			                property: 'typeId',
			                value: rec.get( 'id' ),
			                id: 'typeFilter'
			            } );
		            }
		            else {
			            this.store.removeFilter( 'typeFilter' );
		            }
	            },
	            triggerClick: function( obj ) {
		            this.store.removeFilter( 'typeFilter' );
	            },
	        }
	    }, {
	        xtype: 'axis.combo',
	        fieldLabel: 'Статус',
	        labelWidth: 50,
	        width: 190,
	        storeId: 'urlStatusStore',
	        listeners: {
	            scope: this,
	            select: function( obj, rec ) {
		            if( rec.get( 'id' ) ) {
			            this.store.addFilter( {
			                property: 'statusId',
			                value: rec.get( 'id' ),
			                id: 'statusFilter'
			            } );
		            }
		            else {
			            this.store.removeFilter( 'statusFilter' );
		            }
	            },
	            triggerClick: function( obj ) {
		            this.store.removeFilter( 'statusFilter' );
	            },
	        }
	    } ];

	    this.callParent();
    }
} )