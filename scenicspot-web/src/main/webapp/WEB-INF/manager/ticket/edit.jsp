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
<%@ include file="../common/top.jsp"%>
<link href="/statics/css/phone.css" rel="stylesheet">
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>
							操作提示：
						</h5>
						 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						  <span style="color:#1ab394">输入第三方的票务识别码可先查询是否存在</span>
					</div>
					<div class="ibox-content">
						<form action="/sys/scenic/save" method="post"
							class="form-horizontal m-t" id="commentForm"
							>
									<div class="panel-body">
										<input type="hidden" name="id" id="id" value="${dataModel.id}" />
										<div class="form-group">
											<label class="col-sm-2 control-label"><font
												color="red" size="3px"
												style="font-weight: bold; font-style: italic;">*&nbsp;</font>票务系统识别码：</label>
											<div class="col-sm-4">
												<input type="text" name="third_no" id="third_no"
													class="form-control" value="${dataModel.third_no}">
											</div>
											<div class="col-sm-2">
											    <button class="btn btn-primary" type="button" id="selectTicket">
													<i class="fa fa-search"></i>&nbsp;查询&nbsp;
												</button>
											</div>
										</div>
										<div class="form-group" id="ticketMsg">
										   	<label class="col-sm-2 control-label">票类信息：</label>
										   	 <div class="col-sm-4">
										   	    <table class="table" id="ticketTable" style="color:blue">
										   	       <tr><td>票类名字</td><td id="ticketName">九寨沟成人票</td></tr>
										   	       <tr><td>开始售卖时间</td><td id="startSaleTime">2017-08-31</td></tr>
										   	       <tr><td>结束售卖时间</td><td id="endSaleTime">2017-09-31</td></tr>
										   	       <tr><td>结算价格</td><td id="settlePrice">253</td></tr>
										   	       <tr><td>库存</td><td id="stock">785</td></tr>
										   	       <tr><td>最少购买数量</td><td id="minBuy">1</td></tr>
										   	       <tr><td>最多购买数量</td><td id="maxBuy">100</td></tr>
										   	    </table>
										   	    <label id="errorMsg" style="color:red;">未查询到票类信息请联系票务平台确认该票号</label>
										   	 </div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label"><font
												color="red" size="3px"
												style="font-weight: bold; font-style: italic;">*&nbsp;</font>选择景点：</label>
											<div class="col-sm-6">
											<select name="scenic_id" id="scenic_id"
													class="form-control">
													<option value="">--请选择景区分类--</option>
													<c:forEach var="category" items="${scenicList}">
														<option data-attr-name="${category.scenic_name }"    value="${category.id}"
															<c:if test="${dataModel.scenic_id eq category.id}"> selected ='selected'</c:if>>${category.scenic_name}</option>
													</c:forEach>
												</select>
												
												<input type="hidden" name="scenic_name" id="scenic_name" />
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label">选择票类目：</label>
											<div class="col-sm-6">
												<select name="cate_id" id="cate_id"
													class="form-control">
													<option value="">--请选择景区分类--</option>
													<c:forEach var="category" items="${cateList}">
														<option data-attr-name="${category.cate_name }"    value="${category.id}"
															<c:if test="${dataModel.cate_id eq category.id}"> selected ='selected'</c:if>>${category.cate_name}</option>
													</c:forEach>
												</select>
												<input type="hidden" name="cate_name" id="cate_name" />
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label"><font
												color="red" size="3px"
												style="font-weight: bold; font-style: italic;">*&nbsp;</font>票类名字（1-100个字符之间）：</label>
											<div class="col-sm-6">
											    <input type="text" name="ticket_name" id="ticket_name"
													class="form-control" value="${dataModel.ticket_name}">
											</div>
											<div>
											   <label class="control-label"><font
												color="red" size="3px"
												style="font-size:11px;">1到100个字符&nbsp;</font>
												</label>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label">票类标签（3个以内，多标签之间逗号分割）：</label>
											<div class="col-sm-6">
												<input type="number" name="ticket_lable" id="ticket_lable"
													class="form-control" value="${dataModel.ticket_lable}">
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label">起售价格：</label>
											<div class="col-sm-6">
												<input type="number" name="ticket_price" id="ticket_price"
													class="form-control" value="${dataModel.ticket_price}">
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label">单票/套票：</label>
											<div class="col-sm-8">
												<label class="radio-inline"> <input type="radio"
													<c:if test="${empty dataModel.ticket_package_type || dataModel.ticket_package_type eq '1'}">checked='checked'</c:if>
													value="1" name="ticket_package_type">单票
												</label> <label class="radio-inline"> <input type="radio"
													value="2"
													<c:if test="${dataModel.ticket_package_type eq '2'}">checked='checked'</c:if>
													name="ticket_package_type">套票
												</label>
											</div>
										</div>	
										<div class="form-group">
											<label class="col-sm-2 control-label">是否支持退票：</label>
											<div class="col-sm-8">
												<label class="radio-inline"> <input type="radio"
													<c:if test="${empty dataModel.ticket_isrefund || dataModel.ticket_isrefund eq '1'}">checked='checked'</c:if>
													value="1" name="ticket_isrefund">支持
												</label> <label class="radio-inline"> <input type="radio"
													value="2"
													<c:if test="${dataModel.ticket_isrefund eq '0'}">checked='checked'</c:if>
													name="ticket_isrefund">不支持
												</label>
											</div>
										</div>	
										<div class="form-group">
											<label class="col-sm-2 control-label"><font
												color="red" size="3px"
												style="font-weight: bold; font-style: italic;">*&nbsp;</font>购买须知：</label>
											<div class="col-sm-6">
												<script id="editor" type="text/plain"
													style="width: 100%; height: 280px;">${dataModel.ticket_tip}</script>
													<input type="hidden" name="ticket_tip" id="ticket_tip"/>
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-4 col-sm-offset-3">
												<button class="btn btn-primary" type="button"
													onclick="checkForm();">
													<i class="fa fa-check"></i>&nbsp;&nbsp;提 交&nbsp;&nbsp;
												</button>
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
		</div>
	</div>
	<!-- basic scripts -->
	<!-- 页面底部js¨ -->
	<%@ include file="../common/foot.jsp"%>
	<script src="/assets/js/bootstrap-fileupload.js"></script>
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
		var ticketNoConfirm = false;
		var submitFlag = false;
		$("#ticketMsg").hide();
		$(function(){
			$("#selectTicket").click(function(){
				if($("#third_no").val().isEmpty()){
					bootbox.alert("请输入票务平台的查询编号");
					return;
				}
				Ajax.request("/sys/ticket/selectTicketForThird",{"data":{"ticketTypeId":$("#third_no").val()},"success":function(data){
					if(data.result == 200){
						data = data.data.ResponseBody.TicketTypeList;
						if(data && data.length > 0){
							ticketNoConfirm = true;
							$("#ticketMsg").show();
							$("#errorMsg").hide();
							$("#ticketTable").show();
							$("#ticketName").text(data[0].TicketTypeName);
							$("#startSaleTime").text(data[0].BeginSaleTime);
							$("#endSaleTime").text(data[0].EndSaleTime);
							$("#settlePrice").text(data[0].SettlementPrice);
							$("#stock").text(data[0].Stock);
							$("#minBuy").text(data[0].MinBuyCount);
							if(data[0].MaxBuyCount){
								$("#maxBuy").text(data[0].MaxBuyCount);
							}else{
								$("#maxBuy").text("无限制");
							}
						}else{
							ticketNoConfirm = false;
							$("#ticketMsg").show();
							$("#ticketTable").hide();
							$("#errorMsg").show();
						}
					}else{
						ticketNoConfirm = false;
						$("#ticketMsg").show();
						$("#ticketTable").hide();
						$("#errorMsg").show();
					}
				}});
			});
		});
		function checkForm() {
			$("#scenic_name").val($('#scenic_id>option:selected').attr("data-attr-name"));
			$("#cate_name").val($('#cate_id>option:selected').attr("data-attr-name"));
			if(!'${dataModel.third_no}'){
				if(!ticketNoConfirm){
					bootbox.alert("票务公司票号不存在，请联系票务公司确定票号");
					return;
				}
			}
			if (!$("#ticket_name").val().isNotEmpty() || $("#ticket_name").val().trim().length > 100) {
				bootbox.alert("票类长度为0到100个字符之间");
				return false;
			}
			if($("#scenic_id").val().isEmpty()){
				bootbox.alert("请选择景区");
				return;
			}
		    $("#ticket_tip").val(ue.getContent());
		    if(submitFlag){
		    	bootbox.alert("请等待系统处理");
		    	return;
		    }
		    submitFlag = true;
		    Ajax.request("/sys/ticket/eidt",{"data":$("#commentForm").serialize(),"success":function(data){
		    	if(data.result == 200){
		    		window.location.href="/sys/ticket/list";
		    	}else{
		    		submitFlag = false;
		    		bootbox.alert(data.msg);
		    	}
		    }});
			//$("#commentForm").submit();
		}
	</script>
</body>
</html>