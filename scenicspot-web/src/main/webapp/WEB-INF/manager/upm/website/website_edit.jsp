<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<link href="/assets/css/bootstrap-fileupload.css" rel="stylesheet">
<%@ include file="../../common/top.jsp"%>
<style type="text/css">
.form-control {
	width: 250px;
}
</style>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>
							<c:if test="${not empty webSite}">编辑</c:if>
							<c:if test="${empty webSite}">新增</c:if>
							网站信息
						</h5>
					</div>
					<form action="" method="post" class="form-horizontal"
						id="appwebSiteForm" 
						enctype="multipart/form-data">
						<div class="ibox-content">
							<div class="panel blank-panel">
								<div class="tabs-left">
									<ul class="nav nav-tabs">
										<li class="active"><a data-toggle="tab" href="#tab-1"
											aria-expanded="true">基本信息</a></li>
									</ul>
									<div class="tab-content ">
										<div id="tab-1" class="tab-pane active">
											<div class="panel-body">
												<input type="hidden" name="id" id="id" value="${webSite.id}" />
												<div class="form-group">
													<label class="col-sm-2 control-label"><font
														color="red" size="3px"
														style="font-weight: bold; font-style: italic;">*&nbsp;</font>网站信息标题：</label>
													<div class="col-sm-8">
														<input type="text" name="website_title" id="website_title"
															class="form-control" value="${webSite.website_title}" />
													</div>
												</div>
												<%-- 	<div class="form-group">
													<label class="col-sm-2 control-label">备注1：</label>
													<div class="col-sm-8">
														<input type="text" name="remark_1" id="remark_1"
															class="form-control" value="${webSite.remark_1}" />
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label">备注2：</label>
													<div class="col-sm-8">
														<input type="text" name="remark_2" id="remark_2"
															class="form-control" value="${webSite.remark_2}" />
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label">备注3：</label>
													<div class="col-sm-8">
														<input type="text" name="remark_3" id="remark_3"
															class="form-control" value="${webSite.remark_3}" />
													</div>
												</div> --%>
												<div class="form-group">
													<input type="hidden" name="website_abstract"
														id="website_abstract" value="" /> <label
														class="col-sm-2 control-label"><font color="red"
														size="3px" style="font-weight: bold; font-style: italic;">*&nbsp;</font>网站信息摘要：</label>
													<div class="col-sm-7">
														<script id="editor" type="text/plain"
															style="width: 100%; height: 280px">${webSite.website_abstract}</script>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-4 col-sm-offset-3">
										<button class="btn btn-primary" type="button" onclick="save()">
											<i class="fa fa-check"></i>&nbsp;&nbsp;提 交&nbsp;&nbsp;
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<button class="btn btn-warning" type="button"
											onclick="history.go(-1)">
											<i class="fa fa-close"></i>&nbsp;&nbsp;返 回&nbsp;&nbsp;
										</button>
									</div>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- basic scripts -->
	<!-- 页面底部js¨ -->
	<%@ include file="../../common/foot.jsp"%>
	<script src="/assets/js/bootstrap-fileupload.js"></script>
	<!-- 自定义js -->
	<script type="text/javascript" charset="utf-8">window.UEDITOR_HOME_URL = "<%=path%>/statics/js/uedit/";
	</script>
	<script type="text/javascript" charset="utf-8"
		src="/statics/js/uedit/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8"
		src="/statics/js/uedit/ueditor.all.min.js"></script>
	<script type="text/javascript" charset="utf-8"
		src="/statics/js/uedit/lang/zh-cn/zh-cn.js"></script>
	<script src="/statics/js/jquery-upload.js"></script>
	<script type="text/javascript">
		var ue = UE.getEditor('editor');
		function save() {
			var id = $("#id").val();
			if ($("#website_title").val().isEmpty()) {
				bootbox.alert("网站信息标题必填");
				return;
			}
			var data = $("#appwebSiteForm").serialize();

			if (id == null || id == '') {//保存
				Ajax.request("/website/insertWebSite", {
					"data" : data,
					"success" : function(data) {
						if (data.result == 200) {
							window.location.href = '/website/getWebSitelist';
						} else {
							bootbox.alert("保存失败！");
						}
					}
				});
			} else {
				Ajax.request("/website/updateWebSite", {
					"data" : data,
					"success" : function(data) {
						if (data.result == 200) {
							window.location.href = '/website/getWebSitelist';
						} else {
							bootbox.alert("编辑失败！");
						}
					}
				});
			}
		}
	</script>
</body>
</html>