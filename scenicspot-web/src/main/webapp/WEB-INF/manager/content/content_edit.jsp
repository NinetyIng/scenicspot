<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh">
	<head>
	<base href="<%=basePath%>">
	<!-- 下拉框 -->
	<link href="/assets/css/bootstrap-fileupload.css" rel="stylesheet">
	<!-- jsp文件头和头部 -->
	<%@ include file="../common/top.jsp"%>
	<link rel="stylesheet" href="/static/ace/css/font-awesome.css" />
	<link rel="stylesheet" href="/assets/ace/css/ace-fonts.css" />
	<link rel="stylesheet" href="/assets/ace/css/ace.css" class="ace-main-stylesheet" id="main-ace-style" />
	<link rel="stylesheet" href="/static/ace/css/ace-part2.css" class="ace-main-stylesheet" />
	<link rel="stylesheet" href="/static/ace/css/ace-ie.css" />
	<script src="/assets/ace/js/ace-extra.js"></script>
	<link href="/assets/css/plugins/jedate1/skin/jedate.css"	rel="stylesheet" />
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
</head>
<body class="no-skin">
<!-- /section:basics/navbar.layout -->
<div class="main-container" id="main-container">
	<!-- /section:basics/sidebar -->
	<div class="main-content">
		<div class="main-content-inner">
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
					<form action="/sys/content/${msg}" name="Form" id="Form" method="post" enctype="multipart/form-data">
						<input type="hidden" name="CONTENT_ID" id="CONTENT_ID" value="${pd.CONTENT_ID}"/>
						<input type="hidden" name="STATUS" id="STATUS" value="${pd.STATUS}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:85px;text-align: right;padding-top: 13px;"><font color="red" size="3px" style="font-weight:bold;font-style:italic;">*</font> 栏目:</td>
								<td>
								 	<select name="CATEGORY_CODE" id="CATEGORY_ID" class="form-control" data-placeholder="文章栏目" style="vertical-align:top;width: 150px;">
									 	<option value="">请选择文章栏目</option>
								 		<c:forEach items="${categoryList}" var="item">
											<option value="${item.CATEGORY_CODE}" <c:if test="${pd.CATEGORY_CODE == item.CATEGORY_CODE }">selected</c:if>>${item.CATEGORY_NAME}</option>
									    </c:forEach>
								  	</select>
								</td>
								<td style="width:85px;text-align: right;padding-top: 13px;">专题:</td>
								<td>
									<select name="SUBJECT_ID" id="SUBJECT_ID" class="form-control" data-placeholder="专题报道" style="vertical-align:top;width: 98%;">
										<option value="">选择资讯专题</option>
										<c:forEach items="${subjectList}" var="item">
											<option value="${item.SUBJECT_ID}" <c:if test="${pd.SUBJECT_ID eq item.SUBJECT_ID }">selected</c:if>>${item.NAME}</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<td style="width:85px;text-align: right;padding-top: 13px;"><font color="red" size="3px" style="font-weight:bold;font-style:italic;">*</font> 标题:</td>
								<td><input type="text" class="form-control" name="TITLE" id="TITLE" value="${pd.TITLE}" maxlength="48" placeholder="这里输入标题" onkeyup="ValidateValue(this)" title="标题" style="width:98%;"/></td>
								<td style="width:85px;text-align: right;padding-top: 13px;">外部链接:</td>
								<td><input type="text" class="form-control" name="LINK" id="LINK" value="${pd.LINK}" maxlength="255" onkeyup="if(!isURL(this.value)) {layer.tips('无效链接', '#LINK');}else{layer.tips('链接可用', '#LINK',{tips: [2, '#78BA32']})}" placeholder="这里输入外部访问链接([https|http|ftp|rtsp|mms]://)" title="外部访问链接" style="width:98%;"/></td>
							</tr>
							 <tr>
								<td style="width:85px;text-align: right;padding-top: 13px;"> 标题简称:</td>
								<td><input type="text" class="form-control" name="SORT_TITLE" id="SORT_TITLE" value="${pd.SORT_TITLE}" maxlength="48" onkeyup="ValidateValue(this)" placeholder="这里输入标题简称" title="标题简称" style="width:98%;"/></td>
								<td style="width:85px;text-align: right;padding-top: 13px;">作者:</td>
								<td><input type="text" name="AUTHOR" id="AUTHOR" class="form-control" value="${pd.AUTHOR}" maxlength="24" onkeyup="ValidateValue(this)" placeholder="这里输入作者" title="作者" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:85px;text-align: right;padding-top: 13px;"> 摘要:</td>
								<td><input type="text" class="form-control" name="ABSTRACT" id="ABSTRACT" value="${pd.ABSTRACT}" maxlength="500" placeholder="这里输入摘要" title="摘要" style="width:98%;"/></td>
								<td style="width:85px;text-align: right;padding-top: 13px;"> Tag标签:</td>
								<td>
									<input type="text" class="form-control" name="TAGS" id="TAGS" value="${pd.TAGS}" maxlength="24" onkeyup="ValidateValue2(this)" placeholder="以|分隔填入" title="Tag标签" style="width:98%;"/>
								</td>
							</tr>
							<tr>
								<td style="width:85px;text-align: right;padding-top: 13px;"> 来源:</td>
								<td><input type="text" class="form-control" name="ORGIN" id="ORGIN" value="${pd.ORGIN}" maxlength="24" onkeyup="ValidateValue(this)" onblur="appendX('ORGIN','ORGINURLX')" placeholder="这里输入来源" title="来源" style="width:98%;"/></td>
								<td>头条/焦点</td>
								<td  class="center">
									<label>头条:&nbsp;<input type='checkbox'  id="ISHOT" name="ISHOT"  <c:if test="${pd.ISHOT}">checked="checked"</c:if> /><span class="lbl"></span></label>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<label>焦点:&nbsp;<input type='checkbox'  id="ISFOCUS" name="ISFOCUS"  <c:if test="${pd.ISFOCUS}">checked="checked"</c:if> /><span class="lbl"></span></label>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<label style="display: none;">推荐:&nbsp;<input type='checkbox' class="form-control" id="RECOMMEND" name="RECOMMEND" value="${pd.RECOMMEND}" <c:if test="${pd.RECOMMEND}">checked="checked"</c:if> /><span class="lbl"></span></label>
								</td>
							</tr>
							
							<tr>
								<td style="width:85px;text-align: right;padding-top: 13px;">广告:</td>
									<td  class="center">
									<label>是&nbsp;<input type="radio"   name="CTYPE" value="1"  <c:if test="${pd.CTYPE == 1}">checked="checked"</c:if> /><span class="lbl"></span></label>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<label>否&nbsp;<input type='radio'   name="CTYPE" value="0" <c:if test="${empty pd.CTYPE || pd.CTYPE == 0}">checked="checked"</c:if> /><span class="lbl"></span></label>
								</td>
								<td style="width:85px;text-align: right;padding-top: 13px;">广告URL:</td>
								<td><input type="text" class="form-control" name="ORGINURL" id="ORGINURL" value="${pd.ORGINURL}" maxlength="255" placeholder="这里输入广告链接([https|http]://)" onkeyup="if(!isURL(this.value)) {layer.tips('无效链接', '#ORGINURL');}else{layer.tips('链接可用', '#ORGINURL',{tips: [2, '#78BA32']})}" title="URL" style="width:98%;"/><span id="ORGINURLX"></span></td>
							</tr>
							<tr>
								<td style="width:85px;text-align: right;padding-top: 13px;">固顶级别:</td>
								<td><input type="text" name="TOPLV" id="TOPLV" value="${pd.TOPLV}" maxlength="32" onkeyup="var reg=/^\d{1,8}$/; if(!reg.test(this.value)) {layer.tips('只能输入1到8位数字', '#TOPLV'); this.value='';}"   placeholder="这里输入固顶级别" title="固顶级别" style="width:98%;"/></td>
								<td style="width:85px;text-align: right;padding-top: 13px;">固顶截止日期:</td>
								<td>
									<input type="text" class="form-control date"  style="width:80%; cursor: pointer; background-color: #fff;"  name="TOPLVDATE" id="TOPLVDATE"  readonly="readonly" value='<fmt:formatDate value="${pd.PUTTIME}" pattern="yyyy-MM-dd hh:mm:ss"/>'>
								</td>
							</tr>
						 	 <tr>
								<td style="width:85px;text-align: right;padding-top: 13px;">內容类型:</td>
									<td  class="center">
									<label>单图模式:&nbsp;<input type="radio"   name="MODEL_TYPE" value="0"  <c:if test="${empty pd.MODEL_TYPE || pd.MODEL_TYPE == 0}">checked="checked"</c:if> /><span class="lbl"></span></label>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<label>多图模式:&nbsp;<input type='radio'   name="MODEL_TYPE" value="1" <c:if test="${pd.MODEL_TYPE == 1}">checked="checked"</c:if> /><span class="lbl"></span></label>
								</td>

								 <td class="singlePic" style="width:85px;text-align: right;padding-top: 13px;"><font color="red" size="3px" style="font-weight:bold;font-style:italic;">*</font> 列表图:</td>
								 <td class="singlePic">
									 <span></span>
									 <c:if test="${pd == null || pd.T_IMG == '' || pd.T_IMG == null }">
										 <input type="file" id="TIMG" name="TIMG" onchange="fileType(this,'TIMG')" style="width:98%;"/>
									 </c:if>
									 <c:if test="${pd != null && pd.T_IMG != '' && pd.T_IMG != null }">
										 <a href="${SETTINGPD.IMAGEPATH}${pd.T_IMG}" target="_blank"><img src="${SETTINGPD.IMAGEPATH}${pd.T_IMG}" width="210"/></a>
										 <input type="button" class="btn btn-mini btn-danger" value="删除" onclick="delImg('${pd.CONTENT_ID}','T_IMG');" />
										 <input type="hidden" name="TIMGS" id="TIMGS" value="${pd.T_IMG }"/>
									 </c:if>
								 </td>
								 <td  class="abums" style="width:85px;text-align: right;padding-top: 13px;"><font color="red" size="3px" style="font-weight:bold;font-style:italic;">*</font>相册:</td>
								 <td class="abums">
									 <c:forEach items="${pd.albums}" var="item">
										 <a href="${SETTINGPD.IMAGEPATH}${item.original_img}" target="_blank"><img  src="${SETTINGPD.IMAGEPATH}${item.original_img}" style="width: 210px; height: 140px;"/></a>
									 </c:forEach>
									 <input type="button" class="btn btn-mini" value="选择相册图片" onclick="addResource('${pd.CONTENT_ID}')" />
								 </td>
							</tr>
							<tr>
								<td style="width:85px;text-align: right;padding-top: 13px;">封面图:</td>
								<td >
									<span></span>
									<c:if test="${pd == null || pd.C_IMG == '' || pd.C_IMG == null }">
										<input type="file" id="CIMG" name="CIMG" onchange="fileType(this,'CIMG')" style="width:98%;"/>
									</c:if>
									<c:if test="${pd != null && pd.C_IMG != '' && pd.C_IMG != null}">
										<a href="${SETTINGPD.IMAGEPATH}${pd.C_IMG}" target="_blank"><img src="${SETTINGPD.IMAGEPATH}${pd.C_IMG}" width="210"/></a>
										<input type="button" class="btn btn-mini btn-danger" value="删除" onclick="delImg('${pd.CONTENT_ID}','C_IMG');" />
										<input type="hidden" name="CIMGz" id="CIMGz" value="${pd.C_IMG }"/>
									</c:if>
								</td>
							</tr>
							<tr id="contents">
								<td style="width:85px;text-align: right;padding-top: 13px;"><font color="red" size="3px" style="font-weight:bold;font-style:italic;">*</font> 内容:</td>
								<td colspan="3"><script id="editor" type="text/plain" style="width:100%;height:280px">${pd.CONTENT}</script></td>
							</tr>
							<c:if test="${empty  pd}">	
								<tr>
									<td style="text-align: center;" colspan="10">
											<a class="btn btn-xlg btn-primary"  onclick="submitForm(0)">保存草稿</a>
											<a class="btn btn-xlg btn-danger"  onclick="submitForm(2);">保存并等待发布</a>
											<a class="btn btn-xlg btn-danger" onclick="closed();">取消</a>
									</td>
								</tr>
							</c:if>
							<c:if test="${not empty pd}">	
								<tr>
									<td style="text-align: center;" colspan="10">
										<c:if test="${pd.STATE eq 2}">
											<a class="btn btn-xlg btn-danger"  onclick="submitForm(2);">保存并等待发布</a>
											<a class="btn btn-xlg btn-primary"  onclick="submitForm(1)">发布上线</a>
											<!-- <a class="btn btn-xlg btn-primary" id="save" onclick="publish(1,1)">发布并通知客户端</a> -->
										</c:if>
										<c:if test="${pd.STATE eq 1}">
											<a class="btn btn-xlg btn-danger"  onclick="submitForm(2);">取消发布</a>
											<a class="btn btn-xlg btn-danger"  onclick="submitForm(1);">保存更改</a>
										</c:if>
										<c:if test="${pd.STATE eq 0}">
											<a class="btn btn-xlg btn-primary"  onclick="submitForm(0)">保存草稿</a>
											<a class="btn btn-xlg btn-danger"   onclick="submitForm(2);">保存并等待发布</a>
										</c:if>
										<a class="btn btn-xlg btn-danger" onclick="closed();">取消</a>
									</td>
								</tr>
							</c:if>
						</table>
						</div>
					</form>
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
			</div>
			<!-- /.page-content -->
		</div>
	</div>
	<!-- /.main-content -->
</div>
<!-- /.main-container -->
	<!-- 页面底部js¨ -->
		<%@ include file="../common/foot.jsp"%>
	<!-- 下拉框 -->
	<script type="text/javascript"
		src="/assets/css/plugins/jedate1/jquery.jedate.js"></script>
	<script type="text/javascript" charset="utf-8">window.UEDITOR_HOME_URL = "<%=path%>/statics/js/uedit/";</script>
	<script type="text/javascript" charset="utf-8" src="/statics/js/uedit/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="/statics/js/uedit/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="/statics/js/uedit/lang/zh-cn/zh-cn.js"></script>
    <script src="/assets/ace/js/ace/elements.fileinput.js"></script>
	<script src="/statics/js/jquery-upload.js"></script>
    <!-- ace scripts -->
    <script src="/assets/ace/js/ace/ace.js"></script>
	<!--提示框-->
	<script type="text/javascript">

		$.jeDate("#TOPLVDATE",{
			format:"YYYY-MM-DD hh:mm:ss",
			isTime:true
		});
		//实例化编辑器
		//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
		var ue = UE.getEditor('editor');
		var index = parent.layer.getFrameIndex(window.name);
		function closed(){
			parent.layer.close(index);
		}

		$(function(){

		    $(".abums").hide();
		   $("input[name='MODEL_TYPE']").click(function(){
		       if("0" == $(this).val()){
                   $(".abums").hide();
                   $(".singlePic").show();
			   }else{
                   $(".abums").show();
                   $(".singlePic").hide();
			   }
		   });
		});
		var isSubmitFlag = false;
		//提交表单
		function submitForm(status){
			if(status){
				$("#STATUS").val(status);
			}
			if(checkParamter() && !isSubmitFlag){
				isSubmitFlag = true;
				$("#Form").submit();
			}
		}
		function checkParamter(){
		   if($("#CATEGORY_ID").val().isEmpty()){
			   $("#CATEGORY_ID").tips({
				   side:3,
				   msg:'请选择文章分类',
				   bg:'#AE81FF',
				   time:2
			   });
			   $("#CATEGORY_ID").focus();
			   return false;
		   }
		   if($("#TITLE").val().isEmpty()){
			   $("#TITLE").tips({
				   side:3,
				   msg:'请输入文章标题',
				   bg:'#AE81FF',
				   time:2
			   });
			   $("#TITLE").focus();
			   return false;
		   }
			if($("#TIMGS").val().isEmpty()){
				$("#TIMG").tips({
					side:3,
					msg:'请上传列表图',
					bg:'#AE81FF',
					time:2
				});
				$("#TIMG").focus();
				return false;
			}
			return true;
		}

		function fileType(obj,tname){
			var fileType=obj.value.substr(obj.value.lastIndexOf(".")).toLowerCase();//获得文件后缀名
			if(fileType != '.gif' && fileType != '.png' && fileType != '.jpg' && fileType != '.jpeg'){
				$("#"+tname).tips({
					side:3,
					msg:'请上传图片格式的文件',
					bg:'#AE81FF',
					time:3
				});
				$("#"+tname).val('');
				document.getElementById(tname).files[0] = '请选择图片';
			}
		}
	</script>
</body>
</html>