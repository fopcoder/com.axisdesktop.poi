Ext.define( 'Axis.ux.view.FormView', {
    extend: 'Ext.form.Panel',

    alias: 'widget.axis.formview',

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

	    Ext.apply( this, {
		    buttons: [ {
		        xtype: 'label',
		        text: '',
		        itemId: 'statusBar',
		        listeners: {
			        afterrender: function( obj ) {
				        Ext.tip.QuickTipManager.register( {
				            target: obj.getId(),
				            text: ''
				        } );
			        }
		        }
		    }, '->', {
		        text: 'Save',
		        handler: this.submitForm,
		        scope: this,
		        hidden: this.hideSaveButton
		    }, {
		        text: 'Close',
		        scope: this,
		        handler: this.closeForm,
		        hidden: this.hideCloseButton
		    }, ]
	    } );

	    this.callParent();
    },

    submitForm: function() {
	    this.submit( {
	        scope: this,
	        success: function( form, action ) {
		        if( action.result && action.result.success ) {
			        this.fireEvent( 'submitsuccess', action.result.data );
			        this.setStatusBar( 'OK', { color: 'green', 'text-decoration': 'none' }, null );
		        }
		        else {
			        this.fireEvent( 'submitfailure', action.result.data );
			        this.setStatusBar( 'Failure', { color: 'red', 'text-decoration': 'underline' }, action.result.data );
		        }
	        },
	        failure: function( form, action ) {
	        	this.fireEvent( 'submitfailure', action.result.data );
		        this.setStatusBar( 'Failure', { color: 'red', 'text-decoration': 'underline' }, action.result.data );

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
    
    setStatusBar: function( text, style, tip ) {
    	var st = this.down( '#statusBar' );
    	st.setText( text );
    	st.setStyle( style );
        Ext.QuickTips.getQuickTip().targets[ st.getEl().id ].text = tip;
    }
} )