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
					
					<div class="ibox-title">
                        <h5>菜单列表</h5>
                    </div>
					<div class="ibox-content">
					 
					
						<form action="lot/list.do" method="post" name="Form" id="Form">
							<div class="project-list">
 								<table id="simple-table" class="center table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th>名称</th>
											<th>英文名</th>
											<th>编码</th>
											<th>负责人</th>
											<th>联系电话</th>
											<th>部门职能</th>
											<th class="center" style="width: 85px">操作</th>
										</tr>
									</thead>
									<tbody>
										<!-- 开始循环 -->
										<c:choose>
											<c:when test="${not empty varList}">
												<c:forEach items="${varList}" var="item">
													<tr>
														<td>${item.NAME}</td>
														<td>${item.NAME_EN}</td>
														<td>${item.BIANMA}</td>
														<td>${item.HEADMAN}</td>
														<td>${item.TEL}</td>
														<td>${item.FUNCTIONS}</td>
														</td>
														<td class="center">
															<a href="/menu/toEdit.do?id=${item.MENU_ID}" class="btn btn-primary btn-sm" title="编辑">
                           										<i class="fa fa-pencil"></i>
                       										</a>
                       										<a href="javascript:;" onclick="del('${item.MENU_ID}','${item.PARENT_ID}');" class="btn btn-warning btn-sm" title="删除">
                           										<i class="fa fa-trash"></i>
                       										</a>
                       									</td>
													</tr>
												</c:forEach>
												<tfoot>
	                                        <tr>
	                                            <td colspan="8" style="vertical-align:top;">
														<div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div>
	                                            </td>
	                                        </tr>
	                                    </tfoot>
											</c:when>
											<c:otherwise>
												<tr class="main_info">
													<td colspan="6" class="center">没有相关数据</td>
												</tr>
											</c:otherwise>
										</c:choose>
									</tbody>
								</table>
								 <button type="button" onclick="window.location.href='/menu/toAdd.do?MENU_ID=${MENU_ID}'" class="btn btn-w-m btn-success">添加</button>
								 <button type="button" onclick="window.location.href='/menu/list?MENU_ID=${pd.PARENT_ID}'" class="btn btn-w-m btn-danger">返回</button>
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
		   function del(menu_id,parent_id){
			   if(confirm("确定删除菜单")){
				   Ajax.request("/menu/delete",{"data":{"MENU_ID":menu_id},"success":function(item){
			          	 if(item.result =="200"){
			          		 window.location.href="/menu/list?MENU_ID="+parent_id;
			          	 }else{
			          		 alert(item.msg);
			          	 }
			           }});
			   }
		   }
	</script>
</body>
</html>