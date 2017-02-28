Ext.define( 'Crawler.poi.view.Panel', {
    extend: 'Axis.ux.grid.Panel',

    title: 'POI',
    
    columns: [ {
        text: 'Код',
        dataIndex: 'id',
        width: 80
    }, 
    
    {
        text: 'providerId',
        dataIndex: 'providerId',
        width: 200
    }, {
        text: 'urlId',
        dataIndex: 'urlId',
        width: 70
    }, 
    {
        text: 'categoryId',
        dataIndex: 'categoryId',
        width: 70
    }, 
    {
        text: 'languageId',
        dataIndex: 'languageId',
        width: 70
    },
    {
        text: 'parentId',
        dataIndex: 'parentId',
        width: 70
    },
    {
        text: 'Тип',
        dataIndex: 'typeId',
        width: 70,
        renderer: function( val, meta, rec ) {
	        var store = Ext.data.StoreManager.lookup( 'dataTypeStore' );
	        return store.getById( val ).get( "name" );
        }
    },
    {
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
        text: 'data',
        dataIndex: 'data',
        width: 100,
        renderer: function( val ) {
        	console.log(val.comment);
	        return Ext.JSON.encode( val );
        }
    } ],
    
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