Ext.tip.QuickTipManager.init();
Ext.Loader.setPath( 'Axis', '../share/Axis' );
Ext.Loader.setPath( 'Ext', '../share/Ext' );
Ext.application( {
    extend: 'Ext.app.Application',

    name: 'Crawler',

    autoCreateViewport: 'Crawler.main.view.Main',

    stores: [ 'Crawler.proxy.store.ProxyStatusStore', 'Crawler.url.store.UrlStatusStore', 'Crawler.data.store.DataTypeStore' ],
    launch: function() {

    }
} );
