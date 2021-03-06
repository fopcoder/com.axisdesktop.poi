Ext.define( 'Crawler.provider.view.Panel', {
    extend: 'Axis.ux.grid.Panel',

    title: 'Источники',

    columns: [ {
        text: 'Код',
        dataIndex: 'id',
        width: 80
    }, {
        text: 'Статус',
        dataIndex: 'statusId',
        width: 80
    },  {
        text: 'Название',
        dataIndex: 'name',
        width: 150
    }, {
        text: 'Создан',
        dataIndex: 'created',
        width: 150,
        xtype: 'datecolumn',
        format: 'Y-m-d H:i'
    }, {
        text: 'Изменен',
        dataIndex: 'modified',
        width: 150,
        xtype: 'datecolumn',
        format: 'Y-m-d H:i'
    }, {
        text: 'Описание',
        dataIndex: 'description',
        flex: 1
    } ]

} )