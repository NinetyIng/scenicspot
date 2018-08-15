<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
	<link href="/assets/css/bootstrap-fileupload.css" rel="stylesheet">
	<%@ include file="../../common/top.jsp"%>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5><c:if test="${not empty dataModel}">编辑</c:if><c:if test="${empty dataModel}">新增</c:if>管理员</h5>
                    </div>
                    <div class="ibox-content">
                        <form action="/sys/upm/admin/save" method="post" class="form-horizontal m-t" id="commentForm">
                             
							<input type="hidden" name="admin_id" value="${dataModel.admin_id}" />
							<div class="form-group">
    							<label class="col-sm-2 control-label">真实姓名：</label>
    							<div class="col-sm-8">
       							<input type="text" name="real_name" id="real_name" class="form-control" value="${dataModel.real_name}" required/>
    							</div>
							</div>
							<div class="form-group">
    							<label class="col-sm-2 control-label">登录账号：</label>
    							<div class="col-sm-8">
       						<input type="text" name="user_name" id="user_name" class="form-control" value="${dataModel.user_name}"/>
    							</div>
							</div>
							<div class="form-group">
    							<label class="col-sm-2 control-label">手机号码：</label>
    							<div class="col-sm-8">
       								<input type="text" name="phone" id="phone" class="form-control" value="${dataModel.phone}"/>
    							</div>
							</div>
							<div class="form-group">
    							<label class="col-sm-2 control-label">默认密码：</label>
    							<div class="col-sm-8">
       								<input type="password" name="password" id="password" class="form-control" value="${dataModel.password}"/>
    							</div>
							</div>
							<div class="form-group">
    							<label class="col-sm-2 control-label">头像地址：</label>
    							<div class="col-sm-8">
       								<input type="text" name="header_img" id="header_img" class="form-control" value="${dataModel.header_img}"/>
    							</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">头像图片：</label>
								<div class="col-sm-8">
									<div class="fileupload fileupload-new"
										data-provides="fileupload">
										<div class="fileupload-new thumbnail"
											style="max-height: 200px;">
											<img src="${dataModel.header_img}" style="max-height: 120px;">
										</div>
										<div
											class="fileupload-preview fileupload-exists thumbnail"
											style="max-height: 200px; line-height: 20px;"></div>
										<div>
											<span class="btn btn-default btn-file"><span
												class="fileupload-new">选择文件</span><span
												class="fileupload-exists">重选</span>
												<input id="theFile" name="theFile" type="file"> </span> 
												<a href="#" class="btn btn-default fileupload-exists" data-dismiss="fileupload">移除</a>
										</div>
									</div>
								</div>
							</div>
							
                            <div class="form-group">
                                <div class="col-sm-4 col-sm-offset-3">
                                    <button class="btn btn-primary" type="submit"><i class="fa fa-check"></i>&nbsp;&nbsp;提   交&nbsp;&nbsp; </button>
                                    <button class="btn btn-warning" type="button" onclick="history.go(-1)"><i class="fa fa-close"></i>&nbsp;&nbsp;返   回&nbsp;&nbsp; </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
	<!-- basic scripts -->
	<!-- 页面底部js¨ -->
	<%@ include file="../../common/foot.jsp"%>
	<script src="/assets/js/bootstrap-fileupload.js"></script>
</body>
</html>