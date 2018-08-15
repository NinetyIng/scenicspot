<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh">
<head>
<base href="<%=basePath%>">
<!-- 下拉框 -->
<!-- jsp文件头和头部 -->
		<%@ include file="../common/top.jsp"%>
<!-- 日期框 -->
</head>
<body class="no-skin">

	<!-- /section:basics/navbar.layout -->
	<div class="main-container" id="main-container">
		<!-- /section:basics/sidebar -->
		<div class="main-content">
			<div class="main-content-inner">
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							
						<!-- 检索  -->
						<form action="/sys/content/list.do" method="post" name="Form" id="Form">
						<div class="hr-line-dashed" style="margin: 10px 0;"></div>
						<table style="margin-top:5px;margin-left:20px;">
							<tr>
								<td>
									<div class="nav-search">
										<span class="input-icon">
											<input type="text" placeholder="文章标题" class="form-control" id="nav-search-input" autocomplete="off" name="keywords" value="${pd.keywords }" placeholder="这里输入关键词"/>
										</span>
									</div>
								</td>
								<td style="vertical-align:top;padding-left:2px;">
								 	<select class="chosen-select form-control" name="CATEGORY_CODE" id="CATEGORY_ID" data-placeholder="资讯栏目" style="vertical-align:top;width: 150px;">
									 	<option value="">请选择栏目</option>
								 		<c:forEach items="${categoryList}" var="item">
											<option value="${item.CATEGORY_CODE}" <c:if test="${pd.CATEGORY_CODE eq item.CATEGORY_CODE }">selected</c:if>>${item.CATEGORY_NAME }</option>
									    </c:forEach>
								  	</select>
								</td>
								<td style="vertical-align:top;padding-left:2px;">
								 	<select class="chosen-select form-control" name="STATE" id="STATE" data-placeholder="状态" style="vertical-align:top;width: 150px;">
									 	<option value="">请选择状态</option>
									 	<option value="0" <c:if test="${pd.STATE eq '0'}">selected</c:if>>草稿</option>
									 	<option value="1" <c:if test="${pd.STATE eq '1'}">selected</c:if>>发布上线</option>
									 	<option value="2" <c:if test="${pd.STATE eq '2'}">selected</c:if>>等待发布</option>
								  	</select>
								</td>
								<td style="vertical-align:top;padding-left:2px;">
								 	<select class="chosen-select form-control" name="ISHOT" id="ISHOT" data-placeholder="是否头条" style="vertical-align:top;width: 150px;">
									 	<option value="">是否头条</option>
									 	<option value="0" <c:if test="${pd.ISHOT eq '0'}">selected</c:if>>否</option>
									 	<option value="1" <c:if test="${pd.ISHOT eq '1'}">selected</c:if>>是</option>
								  	</select>
								</td>
								<td style="vertical-align:top;padding-left:2px;">
								 	<select class="chosen-select form-control" name="CLICKS" id="CLICKS" data-placeholder="点击量" style="vertical-align:top;width: 150px;">
									 	<option value="">点击量</option>
									 	<option value="0" <c:if test="${pd.CLICKS eq '0'}">selected</c:if>>降序</option>
									 	<option value="1" <c:if test="${pd.CLICKS eq '1'}">selected</c:if>>升序</option>
								  	</select>
								</td>
								<c:if test="${QX.cha == 1 }">
								<td style="vertical-align:top;padding-left:2px">
								  <button class="btn btn-primary" type="button" onclick="tosearch();">
													<i class="fa fa-search"></i>&nbsp;查询&nbsp;
												</button>
								</td>
								</c:if>
							</tr>
						</table>
						<!-- 检索  -->
					    <div class="hr-line-dashed" style="margin: 10px 0;"></div>
						<table id="simple-table" class="table table-striped table-bordered table-hover" style="margin-top:5px;">	
							<thead>
								<tr>
									<th class="center" style="width:100px">所属栏目</th>
									<th class="center">标题</th>
									<th class="center" style="width:100px">头条/焦点</th>
									<th class="center" style="width:150px">Tag标签</th>
									<th class="center" style="width:130px">固顶|日期</th>
									<th class="center" style="width:80px">创建人</th>
									<th class="center" style="width:170px">上传时间</th>
									<th class="center" style="width:80px">点击量</th>
									<th class="center" style="width:80px">列表模式</th>
									<th class="center" style="width:80px">文章类型</th>
									<th class="center" style="width:80px">状态</th>
									<th class="center" style="width:130px">操作</th>
								</tr>
							</thead>
													
							<tbody>
							<!-- 开始循环 -->	
							<c:choose>
								<c:when test="${not empty varList}">
									<c:if test="${QX.cha == 1 }">
									<c:forEach items="${varList}" var="var" varStatus="vs">
										<tr>
											<td class='left'>${var.CATEGORY_NAME}</td>
											<td class='left'>
												<c:if test="${var.SUBJECT_NAME!=''}">
													<span class="label label-sm label-pink arrowed-right">${var.SUBJECT_NAME}</span>
												</c:if>${var.TITLE}</td>
												
											<td class="center">
												<c:if test="${var.ISHOT}">
													<span class="badge badge-info">头条</span>
												</c:if>
												<c:if test="${var.ISFOCUS}">
													<span class="badge badge-info">焦点</span>
												</c:if>
											</td>	
											<td class='left'>${var.TAGS}</td>
											<td class='center'>
												<c:if test="${var.TOPLV ne '0'}">
													<span class="badge badge-danger">固顶</span>|${var.TOPLVDATE}
												</c:if>
												<c:if test="${var.TOPLV eq '0'}">
													<span class="badge badge-warning">不固顶</span>
												</c:if>
											</td>
											<td class='left'>${var.CREATOR}</td>
											<td class='center'><fmt:formatDate value="${var.CREATETIME}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
											<td class='center'>${var.CLICKS}</td>
											<td class='center'>
												<c:if test="${empty var.MODEL_TYPE || var.MODEL_TYPE eq '0' }">
													<span class="badge badge-info">单图模式</span>
												</c:if>
												<c:if test="${var.MODEL_TYPE eq '1' }">
													<span class="badge badge-info">多图模式</span>
												</c:if>
											</td>
											<td class='center'>
												<c:if test="${empty var.CTYPE || var.CTYPE eq '0' }">
													<span class="badge badge-info">文章新闻</span>
												</c:if>
												<c:if test="${var.CTYPE eq '1' }">
													<span class="badge badge-info">专题广告</span>
												</c:if>
											</td>
											<td class='center'>
												<c:if test="${var.STATE eq '-1'}">
													<span class="badge">暂存草稿</span>
												</c:if>
												<c:if test="${var.STATE eq '1'}">
													<span class="badge badge-success">发布上线</span>
												</c:if>
												<c:if test="${var.STATE eq '2'}">
													<span class="badge badge-warning">等待发布</span>
												</c:if>
												<c:if test="${var.STATE eq '4'}">
													<span class="badge btn-danger">发布驳回</span>
												</c:if>
											</td>
											<td class="center">
												<c:if test="${QX.edit != 1 && QX.del != 1 }">
												<span class="label label-large label-grey arrowed-in-right arrowed-in"><i class="ace-icon fa fa-lock" title="无权限"></i></span>
												</c:if>
												<div class="hidden-sm hidden-xs btn-group">
													<c:if test="${QX.edit==1}">
													 <a class="btn btn-xs btn-light"> 
														<i class="glyphicon glyphicon-link" id="copy" title="复制链接到剪贴板" data-clipboard-text="m.aiwzd.cn/wx/news/detail?CONTENT_ID=${var.CONTENT_ID}"></i>
													</a>
														<a href="javascript:edit('${var.CONTENT_ID}');" class="btn btn-primary btn-sm" title="编辑">
                           										<i class="fa fa-pencil"></i>
                       									</a>
													</c:if>
													<c:if test="${QX.del==1}">
													        <c:if test="${var.STATE eq 1}">
															<a href="javascript:void(0);" onclick="del('${var.CONTENT_ID}','2');" class="btn btn-warning btn-sm" title="删除">
                           										<i class="fa fa-trash"></i>
                       										</a>
                       										</c:if>
                       										<c:if test="${var.STATE eq 2}">
															<a href="javascript:void(0);" onclick="del('${var.CONTENT_ID}','0');" class="btn btn-warning btn-sm" title="删除">
                           										<i class="fa fa-trash"></i>
                       										</a>
                       										</c:if>
                       										<c:if test="${var.STATE eq 0}">
															<a href="javascript:void(0);" onclick="del('${var.CONTENT_ID}','20');" class="btn btn-warning btn-sm" title="删除">
                           										<i class="fa fa-trash"></i>
                       										</a>
                       										</c:if>
													</c:if>
													<input type="hidden" id="#foo${vs.index+1}" value="${var.LINK}">
												</div>
												<div class="hidden-md hidden-lg">
													<div class="inline pos-rel">
														<button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown" data-position="auto" >
															<i class="ace-icon fa fa-cog icon-only bigger-110"></i>
														</button>
														<ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
															<c:if test="${QX.edit == 1 }">
															<li>
																<a style="cursor:pointer;" href="javascript:edit('${var.CONTENT_ID}');"  class="tooltip-success" data-rel="tooltip" title="编辑">
																	<span class="green">
																		<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
																	</span>
																</a>
															</li>
															<%-- </c:if> --%>
															</c:if>
															<c:if test="${QX.del == 1 }">
															<li>
															  <c:if test="${var.STATE eq 1}">
																<a style="cursor:pointer;"  onclick="del('${var.CONTENT_ID}','2');" class="tooltip-error" data-rel="tooltip" title="删除">
																	<span class="red">
																		<i class="ace-icon fa fa-trash-o bigger-120"></i>
																	</span>
																</a>
																</c:if>
																  <c:if test="${var.STATE eq 2}">
																<a style="cursor:pointer;"  onclick="del('${var.CONTENT_ID}','0');" class="tooltip-error" data-rel="tooltip" title="删除">
																	<span class="red">
																		<i class="ace-icon fa fa-trash-o bigger-120"></i>
																	</span>
																</a>
																</c:if>
																  <c:if test="${var.STATE eq 0}">
																<a style="cursor:pointer;"  onclick="del('${var.CONTENT_ID}','20');" class="tooltip-error" data-rel="tooltip" title="删除">
																	<span class="red">
																		<i class="ace-icon fa fa-trash-o bigger-120"></i>
																	</span>
																</a>
																</c:if>
															</li>
															</c:if>
														</ul>
													</div>
												</div>
											</td>
										</tr>
									</c:forEach>
									</c:if>
									<c:if test="${QX.cha == 0 }">
										<tr>
											<td colspan="100" class="center">您无权查看</td>
										</tr>
									</c:if>
								</c:when>
								<c:otherwise>
									<tr class="main_info">
										<td colspan="100" class="center" >没有相关数据</td>
									</tr>
								</c:otherwise>
							</c:choose>
							</tbody>
						</table>
						<div class="page-header position-relative">
						<table style="width:100%;">
							<tr>
								<td style="vertical-align:top;">
									<c:if test="${QX.add == 1 }">
									<a class="btn btn-sm btn-success" onclick="edit();">新增</a>
									</c:if>
									<c:if test="${QX.del == 1 }">
									<a class="btn btn-sm btn-danger" onclick="makeAll('确定要删除选中的数据吗?');" title="批量删除" ><i class='ace-icon fa fa-trash-o bigger-120'></i></a>
									</c:if>
								</td>
								<td style="vertical-align:top;"><div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div></td>
							</tr>
						</table>
						</div>
						</form>
					
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.page-content -->
			</div>
		</div>
		<!-- /.main-content -->

		<!-- 返回顶部 -->
		<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
			<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
		</a>

	</div>
	<!-- 页面底部js¨ -->
	<%@ include file="../common/foot.jsp"%>
	<script  src="/assets/js/plugins/clipboard/dist/clipboard.min.js"></script>
	<!-- 删除时确认窗口 -->
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
		//检索
		function tosearch(){
			$("#Form").submit();
		}
		//删除
		function del(Id,status){
			bootbox.confirm('确定要删除吗?只有是"发布上线"状态的资讯会被删除为等待发布,"等待发布"的资讯会被删除。', function(result) {
				if(result) {
					top.jzts();
					var url = "<%=basePath%>/sys/content/delete.do?CONTENT_ID="+Id+"&STATE="+status+"&tm="+new Date().getTime();
					$.get(url,function(data){
						nextPage('${page.currentPage}');
					});
				}
			});
		}
		function edit(contentId){
			var title = "添加文章";
			var url = "<%=basePath%>sys/content/editPage.do";
			if(contentId){
				title = "编辑文章";
				url = '<%=basePath%>sys/content/editPage.do?CONTENT_ID='+contentId;
			}
			var index = layer.open({
				  type: 2,
				  title: '<font color="gray" size="3px"><strong>'+title+'</strong></font>',
				  content: url,
				  area: ['300px', '195px'],
				  maxmin: true
				});
			layer.full(index);
		}
		//批量操作
		function makeAll(msg){
			bootbox.confirm(msg, function(result) {
				if(result) {
					var str = '';
					for(var i=0;i < document.getElementsByName('ids').length;i++){
					  if(document.getElementsByName('ids')[i].checked){
					  	if(str=='') str += document.getElementsByName('ids')[i].value;
					  	else str += ',' + document.getElementsByName('ids')[i].value;
					  }
					}
					if(str==''){
						bootbox.dialog({
							message: "<span class='bigger-110'>您没有选择任何内容!</span>",
							buttons: 			
							{ "button":{ "label":"确定", "className":"btn-sm btn-success"}}
						});
						$("#zcheckbox").tips({
							side:1,
				            msg:'点这里全选',
				            bg:'#AE81FF',
				            time:8
				        });
						return;
					}else{
						if(msg == '确定要删除选中的数据吗?'){
							top.jzts();
							$.ajax({
								type: "POST",
								url: '<%=basePath%>content/deleteAll.do?tm='+new Date().getTime(),
						    	data: {DATA_IDS:str},
								dataType:'json',
								//beforeSend: validateData,
								cache: false,
								success: function(data){
									 $.each(data.list, function(i, list){
											nextPage('${page.currentPage}');
									 });
								}
							});
						}
					}
				}
			});
		};
	
	</script>


</body>
</html>