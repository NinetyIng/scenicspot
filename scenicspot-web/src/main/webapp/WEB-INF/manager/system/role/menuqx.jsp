<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
	<base href="<%=basePath%>">
	<!-- jsp文件头和头部 -->
	<%@ include file="../../common/top.jsp"%>
	<link type="text/css" rel="stylesheet" href="/assets/js/plugins/zTree/2.6/zTreeStyle.css"/>
	<style type="text/css">
	footer{height:50px;position:fixed;bottom:0px;left:0px;width:100%;text-align: center;}
	</style>
<body>

</head>
<body class="no-skin">

	<!-- /section:basics/navbar.layout -->
	<div class="main-container" id="main-container">
		<!-- /section:basics/sidebar -->
		<div class="main-content">
			<div class="main-content-inner">
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
								<div style="overflow: scroll; scrolling: yes;height:415px;width: 309px;">
								<ul id="tree" class="tree" style="overflow:auto;"></ul>
								</div>
							</div>
						<!-- /.col -->
						</div>
					</div>
					<!-- /.row -->
				</div>
				<!-- /.page-content -->
			</div>
		</div>
		<!-- /.main-content -->
		<div style="width: 100%;padding-top: 5px;" class="center">
			<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
			<a class="btn btn-mini btn-danger" onclick="parent.location.reload();">取消</a>
		</div>
		
		 <script src="/assets/js/jquery-2.1.1.min.js"></script>
		     <script src="/statics/js/util/Ajax.js"></script>
	<script type="text/javascript" src="/assets/js/plugins/zTree/2.6/jquery.ztree-2.6.min.js"></script>
	<script type="text/javascript">
		var zTree;
		$(document).ready(function(){
			var setting = {
			    showLine: true,
			    checkable: true
			};
			var zn = '${zTreeNodes}';
			var zTreeNodes = eval(zn);
			zTree = $("#tree").zTree(setting, zTreeNodes);
		});
		//保存
		 function save(){
			var nodes = zTree.getCheckedNodes();
			var tmpNode;
			var ids = "";
			for(var i=0; i<nodes.length; i++){
				tmpNode = nodes[i];
				if(i!=nodes.length-1){
					ids += tmpNode.id+",";
				}else{
					ids += tmpNode.id;
				}
			}
			var ROLE_ID = "${ROLE_ID}";
			var url = "<%=basePath%>role/saveMenuqx.do";
			var postData;
			postData = {"ROLE_ID":ROLE_ID,"menuIds":ids};
			Ajax.request("/role/saveMenuqx.do",{"data":postData,"success":function(item){
				if(item.result == "200"){
				    parent.location.reload();	
				}else{
					alert(item.msg);
				}
			}});
		 }
	</script>
</body>
</html>