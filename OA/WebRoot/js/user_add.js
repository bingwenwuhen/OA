var user={
		formValidate:function(){
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
					if($("#message").text()=="该用户名已存在"){
						alert("请输入合理的用户名！");
						return false;
					}
					return true;
				}
			});
		},
	initEvent:function(){
		$("input[name='username']").unbind("blur");
		$("input[name='username']").bind("blur",function(){
			user.checkUser($(this).val());
		});
	},
	checkUser:function(username){
		var parameter={
			username:username	
		};
		$.post("userJSONAction_checkUsername.action",parameter,function(data){
			$("#message").text(data);
			if(data=="该用户名可以使用"){
				$("#message").css("color","blue");
			}else{
				$("#message").css("color","red");
			}
		});
	}
};
$().ready(function(){
	user.formValidate();
	user.initEvent();
	
	
	
});