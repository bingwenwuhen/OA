var privilege={
	/*
	 * 所有的初始化的操作
	 */
	init:{
		/**
		 * 所有的初始化的事件
		 */
		initEvent:function(){
			/*
			 * 设置权限的click事件
			 */
			$("a").each(function(){
				if($(this).text()=="设置权限"){
					$(this).unbind("click");
					$(this).bind("click",function(){
						/*
						 * 1、显示所有的div
						 * 2、动态的显示用户名
						 * 3、加载权限树
						 */
						var hobj=this;
						//初始化数据
						//privilege.init.initData(hobj);
						//initData函数调用 是当前对象
						privilege.init.initData.call(hobj);
						privilege.pFunction.divOption.showDiv();		//显示所有的div
						privilege.pFunction.userOption.showUsername();	//动态地显示用户名
						privilege.pFunction.privilegeTree.loadPrivilegeTree();		//加载权限树
						return false;
					});
				}
			});
			/*
			 * 全选按钮的事件
			 */
			$("#allchecked").unbind("click");
			$("#allchecked").bind("click",function(){
				privilege.pFunction.privilegeTree.checkAll.call(this);
			});
			/*
			 * 保存权限的事件
			 */
			$("#savePrivilege").unbind("click");
			$("#savePrivilege").bind("click",function(){
				
			});
		},
		/*
		 * 初始化数据
		 */
		initData:function(){
			/*alert($(this).parent().siblings("td:first").text());
			alert($(this).parent().siblings("input[type='hidden']:first").val());*/
			var username=$(this).parent().siblings("td:first").text();
			var uid=$(this).parent().siblings("input[type='hidden']:first").val();
			privilege.data.user.username=username;
			privilege.data.user.uid=uid;
			
		}
	},
		/*
		 * 按照功能划分区域
		 */
		pFunction:{
			privilegeTree:{			//所有的权限树的操作
				zTree:'',
				/*
				 * 树的配置
				 */
				setting:{
					isSimpleData: true,
					treeNodeKey: "mid",
					treeNodeParentKey: "pid",
					showLine: true,
					root:{ 
						isRoot:true,
						nodes:[]
					},
					checkable:true,
					checkType:{
						"Y":"p",
						"N":"s",
					},
					callback:{
						/*
						 * 在点击树上复选框之前触发该方法
						 */
						beforeChange:function(treeId,treeNode){
							privilege.pFunction.privilegeTree.controlCheckBox({
									"Y":"p",
									"N":"s"
							});
						},
						change:function(treeId,treeNode){
							if(privilege.pFunction.privilegeTree.zTree.getCheckedNodes(false).length!=0){//获取到没有选中的
								$("#allchecked").attr("checked",false);
							}else{
								$("#allchecked").attr("checked",true);
							}
						}
					}
				},
				/*
				 * 显示权限树
				 */
				loadPrivilegeTree:function(){
					$.post("privilegeAction_showPrivilege.action",null,function(data){
						privilege.pFunction.privilegeTree.zTree=$("#privilegeTree").zTree(privilege.pFunction.privilegeTree.setting,data.privilegeList);
						/*
						 * 这里是设置全选按钮默认状态的最佳位置
						 * 		*默认值必须在设置权限的超级链接中设置
						 * 		*确保zTree必须有值
						 */
						if(privilege.pFunction.privilegeTree.zTree.getCheckedNodes(false)){//获取到没有选中的
							$("#allchecked").attr("checked",false);
						}else{
							$("#allchecked").attr("checked",true);
						}

					});
					
				},
				/*
				 * 对权限树复选框的控制
				 */
				controlCheckBox:function(checkType){
					var setting=privilege.pFunction.privilegeTree.zTree.getSetting();
					setting.checkType=checkType;
					privilege.pFunction.privilegeTree.zTree.updateSetting(setting);
				},
				/*
				 * 针对某一用户保存权限
				 */
				savePrivilege:function(){
					
				},
				/*
				 * 全选复选框的实现
				 */
				checkAll:function(){
					/*
					 * 改变树上的复选框的选中规则
					 * 		在执行该函数的时候，zTree已经存在了
					 */
					/*
					 * 改变zTree中复选框的规则
					 */
					privilege.pFunction.privilegeTree.controlCheckBox({
						"Y":"ps",
						"N":"ps"
					});
					if($(this).attr("checked")){			//全选
						privilege.pFunction.privilegeTree.zTree.checkAllNodes(true);
					}else{									//全不选
						privilege.pFunction.privilegeTree.zTree.checkAllNodes(false);
					}
				}
			},
			userOption:{			//存放用户的操作
				showUsername:function(){			//把用户名动态地显示到相应div中
					$("#userImage").text(privilege.data.user.username);
				}
			},
			divOption:{				//div的操作
				showDiv:function(){
					$("#userTitle").show();
					$("#privilegeTitle").show();
					$("#privilegeContent").show();
				}
			}
	},
	/*
	 * json对数据的封装
	 */
	data:{			//所有的数据的封装
		user:{		//存放用户的数据
			uid:'',
			username:''
		}
	}
};

$().ready(function(){
	privilege.init.initEvent();
});

/**
 * 全选按钮状态的设置：
 * 		在点击完成设置权限的超级链接时，全选按钮的默认状态已经确定了
 * 			必须在事件中做这件事情
 * 		在全选按钮设置默认值之前，必须保证权限树已经生成了
 * 
 * 
 * */
