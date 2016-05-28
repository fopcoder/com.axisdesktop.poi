Ext.define( 'Crawler.proxy.store.ProxyStatusStore', {
    extend: 'Axis.ux.store.GridStore',

    model: 'Crawler.proxy.model.ProxyStatus',
    storeId: 'proxyStatusStore',
    sorters: [ {
        property: 'id',
        direction: 'asc'
    } ]
} )