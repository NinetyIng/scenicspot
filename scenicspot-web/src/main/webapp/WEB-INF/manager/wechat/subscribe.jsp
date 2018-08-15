<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<link rel="stylesheet" href="/static/ace/css/chosen.css" />
<link rel="stylesheet" href="/static/css/App.css" />
<!-- jsp文件头和头部 -->
<base href="<%=basePath%>">
<%@ include file="../common/top.jsp"%>
<link rel="stylesheet" href="<%=basePath%>statics/css/wechart.css" />

</head>
<body class="no-skin">

	<!-- /section:basics/navbar.layout -->
	<div class="main-container" id="main-container">
		<!-- /section:basics/sidebar -->
		<div class="main-content">
			<div class="main-content-inner">
				<br>
				<div class="row">
					<div class="col-md-2 col-sm-3">
						<ul id="myTab" class="nav nav-pills nav-stacked">
							<li class="<c:if test="${pd.reply_type==0}">active</c:if>"><a href="#wb-tab" data-toggle="tab"> <i
									class="fa fa-book"></i> &nbsp;&nbsp;文本回复
							</a></li>
							<li class="<c:if test="${pd.reply_type==1}">active</c:if>"><a href="#tw-tab" data-toggle="tab">
									<i class="fa fa-pencil"></i> &nbsp;&nbsp;素材回复
							</a></li>
						</ul>
					</div>
					
					<div class="col-md-10 col-sm-9">
						<div class="tab-content stacked-content">
							<div class="tab-pane fade <c:if test="${pd.reply_type==0}">active</c:if> in" id="wb-tab">
								<h3 class="heading">文本回复</h3>
								<div class="col-xs-12 col-sm-4" style="width: 401px">
									<div class="widget-box widget-color-pink ui-sortable-handle">
										<div class="widget-header">
											<h4 class="widget-title">文本回复</h4>
										</div>
										<div class="widget-body">
											<div class="widget-main">
												<form action="/replyRule/save" method="post"
													class="form-horizontal">
													<div class="form-group">
														<div class="col-md-5" style="width: 100%;">
															<textarea id="reply_content" name="reply_content" class="form-control" rows="10"><c:if test="${pd.reply_type==0}">${pd.reply_content}</c:if></textarea>
														</div>
													</div>

												</form>
											</div>
											<div class="widget-toolbox padding-8 clearfix">
													<button class="btn btn-success" style="width: 100%"
														onclick="saveTextReply()">
														<span class="bigger-110">保存关注回复</span> <i
															class="ace-icon fa fa-check icon-on-right"></i>
													</button>
												</div>
										</div>
									</div>
								</div>
							</div>
							<div class="tab-pane fade <c:if test="${pd.reply_type==1}">active</c:if> in" id="tw-tab">
								<h3 class="heading">图文回复</h3>
								<div class="row">
									<div class="col-xs-12 col-sm-4" style="width: 10px"></div>
									<div class="col-xs-8 col-sm-3"  style="width: 431px">
										<div class="widget-box widget-color-dark ui-sortable-handle">
											<div class="widget-header">
												<h4 class="widget-title">效果预览</h4>

												<span class="widget-toolbar"> <a
													href="javascript:void(0)" data-action="settings"
													title="新增一行" onclick="addRow()"> <i
														class="ace-icon fa fa-plus green"></i>
												</a> &nbsp; <a href="javascript:void(0)" data-action="close"
													onclick="delRow()" title="删除选择行"> <i
														class="ace-icon fa fa-trash pink"></i>
												</a>
												</span>
											</div>
											<div class="widget-body">
												<div class="widget-main">
													<div id="previewDiv" class="appmsg">
														<div id="row0" class="current">
															<div class="cover_appmsg_item">
																<div class="">
																	<div class="img_div">
																		<h4 class="appmsg_title_cover">
																			<a href="javascript:void(0);" onclick="return false;">请输入标题</a>
																		</h4>
																		<div class="first_thumb_img"
																			style="background-image: url('');"></div>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</div>
												<div class="widget-toolbox padding-8 clearfix">
													<button class="btn btn-success" style="width: 100%"
														onclick="saveNewsReply()">
														<span class="bigger-110">保存关注回复</span> <i
															class="ace-icon fa fa-check icon-on-right"></i>
													</button>
												</div>
											</div>
										</div>
									</div>
									<!-- /.span -->
									<!-- 编辑图文 -->
							<div class="col-sm-7">
								<div class="ibox float-e-margins">
									<div class="ibox-title">
										<h5>图文编辑</h5>
									</div>
									<div class="ibox-content">
										<form class="form-horizontal" role="form">
											<div class="form-group">
												<label class="col-sm-11 control-label"
													style="text-align: left;"></label>
											</div>
											<div class="form-group">
												<label class="col-sm-2 control-label">标题</label>

												<div class="col-sm-9">
													<input type="text" id="title_input"
														onkeyup="onInputKeyUp('title_input')" class="form-control">
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-2 control-label">排序</label>
												<div class="col-sm-9">
													<input type="text" id="order_input"
														onkeyup="onInputKeyUp('order_input')" class="form-control">
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-2 control-label">摘要</label>
												<div class="col-sm-9">
													<input type="text" id="desc_input"
														onkeyup="onInputKeyUp('desc_input')" class="form-control">
												</div>
											</div>

											<div class="form-group">
												<label class="col-sm-2 control-label no-padding-right"
													style="padding-top: 0px;">授权</label>
												<div class="col-sm-9" id="author_div"></div>
											</div>

											<div class="form-group">
												<label class="col-sm-2 control-label">链接</label>
												<div class="col-sm-9">
													<input type="text" id="url_input"
														onkeyup="onInputKeyUp('url_input')" class="form-control">
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-2 control-label">图片</label>
												<div class="col-sm-9">
													<input type="text" id="img_input"
														onkeyup="onInputKeyUp('img_input')" class="form-control">
												</div>
											</div>
										</form>

									</div>
								</div>
								<!-- 编辑图文结束 -->
							</div>
									<!-- /.span -->
								</div>
							</div>
						</div>
					</div>
					<!-- /.page-content -->
				</div>
			</div>
		</div>
		
		<c:if test="${pd.reply_type==1}">
			<textarea id="reply_content_area" style="display: none;">
				${pd.reply_content}
			</textarea>
		</c:if>
		<!-- basic scripts -->
		<!-- 页面底部js¨ -->
		<%@ include file="../common/foot.jsp"%>
		<script type="text/javascript">
			var reply_type=${pd.reply_type};
			$(document).ready(function() {
				if(reply_type==1){
					items=$.parseJSON($("#reply_content_area").val());
					currentItem=items[0];
					updatePreview();
					initForm();
				}else{
					tempCount = 0;
					currentItem = getNewDataItem();
					items.push(currentItem);
					$("#previewDiv").html("");
					var html = getFirstRow();
					$("#previewDiv").html(html);
					$("#row0").addClass("current");
					initForm();
					$("[name='author_radio']").on("change", function(e) {
						currentItem.author = $(e.target).val();
					});	
				}
			});

			var items = new Array();
			var currentItem = null;
			var tempCount = 1;
			function changeEditRow(index) {
				currentItem = getItem(index);
				$(".item_row").removeClass("current");
				$("#row" + index).addClass("current");
				initForm();
			}

			function updatePreview() {
				var item = items[0];
				item.index = 0;
				var html = getFirstRow();
				$("#previewDiv").html(html);
				for (var i = 1; i < items.length; i++) {
					item = items[i];
					item.index = i;
					$("#previewDiv").append(getRowHtml(item));
				}
				$("#row" + currentItem.index).addClass("current");
			}

			function addRow() {
				tempCount = tempCount + 1;
				currentItem = getNewDataItem();
				items.push(currentItem);
				$("#previewDiv").append(getRowHtml(currentItem));
				changeEditRow(currentItem.index);
			}

			function delRow() {
				if (items.length == 1) {
					alert("最少要有一條消息!");
					return;
				}
				$("#row" + currentItem.index).remove();
				var itemIndex = currentItem.index;
				for (var i = 0; i < items.length; i++) {
					var item = items[i];
					if (item.index == itemIndex) {
						items.remove(i);
					}
				}
				updatePreview();
				currentItem = items[0];
				changeEditRow(currentItem.index);
			}

			function initForm() {
				$("#title_input").val(currentItem.title);
				$("#img_input").val(currentItem.img);
				$("#order_input").val(currentItem.order);
				$("#url_input").val(currentItem.url);
				$("#desc_input").val(currentItem.desc);
				$("#author_input").val(currentItem.author);
				getRadio();
			}

			function getRadio() {
				var str = '<label>';
				str = str
						+ '<input name="author_radio" id="radio0" type="radio" class="ace" value="0" onclick="onInputKeyUp(\'radio0\')"';
				if (currentItem.author != 1) {
					str = str + ' checked="checked" ';
				}
				str = str + '/> <span class="lbl">不需要授权</span>';
				str = str + '</label> ';
				str = str + '<label>';
				str = str
						+ '<input name="author_radio" id="radio1" type="radio" class="ace" value="1" onclick="onInputKeyUp(\'radio1\')"';
				if (currentItem.author == 1) {
					str = str + ' checked="checked" ';
				}
				str = str + '/> <span class="lbl">需要Author授权</span>';
				str = str + '</label>';
				$("#author_div").html(str);
			}

			function getRowHtml(item) {
				if (item == null) {
					return "";
				}
				var html = '';
				html = '<div id="row' + item.index
						+ '" onclick="changeEditRow(' + item.index
						+ ')" class="item_row">';
				html = html + '<div class="appmsg_item">';
				html = html
						+ '<div class="thumb_img" style="background-image:url('
						+ "'" + item.img + "'" + ');">';
				html = html + '</div>';
				html = html + '<h4 class="appmsg_title js_appmsg_title">'
						+ item.title + '</h4>';
				html = html + '</div>';
				html = html + '</div>';
				return html;
			}

			function getFirstRow() {
				var html = '<div id="row' + items[0].index
						+ '" class="item_row" onclick="changeEditRow('
						+ items[0].index + ')">';
				html = html + '<div class="cover_appmsg_item">';
				html = html + '<div class="">';
				html = html + '<div class="img_div">';
				html = html
						+ '<h4 class="appmsg_title_cover"><a href="javascript:void(0);" onclick="return false;">'
						+ items[0].title + '</a></h4>';
				html = html
						+ '<div class="first_thumb_img" style="background-image:url('
						+ "'" + items[0].img + "'" + ');">';
				html = html + '</div>';
				html = html + '</div>';
				html = html + '</div>';
				html = html + '</div>';
				html = html + '</div>';
				return html;
			}

			function getNewDataItem() {
				var item = {};
				item.title = "请输入标题";
				item.img = "";
				item.desc = "";
				item.order = 1;
				item.url = "";
				item.author = "";
				item.index = tempCount;
				return item;
			}

			function getItem(itemIndex) {
				for (var i = 0; i < items.length; i++) {
					var item = items[i];
					if (item.index == itemIndex) {
						return item;
					}
				}
				return null;
			}
			Array.prototype.remove = function(dx) {
				if (isNaN(dx) || dx > this.length) {
					return false;
				}
				for (var i = 0, n = 0; i < this.length; i++) {
					if (this[i] != this[dx]) {
						this[n++] = this[i]
					}
				}
				this.length -= 1
			};
			//==============================================================================
			//保存到服務器
			//==============================================================================
			function saveNewsReply() {
				var obj = {};
				var jsonText = JSON.stringify(items);
				obj.data = jsonText;
				obj.reply_type=1;
				var data = $.parseJSON(jsonText);
				app.post(obj, "/sys/wechat/saveSubscribe", onAddResult,onAddFault);
			}
			
			function saveTextReply() {
				var obj = {};
				var jsonText = JSON.stringify(items);
				obj.data = $("#reply_content").val();
				obj.reply_type=0;
				var data = $.parseJSON(jsonText);
				app.post(obj, "/sys/wechat/saveSubscribe", onAddResult,onAddFault);
			}
			function onAddResult(data){
				if(data.result==200){
					alert(data.msg);	
				}else{
					alert(data.msg);
				}
				
			}
			function onAddFault(e){
				alert("Fault"+e);
			}
			//==============================================================================
			//事件侦探
			//==============================================================================
			function onInputKeyUp(cName) {
				currentItem.title = $("#title_input").val();
				currentItem.img = $("#img_input").val();
				currentItem.order = $("#order_input").val();
				currentItem.url = $("#url_input").val();
				currentItem.desc = $("#desc_input").val();
				currentItem.author = $("[name='author_radio']").filter(
						":checked").val();
				if (cName == "img_input" || cName == "title_input"
						|| cName == "order_input") {
					updatePreview();
				}
			}
		</script>
</body>
</html>

