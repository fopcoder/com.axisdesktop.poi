Ext.define( 'Axis.ux.form.field.Combo', {
    extend: 'Ext.form.field.ComboBox',
    
    alias: 'widget.axis.combo',

    uses: [ 'Ext.ux.form.trigger.Clear' ],

    valueFiled: 'id',
    displayField: 'name',
    triggers: {
	    clear: {
	        type: 'clear',
	        handler: function( obj ) {
		        obj.clearValue();
		        obj.fireEvent( 'triggerClick' );
	        }
	    }
    },

    constructor: function( config ) {
	    config = config || {};
	    Ext.apply( this, config );
	    
	    if( this.storeId )	{
	    	this.store = Ext.StoreManager.lookup( this.storeId );
	    }
	    else if( this.storeConfig )	{
	    	this.store = Ext.create( 'Axis.ux.store.GridStore', this.storeConfig );
	    }
	    
	    this.callParent( arguments );
    }

} )