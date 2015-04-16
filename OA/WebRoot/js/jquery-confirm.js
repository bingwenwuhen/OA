(function(jQuery){
	/*$.fn.confirm=function(message){
		window.confirm(message);
	};*/
	$.confirm=function(confirJson){
		$("a").each(function(){
			if($(this).text()=="删除"){
				$(this).unbind("click");
				$(this).bind("click",function(){
					confirJson.callback();
					return window.confirm(confirJson.message);
				});
			}
		});
	};
})(jQuery);
