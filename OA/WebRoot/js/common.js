/*var common={
		myconfig:function(message){
			return window.confirm(message);
		}
};*/
$().ready(function(){
	/**
	 * 当页面进行加载的时候，给名称为删除的链接添加一个事件
	 */
	/*$("a").each(function(){
		if($(this).text()=="删除"){
			$(this).unbind("click");
			$(this).bind("click",function(){
				return $(this).confirm("你确认是否要删除？");
			});
		}
	});*/
	$.confirm({message:"你确定要删除吗？",callback:function(){
		alert("a");
	}});
});
