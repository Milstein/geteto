function test(msg) {
	alert(msg);
}

AsyncUpload = {

	createFrame : function(formElement, completeCallback) {
		var frameName = 'frameID';
		var divElement = document.createElement('DIV');
		divElement.innerHTML = '<iframe style="display:none"  src="about:blank" id="'+frameName+'" name="'
		+frameName+'" onload="AsyncUpload.documentLoaded(\''+frameName+'\')"></iframe>';
		document.body.appendChild(divElement);
		
		var frameElement = document.getElementById(frameName);
		if (completeCallback && typeof(completeCallback) == 'function') {
			frameElement.completeCallback = completeCallback;
		}
		formElement.setAttribute('target', frameName);
		
	},

	documentLoaded : function(elementID) {
		var frameElement = document.getElementById(elementID);
		if (frameElement.contentDocument) {
			var documentElement = frameElement.contentDocument;
		} else if (frameElement.contentWindow) {
			var documentElement = frameElement.contentWindow.document;
		} else {
			var documentElement = window.frames[elementID].document;
		}
		if (typeof(frameElement.completeCallback) == 'function') {
			frameElement.completeCallback();
		}
	},

	submitForm : function(formElement, startCallback, completeCallback) 
	{
		AsyncUpload.createFrame(formElement, completeCallback);
		if (startCallback && typeof(startCallback) == 'function') 
		{
			return startCallback();
		} else {
			return true;
		}
	}

}
