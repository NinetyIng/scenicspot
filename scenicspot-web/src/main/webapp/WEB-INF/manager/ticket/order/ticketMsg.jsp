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
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-content">
						<form action="/sys/scenic/save" method="post"
							class="form-horizontal m-t" id="commentForm">
									<div class="panel-body">
										 	    <table class="table" id="ticketTable" >
										   	       <tr><td>票类名字</td><td style="color:blue" id="ticketName">九寨沟成人票</td></tr>
										   	       <tr><td>开始售卖时间</td><td style="color:blue" id="startSaleTime">2017-08-31</td></tr>
										   	       <tr><td>结束售卖时间</td><td style="color:blue" id="endSaleTime">2017-09-31</td></tr>
										   	       <tr><td>有效期开始时间</td><td style="color:blue" id="beginValidTime">2017-08-31</td></tr>
										   	       <tr><td>有效期结束时间</td><td style="color:blue" id="endValidTime">2017-09-31</td></tr>
										   	       <tr><td>门市价格</td><td style="color:blue" id="marketPrice">253</td></tr>
										   	       <tr><td>零售价格</td><td style="color:blue" id="retailPrice">253</td></tr>
										   	       <tr><td>结算价格</td><td style="color:blue" id="settlePrice">253</td></tr>
										   	       <tr><td>库存</td><td style="color:blue" id="stock">785</td></tr>
										   	       <tr><td>提前下单天数</td><td style="color:blue" id="advanceBookDays">785</td></tr>
										   	       <tr><td>可预订最晚下单时间</td><td style="color:blue" id="lastBookTime">785</td></tr>
										   	       <tr><td>支付方式</td><td style="color:blue" id="paymentMethod">785</td></tr>
										   	       <tr><td>是否需要实名制</td><td style="color:blue" id="isRealName">785</td></tr>
										   	       <tr><td>最少购买数量</td><td style="color:blue" id="minBuy">1</td></tr>
										   	       <tr><td>最多购买数量</td><td style="color:blue" id="maxBuy">100</td></tr>
										   	       <tr><td>实名限制</td><td style="color:blue" id="limitIdcardNum">100</td></tr>
										   	    </table>
										   	    <label id="noTicketMsg" class="label label-danger">
										   	    </label>
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
	<script type="text/javascript">
	$(function(){
		var data = '${dataModel}';
		data = JSON.parse(data);
		if(data && data.ResponseBody && data.ResponseBody.TicketTypeList && data.ResponseBody.TicketTypeList.length > 0){
			data = data.ResponseBody.TicketTypeList;
			$("#ticketTable").show();
			$("#noTicketMsg").hide();
			$("#ticketName").text(data[0].TicketTypeName);
			$("#startSaleTime").text(data[0].BeginSaleTime);
			$("#endSaleTime").text(data[0].EndSaleTime);
			$("#settlePrice").text(data[0].SettlementPrice);
			$("#stock").text(data[0].Stock);
			$("#minBuy").text(data[0].MinBuyCount);
			$("#beginValidTime").text(data[0].BeginValidTime);
			$("#endValidTime").text(data[0].EndValidTime);
			$("#marketPrice").text(data[0].MarketPrice);
			$("#retailPrice").text(data[0].RetailPrice);
			$("#advanceBookDays").text(data[0].AdvanceBookDays);
			$("#lastBookTime").text(data[0].LastBookTime);
			if(data[0].PaymentMethod == "1"){
				$("#paymentMethod").text("在线支付");
			}else{
				$("#paymentMethod").text("现场支付");
			}
			var IdCardLimitDays = data[0].IdCardLimitDays;
			var IdCardLimitCount = data[0].IdCardLimitCount;
			var limit = "每身份证"+IdCardLimitDays+"天内最多可购买数量"+IdCardLimitCount;
			if(data[0].IsRealName){
			   $("#isRealName").text("实名认证");
			   if(IdCardLimitCount == "0"){
				   $("#limitIdcardNum").text("不限制身份证购买数量");
			   }else{
				   $("#limitIdcardNum").text(limit);
			   }
			}else{
				$("#limitIdcardNum").text("不限制身份证购买数量");
				$("#isRealName").text("不认证");
			}
		}else{
			$("#ticketTable").hide();
			$("#noTicketMsg").show().text("第三方票务系统无该票号的信息，请联系线下售票系统");
		}
	});
	</script>
</body>
</html>