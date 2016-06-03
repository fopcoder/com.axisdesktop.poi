Ext.define( 'Crawler.data.store.DataTypeStore', {
    extend: 'Axis.ux.store.GridStore',

    model: 'Crawler.data.model.DataType',
    storeId: 'dataTypeStore',
    sorters: [ {
        property: 'id',
        direction: 'asc'
    } ]
} )