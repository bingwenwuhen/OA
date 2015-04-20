$().ready(function(){
	$("input[type='image']").unbind("click");
	$("input[type='image']").bind("click",function(){
		if($("select[name='did'] option:selected").attr("value")==""){
			alert("请选择部门!");
			return false;
		}else{
			if(!$("select[name='pids'] option:selected").attr("value")){
				alert("请选择岗位！");
				return false;
			}
			return true;
		}
	});
	
});