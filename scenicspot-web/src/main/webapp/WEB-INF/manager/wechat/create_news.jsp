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
						<h5>新增微信回复</h5>
					</div>
					<div class="ibox-content">
						<!-- 图文消息 -->
						<div class="row">
							<div class="col-sm-8" id="richEdit">
							<div class="row">
							<!-- 效果预览 -->
							<div class="col-sm-5">
								<div class="ibox float-e-margins">
									<div class="ibox-title">
										<h5>图文预览</h5>
										<div class="ibox-tools">
											<a href="javascript:void(0)" onclick="addRow()" title="新增一行"
												class="btn btn-primary btn-xs"><i class="fa fa-plus"></i>&nbsp;新增</a>
											<a href="javascript:void(0)" onclick="delRow()" title="删除选择行"
												class="btn btn-danger btn-xs"><i class="fa fa-trash"></i>&nbsp;删除</a>
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
										<div class="widget-toolbox padding-8" style="width: 100%;margin-bottom: 15px;">
											<textarea id="key_word" class="form-control" placeholder="请输入关键词" ></textarea>
										</div>
									 	<div style="width: 100%;text-align: center;margin-bottom: 5px;">
	                                    	<button class="btn btn-primary" type="button" style="width: 48%;float: left;" onclick="saveNewsReply()"><i class="fa fa-check"></i>&nbsp;&nbsp;保   存&nbsp;&nbsp; </button>
	                                    	<button class="btn btn-warning" type="button" style="width: 48%;float: right;" onclick="history.go(-1)"><i class="fa fa-close"></i>&nbsp;&nbsp;返   回&nbsp;&nbsp; </button>
	                                	</div>
									</div>
									
								</div>
							</div>
							<!-- 效果预览结束 -->
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
						</div>
						</div>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>

	<!-- basic scripts -->
	<!-- 页面底部js¨ -->
	<%@ include file="../common/foot.jsp"%>
	<script type="text/javascript">
			$(document).ready(function(){ 
				tempCount=0;
				currentItem=getNewDataItem();
				items.push(currentItem);
				$("#previewDiv").html("");
				var html=getFirstRow();
				$("#previewDiv").html(html);
				$("#row0").addClass("current");
				initForm();
				$("[name='author_radio']").on("change", function (e) {currentItem.author=$(e.target).val();}); 
			});
		
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
					alert("最少要有一條消息!");
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
			//保存到服務器
			//==============================================================================
			function saveNewsReply(){
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
				if(checkItem($("#key_word").val())){
					alert("请填写关键词");
					return;
				}
				var obj={};
				var jsonText = JSON.stringify(items);
				obj.data=jsonText;
				obj.key_word=$("#key_word").val();
				var data=$.parseJSON(jsonText);
				app.post(obj,"/sys/wechat/save_reply",onAddResult,onAddFault);
			}
			function checkItem(paraStr){
				paraStr=$.trim(paraStr);
				if(paraStr==""){
					return true;
				}
				return false;
			}
			function popInfo(rowNum,label){
				alert("第:"+(rowNum+1)+"行未填写:"+label);
			}
			
			
			
			function onAddResult(data){
				if(data.result==200){
					alert(data.msg);
					window.location.href = "/sys/wechat/reply_list.do";
				}else{
					alert(data.msg);
				}
				
			}
			function onAddFault(){
				alert("Fault");
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
			
			
		</script>
</body>
</html>