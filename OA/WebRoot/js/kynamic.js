var kynamic={
	/*
	 * 树的操作
	 */
	kynamicTree:{
		zTree:"",
		pNode:"",
		setting:{
			isSimpleData: true,
			treeNodeKey: "kid",
			treeNodeParentKey: "pid",
			//keepParent:true,
			showLine: true,
			root:{ 
				isRoot:true,
				nodes:[]
			},
			callback:{
				rightClick:function(event,treeId,treeNode){
					/*
					 * 在点击右键的时候把treeNode赋值给pNode
					 */
					kynamic.kynamicTree.pNode=treeNode;
					/**
					 * 判断点击的是文件夹节点还是文件节点
					 */
					if(treeNode.isParent){			//文件夹节点
						kynamic.kynamicTree.controlRMenu({
							x:event.clientX,
							y:event.clientY,
							addFile:true,
							addFolder:true,
							deleteNode:true,
							updateNode:true
						});
					}else{							//文件节点
						kynamic.kynamicTree.controlRMenu({
							x:event.clientX,
							y:event.clientY,
							addFile:false,
							addFolder:false,
							deleteNode:true,
							updateNode:true
						});
					}
				}
			}
		},
		loadKynamicTree:function(){
			$.post("kynamicAction_showKynamicTree.action",null,function(data){
				kynamic.kynamicTree.zTree=$("#kynamicTree").zTree(kynamic.kynamicTree.setting,data.kynamicList);
			});
		},
		/*
		 * 控制右键菜单的显示
		 * 		div的位置
		 * 		右键菜单的菜单项
		 */
		controlRMenu:function(rMenuJson){
			/*
			 * 菜单项的显示逻辑
			 */
			/*
			 * 增加文件的菜单的显示控制
			 */
			$("#showRMenu").show();
			$("#rMenu").css({"top":rMenuJson.y+"px","left":rMenuJson.x+"px","display":"block"});
			if(rMenuJson.addFile){
				$("#addFile").show();
			}else{
				$("#addFile").hide();
			}
			
			if(rMenuJson.addFolder){
				$("#addFolder").show();
			}else{
				$("#addFolder").hide();
			}
			
			if(rMenuJson.deleteNode){
				$("#deleteNode").show();
			}else{
				$("#deleteNode").hide();
			}
			
			if(rMenuJson.updateNode){
				$("#updateNode").show();
			}else{
				$("#updateNode").hide();
			}
		},
		/*
		 * 增加节点
		 */
		addNode:function(addJSON){
			/*
			 * 1、在kynamic表中增加一行数据
			 * 2、在指定的父节点下增加一个子节点
			 */
			debugger;
			var folderName=window.prompt(addJSON.fileMessage);
			if(folderName!=null){				//folderName不为null
				if(folderName!=""){
					//检查是否有重名的现象
					$.post("kynamicAction_isExistName.action",{name:folderName},function(data){
						if(data.message=="1"){			//重名了
							alert("该用户名已经存在，请重新输入!");
						}else{
							var parameter={
									name:folderName,
									isParent:addJSON.isParent,
									pid:kynamic.kynamicTree.pNode.kid
								};
								$.post("kynamicAction_saveKynamic.action",parameter,function(data2){
									var kid=data2.kid;
									var newNode={
											kid:kid,
											pid:kynamic.kynamicTree.pNode.kid,
											isParent:addJSON.isParent,
											name:folderName
									};
									kynamic.kynamicTree.zTree.addNodes(kynamic.kynamicTree.pNode,newNode,true);
								});
						}
					});
				}else{
					alert(addJSON.errorMessage);
				}
			}
		},
		/*
		 * 增加文件
		 */
		addFile:function(){
			kynamic.kynamicTree.addNode({
				fileMessage:"请输入文件的名称",
				errorMessage:"文件的名称不能为空",
				isParent:false
			});
		},
		/*
		 * 增加文件夹
		 */
		addFolder:function(){
			kynamic.kynamicTree.addNode({
				fileMessage:"请输入文件夹的名称",
				errorMessage:"文件夹的名称不能为空",
				isParent:true
			});
		},
		/*
		 * 删除节点
		 */
		deleteNode:function(){
			/*
			 * 1、判断当前删除的节点是否是文件节点
			 * 		是
			 * 			直接删除
			 * 		否
			 * 			判断文件夹下是否有子节点
			 * 			如果没有
			 * 				删除
			 * 			如果有
			 * 				提示不能删除
			 */
			if(kynamic.kynamicTree.pNode.isParent){		//文件夹节点
				
			}else{			//文件节点
				if(window.confirm("你确认要删除吗？")){
					var parameter={
							kid:kynamic.kynamicTree.pNode.kid	
						};
					//判断该节点是否有兄弟节点
					$.post("kynamicAction_showSiblingNodes.action",parameter,function(data){
						if(data.kynamicList.length<2){		//没有兄弟节点
							//kynamic.kynamicTree.zTree.getNodesByParam("kid",kynamic.kynamicTree.pNode.pid);
							$.post("kynamicAction_showParentNode.action",parameter,function(data2){
								var pNode=kynamic.kynamicTree.zTree.getNodeByParam("kid",data2.kynamic.kid);			//获取父节点
								
								$.post("kynamicAction_deleteNode.action",parameter,function(data3){
									kynamic.kynamicTree.zTree.removeNode(kynamic.kynamicTree.pNode);
									//更新父节点的isParent的属性为true
									pNode.isParent=true;
									kynamic.kynamicTree.zTree.refresh();
									alert(data3.message);
								});
							});
						}
					});
					
				}
			}
		},
		/*
		 * 修改节点
		 */
		updateNode:function(){
			
		}
	},
	/**
	 * 版本的维护
	 */
	version:{
		
	}
};
$().ready(function(){
	kynamic.kynamicTree.loadKynamicTree();
	/*
	 * hover在这里只是声明事件，事件中的函数中的内容到底是否执行，根据触发的时候判断
	 */
	$("#rMenu").hover(function(){
		/*
		 * 声明增删改查事件
		 */
		$("#addFile").unbind();
		$("#addFile").bind("click",function(){
			kynamic.kynamicTree.addFile();
		});
		$("#addFolder").unbind();
		$("#addFolder").bind("click",function(){
			kynamic.kynamicTree.addFolder();
		});
		$("#deleteNode").unbind();
		$("#deleteNode").bind("click",function(){
			kynamic.kynamicTree.deleteNode();
		});
		$("#updateNode").unbind();
		$("#updateNode").bind("click",function(){
			kynamic.kynamicTree.updateNode();
		});
	},function(){
		//当该方法被触发时，树早已经存在了，右键菜单已经显示了
		$("#rMenu").hide();
	}); 	 
});