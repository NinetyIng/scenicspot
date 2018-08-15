<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ taglib prefix="util" uri="/WEB-INF/tld/util-tags.tld"%> --%>
<%-- <%@ taglib prefix="page" uri="/WEB-INF/tld/page-tags.tld"%> --%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<%@ include file="../common/top.jsp"%>
<link rel="stylesheet" href="<%=basePath%>statics/css/wechart.css" />
</head>
<body>
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>
							<c:if test="${isCreate==0}">编辑</c:if>
							<c:if test="${isCreate==1}">新增</c:if>
							微信菜单
						</h5>
					</div>
					<div class="ibox-content">
						<div class="row">
							<!-- 表单 -->
								<div class="col-sm-4">
									<div class="ibox float-e-margins">
										<div class="ibox-title">
											<h5>
												基本信息
											</h5>
										</div>
										<div class="ibox-content">
											<form class="form-horizontal" data-toggle="validator"  role="form">
												<div class="form-group">
													<label class="col-sm-2 control-label">上级菜单</label>
													<div class="col-sm-8">
														<select id="parent_id" class="form-control">
															<option value="0" <c:if test="${dataModel.parent_id==0 }">selected="selected"</c:if>>一级菜单</option>
															<c:forEach items="${menus}" var="menu">
																<option value="${menu.id}" <c:if test="${dataModel.parent_id==menu.id }">selected="selected"</c:if> >${menu.menu_name}</option>
															</c:forEach>
														</select>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label">菜单名称</label>
													<div class="col-sm-8">
														<input type="text" id="menu_name" name="menu_name" data-error="请输入菜单名称！" value="${dataModel.menu_name}" class="form-control" required>
													</div>
													<div class="help-block with-errors"></div>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label">排序序号</label>
													<div class="col-sm-8">
														<input type="number" max="99" min="0" id="order_no" name="order_no" data-error="请输入0~99间有效数值" value="${dataModel.order_no}" class="form-control" required>
													</div>
													<div class="help-block with-errors"></div>
												</div>
												<div class="form-group" id="content_type">
													<label class="col-sm-2 control-label" style="padding-top: 0px;">内容类型</label>
													<div class="col-sm-8">
														<label> 
															<input name="menu_type" <c:if test="${dataModel.menu_type==0 }">checked="checked"</c:if>
															type="radio" class="ace" value="0" required/> <span class="lbl">回复文字</span>
														</label> 
														<label> <input name="menu_type" <c:if test="${dataModel.menu_type==2 }">checked="checked"</c:if>
															type="radio" class="ace" value="2" required/> <span class="lbl">跳转网页</span>
														</label> 
														<label> <input name="menu_type" <c:if test="${dataModel.menu_type==1 }">checked="checked"</c:if>
															type="radio" class="ace" value="1" required/> <span class="lbl">图文素材</span>
														</label>
													</div>
													<div class="help-block with-errors"></div>
												</div>
												
												
												<div id="r_link_div" class="form-group" <c:if test="${dataModel.menu_type!=2 }">style="display: none;"</c:if> >
													<label class="col-sm-2 control-label">网页地址</label>
													<div class="col-sm-8">
														<input type="url" id="menu_link" name="menu_link" class="form-control" value="<c:if test="${dataModel.menu_type==2}">${dataModel.menu_content }</c:if>" data-error="请输入有效链接地址"  required>
													</div>
													<div class="help-block with-errors"></div>
												</div>
												<div id="r_link_radio_div" class="temp-ctr form-group" <c:if test="${dataModel.menu_type!=2 }">style="display: none;"</c:if> >
													<label class="col-sm-2 control-label no-padding-right" style="padding-top: 0px;">用户授权</label>
													<div class="col-sm-8">
														<label><input name="author" type="radio" class="ace" value="0" <c:if test="${dataModel.author==0}">checked="checked"</c:if> required/> <span class="lbl">不需要授权</span></label>
														<label><input name="author" type="radio" class="ace" value="1" <c:if test="${dataModel.author==1}">checked="checked"</c:if> required/> <span class="lbl">需要Author授权</span></label>
													</div>
													<div class="help-block with-errors"></div>
												</div>
												
												<div id="r_text_div" class="form-group" <c:if test="${dataModel.menu_type!=0}">style="display: none;"</c:if> >
													<label class="col-sm-2 control-label">回复文字</label>
													<div class="col-sm-8">
														<textarea name="menu_content" id="menu_content" class="form-control" placeholder="请输入回复内容" data-error="请输入回复内容" required><c:if test="${dataModel.menu_type==0}">${dataModel.menu_content }</c:if></textarea>
													</div>
													<div class="help-block with-errors"></div>
												</div>
												<div class="widget-toolbox padding-8 clearfix">
													<div class="form-group">
															<label class="col-sm-2 control-label"></label>
															<div class="col-sm-8">
																<button class="btn btn-primary" type="button"
																	onclick="saveMenu()">
																	<i class="fa fa-check"></i>&nbsp;&nbsp;提 交&nbsp;&nbsp;
																</button>
																&nbsp;&nbsp;&nbsp;&nbsp;
																<button class="btn btn-warning" onclick="history.go(-1)">
																	<i class="fa fa-close"></i>&nbsp;&nbsp;返 回&nbsp;&nbsp;
																</button>
															</div>
														</div>
												</div>
											</form>
										
										</div>
									</div>
								</div>
							<!-- 表单结束 -->
							<!-- 图文消息 -->
								<div class="col-sm-8" id="richEdit" style=" <c:if test="${dataModel.menu_type!=1 }">display: none;</c:if>">
									<div class="row">
										<!-- 效果预览 -->
										<div class="col-sm-5">
											<div class="ibox float-e-margins">
												<div class="ibox-title">
													<h5>
														图文预览
													</h5>
													<div class="ibox-tools">
							                           <a href="javascript:void(0)" onclick="addRow()" title="新增一行" class="btn btn-primary btn-xs"><i class="fa fa-plus"></i>&nbsp;新增</a>
							                           <a href="javascript:void(0)" onclick="delRow()" title="删除选择行" class="btn btn-danger btn-xs"><i class="fa fa-trash"></i>&nbsp;删除</a>
							                        </div>
							                        
												</div>
												<div class="ibox-content">
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
											</div>
										</div>
										<!-- 效果预览结束 -->
										<!-- 编辑图文 -->
										<div class="col-sm-7">
											<div class="ibox float-e-margins">
												<div class="ibox-title">
													<h5>
														图文编辑
													</h5>
												</div>
												<div class="ibox-content">
													<form class="form-horizontal" role="form" data-toggle="validator" >
														<div class="form-group">
															<label class="col-sm-11 control-label"
																style="text-align: left;"></label>
														</div>
														<div class="form-group">
															<label class="col-sm-2 control-label">标题</label>
		
															<div class="col-sm-9">
																<input type="text" id="title_input"
																	onkeyup="onInputKeyUp('title_input')"
																	class="form-control" data-error="请输入标题！" required>
															</div>
															<div class="help-block with-errors"></div>
														</div>
														<div class="form-group">
															<label class="col-sm-2 control-label">排序</label>
															<div class="col-sm-9">
																<input type="number" id="order_input" max="99" min="0" data-error="请输入0~99间有效数值！"
																	onkeyup="onInputKeyUp('order_input')"
																	class="form-control" required>
															</div>
															<div class="help-block with-errors"></div>
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
																<input type="url" id="url_input" data-error="请输入有效地址链接！"
																	onkeyup="onInputKeyUp('url_input')" class="form-control" required>
																	<span class="help-block">以'<font color="#18a689">http://</font>'或'<font color="#18a689">https://</font>'打头</span>
															</div>
															<div class="help-block with-errors"></div>
														</div>
														<div class="form-group">
															<label class="col-sm-2 control-label">图片</label>
															<div class="col-sm-9">
																<input type="url" id="img_input" data-error="请输入有效地址链接！"
																	onkeyup="onInputKeyUp('img_input')" class="form-control" required>
																	<span class="help-block">以'<font color="#18a689">http://</font>'或'<font color="#18a689">https://</font>'打头</span>
															</div>
															<div class="help-block with-errors"></div>
														</div>
													</form>
												
												</div>
											</div>
										</div>
										<!-- 编辑图文结束 -->
									</div>
								</div>
							<!-- 图文消息结束 -->
							
						</div>
					
					
					
					
					
					
					</div>
				</div>
			</div>
		</div>
	</div>

	<c:if test="${not empty dataModel}">
		<textarea id="reply_content" style="display: none;">
			${dataModel.menu_content}
		</textarea>
		<input type="hidden" id="menu_key_hi" value="${dataModel.menu_key}">
		<input type="hidden" id="menu_id_hi" value="${dataModel.id}">
		<input type="hidden" id="menu_type_hi" value="${dataModel.menu_type}">
	</c:if>
	<!-- basic scripts -->
	<!-- 页面底部js¨ -->
	<%@ include file="../common/foot.jsp"%>
	<script type="text/javascript">
		var replyType=-1;
		var isEdit=false;
		function onComplete(){
			$("[name='menu_type']").on("change", function(e) {onTypeChange($(e.target).val());});
			var id=$("#menu_id_hi").val();
			if(id>0){
				isEdit=true;
			}
			initFrom();
		}
		function onTypeChange(type) {
			replyType=type;
			if (type == 0) {
				$("#r_text_div").show();
				$("#r_link_div").hide();
				$("#r_link_radio_div").hide();
				$("#richEdit").hide();
			} else if (type == 2) {
				$("#r_text_div").hide();
				$("#r_link_div").show();
				$("#r_link_radio_div").show();
				$("#richEdit").hide();
			} else if (type == 1) {
				$("#r_text_div").hide();
				$("#r_link_div").hide();
				$("#r_link_radio_div").hide();
				$("#richEdit").show();
			}

		}

	</script>
	
	<script type="text/javascript">
		//==============================================================================
		//保存到服務器
		//==============================================================================
		function saveMenu(){
			var obj={};
			obj.menu_name=$("#menu_name").val();
			obj.order_no=$("#order_no").val();
			obj.parent_id=$("#parent_id").val();
			obj.menu_key="";
			replyType=$("[name='menu_type']").filter(":checked").val(); 
			obj.menu_type=replyType;
			if(isEdit){
				obj.id=$("#menu_id_hi").val();
				obj.menu_key=$("#menu_key_hi").val();
			}
			if (replyType == 0) {
				obj.menu_content=$("#menu_content").val();
				obj.author=0;
			} else if (replyType == 2) {
				obj.menu_content=$("#menu_link").val();
				obj.author=$("[name='author']").filter(":checked").val(); 
			} else if (replyType == 1) {
				for(var i=0;i<items.length;i++){
					var item=items[i];
					if(checkItem(item.title)){
						popInfo(i,"标题");
						return;
					}
					if(checkItem(item.img)){
						popInfo(i,"封面图地址");
						return;
					}
					if(checkItem(item.desc)){
						popInfo(i,"摘要");
						return;
					}
					if(checkItem(item.url)){
						popInfo(i,"图文详情URL地址");
						return;
					}
					if(checkItem(item.title)){
						popInfo(i,"标题");
						return;
					}
				}
				obj.author=0;
				var jsonText = JSON.stringify(items);
				obj.menu_content=jsonText;
			}
			app.post(obj,"/sys/wechat/save_menu",onAddResult,onAddFault);
		}
		
		function checkItem(paraStr){
			paraStr=$.trim(paraStr);
			if(paraStr==""){
				return true;
			}
			return false;
		}
		function popInfo(rowNum,label){
			bootbox.alert("第:"+(rowNum+1)+"行未填写:"+label);
		}
		
		
		function onAddResult(data){
			if(data.result==200){
				bootbox.alert("保存成功！");	
				location.href="/sys/wechat/menu_list";
			}else{
				alert(data.msg);
			}
		}
		function onAddFault(data){
			if(data.result==200){
				bootbox.alert("保存成功！");	
				location.href="/sys/wechat/menu_list";
			}else{
				bootbox.alert(data.msg);
			}
		}
	</script>
	
	
	<script type="text/javascript">
	
			function initFrom(){
				if(isEdit){
					items=$.parseJSON($("#reply_content").val());
					currentItem=items[0];
					updatePreview();
				}else{
					tempCount=0;
					currentItem=getNewDataItem();
					items.push(currentItem);
					$("#previewDiv").html("");
					var html=getFirstRow();
					$("#previewDiv").html(html);
					$("#row0").addClass("current");
				}
				initForm();
			}
		
			var items=new Array();
			var currentItem=null;
			var tempCount=1;
			function changeEditRow(index){
				currentItem=getItem(index);
				$(".item_row").removeClass("current");
				$("#row"+index).addClass("current");
				initForm();
			}
			
			function updatePreview(){
				var item=items[0];
				item.index=0;
				var html=getFirstRow();
				$("#previewDiv").html(html);
				for(var i=1;i<items.length;i++){
					item=items[i];
					item.index=i;
					$("#previewDiv").append(getRowHtml(item));
				}
				$("#row"+currentItem.index).addClass("current");				
			}
			
			function addRow(){
				tempCount=tempCount+1;
				currentItem=getNewDataItem();
				items.push(currentItem);
				$("#previewDiv").append(getRowHtml(currentItem));
				changeEditRow(currentItem.index);
			}
			
			function delRow(){
				if(items.length==1){
					bootbox.alert("最少要有一條消息!");
					return;
				}
				$("#row"+currentItem.index).remove();
				var itemIndex=currentItem.index;
				for(var i=0;i<items.length;i++){
					var item=items[i];
					if(item.index==itemIndex){
						items.remove(i);
					}
				}
				updatePreview();
				currentItem=items[0];
				changeEditRow(currentItem.index);
			}
			
			function initForm(){
				$("#title_input").val(currentItem.title);
				$("#img_input").val(currentItem.img);
				$("#order_input").val(currentItem.order);
				$("#url_input").val(currentItem.url);
				$("#desc_input").val(currentItem.desc);
				$("#author_input").val(currentItem.author);
				getRadio();
			}
			
			function getRadio(){
				var str='<label>'; 
					str=str+'<input name="author_radio" id="radio0" type="radio" class="ace" value="0" onclick="onInputKeyUp(\'radio0\')"';
					if(currentItem.author!=1){
						str=str+' checked="checked" ';
					}
					str=str+'/> <span class="lbl">不需要授权</span>';
					str=str+'</label> ';
					str=str+'<label>';
					str=str+'<input name="author_radio" id="radio1" type="radio" class="ace" value="1" onclick="onInputKeyUp(\'radio1\')"';
					if(currentItem.author==1){
						str=str+' checked="checked" ';
					}
					str=str+'/> <span class="lbl">需要Author授权</span>';
					str=str+'</label>';
					$("#author_div").html(str); 
			}
			
			
			function getRowHtml(item){
				if(item==null){
					return "";
				}
				var html='';
					html='<div id="row'+item.index+'" onclick="changeEditRow('+item.index+')" class="item_row">';
					html=html+ '<div class="appmsg_item">';
					html=html+'<div class="thumb_img" style="background-image:url('+"'"+item.img+"'"+');">';
					html=html+'</div>';
					html=html+'<h4 class="appmsg_title js_appmsg_title">'+item.title+'</h4>';
					html=html+'</div>';
					html=html+'</div>';
					return html;
			}
			
			function getFirstRow(){
				var html='<div id="row'+items[0].index+'" class="item_row" onclick="changeEditRow('+items[0].index+')">';
				html=html+ '<div class="cover_appmsg_item">';
				html=html+ '<div class="">';
				html=html+ '<div class="img_div">';
				html=html+ '<h4 class="appmsg_title_cover"><a href="javascript:void(0);" onclick="return false;">'+items[0].title+'</a></h4>';
				html=html+ '<div class="first_thumb_img" style="background-image:url('+"'"+items[0].img+"'"+');">';
				html=html+ '</div>';
				html=html+ '</div>';
				html=html+ '</div>';
				html=html+ '</div>';
				html=html+ '</div>';
				return html;
			}
			
			function getNewDataItem(){
				var item={};
				item.title="请输入标题";
				item.img="";
				item.desc="";
				item.order=1;
				item.url="";
				item.author="";
				item.index=tempCount;
				return item;
			}
			
			function getItem(itemIndex){
				for(var i=0;i<items.length;i++){
					var item=items[i];
					if(item.index==itemIndex){
						return item;
					}
				}
				return null;
			}
			Array.prototype.remove=function(dx) 
			{ 
			    if(isNaN(dx)||dx>this.length){return false;} 
			    for(var i=0,n=0;i<this.length;i++) 
			    { 
			        if(this[i]!=this[dx]) 
			        { 
			            this[n++]=this[i] 
			        } 
			    } 
			    this.length-=1 
			} 
			
			//==============================================================================
			//事件侦探
			//==============================================================================
			function onInputKeyUp(cName){
				currentItem.title=$("#title_input").val();
				currentItem.img=$("#img_input").val();
				currentItem.order=$("#order_input").val();
				currentItem.url=$("#url_input").val();
				currentItem.desc=$("#desc_input").val();
				currentItem.author=$("[name='author_radio']").filter(":checked").val(); 
				if(cName=="img_input"||cName=="title_input"||cName=="order_input"){
					updatePreview();	
				}
			}
			onComplete();
		</script>
</body>
</html>