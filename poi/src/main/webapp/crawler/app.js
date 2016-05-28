Ext.tip.QuickTipManager.init();
Ext.Loader.setPath( 'Axis', '../share/Axis' );
Ext.application( {
    extend: 'Ext.app.Application',

    name: 'Crawler',

    autoCreateViewport: 'Crawler.view.main.Main',

    stores: [ 'Crawler.proxy.store.ProxyStatusStore', 'Crawler.url.store.UrlStatusStore' ],
    launch: function() {

    }
} );
