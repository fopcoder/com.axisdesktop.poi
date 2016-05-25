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
		        text: 'Save',
		        handler: this.submitForm,
		        scope: this,
		        hidden: this.hideSaveButton
		    }, {
		        text: 'Close',
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
	        success: function() {
		        this.fireEvent( 'submitsuccess', this );
	        },
	        failure: function( form, action ) {
		        console.log( 'form failure' );
		        console.log( arguments );
		        if( this.getFields ) {
			        this.getFields().findBy( function( field ) {
				        var hasActiveError = Ext.isEmpty( field.getActiveError() );
				        console.log( field, 'has error: ' + ( hasActiveError ? 'NO' : 'YES' ) );
			        } );
		        }
		        // success: function(form, action) {
		        // console.log(form.isValid());
		        // var values = form.getValues();
		        // console.log(values);
		        // },
		        // failure: function(form, action) {
		        // form.getFields().findBy(function(field) {
		        // var hasActiveError = Ext.isEmpty(field.getActiveError());
		        // console.log(field, 'has error: ' + (hasActiveError ? 'NO' :
		        // 'YES'));
		        // });
		        // }

	        }
	    } );
    },

    closeForm: function() {
	    this.fireEvent( 'formclose', this );
    }
} )