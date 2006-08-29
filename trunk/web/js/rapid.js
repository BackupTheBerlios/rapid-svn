/*
 * Updates a named container with html data retrieved from url.
 */
function ajaxUpdate( url, container ) {
	//alert( url + ", " + container );
	new Ajax.Updater( container, url );
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