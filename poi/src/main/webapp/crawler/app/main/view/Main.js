Ext.define( 'Crawler.main.view.Main', {
    extend: 'Ext.panel.Panel',

    requires: [ 'Ext.plugin.Viewport' ],

    constructor: function() {
	    var me = this;

	    Ext.apply( this, {
	        layout: 'fit',
	        items: [ {
	            itemId: 'cardPanel',
	            layout: 'card',

	            tbar: [ {
	                text: 'Прокси',
	                handler: me.switchCard,
	                itemId: 'proxy'
	            }, {
	                text: 'Ссылки',
	                handler: me.switchCard,
	                itemId: 'url'
	            }, {
	                text: 'Источники',
	                handler: me.switchCard,
	                itemId: 'provider'
	            }, {
	                text: 'Данные',
	                handler: me.switchCard,
	                itemId: 'data'
	            }, {
	                text: 'Справочники',
	                menu: [ {
	                    text: 'Статусы прокси',
	                    handler: me.switchCard,
	                    itemId: 'proxyStatus'
	                }, {
	                    text: 'Статусы ссылок',
	                    handler: me.switchCard,
	                    itemId: 'urlStatus'
	                }, {
	                    text: 'Типы данных',
	                    handler: me.switchCard,
	                    itemId: 'dataType'
	                } ]
	            } ],

	            items: [ //
	            Ext.create( 'Crawler.proxy.view.Panel', {
		            itemId: 'proxy'
	            } ), //
	            Ext.create( 'Crawler.simple.view.Panel', {
	                title: 'Статусы прокси',
	                itemId: 'proxyStatus',
	                storeId: 'proxyStatusStore'
	            } ), //
	            Ext.create( 'Crawler.simple.view.Panel', {
	                title: 'Статусы ссылок',
	                itemId: 'urlStatus',
	                storeId: 'urlStatusStore'
	            } ), //
	            Ext.create( 'Crawler.simple.view.Panel', {
	                title: 'Типы данных',
	                itemId: 'dataType',
	                storeId: 'dataTypeStore'
	            } ), //
	            Ext.create( 'Crawler.url.view.Panel', {
		            itemId: 'url'
	            } ),//
	            Ext.create( 'Crawler.provider.view.Panel', {
		            itemId: 'provider'
	            } ), //
	            Ext.create( 'Crawler.data.view.Panel', {
		            itemId: 'data'
	            } ) ]

	        }

	        ],

	    } )

	    this.callParent( arguments );
    },

    switchCard: function() {
	    this.up( '#cardPanel' ).setActiveItem( this.itemId );
    }

} );