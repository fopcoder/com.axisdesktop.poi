Ext.define( 'Axis.ux.view.GridView', {
    extend: 'Ext.grid.Panel',

    title: 'Axis.ux.view.GridView',

    enablePaging: true,
    enableToolbar: true,
    enableReloadButton: true,
    enableAddButton: false,
    enableDelButton: false,
    border: false,

    constructor: function( config ) {
	    config = config || {};
	    Ext.apply( this, config );

	    this.store = Ext.create( 'Axis.ux.store.GridStore', this.storeConfig );
	  
	    var cfg = {
		    dockedItems: []
	    };

	    if( this.enablePaging ) {
		    cfg.dockedItems.push( {
		        xtype: 'pagingtoolbar',
		        store: this.store,
		        dock: 'bottom',
		        displayInfo: true,
		    } );
	    }

	    if( this.enableToolbar ) {
		    var tbar = Ext.create( 'Ext.toolbar.Toolbar' );

		    if( this.enableReloadButton ) {
			    tbar.add( {
			        text: 'Обновить',
			        itemId: 'reload',
			        handler: this.reloadGrid,
			        scope: this
			    } );
		    }

		    if( this.enableAddButton ) {
			    tbar.add( {
			        text: 'Добавить',
			        itemId: 'add',
			        handler: this.addRow,
			        scope: this
			    } );
		    }

		    if( this.enableDelButton ) {
			    tbar.add( {
			        text: 'Удалить',
			        itemId: 'delete',
			        handler: this.delRow,
			        scope: this
			    } );
		    }

		    if( this.appendToolbar && this.appendToolbar.length > 0 ) {
			    tbar.add( this.appendToolbar );
			    this.tbar = null;
		    }

		    cfg.dockedItems.push( tbar );
	    }

	    Ext.apply( this, cfg );

	    this.callParent();
    },

    reloadGrid: function() {
	    this.store.reload();
    },

    addRow: function() {

    },

    delRow: function() {
	    var sel = this.getSelectionModel().getSelection();

	    if( sel.length == 0 ) {
		    Ext.Msg.alert( 'Selection is empty!' );
		    return;
	    }

	    Ext.Msg.confirm( 'Delete row', 'Delete selected rows?', function( but ) {
		    if( but == 'yes' ) {
			    if( this.delRowHandler ) {
				    this.delRowHandler
			    }
			    else {
				    this.store.remove( sel );
			    }
		    }
	    }, this );

    }

} )