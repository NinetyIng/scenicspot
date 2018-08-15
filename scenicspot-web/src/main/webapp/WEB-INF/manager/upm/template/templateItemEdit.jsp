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
                        <h5><c:if test="${not empty dataModel}">编辑</c:if><c:if test="${empty dataModel}">新增</c:if>商品品牌</h5>
                    </div>
                    <div class="ibox-content">
                        <form action="/sys/upm/template/templateItemSave" method="post" class="form-horizontal m-t" id="commentForm" enctype="multipart/form-data">
                        
					        <input type="hidden" name="item_id" id="item_id" value="${dataModel.item_id}" />
					        <div class="form-group">
    							<label class="col-sm-2 control-label">所属模块：</label>
    							<div class="col-sm-8">
                                     <select name="template_id"  class="form-control" >
                                         <c:forEach items="${templateList}" var="template">
                                              <option value="${template.template_id}" <c:if test="${dataModel.template_id eq template.template_id}"> selected ='selected'</c:if>>${template.template_title}</option>
                                         </c:forEach>
                                     </select>
    							</div>
							</div>
							<div class="form-group">
    							<label class="col-sm-2 control-label">按钮名称：</label>
    							<div class="col-sm-8">
                                     <input type="text" name="item_name" id="item_name" class="form-control" value="${dataModel.item_name}"/>
    							</div>
							</div>
							<div class="form-group">
    							<label class="col-sm-2 control-label">按钮icon：</label>
    							<div class="col-sm-8">
       						         <div class="fileupload fileupload-new" data-provides="fileupload">
											<div class="fileupload-new thumbnail" style="max-height: 200px;">
												<img src="${SETTINGPD.IMAGEPATH}${dataModel.item_icon}" height="100" width="200">
												<%-- <%=basePath%>UploadServlet?getthumb=${dataModel.list_img} --%>
											</div>
											<div class="fileupload-preview fileupload-exists thumbnail"
												style="max-height: 200px; line-height: 20px;"></div>
											<div>
												<span class="btn btn-default btn-file"> 
													<span class="fileupload-new">选择文件</span> 
													<span class="fileupload-exists">重选</span> 
													 <input type="file" name="files"/>
												</span> 
												<a href="#" class="btn btn-default fileupload-exists" data-dismiss="fileupload">移除</a>
											</div>
										</div>
    							</div>
							</div>
							<div class="form-group">
    							<label class="col-sm-2 control-label">图片：</label>
    							<div class="col-sm-8">
       						         <div class="fileupload fileupload-new" data-provides="fileupload">
											<div class="fileupload-new thumbnail" style="max-height: 200px;">
												<img src="${SETTINGPD.IMAGEPATH}${dataModel.item_img}" height="100" width="200">
												<%-- <%=basePath%>UploadServlet?getthumb=${dataModel.list_img} --%>
											</div>
											<div class="fileupload-preview fileupload-exists thumbnail"
												style="max-height: 200px; line-height: 20px;"></div>
											<div>
												<span class="btn btn-default btn-file"> 
													<span class="fileupload-new">选择文件</span> 
													<span class="fileupload-exists">重选</span> 
													 <input type="file" name="files"/>
												</span> 
												<a href="#" class="btn btn-default fileupload-exists" data-dismiss="fileupload">移除</a>
											</div>
										</div>
    							</div>
							</div>
							<div class="form-group">
    							<label class="col-sm-2 control-label">展示类型：</label>
    							<div class="col-sm-8">
                                     <select name="item_view_code"  class="form-control" >
                                              <option value="00200" <c:if test="${dataModel.item_view_code eq '00200'}"> selected ='selected'</c:if>>商品分类</option>
                                              <option value="00201" <c:if test="${dataModel.item_view_code eq '00201'}"> selected ='selected'</c:if>>体验馆</option>
                                              <option value="00202" <c:if test="${dataModel.item_view_code eq '00202'}"> selected ='selected'</c:if>>拍卖</option>
                                              <option value="00203" <c:if test="${dataModel.item_view_code eq '00203'}"> selected ='selected'</c:if>>高端定制</option>
                                              <option value="00204" <c:if test="${dataModel.item_view_code eq '00204'}"> selected ='selected'</c:if>>商品主题</option>
                                              <option value="00204" <c:if test="${dataModel.item_view_code eq '00204'}"> selected ='selected'</c:if>>发现</option>
                                     </select>
    							</div>
							</div>
							<div class="form-group">
    							<label class="col-sm-2 control-label">请求参数：</label>
    							<div class="col-sm-8">
                                     <input type="text" name="item_parameters" id="item_parameters" class="form-control" value="${dataModel.item_parameters}"/>
    							</div>
							</div>
							<div class="form-group">
    							<label class="col-sm-2 control-label">打开方式：</label>
    							<div class="col-sm-8">
                                      <select name="item_open_type" id="item_open_type"  class="form-control" onchange="type_attr()">
                                              <option value="0">请选择商品类型</option>
                                              <option value="url" <c:if test="${dataModel.item_open_type eq 'url'}"> selected ='selected'</c:if>>url</option>
                                              <option value="view" <c:if test="${dataModel.item_open_type eq 'view'}"> selected ='selected'</c:if>>view</option>
                                     </select>
    							</div>
							</div>
							<div class="form-group" id="openUrl" style="display: none">
    							<label class="col-sm-2 control-label">打开链接：</label>
    							<div class="col-sm-8">
       						          <input type="text" name="item_open_url" id="item_open_url" class="form-control" value="${dataModel.item_open_url}" />
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
    <script type="text/javascript">
    //下拉框
    function type_attr(){
		   var options=$("#item_open_type option:selected"); 
		   var type = options.val();
		   //根据类型id获得所有的属性,异步请求,返回界面
		   if(type == "url"){
			   $("#openUrl").css('display','block');
		   }else if(type == "view"){
			   $("#openUrl").css('display','none');
		   }
		   
	   }
    </script>
</body>
</html>