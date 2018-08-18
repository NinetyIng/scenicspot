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
.file-pick {
	font-size: 18px;
	background: #00b7ee;
	border-radius: 3px;
	line-height: 44px;
	padding: 0 30px;
	color: #fff;
	display: inline-block;
	margin: 0 auto 20px auto;
	cursor: pointer;
	box-shadow: 0 1px 1px rgba(0, 0, 0, 0.1);
}

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
							<c:if test="${not empty links}">编辑</c:if>
							<c:if test="${empty links}">新增</c:if>
							友情链接
						</h5>
					</div>
					<form action="" method="post"
						class="form-horizontal" id="applinksForm" name="applinksForm"
						enctype="multipart/form-data">
						<div class="ibox-content">
							<div class="panel blank-panel">
								<div class="tabs-left">
									<div class="tab-content ">
										<div id="tab-1" class="tab-pane active">
											<div class="panel-body">
												<input type="hidden" name="id" id="id" value="${links.id}" />
												<div class="form-group">
													<label class="col-sm-2 control-label"><font
														color="red" size="3px"
														style="font-weight: bold; font-style: italic;">*&nbsp;</font>友情链接标题：</label>
													<div class="col-sm-8">
														<input type="text" name="link_title" id="link_title"
															class="form-control" value="${links.link_title}" />
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label"><font
														color="red" size="3px"
														style="font-weight: bold; font-style: italic;">*&nbsp;</font>友情链接地址：</label>
													<div class="col-sm-8">
														<input type="text" name="link_url" id="link_url"
															class="form-control" value="${links.link_url}" />
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label">排序：</label>
													<div class="col-sm-2">
														<input type="text" name="link_sort" id="link_sort"
															class="form-control" value="${links.link_sort}" />
													</div>
													<label class="col-sm-2 control-label">排序值（0-100）</label>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label">状态：</label>
													<div class="col-sm-8">
														<label class="radio-inline"><input type="radio"
															name="link_status" value="1"
															<c:if test="${links.link_status == 1 || empty links.link_status}">checked="checked"</c:if> />显示</label>
														<label class="radio-inline"><input type="radio"
															name="link_status" value="0"
															<c:if test="${links.link_status == 0}">checked="checked"</c:if> />隐藏</label>
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
	<script type="text/javascript" src="/statics/js/user/Category.js"></script>
	<script type="text/javascript" charset="utf-8">window.UEDITOR_HOME_URL = "<%=path%>/statics/js/uedit/";
	</script>
	<script type="text/javascript" charset="utf-8"
		src="/statics/js/uedit/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8"
		src="/statics/js/uedit/ueditor.all.min.js">
		
	</script>
	<script type="text/javascript" charset="utf-8"
		src="/statics/js/uedit/lang/zh-cn/zh-cn.js"></script>
	<script src="/statics/js/jquery-upload.js"></script>
	<script type="text/javascript">
		var ue = UE.getEditor('editor');
		function save() {
			var id = $("#id").val();
			if ($("#link_title").val().isEmpty()) {
				bootbox.alert("友情链接标题必填");
				return;
			}
			if ($("#link_url").val().isEmpty()) {
				bootbox.alert("友情链接地址必填");
				return;
			}
			var data = $("#applinksForm").serialize();
			
			if (id == null || id == '') {//保存
				Ajax.request("/links/insertlinks", {
					"data" : data,
					"success" : function(data) {
						if (data.result == 200) {
							window.location.href='/links/getlinkslist.do';
						} else {
							bootbox.alert("保存失败！");
						}
					}
				});
			} else {
				Ajax.request("/links/updatelinks", {
					"data" : data,
					"success" : function(data) {
						if (data.result == 200) {
							window.location.href='/links/getlinkslist.do';
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