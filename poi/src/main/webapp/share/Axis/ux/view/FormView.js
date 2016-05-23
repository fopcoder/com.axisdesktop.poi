Ext.define( 'Axis.ux.view.FormView', {
    extend: 'Ext.form.Panel',

    border: false,
    bodyPadding: 10,
    // margin: 10,
    defaults: {
	    anchor: '100%',
    // margin: 5
    },

    constructor: function( config ) {
	    config = config || {};

	    Ext.apply( this, config, {
		    buttons: [ {
		        text: 'Сохранить',
		        handler: this.submitForm,
		        scope: this
		    }, {
		        text: 'Закрыть',
		        scope: this,
		        handler: this.closeForm
		    } ],
	    } );

	    this.callParent();
    },

    submitForm: function() {
	    console.log( this.getValues() );

	    this.submit( {
	        scope: this,
	        // params: this.getValues(),
	        success: function() {
		        this.fireEvent( 'submitsuccess', this );
	        },
	        failure: function( form, action ) {
		        console.log( 'form failure' );

		        this.getFields().findBy( function( field ) {
			        var hasActiveError = Ext.isEmpty( field.getActiveError() );
			        console.log( field, 'has error: ' + ( hasActiveError ? 'NO' : 'YES' ) );
		        } );

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