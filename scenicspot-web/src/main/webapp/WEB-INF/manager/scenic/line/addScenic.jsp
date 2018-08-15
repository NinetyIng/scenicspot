<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<link href="/assets/css/bootstrap-fileupload.css" rel="stylesheet">
<%@ include file="../../common/top.jsp"%>
<link href="/statics/css/phone.css" rel="stylesheet">
</head>
<body class="gray-bg"> 
	<form action="/sys/culture/resource/uploadVedio" method="post"
						class="form-horizontal" id="commentForm"
						enctype="multipart/form-data">
	<div class="wrapper wrapper-content">
			<div class="row" style="background-color: #fff;">
				<div class="col-sm-12">
					<div class="ibox float-e-margins">
						<div class="ibox-content">
						   		<div class="row">
	                            <div class="col-sm-6 b-r">
								  	<div class="form-group">
		    							<label class="col-sm-2 control-label"><font  color="red" size="3px"  style="font-weight: bold; font-style: italic;">*&nbsp;</font>景点类别：</label>
		    							<div class="col-sm-8">
		       						         <div class="input-group">
				                               	<select name="category_id" id="category_id"
													class="form-control">
													<option value="">--请选择景区分类--</option>
													<c:forEach var="category" items="${categorylist}">
														<option data-type='${category.scenic_is_voice}'   value="${category.id}" >${category.scenic_category}</option>
													</c:forEach>
												</select>
				                            </div>
		    							</div>
									</div>	
								  <div class="form-group">
		    							<label class="col-sm-2 control-label"><font  color="red" size="3px"  style="font-weight: bold; font-style: italic;">*&nbsp;</font>选择景点：</label>
		    							<div class="col-sm-8">
		       						         <div class="input-group" id="scenic_change">
				                                <span class="btn btn-default btn-file"> <span class="fileupload-new" onclick="selectScenic()">选择景点</span>
												</span>
				                            </div>
		    							</div>
									</div>	
									<div class="form-group">
		    							<label class="col-sm-2 control-label"><font  color="red" size="3px"  style="font-weight: bold; font-style: italic;">*&nbsp;</font>景点排序（序号越大该景点排序越靠前）：</label>
		    							<div class="col-sm-8">
		       						         <div class="input-group">
				                                <input type="number" class="form-control" name="line_scenic_desc" id="line_scenic_desc" value="${modelData.line_scenic_desc}">
				                            </div>
		    							</div>
									</div>	
									<div class="form-group">
							            <div class="col-sm-4 col-sm-offset-5">
							                <button class="btn btn-primary" type="button"  onclick="sureSelect();"><i class="fa fa-check"></i>&nbsp;&nbsp;提   交&nbsp;&nbsp; </button>
							                <button class="btn btn-warning"  type="button"  onclick="closed();"><i class="fa fa-exchange"></i>&nbsp;&nbsp;返   回&nbsp;&nbsp; </button>
							            </div>
							         </div>	
									 </div>
								</div>	
						</div>
					</div>
				</div>
			</div>
	</div>
	</form>
	<!-- basic scripts -->
	<!-- 页面底部js¨ -->
	<%@ include file="../../common/foot.jsp"%>
	<script src="/assets/js/bootstrap-fileupload.js"></script>
	<script src="/statics/js/jquery-upload.js"></script>
	<script type="text/javascript">
		var index = parent.layer.getFrameIndex(window.name);
		function closed() {
			parent.layer.close(index);
		}
		
		
		var excludeScenicIds = '${pd.excludeScenicIds}';
	  function selectScenic(){
		 var category_id =  $("#category_id").val();
		 if(category_id.isEmpty()){
			bootbox.alert("请选择景点分类") 
			return;
		 }
		 var index = layer.open({
	            type: 2,
	            title: '选择景点',
	            maxmin: true,
	            shadeClose: true, //点击遮罩关闭层
	            area : ['850px' , '600px'],
	            content: "/sys/scenic/selectScenic?excludeScenicIds="+excludeScenicIds+"&line_id=${pd.line_id}&category_id="+category_id,
	        });
	  }
	  var scenicName,scenicLogo,scenicId;
	  function callBack(scenic_name,scenic_logo,scenic_id){
		  scenicName = scenic_name;
		  scenicLogo = scenic_logo;
		  scenicId = scenic_id;
		  $("#scenic_change").empty().append("<p>"+scenic_name+"</p><img style='width:100px;height:100px;' src='${SETTINGPD.IMAGEPATH}"+scenic_logo+"' />");
	  }
	  function sureSelect(){
		  if(!scenicId){
			  bootbox.alert("请选择景点");
			  return;
		  }
		  if($("#line_scenic_desc").val().isEmpty() || !$("#line_scenic_desc").val().isNumber()){
			  bootbox.alert("排序为整数");
			  return;
		  }
		  parent.selectCallBack(scenicId,scenicName,scenicLogo,$("#line_scenic_desc").val());
		  closed();
	  }
	</script>
</body>
</html>