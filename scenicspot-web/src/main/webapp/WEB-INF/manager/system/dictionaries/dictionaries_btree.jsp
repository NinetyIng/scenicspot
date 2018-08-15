﻿<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>码表管理</title>
    <link href="/assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="/assets/css/font-awesome.min.css" rel="stylesheet">
    <link href="/assets/css/animate.min.css" rel="stylesheet">
    <link href="/assets/css/style.min.css" rel="stylesheet">
    <link href="/assets/css/plugins/treeview/bootstrap-treeview.css" rel="stylesheet">

</head>
<body class="gray-bg">
    <div class="row wrapper wrapper-content animated fadeInRight">
        <div class="col-sm-4">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>菜单预览</h5>
                    <div class="ibox-tools" >
                    </div>
                </div>
                <div class="ibox-content">
                    <div>
                        <div id="treeview11" class="test"></div>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
        <div class="col-sm-8" style="height:900px;">
            <div class="ibox float-e-margins" style="height:100%">
                <div class="ibox-content" style="height:100%">
                   <iframe name="treeFrame" id="treeFrame" frameborder="0" src="<%=basePath%>/dictionaries/list.do?DICTIONARIES_ID=${'' == DICTIONARIES_ID?'0':DICTIONARIES_ID}&currentPage=${null == pd.dnowPage || '' == pd.dnowPage?'1':pd.dnowPage}" style="margin:0 auto;width:100%;height:100%;"></iframe>
                </div>
            </div>
        </div>
    </div>
    <input type="hidden" value="${menuJson}" id="menuJson"/>
    <script src="/statics/js/util/Ajax.js"></script>
    <script src="/assets/js/jquery-2.1.1.min.js"></script>
    <script src="/assets/js/bootstrap.min.js"></script>
    <!-- 自定义js -->
    <script src="/assets/js/content.min.js"></script>
    <!-- Bootstrap-Treeview plugin javascript -->
    <script src="/assets/js/plugins/treeview/bootstrap-treeview.js"></script>
</body>
  <script type="text/javascript">
    $(function(){
    	initTree();
    });
    function initTree(){
    	Ajax.request("/dictionaries/dictionariesJson",{"success":function(data){
    		if(data.result == "200"){
    			$("#treeview11").treeview({
    				color : "#428bca",
    				data : data.data,
    				onNodeSelected : function(e, o) {
    					$("#treeFrame").attr("src",o.href);
    				}
    			});
    		}
    	}});
    }
  </script>
</html>

