var tree={
		setting:{
			isSimpleData: true,
			treeNodeKey: "mid",
			treeNodeParentKey: "pid",
			showLine: true,
			root:{ 
				isRoot:true,
				nodes:[]
			}
		},
	loadTree:function(){
		$.post("menuitemAction_getAllMenuitem.action",null,function(data){
			alert(data.menuitemList);
			$("#tree").zTree(tree.setting,data.menuitemList);
		});
	}	
};
$().ready(function(){
	tree.loadTree();
});


/*
 * 
 */
