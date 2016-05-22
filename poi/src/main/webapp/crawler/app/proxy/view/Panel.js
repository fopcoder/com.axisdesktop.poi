Ext.define( 'Crawler.proxy.view.Panel', {
    extend: 'Axis.ux.view.GridView',
    
    title: 'Прокси сервера',
    
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
//    dockedItems: [ {
//        xtype: 'pagingtoolbar',
//        store: 'simpsonsStore', // same store GridPanel is using
//        dock: 'bottom',
//        displayInfo: true
//    } ]
} )