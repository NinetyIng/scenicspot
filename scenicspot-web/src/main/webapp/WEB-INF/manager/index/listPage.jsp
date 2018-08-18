<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../common/top.jsp"%>
	<style>
		.search-condition{
		}
	
	</style>
</head>
<body>
 <div class="wrapper wrapper-content animated fadeInUp">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox">
					<div class="ibox-title">
						<h5>商品列表</h5>
						<div class="ibox-tools">
							<a href="/test/editPage.do" class="btn btn-primary btn-xs"><i class="fa fa-plus"></i>&nbsp;新增</a>
						</div>
					</div>
					<div class="ibox-content">
						<form action="lot/list.do" method="post" name="Form" id="Form">
							<div class="search-condition row">
								<div class="col-md-2">
                                    <div class="input-group">
	    	                            <span class="input-group-addon">商品编号</span>
		                                <input type="text" class="form-control">
		                            </div>
                                </div>
								<div class="col-md-2">
                                    <div class="input-group">
	    	                            <span class="input-group-addon">商品编号</span>
		                                <input type="text" class="form-control">
		                            </div>
                                </div>
	                            <div class="col-md-2">
                                    <div class="input-group">
	    	                            <span class="input-group-addon">商品编号</span>
		                                <input type="text" class="form-control">
		                            </div>
                                </div>
                                <div class="col-md-2">
                                    <div class="input-group">
	    	                           <button class="btn btn-primary" type="button" onclick="testFun()">
													<i class="fa fa-search"></i>&nbsp;查询&nbsp;
												</button>
		                            </div>
                                </div>
							</div>
							<div class="hr-line-dashed" style="margin: 10px 0;"></div>
							<div class="project-list">
 								<table id="simple-table" class="center table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th>#</th>
											<th>商品名称</th>
											<th>商品编号</th>
											<th>商品规格</th>
											<th>售价</th>
											<th>赠送积分</th>
											<th>佣金</th>
											<th class="center" style="width: 85px">操作</th>
										</tr>
									</thead>

									<tbody>
										<!-- 开始循环 -->
													<tr>
														<td>123</td>
														<td>名称</td>
														<td>123456</td>
														<td>123</td>
														<td>50</td>
														<td>50</td>
														<td>123</td>
														<td class="center">
															<a href="/goods/edit.do?id=1" class="btn btn-primary btn-sm" title="编辑">
                           										<i class="fa fa-pencil"></i>
                       										</a>
                       										<a href="/goods/del?id=1" class="btn btn-warning btn-sm" title="删除">
                           										<i class="fa fa-trash"></i>
                       										</a>
                       									</td>
													</tr>
												<tr class="main_info">
													<td colspan="100" class="center">没有相关数据</td>
												</tr>
									</tbody>
										<tfoot>
	                                        <tr>
	                                            <td colspan="8">
	                                               	<div class="dataTables_paginate paging_bootstrap pull-right">
													</div>	
	                                            </td>
	                                        </tr>
	                                    </tfoot>
								</table>
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
</body>
</html>