<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../common/top.jsp"%>
</head>
<body>
	<div class="wrapper wrapper-content animated fadeInUp">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox">
					<form action="/sys/ticket/order/list.do" method="post" name="Form"
						id="Form">
						<div class="ibox-title">
							<h5>操作提示：</h5>   点击票务识别号可查询票务公司的具体票务信息
						</div>
						<div class="ibox-content">
							<div class="search-condition row">
							<div class="col-md-2">
							    <div class="form-group">
							    <input type="text" name="keyword" placeholder="预订人/手机号/订单号/票务号" class="form-control" value="${pd.keyword}">
							    </div>
							</div>
							<div class="col-md-2">
									<div class="form-group">
										<select class="form-control" name="to_scenic_id" id="to_scenic_id">
										   <option value="">请选择景区</option>
										   	<c:forEach var="category" items="${scenicList}">
											    <option data-attr-name="${category.scenic_name }"    value="${category.id}"
												<c:if test="${pd.to_scenic_id eq category.id}"> selected ='selected'</c:if>>${category.scenic_name}
												</option>										    </c:forEach>
										</select>
									</div>
								</div>
								<div class="col-md-2">
									<div class="form-group">
										<select class="form-control" name=to_ticket_id id="to_ticket_id">
										   <option value="">请选择票类</option>
										   	<c:forEach var="category" items="${ticketList}">
											    <option data-attr-name="${category.ticket_name }"    value="${category.id}"
												<c:if test="${pd.to_ticket_id eq category.id}"> selected ='selected'</c:if>>${category.cate_name}-${category.ticket_name}
												</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="col-md-2">
								<div class="form-group">
									<select class="form-control" name="to_pay_status" id="to_pay_status">
										<option value=''>支付状态</option>
										<option value='1'
											<c:if test="${pd.to_pay_status eq 1}">selected = 'selected'</c:if>>
											已支付</option>
										<option value='2'
											<c:if test="${pd.to_pay_status eq 2}">selected = 'selected'</c:if>>
											未支付</option>
										<option value='3'
											<c:if test="${pd.to_pay_status eq 3}">selected = 'selected'</c:if>>
											已退款</option>
									</select>
									</div>
								</div>
								<div class="col-md-2">
									<select class="form-control" name="to_status" id="to_status">
										<option value=''>订单状态</option>
										<option value='1'
											<c:if test="${pd.to_status eq 1}">selected = 'selected'</c:if>>
											待付款</option>
										<option value='2'
											<c:if test="${pd.to_status eq 2}">selected = 'selected'</c:if>>
											待使用</option>
										<option value='3'
											<c:if test="${pd.to_status eq 3}">selected = 'selected'</c:if>>
											已使用</option>
										<option value='4'
											<c:if test="${pd.to_status eq 4}">selected = 'selected'</c:if>>
											已取消</option>
										<option value='5'
											<c:if test="${pd.to_status eq 5}">selected = 'selected'</c:if>>
											退款中</option>	
										<option value='6'
											<c:if test="${pd.to_status eq 6}">selected = 'selected'</c:if>>
											已退款</option>			
									</select>
								</div>
								<div class="col-md-1">
									<div class="input-group">
										<button class="btn btn-primary" type="submit">
											<i class="fa fa-search"></i>&nbsp;查询&nbsp;
										</button>
									</div>
								</div>
							</div>
						</div>
						<div class="ibox-content">
							<div class="project-list">
								<table id="simple-table"
									class="center table table-striped table-bordered table-hover">
									<thead>
										<tr>
										    <th>下单时间</th>
											<th>订单编号</th>
											<th>预订人信息</th>
											<th>景点名称</th>
											<th>票类信息</th>
											<th>结算价格</th>
											<th>预定数量</th>
											<th>出行日期</th>
											<th>订单状态</th>
											<th>支付状态</th>
											<th>是否实名购买</th>
											<th class="center" style="width: 200px">操作</th>
										</tr>
									</thead>
									<tbody>
										<!-- 开始循环 -->
										<c:choose>
											<c:when test="${not empty dataModel}">
												<c:forEach items="${dataModel}" var="item">
													<tr>
													   <td>${item.createTime}</td>
														<td>${item.orderNo}</td>
														<td>
														<p>姓名:${item.contactName}</p>
														<p>联系电话:${item.contactMobile}</p>
														</td>
														<td>
														<a href="/sys/scenic/editPage.do?id=${item.scenicId}">${item.scenicName}</a>
														</td>
														<td>
														<p style="color:blue;cursor: pointer;" onclick="openTicketMsg('${item.thirdNo}');">
														   票务识别号：${item.thirdNo}
														</p>
														<p>
														   票类名称：${item.ticketName}
														   <c:if test="${empty item.packageType || item.packageType eq 1}">
																<span class="label label-primary">单票</span>
															</c:if> <c:if test="${item.packageType eq 2}">
																<span class="label label-danger">套票</span>
															</c:if>
														</p>
														</td>
														<td>${item.settlementPrice}</td>
														<td>
														${item.quantity}
														</td>
													    <td>
														${item.travelDate}
														</td>
														   <td>
														   <c:if test="${item.status eq '1'}">
														         <span class="label label-warning">待付款</span>
														   </c:if>
														   <c:if test="${item.status eq '2'}">
														       <span class="label label-primary"> 待使用</span>
														   </c:if>
														   <c:if test="${item.status eq '3'}">
														         <span class="label  label-info">已使用</span>
														   </c:if>
														   	<c:if test="${item.status eq '4'}">
														         <span class="label  label-inverse">已取消</span>
														   </c:if>
														   <c:if test="${item.status eq '5'}">
														         <span class="label  label-light">退款中</span>
														   </c:if>
														    <c:if test="${item.status eq '6'}">
														         <span class="label  label-success">已退款</span>
														   </c:if>
														</td>
														 <td>
														 <c:if test="${item.payStatus eq '1'}">
														  <span class="label label-primary">     已支付（${item.payMethod}）</span>
														  </c:if>
														  <c:if test="${item.payStatus eq '2'}">
														     <span class="label label-warning">  待支付</span>
														   </c:if>
														   <c:if test="${item.payStatus eq '3'}">
														     <span class="label label-danger">  已退款</span>
														   </c:if>
														</td>
														<td>
														   <c:if test="${item.isRealName eq '1'}">
														  <span class="label label-primary">  实名预定</span>     
														  </c:if>
														  <c:if test="${item.isRealName eq '2'}">
														       <span class="label label-danger">   未实名预定</span>     
														   </c:if>
														</td>
														<td class="center">
														<%-- <c:if test="${QX.edit == 1}"> --%>
														<a
															href="/sys/ticket/order/editPage.do?id=${item.id}"
															class="btn btn-primary btn-sm" title="编辑"> <i
																class="fa fa-pencil"></i>
														</a> 
														</td>
													</tr>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<tr class="main_info">
													<td colspan="12" class="center">没有相关数据</td>
												</tr>
											</c:otherwise>
										</c:choose>
									</tbody>
									<tfoot>
										<tr>
											<td colspan="12">
												<div class="dataTables_paginate paging_bootstrap pull-right">
													${page.pageStr}</div>
											</td>
										</tr>
									</tfoot>
								</table>
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
</body>


<script type="text/javascript">
function openTicketMsg(ticketTypeId){
	var index = layer.open({
        type: 2,
        title: '票务信息',
        maxmin: true,
        shadeClose: true, //点击遮罩关闭层
        area : ['850px' , '600px'],
        content: "/sys/ticket/order/ticketMsg?ticketTypeId="+ticketTypeId,
    });
}




</script>
</html>