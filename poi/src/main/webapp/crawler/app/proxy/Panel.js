Ext.define( 'Crawler.proxy.Panel', {
    extend: 'Axis.ux.view.Grid',
    columns: [ {
	    text: 'uuuu', dataIndex: 'name', flex: 1
    }, {
	    text: 'yyyyy', dataIndex: 'id', flex: 1
    } ],
    border: false,
    layout: 'fit',
    store: Ext.create( 'Ext.data.ArrayStore', { data: [[1,'111'],[2,'22222']], fields: ['id','name'] })
} )