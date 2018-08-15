<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
	    <link href="/assets/css/bootstrap.min.css" rel="stylesheet">
	    <link href="/assets/css/font-awesome.min.css" rel="stylesheet">
	    <link href="/assets/css/animate.min.css" rel="stylesheet">
	    <link href="/assets/css/style.min.css" rel="stylesheet">
    <!-- Gritter -->
        <link href="/assets/js/plugins/gritter/jquery.gritter.css" rel="stylesheet">
        <link href="/assets/css/animate.min.css" rel="stylesheet">
        <link href="/assets/css/style.min.css" rel="stylesheet">
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content">
      <div class="ibox float-e-margins">
       <div class="ibox-title">
           <div class="alert alert-block alert-success">
								<button type="button" class="close" data-dismiss="alert">
									<i class="ace-icon fa fa-times"></i>
								</button>
								<i class="ace-icon fa fa-check green"></i>
								欢迎使用<strong class="green">汉唐影视城</strong>管理系统
							</div>
       </div>
    </div>
   </div>
  </div>
</body>
  <!-- 第三方插件 -->
     <script src="/assets/js/jquery-2.1.1.min.js"></script>
    <script src="/assets/js/bootstrap.min.js"></script>
    <script src="/assets/js/bootstrap.min.js"></script>
    <script src="/assets/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="/assets/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="/assets/js/plugins/layer/layer.min.js"></script>
    <script src="/assets/js/hplus.min.js?v=3.2.0"></script>
    <script src="/assets/js/contabs.min.js"></script>
    <script src="/assets/js/plugins/pace/pace.min.js"></script>
    <!-- Flot -->
    <script src="/assets/js/plugins/flot/jquery.flot.js"></script>
    <script src="/assets/js/plugins/flot/jquery.flot.tooltip.min.js"></script>
    <script src="/assets/js/plugins/flot/jquery.flot.spline.js"></script>
    <script src="/assets/js/plugins/flot/jquery.flot.resize.js"></script>
    <script src="/assets/js/plugins/flot/jquery.flot.pie.js"></script>
    <script src="/assets/js/plugins/flot/jquery.flot.symbol.js"></script>

    <!-- Peity -->
    <script src="/assets/js/plugins/peity/jquery.peity.min.js"></script>
    <!-- jQuery UI -->
    <script src="/assets/js/plugins/jquery-ui/jquery-ui.min.js"></script>

    <!-- Jvectormap -->
    <script src="/assets/js/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
    <script src="/assets/js/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
    <!-- EayPIE -->
    <script src="/assets/js/plugins/easypiechart/jquery.easypiechart.js"></script>
    <!-- Sparkline -->
    <script src="/assets/js/plugins/sparkline/jquery.sparkline.min.js"></script>
    <script>
    	$(document).ready(function() {
    		var now = new Date();
    		var day = now.getDay()==0?7:now.getDay();
    		var td = $("#simple-table tr td");
    		for(var i=0;i<td.length;i++) {
   				if(td.eq(i).index() == day) {
       				td.eq(i).css("color","blue");
       			}
    		}
    	});
    
    	function queryPosition(lng, lat) {
    		layer.open({
				 type: 2,
				 title: '警告位置',
				 shadeClose: true,
				 shade: 0.5,
				 area: ['600px', '540px'],
				 content: '/student/getPosition?lng='+lng+"&lat="+lat
			});
    	}
    </script>
</html>