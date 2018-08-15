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
	<link href="/assets/css/plugins/summernote/summernote.css" rel="stylesheet">
	<link href="/assets/css/plugins/summernote/summernote-bs3.css" rel="stylesheet">
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
	</style>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5><c:if test="${not empty dataModel}">编辑</c:if><c:if test="${empty dataModel}">新增</c:if></h5>
                    </div>
                    <div class="ibox-content">
                        <form action="/sys/upm/template/templateSave" method="post" class="form-horizontal m-t" id="commentForm" enctype="multipart/form-data">
                             
				            <input type="hidden" name="template_id" id="template_id" value="${dataModel.template_id}" />
							<div class="form-group">
    							<label class="col-sm-2 control-label">所属页面：</label>
    							<div class="col-sm-8">
                                     <select name="page_code"  class="form-control" >
                                              <option value="index" <c:if test="${dataModel.page_code == 'index'}">selected="selected"</c:if>>index</option>
                                              <option value="list" <c:if test="${dataModel.page_code == 'list'}">selected="selected"</c:if>>list</option>
                                              <option value="detail" <c:if test="${dataModel.page_code == 'detail'}">selected="selected"</c:if>>detail</option>
                                     </select>
    							</div>
							</div>
							<div class="form-group">
    							<label class="col-sm-2 control-label">序号：</label>
    							<div class="col-sm-8">
                                     <select name="sort"  class="form-control" >
                                              <option value="1" <c:if test="${dataModel.sort == 1}">selected="selected"</c:if>>1</option>
                                              <option value="2" <c:if test="${dataModel.sort == 2}">selected="selected"</c:if>>2</option>
                                              <option value="3" <c:if test="${dataModel.sort == 3}">selected="selected"</c:if>>3</option>
                                              <option value="4" <c:if test="${dataModel.sort == 4}">selected="selected"</c:if>>4</option>
                                              <option value="5" <c:if test="${dataModel.sort == 5}">selected="selected"</c:if>>5</option>
                                     </select>
    							</div>
							</div>
							<div class="form-group">
    							<label class="col-sm-2 control-label">模块标题：</label>
    							<div class="col-sm-8">
                                     <input type="text" name="template_title" id="template_title" class="form-control" value="${dataModel.template_title}"/>
    							</div>
							</div>
							<div class="form-group">
    							<label class="col-sm-2 control-label">模块类型：</label>
    							<div class="col-sm-8">
                                     <select name="template_type"  class="form-control" >
                                              <option value="00100" <c:if test="${dataModel.template_type eq '00100'}">selected="selected"</c:if>>轮播图</option>
                                              <option value="00101" <c:if test="${dataModel.template_type eq '00101'}">selected="selected"</c:if>>导航栏</option>
                                              <option value="00102" <c:if test="${dataModel.template_type eq '00102'}">selected="selected"</c:if>>商品分类</option>
                                              <option value="00103" <c:if test="${dataModel.template_type eq '00103'}">selected="selected"</c:if>>热销商品</option>
                                     </select>
    							</div>
							</div>
							<div class="form-group">
    							<label class="col-sm-2 control-label">标题图：</label>
    							<div class="col-sm-8">
       						         <div class="fileupload fileupload-new" data-provides="fileupload">
											<div class="fileupload-new thumbnail" style="max-height: 200px;">
												<img src="${SETTINGPD.IMAGEPATH}${dataModel.template_title_img}" height="100" width="200">
												<%-- <%=basePath%>UploadServlet?getthumb=${dataModel.list_img} --%>
											</div>
											<div class="fileupload-preview fileupload-exists thumbnail"
												style="max-height: 200px; line-height: 20px;"></div>
											<div>
												<span class="btn btn-default btn-file"> 
													<span class="fileupload-new">选择文件</span> 
													<span class="fileupload-exists">重选</span> 
													 <input type="file" name="files" id="modular_title_img"/>
												</span> 
												<a href="#" class="btn btn-default fileupload-exists" data-dismiss="fileupload">移除</a>
											</div>
										</div>
    							</div>
							</div>
							<div class="form-group">
    							<label class="col-sm-2 control-label">背景图：</label>
    							<div class="col-sm-8">
       						         <div class="fileupload fileupload-new" data-provides="fileupload">
											<div class="fileupload-new thumbnail" style="max-height: 200px;">
												<img src="${SETTINGPD.IMAGEPATH}${dataModel.template_background_img}" height="100" width="200">
												<%-- <%=basePath%>UploadServlet?getthumb=${dataModel.list_img} --%>
											</div>
											<div class="fileupload-preview fileupload-exists thumbnail"
												style="max-height: 200px; line-height: 20px;"></div>
											<div>
												<span class="btn btn-default btn-file"> 
													<span class="fileupload-new">选择文件</span> 
													<span class="fileupload-exists">重选</span> 
													 <input type="file" name="files" id="modular_background_img"/>
												</span> 
												<a href="#" class="btn btn-default fileupload-exists" data-dismiss="fileupload">移除</a>
											</div>
										</div>
    							</div>
							</div>
							
							<div class="form-group">
    							<label class="col-sm-2 control-label">是否显示标题：</label>
    							<div class="col-sm-8">
                                     <label class="radio-inline"><input type="radio" name="is_show_title" value="1"
															<c:if test="${dataModel.is_show_title == 1}">checked="checked"</c:if> />显示</label>
									 <label class="radio-inline"><input type="radio" name="is_show_title" value="0"
															<c:if test="${dataModel.is_show_title == 0}">checked="checked"</c:if> />不显示</label>
    							</div>
							</div>
							<div class="form-group">
    							<label class="col-sm-2 control-label">是否有间距：</label>
    							<div class="col-sm-8">
                                     <label class="radio-inline"><input type="radio" name="is_margin" value="1"
															<c:if test="${dataModel.is_margin == 1}">checked="checked"</c:if> />普通</label>
									 <label class="radio-inline"><input type="radio" name="is_margin" value="0"
															<c:if test="${dataModel.is_margin == 0}">checked="checked"</c:if> />无间距</label>
    							</div>
							</div>
                            <div class="form-group">
                                <div class="col-sm-4 col-sm-offset-3">
                                    <button class="btn btn-primary" type="submit"><i class="fa fa-check"></i>&nbsp;&nbsp;提   交&nbsp;&nbsp; </button>
                                    <button class="btn btn-warning" onclick="history.go(-1)"><i class="fa fa-close"></i>&nbsp;&nbsp;返   回&nbsp;&nbsp; </button>
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
<!-- 自定义js -->
	<script src="/assets/js/plugins/summernote/summernote.min.js"></script>
    <script src="/assets/js/plugins/summernote/summernote-zh-CN.js"></script>
</body>
</html>