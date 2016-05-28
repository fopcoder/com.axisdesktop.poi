Ext.define( 'Crawler.url.store.UrlStatusStore', {
    extend: 'Axis.ux.store.GridStore',

    model: 'Crawler.url.model.UrlStatus',
    storeId: 'urlStatusStore',
    sorters: [ {
        property: 'id',
        direction: 'asc'
    } ]
} )