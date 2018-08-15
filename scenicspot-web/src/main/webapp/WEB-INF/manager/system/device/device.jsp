<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">
<!-- jsp文件头和头部 -->
<%@ include file="../../common/top.jsp"%>
    <title>设备管理</title>
</head>

<body class="gray-bg">

    <div class="wrapper wrapper-content animated fadeInUp">
        <div class="row">
            <div class="col-sm-12">
<form action="/device/index" method="post">
                <div class="ibox">
                    <div class="ibox-title">
                        <h5>所有设备</h5>
                    </div>
                    <div class="ibox-content">
                     <div class="row m-b-sm m-t-sm">
                            <div class="col-md-1">
                                <button type="button" id="loading-example-btn" class="btn btn-white btn-sm"><i class="fa fa-refresh"></i> 刷新</button>
                            </div>
                            <div class="col-md-11">
                                <div class="input-group">
                                    <input type="text" placeholder="请输入设备号或绑定学生姓名" value="${pd.keywords }" name="keywords" class="input-sm form-control"> <span class="input-group-btn">
                                        <button  class="btn btn-sm btn-primary"> 搜索</button> </span>
                                </div>
                            </div>
                        </div>
                        <div class="project-list">
                            <table class="table table-hover">
                                   <tr>
											<th>设备号</th>
											<th>设备绑定手机号码</th>
											<th>状态</th>
											<th>拥有者</th>
											<th>运营商类型</th>
											<th class="center">操作</th>
										</tr>
                                <tbody>
                                <c:forEach items="${dataModel}" var="item">
	                                    <tr>
	                                       <td class="project-title">
	                                            <a href="project_detail.html">${item.device_no}</a>
	                                        </td>
	                                        <td class="project-title">
	                                            <a href="project_detail.html">${item.device_phone}</a>
	                                        </td>
	                                        <td class="project-status">
	                                           <c:if test="${item.status eq 1}">
	                                               <span class="label label-primary">活动中
	                                            </c:if>
	                                            <c:if test="${item.status ne 1}">
                                                    <span class="label label-default">未注册
	                                            </c:if>
	                                        </td>
	                                        <td class="project-title">
	                                           ${item.stu_name}
	                                        </td>
	                                        <td class="project-completion">
	                                        
	                                           <c:choose>
	                                             <c:when test="${item.device_yxs eq 1 }">
	                                                                                                                  移动
	                                             </c:when>
	                                             <c:when test="${item.device_yxs eq 2 }">
	                                                                                                                  联通
	                                             </c:when>
	                                             <c:otherwise>未知</c:otherwise>
	                                           </c:choose>
	                                        </td>
	                                        <td class="center project-actions">
	                                            <a href="javascript:;" class="btn btn-white btn-sm opera"  device_no="${item.device_no}"  tag="IP"><i class="fa fa-folder"></i> 设置ip端口 </a>
	                                            <a href="javascript:;" class="btn btn-white btn-sm opera"  device_no="${item.device_no}" tag="FACTORY"><i class="fa fa-cogs"></i> 恢复出厂设置 </a>
	                                            <a href="javascript:;" class="btn btn-white btn-sm opera"  device_no="${item.device_no}" tag="RESET"><i class="fa fa-train"></i> 重新启动 </a>
	                                            <a href="javascript:;" class="btn btn-white btn-sm opera"  device_no="${item.device_no}" tag="POWEROFF"><i class="fa fa-minus-square"></i> 关机 </a>
	                                            <a href="javascript:;" class="btn btn-white btn-sm opera"  device_no="${item.device_no}" tag="FIND"><i class="fa fa-eye"></i> 查找手环 </a>
	                                            <a href="javascript:;" class="btn btn-white btn-sm opera"  device_no="${item.device_no}" tag="MORE"><i class="fa fa-cog"></i> 更多操作 </a>
	                                        </td>
	                                    </tr>
                                    </c:forEach>
                                    </tbody>
                                    	<tfoot>
	                                        <tr>
	                                            <td colspan="8" style="vertical-align:top;">
														<div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div>
	                                            </td>
	                                        </tr>
	                                    </tfoot>
                                </table>
                            </div>
                        </div>
                    </div>
</form>                    
                </div>
            </div>
        </div>
   	<!-- /.main-container -->
	<!-- basic scripts -->
	<!-- 页面底部js¨ -->
	<%@ include file="../../common/foot.jsp"%>
	</body>
	<script>
	    $(function(){
	        //操作命令的点击事件 
	    	$(".opera").on("click",function(){
	    		
	    		var tag = $(this).attr("tag");
	    		var device_no = $(this).attr("device_no");
		    	var title = "发送命令";
		    	if(tag == "MORE"){
		    		layer.open({
						 type: 2,
						  title: title,
						  shadeClose: true,
						  shade: 0.5,
						  area: ['510px', '510px'],
						  content: "device/commandList?device_no="+device_no
						});
		    	}else{
		    		layer.open({
						 type: 2,
						  title: title,
						  shadeClose: true,
						  shade: 0.5,
						  area: ['510px', '510px'],
						  content: '/device/commandPage?device_no='+device_no+'&command='+tag
						});
		    	}
	    		
	    	});
	    })
	</script>
</html>