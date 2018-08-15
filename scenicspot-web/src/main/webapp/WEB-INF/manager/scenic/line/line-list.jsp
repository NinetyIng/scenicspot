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
					<form action="/sys/scenic/list" method="post" name="Form"
						id="Form">
						<div class="ibox-title">
							<h5>攻略列表</h5>
							<div class="ibox-tools">
								<a href="/sys/scenic/addLinePage" class="btn btn-primary btn-xs"><i
									class="fa fa-plus"></i>&nbsp;新增攻略</a>
							</div>
						</div>
						<div class="ibox-content">
							<div class="search-condition row">
							</div>
						</div>
						<div class="ibox-content">
							<div class="project-list">
								<table id="simple-table"
									class="center table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th>攻略名称</th>
											<th>攻略图片</th>
											<th>攻略状态</th>
											<th>攻略排序</th>
											<th>创建时间</th>
											<th class="center" style="width: 200px">操作</th>
										</tr>
									</thead>
									<tbody>
										<!-- 开始循环 -->
										<c:choose>
											<c:when test="${not empty dataModel}">
												<c:forEach items="${dataModel}" var="item">
													<tr>
														<td>${item.line_name}</td>
														<td><img alt="" width="50px" height="45px" onerror="this.src='/statics/img/no-img.jpg'"
															src="${SETTINGPD.IMAGEPATH}${item.line_logo}"></td>
														<td><c:if test="${item.line_status eq 1}">
																<span class="label label-primary">正常</span>
															</c:if> <c:if test="${item.line_status eq 2}">
																<span class="label label-danger">隐藏</span>
															</c:if></td>
														<td>${item.line_order}</td>	
														<td>${item.create_time}</td>	
														<td class="center">
														<%-- <c:if test="${QX.edit == 1}"> --%>
												    <a class="btn btn-xs btn-light"> 
														<i class="glyphicon glyphicon-link" id="copy" title="复制链接到剪贴板" data-clipboard-text="m.aiwzd.cn/wx/line/detail?line_id=${item.id}"></i>
													</a>
														<a
															href="/sys/scenic/addLinePage?id=${item.id}"
															class="btn btn-primary btn-sm" title="编辑"> <i
																class="fa fa-pencil"></i>
														</a> 
														<%-- </c:if> --%>
														<a
															href="deleteScenicLine('${item.id}');"
															class="btn btn-warning btn-sm" title="删除"> <i
																class="fa fa-trash"></i>
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
	<script  src="/assets/js/plugins/clipboard/dist/clipboard.min.js"></script>
		<script type="text/javascript">
		
	    var clipboard = new Clipboard('#copy');
		clipboard.on('success', function(e) {
		    console.info('Action:', e.action);
		    console.info('Text:', e.text);
		    console.info('Trigger:', e.trigger);
		    layer.alert('链接：'+e.text+'\n已复制到剪贴板中', {icon: 6});
	        console.log(e);
		    e.clearSelection();
		});
		 
		clipboard.on('error', function(e) {
		    console.error('Action:', e.action);
		    console.error('Trigger:', e.trigger);
		}); 
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
		//删除 攻略
	    function deleteScenicLine(scenic_id){
			bootbox.confirm("确认删除该攻略吗？",function(result){
				if(result){
				  Ajax.request("/sys/scenic/deleteLine",{"data":{"id":scenic_id},"success":function(data){
					  if(data.result == 200){
						  nextPage('${page.currentPage}');
					  }
				  }});	
				}
			});
		}
	</script>
</body>
</html>