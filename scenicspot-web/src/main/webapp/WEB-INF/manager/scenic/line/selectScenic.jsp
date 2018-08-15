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
						<div class="ibox-content">
							<div class="project-list">
								<table id="simple-table"
									class="center table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th>景点名称</th>
											<th>景点图片</th>
											<th>状态</th>
											<th class="center" style="width: 200px">选择</th>
										</tr>
									</thead>
									<tbody>
										<!-- 开始循环 -->
										<c:choose>
											<c:when test="${not empty dataModel}">
												<c:forEach items="${dataModel}" var="item">
													<tr>
														<td>${item.scenic_name}</td>
														<td><img alt="" width="50px" height="45px"
															src="${SETTINGPD.IMAGEPATH}${item.scenic_logo}"></td>
														<td><c:if test="${item.line_status eq 1}">
																<span class="label label-primary">正常</span>
															</c:if> <c:if test="${item.scenic_status eq 2}">
																<span class="label label-danger">隐藏</span>
															</c:if></td>
														<td class="center">
														    <span onclick="selectScenic('${item.scenic_name}','${item.scenic_logo}','${item.id}');" class="label label-info">选择</span>
														</td>
													</tr>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<tr class="main_info">
													<td colspan="12"  class="center">无可选用数据</td>
												</tr>
											</c:otherwise>
										</c:choose>
									</tbody>
								</table>
							</div>
						</div>
				</div>
			</div>
		</div>
	</div>
	<!-- basic scripts -->
	<!-- 页面底部js¨ -->
	<%@ include file="../../common/foot.jsp"%>
		<script type="text/javascript">
		var index = parent.layer.getFrameIndex(window.name);
		function closed() {
			parent.layer.close(index);
		}
		//景区相册
		function openAlbum(scenic_id){
			var index = layer.open({
	            type: 2,
	            title: '景区相册',
	            maxmin: true,
	            shadeClose: true, //点击遮罩关闭层
	            area : ['850px' , '600px'],
	            content: "/sys/scenic/albums?scenic_id="+scenic_id,
	        });
			layer.full(index);
		}
		
		function selectScenic(scenic_name,scenic_logo,id){
			parent.callBack(scenic_name,scenic_logo,id);
			closed();
		}
	</script>
	
</body>
</html>