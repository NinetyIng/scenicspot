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
							<c:if test="${not empty seo}">编辑</c:if>
							<c:if test="${empty seo}">新增</c:if>
							友情链接
						</h5>
					</div>
					<form action="" method="post" class="form-horizontal"
						id="appseoForm" name="appseoForm" enctype="multipart/form-data">
						<div class="ibox-content">
							<div class="panel blank-panel">
								<div class="tabs-left">
									<div class="tab-content ">
										<div id="tab-1" class="tab-pane active">
											<div class="panel-body">
												<input type="hidden" name="id" id="id" value="${seo.id}" />
												<div class="form-group">
													<label class="col-sm-2 control-label"><font
														color="red" size="3px"
														style="font-weight: bold; font-style: italic;">*&nbsp;</font>SEO标题：</label>
													<div class="col-sm-8">
														<input type="text" name="seo_title" id="seo_title"
															class="form-control" value="${seo.seo_title}" />
													</div>
												</div>
												<c:if test="${not empty seo && not empty seo.id}">
													<div class="form-group">
														<label class="col-sm-2 control-label"><font
															color="red" size="3px"
															style="font-weight: bold; font-style: italic;">*&nbsp;</font>SEO-CODE值：</label>
														<div class="col-sm-3">
															<input type="text" name="seo_code" id=seo_code
																class="form-control" value="${seo.seo_code}" />
														</div>
														<label class="col-sm-3 control-label" style="color: red; text-align: left;">【SEO-CODE值谨慎修改】</label>
													</div>
												</c:if>
												<div class="form-group">
													<label class="col-sm-2 control-label"><font
														color="red" size="3px"
														style="font-weight: bold; font-style: italic;">*&nbsp;</font>SEO-搜索值：</label>
													<div class="col-sm-3">
														<textarea placeholder="" name="seo_value" cols="20" rows="5"
															id="seo_value" class="form-control">${seo.seo_value}</textarea>
													</div>
													<label class="col-sm-3 control-label" style="color: red; text-align: left;" >【SEO-搜索值多个以中文逗号（，）隔开】</label>
												</div>
											    <div class="form-group">
													<label class="col-sm-2 control-label"><font
														color="red" size="3px"
														style="font-weight: bold; font-style: italic;">*&nbsp;</font>网页title：</label>
													<div class="col-sm-3">
														<textarea placeholder="" name="seo_web_title" cols="20" rows="5"
															id="seo_web_title" class="form-control">${seo.seo_web_title}</textarea>
													</div>
													<label class="col-sm-3 control-label" style="color: red; text-align: left;" ></label>
												</div>
												
												<div class="form-group">
													<label class="col-sm-2 control-label"><font
														color="red" size="3px"
														style="font-weight: bold; font-style: italic;">*&nbsp;</font>网页描述：</label>
													<div class="col-sm-3">
														<textarea placeholder="" name="seo_description" cols="20" rows="5"
															id="seo_description" class="form-control">${seo.seo_description}</textarea>
													</div>
													<label class="col-sm-3 control-label" style="color: red; text-align: left;" ></label>
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
	<script type="text/javascript" charset="utf-8">window.UEDITOR_HOME_URL = "<%=path%>
		/statics/js/uedit/";
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
			if ($("#seo_title").val().isEmpty()) {
				bootbox.alert("SEO标题必填");
				return;
			}
			if ($("#seo_value").val().isEmpty()) {
				bootbox.alert("SEO搜索值必填");
				return;
			}
			var data = $("#appseoForm").serialize();

			if (id == null || id == '') {//保存
				Ajax.request("/seo/insertseo", {
					"data" : data,
					"success" : function(data) {
						if (data.result == 200) {
							window.location.href = '/seo/getseolist.do';
						}else {
							bootbox.alert("保存失败！");
						}
					}
				});
			} else {
				if ($("#seo_code").val().isEmpty()) {
					bootbox.alert("SEO-CODE值必填");
					return;
				}
				Ajax.request("/seo/updateseo", {
					"data" : data,
					"success" : function(data) {
						if (data.result == 200) {
							window.location.href = '/seo/getseolist.do';
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