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
				},
				/*
				 * kynamic的点击事件
				 */
				click:function(event,treeId,treeNode){
					/*
					 * 在单击的时候，右键不一定发生了，所以要重新给pnode赋值
					 */
					kynamic.kynamicTree.pNode=treeNode;
					var parameter={
						kid:kynamic.kynamicTree.pNode.kid	
					};
					$.post("kynamicAction_showVersionsByKid.action",parameter,function(data){
						if(data.versionList.length==0){			//没有版本
							alert("aaa");
						}else{									//有版本
							//控制div和checkin和checkout按钮的显示
							kynamic.version.controlShowVersion({
								addVersion:false,
								versionList:true,
								checkin:false,
								checkout:false
							});
							kynamic.version.showVersionsByKID(data.versionList);
						}
					});
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
				if(kynamic.kynamicTree.zTree.getNodeByParam("pid",kynamic.kynamicTree.pNode.kid)){
					alert("因为有子节点，所以不能删除！");
				}else{
					
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
							}else{		//有兄弟节点的情况
								$.post("kynamicAction_deleteNode.action",parameter,function(data3){
									kynamic.kynamicTree.zTree.removeNode(kynamic.kynamicTree.pNode);
									//更新父节点的isParent的属性为true
									pNode.isParent=true;
									alert(data3.message);
								});
							}
						});
						
					}
					
				}
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
						}else{
							$.post("kynamicAction_deleteNode.action",parameter,function(data3){
								kynamic.kynamicTree.zTree.removeNode(kynamic.kynamicTree.pNode);
								//更新父节点的isParent的属性为true
								pNode.isParent=true;
								alert(data3.message);
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
			var newName=window.prompt("请重新输入名称",kynamic.kynamicTree.pNode.name);
			var parameter={
					name:newName
			};
			var para={
					kid:kynamic.kynamicTree.pNode.kid,
					name:kynamic.kynamicTree.name
			};
			$.post("kynamicAction_isExistName.action",parameter,function(data){
				if(data.message=="1"){				//重名了
					alert("该文件名称已经存在，请重新输入！");
				}else{		//进行修改
					$.post("kynamicAction_updateKynamic.action",para,function(data2){
						kynamic.kynamicTree.pNode.name=newName;
						kynamic.kynamicTree.zTree.refresh();
					});
				}
			});
		}
	},
	/**
	 * 版本的维护
	 */
	version:{
		/*
		 * 如果当前点击的节点有版本，则显示版本
		 */
		showVersionsByKID:function(versionList){
			/*
			 *  <tr>
				    <td width="240" height="26" align="center" valign="middle" bgcolor="#FFFFFF" style="border-bottom:1px solid #f3f8fd;">1</td>
				    <td width="232" align="center" valign="middle" bgcolor="#FFFFFF" style="border-bottom:1px solid #f3f8fd;">2010-5-24 09:56:33</td>
				    <td width="231" align="center" valign="middle" bgcolor="#FFFFFF" style="border-bottom:1px solid #f3f8fd;">删除</td>
  				</tr>
			 */
			$("#showVersion").empty();
			for(var i=0;i<versionList.length;i++){
				/*(function(){
					var version=versionList[i].version;
					var updatetime=versionList[i].updatetime; 
					var $versionA=$("<a/>");
					$versionA.text(version);
					$versionA.css("cursor","pointer");
					$versionA.unbind("click");
					
					 * click事件是在单击的时候触发的，这段代码所在的函数showVersionByID,而这个函数在回调函数中
					 * 		versionList是传递过来的参数，所以回调函数的声明周期结束以后，该参数就不存在了，所以
					 * 		alert(versionList[i]); is undifined
					 
					$versionA.bind("click",function(){
						alert(version);
					});
					
					var $versionTD=$("<td/>");
					$versionTD.attr("height","26");
					$versionTD.attr("align","center");
					$versionTD.attr("valign","middle");
					$versionTD.attr("bgcolor","#FFFFFF");
					$versionTD.attr("style","border-bottom:1px solid #f3f8fd;");
					$versionTD.append($versionA);
					
					var $updatetimeTD=$("<td/>");
					$updatetimeTD.attr("align","center");
					$updatetimeTD.attr("valign","middle");
					$updatetimeTD.attr("bgcolor","#FFFFFF");
					$updatetimeTD.attr("style","border-bottom:1px solid #f3f8fd;");
					$updatetimeTD.text(updatetime);
					
					var $A=$("<a/>");
					$A.text("删除");
					$delTD=$("<td/>");
					$delTD.attr("align","center");
					$delTD.attr("valign","middle");
					$delTD.attr("bgcolor","#FFFFFF");
					$delTD.attr("style","border-bottom:1px solid #f3f8fd;");
					$delTD.append($A);
					
					var $TR=$("<tr/>");
					$TR.append($versionTD);
					$TR.append($updatetimeTD);
					$TR.append($delTD);
					$("#showVersion").append($TR);
				})();*/
				//第二种解决方法
				var version=versionList[i].version;
				var updatetime=versionList[i].updatetime; 
				var $versionA=$("<a/>");
				$versionA.text(version);
				$versionA.css("cursor","pointer");
				$versionA.attr("version",version);
				$versionA.unbind("click");
				$versionA.bind("click",function(){
					alert($(this).attr("version"));
				});
				
				var $versionTD=$("<td/>");
				$versionTD.attr("height","26");
				$versionTD.attr("align","center");
				$versionTD.attr("valign","middle");
				$versionTD.attr("bgcolor","#FFFFFF");
				$versionTD.attr("style","border-bottom:1px solid #f3f8fd;");
				$versionTD.append($versionA);
				
				var $updatetimeTD=$("<td/>");
				$updatetimeTD.attr("align","center");
				$updatetimeTD.attr("valign","middle");
				$updatetimeTD.attr("bgcolor","#FFFFFF");
				$updatetimeTD.attr("style","border-bottom:1px solid #f3f8fd;");
				$updatetimeTD.text(updatetime);
				
				var $A=$("<a/>");
				$A.text("删除");
				$delTD=$("<td/>");
				$delTD.attr("align","center");
				$delTD.attr("valign","middle");
				$delTD.attr("bgcolor","#FFFFFF");
				$delTD.attr("style","border-bottom:1px solid #f3f8fd;");
				$delTD.append($A);
				
				var $TR=$("<tr/>");
				$TR.append($versionTD);
				$TR.append($updatetimeTD);
				$TR.append($delTD);
				$("#showVersion").append($TR);
			}
		},
		/*
		 * 控制两个div和checkin checkout的显示
		 */
		controlShowVersion:function(versionShowJson){
			if(versionShowJson.addVersion){
				$("#addVersion").show();
			}else{
				$("#addVersion").hide();
			}
			
			if(versionShowJson.versionList){
				$("#versionList").show();
			}else{
				$("#versionList").hide();
			}
			
			if(versionShowJson.checkin){
				$("#checkin").show();
			}else{
				$("#checkin").hide();
			}
			
			if(versionShowJson.checkout){
				$("#checkout").show();
			}else{
				$("#checkout").hide();
			}
			
		},
		/*
		 * check in	操作
		 */
		checkin:function(){
			/*
			 * 通过check in 操作生成一个新的版本号
			 * 	如果该节点没有版本号
			 * 		那么新的版本号为1
			 * 	如果该节点有版本号
			 * 		版本号为最高版本号加1
			 */
		},
		/*
		 * 点击某一个版本号，显示具体的title和content内容
		 */
		showContent:function(){
			
		},
		/*
		 * 删除某一个版本
		 */
		deleteVersion:function(){
			
		}
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