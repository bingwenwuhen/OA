<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script type="text/javascript">
		window.onload=function(){
			for(var i=0;i<100;i++){
				var div=document.createElement("div");
				div.style.border="1px solid green";
				div.innerText="Hello World!";
				document.body.appendChild(div);
			}
		};
	</script>
  </head>
  
  <body>

  </body>
</html>
