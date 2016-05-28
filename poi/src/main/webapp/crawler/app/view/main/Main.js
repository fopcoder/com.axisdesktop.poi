Ext.define( 'Crawler.view.main.Main', {
    extend: 'Ext.panel.Panel',

    requires: [ 'Ext.plugin.Viewport' ],

    constructor: function() {
	    Ext.apply( this, {
	        layout: 'fit',
	        items: [ {
	            layout: 'card',
	            tbar: [ {
	                text: 'Прокси',
	                handler: function() {
		                this.up( 'panel' ).setActiveItem( 'proxy' );
	                }
	            }, {
	                text: 'Ссылки',
	                handler: function() {
		                this.up( 'panel' ).setActiveItem( 'url' );
	                }
	            } ],

	            forceFut: true,
	            items: [ Ext.create( 'Crawler.proxy.view.Panel', {
		            itemId: 'proxy'
	            } ), Ext.create( 'Crawler.url.view.Panel', {
		            itemId: 'url'
	            } ) ]

	        }

	        ],

	    } )

	    this.callParent( arguments );
    },

// requires : [ 'Ext.MessageBox', 'MyApp.view.main.MainController',
// 'MyApp.view.main.MainModel', 'MyApp.view.main.List' ],
//
// controller : 'main',
// viewModel : 'main',
//
// defaults : {
// styleHtmlContent : true
// },

// tabBarPosition : 'bottom',

// items : [ {
// title : 'Home',
// iconCls : 'fa-home',
// layout : 'fit',
// items : [ {
// xtype : 'mainlist'
// } ]
// }, {
// title : 'Users',
// iconCls : 'fa-user',
// bind : {
// html : '{loremIpsum}'
// }
// }, {
// title : 'Groups',
// iconCls : 'fa-users',
// bind : {
// html : '{loremIpsum}'
// }
// }, {
// title : 'Settings',
// iconCls : 'fa-cog',
// bind : {
// html : '{loremIpsum}'
// }
// } ]
} );