Ext.define( 'Crawler.view.main.Main', {
    extend: 'Ext.panel.Panel',
    items: [ {
         id: 'koko',
        tbar: [ {
            text: 'Next',
            handler: function() {
	             console.log( this );
	             Ext.getCmp( 'koko' ).setActiveItem( 1 );
            }
        } ],
        layout: 'card',
        forceFut: true,
        items: [ Ext.create( 'Crawler.proxy.Panel' ), {
	        title: '2222'
        }, ]

    }

    ],
layout: 'fit',
    requires: [ 'Ext.plugin.Viewport' ],

    xtype: 'app-main',

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