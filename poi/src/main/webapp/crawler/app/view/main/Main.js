Ext.define( 'Crawler.view.main.Main', {
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
	                storeConfig: {
		                model: 'Crawler.proxy.model.ProxyStatus',
	                }
	            } ), //
	            Ext.create( 'Crawler.simple.view.Panel', {
	                title: 'Статусы ссылок',
	                itemId: 'urlStatus',
	                storeConfig: {
		                model: 'Crawler.url.model.UrlStatus',
	                }
	            } ), //
	            Ext.create( 'Crawler.simple.view.Panel', {
	                title: 'Типы данных',
	                itemId: 'dataType',
	            // storeConfig: {
	            // model: 'Crawler.data.model.DataType',
	            // }
	            } ), //
	            Ext.create( 'Crawler.url.view.Panel', {
		            itemId: 'url'
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