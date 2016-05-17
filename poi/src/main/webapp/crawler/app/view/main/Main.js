Ext.define( 'Crawler.view.main.Main', {
    extend: 'Ext.panel.Panel',
    items: [ {
        title: '1111',
        id: 'koko',
        tbar: [ {
            text: 'Next',
            handler: function() {
	            console.log( this );
	            Ext.getCmp( 'koko' ).setActiveItem( 1 );
            }
        } ],
        layout: 'card',
        items: [ {
	        title: '2222'
        }, {
	        title: '3333'
        } ]

    }

    ],

    // requires: [
    // 'Ext.plugin.Viewport'
    // ],

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