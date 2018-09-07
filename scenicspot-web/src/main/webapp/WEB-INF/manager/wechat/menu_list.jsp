<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
</head>
<body>
 <div class="wrapper wrapper-content animated fadeInUp">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox">
					<div class="ibox-title">
						<h5>微信菜单列表</h5>
						<div class="ibox-tools">
							<a href="/sys/wechat/edit_menu.do" class="btn btn-primary btn-xs"><i class="fa fa-plus"></i>&nbsp;新增</a>
							<a class="btn btn-primary btn-xs" href="javascript:void(0)" onclick="releaseMenu()">发布到公众号</a>
						</div>
					</div>
					<div class="ibox-content">
						<div class="dd" id="nestable">
                            <ol class="dd-list">
                              <c:choose>
								<c:when test="${not empty menus}">
									<c:forEach items="${menus}" var="rootMenu">
										 <li class="dd-item" data-id="1">
		                                    <div class="dd-handle">
		                                    	<span class="pull-right">
													<a style="cursor:pointer;" class="green" href="/sys/wechat/edit_menu.do?id=${rootMenu.id}" title="编辑">
														<i class="ace-icon fa fa-pencil bigger-130"></i>
													</a>
													&nbsp;
													<a style="cursor:pointer;" class="red" onclick="delMenu('${rootMenu.id}')" title="删除">
														<i class="ace-icon fa fa-trash-o bigger-130"></i>
													</a>
												</span>
		                                        <span class="label label-info">
		                                        <c:if test="${rootMenu.menu_type==2}"><i class="fa fa-external-link"></i></c:if>
		                                        <c:if test="${rootMenu.menu_type==1}"><i class="fa fa-file-image-o"></i></c:if>
		                                        <c:if test="${rootMenu.menu_type==0}"><i class="fa fa-comments"></i></c:if>
		                                        </span> ${rootMenu.menu_name }
		                                    </div>
		                                    <c:if test="${not empty rootMenu.subMenu}">
			                                    <ol class="dd-list">
			                                    	<c:forEach items="${rootMenu.subMenu}" var="subMenu">
				                                        <li class="dd-item" data-id="2">
				                                            <div class="dd-handle">
				                                                <span class="pull-right">
					                                                <a style="cursor:pointer;" class="green" href="/sys/wechat/edit_menu.do?id=${subMenu.id}" title="编辑">
																		<i class="ace-icon fa fa-pencil bigger-130"></i>
																	</a>
																	&nbsp;
					                                                <a style="cursor:pointer;" class="red" onclick="delMenu('${subMenu.id}')" title="删除" OnClick="return confirm('确认删除吗？')">
																		<i class="ace-icon fa fa-trash-o bigger-130"></i>
																	</a>
																</span>
				                                                <span class="label label-info">
																	 <c:if test="${subMenu.menu_type==2}"><i class="fa fa-external-link"></i></c:if>
							                                        <c:if test="${subMenu.menu_type==1}"><i class="fa fa-file-image-o"></i></c:if>
							                                        <c:if test="${subMenu.menu_type==0}"><i class="fa fa-comments"></i></c:if>
																</span> ${subMenu.menu_name }
				                                            </div>
				                                        </li>
			                                        </c:forEach>
			                                    </ol>
		                                    </c:if>
		                                </li>
									</c:forEach>
								</c:when>
							</c:choose>
                            </ol>
                        </div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- basic scripts -->
	<!-- 页面底部js¨ -->
	<%@ include file="../common/foot.jsp"%>
	<script src="<%=basePath%>statics/js/ace/jquery.nestable.js"></script>
	<script src="<%=basePath%>statics/js/layer/layer.js"></script>
	<script type="text/javascript">
		function init(){
			$('.dd').nestable();	
		}
		init();
		
		function releaseMenu(){
			app.post(null,"/sys/wechat/releaseMenu",onAddResult,onAddFault);
		}
		function onAddResult(data){
			var str = JSON.stringify(data);
			layer.msg(data.msg);
			if(data.result == '200'){
				setTimeout('location.reload()',1000);
			}
		}
		function onAddFault(){
			layer.msg(data.msg);
		}
		function delMenu(id){
			var obj={};
			obj.id=id;
			app.post(obj,"/sys/wechat/delMenu",onAddResult,onAddFault);
		}
	</script>
</body>
</html>