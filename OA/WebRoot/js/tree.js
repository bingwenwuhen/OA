var tree={
		zTree:'',
		pNode:'',
		setting:{
			isSimpleData: true,
			treeNodeKey: "mid",
			treeNodeParentKey: "pid",
			showLine: true,
			root:{ 
				isRoot:true,
				nodes:[]
			},
			callback:{
				/**
				 * event:鼠标事件
				 * treeId：树的容器id
				 * treeNode：当前点击的节点
				 */
				expand:function(event,treeId,treeNode){
					tree.pNode=treeNode;
					tree.loadNodeByPNODE();
				}
			}
		},
	loadTree:function(){
		$.post("menuitemAction_getAllMenuitem.action",null,function(data){
			$("#tree").zTree(tree.setting,data.menuitemList);
		});
	},
	loadRootNode:function(){
		var parameter={
			pid:0	
		};
		$.post("menuitemAction_showMenuitemsById.action",parameter,function(data){
			tree.zTree=$("#tree").zTree(tree.setting,data.menuitemList);
		});
	},
	/*
	 * 该方法是在点击父节点的+号的时候执行的，这就意味着在执行该方法的时候，树早已经生成了，所以才能用tree.zTree
	 */
	loadNodeByPNODE:function(){
		var parameter={
			pid:tree.pNode.mid
		};
		if(!tree.zTree.getNodeByParam("pid",tree.pNode.mid)){
			$.post("menuitemAction_showMenuitemsById.action",parameter,function(data){
				/**
				 * 把查询出来的子节点追加到父节点上
				 */
				tree.zTree.addNodes(tree.pNode,data.menuitemList,true);
			});
		}
	}
};
$().ready(function(){
	//tree.loadTree();
	tree.loadRootNode();
});

