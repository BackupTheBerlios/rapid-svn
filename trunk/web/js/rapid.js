/*
 * Updates a named container with html data retrieved from url.
 */
function ajaxUpdate( url, container ) {
	//alert( url + ", " + container );
	new Ajax.Updater( container, url );
}

function ajaxText( url, handler ) {
	new Ajax.Request( url, {
		method: 'get',
		onComplete: handler
	} );
}

function setKey( response ) {
	//alert( response );
	$('_key').value = response.responseText;
}

function ajaxForm(form, event, container) {
	//alert( 'form:'+form+',event:'+event+',container:'+container );
    var params = Form.serialize(form);
    if (event != null) params = event + '&' + params;
    //alert( 'params:'+params );
    new Ajax.Updater(container, form.action, {method:'post', postBody:params});
}

function ajaxFormId(formId, event, container) {
	var form = document.getElementById( formId );
	//alert( '[ajaxFormId] formId:'+formId+',form:'+ form );
	ajaxForm( form, event, container );
}

function selectedValueOf( selectId ) {
	alert( 'selectedValueOf('+selectId+')' );
	var select = document.getElementById( selectId );
	return( selectedValue( select ) );
}

function selectedValue( select ) {
	//alert( select );
	for ( var i = 0; i < select.length; ++i ) {
		if ( select.options[i].selected == true ) {
			//alert( select.options[i].value );
			return select.options[i].value;
		}
	}
	return -1;
}
