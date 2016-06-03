Ext.define( 'Axis.ux.form.Panel', {
    extend: 'Ext.form.Panel',

    alias: 'widget.axis.formpanel',

    border: false,
    bodyPadding: 10,
    defaults: {
	    anchor: '100%',
    },
    hideSaveButton: false,
    hideCloseButton: false,

    constructor: function( config ) {
	    config = config || {};
	    Ext.apply( this, config );

	    this.statusBar = Ext.create( 'Ext.ux.StatusBar', {
	    	itemId: 'statusBar',
	        text: 'Ready',
	        iconCls: 'x-status-text',
	    } )

	    Ext.apply( this, {
		    buttons: [ this.statusBar, '->', {
		        text: 'Сохранить',
		        handler: this.submitForm,
		        scope: this,
		        hidden: this.hideSaveButton
		    }, {
		        text: 'Закрыть',
		        scope: this,
		        handler: this.closeForm,
		        hidden: this.hideCloseButton
		    } ]
	    } );

	    this.callParent();
    },

    submitForm: function() {
	    this.submit( {
	        scope: this,
	        success: function( form, action ) {
		        if( action.result && action.result.success ) {
			        this.fireEvent( 'submitsuccess', action.result.data );
			        this.setStatusBar( 1, null );
		        }
		        else {
			        this.fireEvent( 'submitfailure', action.result.data );
			        this.setStatusBar( 0, action.result.data );
		        }
	        },
	        failure: function( form, action ) {
		        this.fireEvent( 'submitfailure', action.result.data );
		        this.setStatusBar( 0, action.result.data );

		        form.getFields().findBy( function( field ) {
			        var hasActiveError = Ext.isEmpty( field.getActiveError() );
			        console.log( field, 'has error: ' + ( hasActiveError ? 'NO' : 'YES' ) );
		        } );
	        }
	    } );
    },

    closeForm: function() {
	    this.fireEvent( 'formclose' );
    },

    setStatusBar: function( status, tip ) {
	    if( status == 1 ) {
		    this.statusBar.setStatus( { text: 'OK', iconCls: 'x-status-valid' } );
	    }
	    else {
		    this.statusBar.setStatus( { text: 'Ошибка', iconCls: 'x-status-error' }  );
	    }
	    // this.statusBar.
	    // var st = this.down( '#statusBar' );
	    // st.setText( text );
	    // st.setStyle( style );
	    // Ext.QuickTips.getQuickTip().targets[ st.getEl().id ].text = tip;
    }
} )