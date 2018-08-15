<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../common/top.jsp"%>
	<link href="/assets/css/bootstrap-fileupload.css" rel="stylesheet">
	<link href="/assets/css/plugins/clockpicker/clockpicker.css" rel="stylesheet">
	<link href="/assets/css/plugins/summernote/summernote.css" rel="stylesheet">
	<link href="/assets/css/plugins/summernote/summernote-bs3.css" rel="stylesheet">
	<style type="text/css">
	.file-pick {
	    font-size: 18px;
	    background: #00b7ee;
	    border-radius: 3px;
	    line-height: 44px;
	    padding: 0 30px;
	    color: #fff;
	    display: inline-block;
	    margin: 0 auto 20px auto;
	    cursor: pointer;
	    box-shadow: 0 1px 1px rgba(0, 0, 0, 0.1);
	}
	</style>
	<style type="text/css">
	.date{
		width: 110px!important;border-right: none;padding-right: 6px;text-align: right;
	}
	.time{
		width: 110px!important;border-left: none;padding-left: 0px;
	}
	.input-group{
		padding-right: 0px!important;
	}
</style>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>
							<c:if test="${isCreate==0}">编辑</c:if>
							<c:if test="${isCreate==1}">新增</c:if>
							商品
						</h5>
					</div>
					<form action="goods/save" method="post"	class="form-horizontal" id="commentForm">
					<div class="ibox-content">
						<div class="panel blank-panel">
							<div class="tabs-left">
								<ul class="nav nav-tabs">
									<li class="active"><a data-toggle="tab"
										href="#tab-1" aria-expanded="true">
											基本信息</a></li>
									<li class=""><a data-toggle="tab"
										href="#tab-3" aria-expanded="true">其他信息</a>
									</li>
									<li class=""><a data-toggle="tab"
										href="#tab-2" aria-expanded="true">详细描述</a>
									</li>
									<li class=""><a data-toggle="tab"
										href="#tab-4" aria-expanded="true">产品相册</a>
									</li>
								</ul>
								<div class="tab-content ">
									
										<div id="tab-1" class="tab-pane active">
											<div class="panel-body">
												<div class="form-group">
													<label class="col-sm-2 control-label">商品名称：</label>
													<div class="col-sm-8">
														<input type="text" name="goods_name" id="goods_name"
															class="form-control" value="${dataModel.goods_name}"
															required />
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label">商品编号：</label>
													<div class="col-sm-8">
														<input type="text" name="goods_sn" id="goods_sn"
															class="form-control" value="${dataModel.goods_sn}" />
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label">关键词：</label>
													<div class="col-sm-8">
														<input type="text" name="goods_keywords"
															id="goods_keywords" class="form-control"
															value="${dataModel.goods_keywords}" />
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label">商品分类：</label>
													<div class="col-sm-8">
														<input type="text" name="category_id" id="category_id"
															class="form-control" value="${dataModel.category_id}" />
													</div>
												</div>

												<div class="form-group">
													<label class="col-sm-2 control-label">省市区选择：</label>
													<div class="col-sm-8">
														<div class="row">
															<div class="col-md-4">
																<select name="p" id="province" class="form-control" data-value="福建省">
																	<option>请选择省份</option>
																</select>
															</div>
															<div class="col-md-4">
																<select name="c" id="city" class="form-control" data-value="泉州市">
																	<option>请选择城市</option>
																</select>
															</div>
															<div class="col-md-4">
																<select name="a" id="area" class="form-control" data-value="惠安县">
																	<option>请选择区县</option>
																</select>
															</div>
														</div>
													</div>
												</div>
												
												
												
												<div class="form-group">
													<label class="col-sm-2 control-label">时间区间：</label>
													<div class="col-sm-8">
														<div class="row">
															<div class="col-md-8">
																<div class="input-group">
																	<div class="input-group date" style="float: left;">
																		<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
																		<input type="text" class="form-control date" style="width: 110px;"  name="startDate" id="startDate" value="${dataModel.startDate}">
																	</div>
																	<div class="input-group clockpicker" data-autoclose="true"  style="float: left;">
											                            <input type="text" class="form-control time" name="startTime" id="startTime" value="${dataModel.startTime}" style="width: 110px;">
											                        </div>
																	<div class="input-group" style="float: left;">
																		<span class="input-group-addon" style="height: 34px;">到</span>
																	</div>
																	<div class="input-group date" style="float: left;">
																		<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
																		<input type="text" class="form-control date" style="width: 110px;"  name="endDate" id="endDate" value="${dataModel.endDate}">
																	</div>
																	<div class="input-group clockpicker" data-autoclose="true"  style="float: left;">
											                            <input type="text" class="form-control time" name="endTime" id="endTime" value="${dataModel.endTime}" style="width: 110px;">
											                        </div>
																</div>
															</div>
														</div>
													</div>
												</div>
												
												
												
												

												<input type="hidden" name="brand_id" id="brand_id" value="0" />
												<div class="form-group">
													<label class="col-sm-2 control-label">商品价格：</label>
													<div class="col-sm-8">
														<div class="row">
															<div class="col-md-4">
																<input type="text" name="goods_saleprice"
																	id="goods_saleprice" class="form-control"
																	value="${dataModel.goods_saleprice}" /> <span
																	class="help-block m-b-none">本店售价</span>
															</div>
															<div class="col-md-4">
																<input type="text" name="goods_costprice"
																	id="goods_costprice" class="form-control"
																	value="${dataModel.goods_costprice}" /> <span
																	class="help-block m-b-none">成本价格</span>
															</div>
															<div class="col-md-4">
																<input type="text" name="goods_costprice"
																	id="goods_costprice" class="form-control"
																	value="${dataModel.goods_costprice}" /> <span
																	class="help-block m-b-none">市场售价</span>
															</div>
														</div>
													</div>
												</div>

												<div class="form-group">
													<label class="col-sm-2 control-label">代理佣金：</label>
													<div class="col-sm-8">
														<div class="row">
															<div class="col-md-4">
																<input type="text" name="first_agency_rab"
																	id="first_agency_rab" class="form-control"
																	value="${dataModel.first_agency_rab}" /> <span
																	class="help-block m-b-none">一级佣金</span>
															</div>
															<div class="col-sm-4">
																<input type="text" name="second_agency_rab"
																	id="second_agency_rab" class="form-control"
																	value="${dataModel.second_agency_rab}" /> <span
																	class="help-block m-b-none">二级佣金</span>
															</div>
															<div class="col-sm-4">
																<input type="text" name="third_agency_rab"
																	id="third_agency_rab" class="form-control"
																	value="${dataModel.third_agency_rab}" /> <span
																	class="help-block m-b-none">三级佣金</span>
															</div>
														</div>
													</div>
												</div>

												<div class="form-group">
													<label class="col-sm-2 control-label">时间选择：</label>
													<div class="col-sm-8">
														<input type="text" class="form-control" value="2012-05-15 21:05" id="datetimepicker" data-date-format="yyyy-mm-dd hh:ii">
													</div>
												</div>

												<div class="form-group">

													<label class="col-sm-2 control-label">列表图片：</label>

													<div class="col-sm-8">
														<div class="fileupload fileupload-new"
															data-provides="fileupload">
															<div class="fileupload-new thumbnail"
																style="max-height: 200px;">
																<img src=""><!-- <%=basePath %>UploadServlet?getthumb=${dataModel.goods_image} -->
															</div>
															<div
																class="fileupload-preview fileupload-exists thumbnail"
																style="max-height: 200px; line-height: 20px;"></div>
															<div>
																<span class="btn btn-default btn-file"><span
																	class="fileupload-new">选择文件</span><span
																	class="fileupload-exists">重选</span>
																	<input id="theFile" name="theFile" type="file"> </span> 
																	<a href="#" class="btn btn-default fileupload-exists" data-dismiss="fileupload">移除</a>
															</div>
														</div>
													</div>
												</div>

<div class="form-group">
											<div class="col-sm-4 col-sm-offset-3">
												<button class="btn btn-primary" type="button" onclick="testFun()">
													<i class="fa fa-check"></i>&nbsp;&nbsp;提 交&nbsp;&nbsp;
												</button>
												<button class="btn btn-warning" onclick="history.go(-1)">
													<i class="fa fa-close"></i>&nbsp;&nbsp;返 回&nbsp;&nbsp;
												</button>
											</div>
										</div>




											</div>
										</div>
										<div id="tab-2" class="tab-pane">
											<div class="panel-body">
												<div class="ibox-content no-padding">
													<div id="htmlEditor"></div>
												</div>
												<br>
												<div class="form-group">
											<div class="col-sm-4 col-sm-offset-3">
												<button class="btn btn-primary" type="button" onclick="testFun()">
													<i class="fa fa-check"></i>&nbsp;&nbsp;提 交&nbsp;&nbsp;
												</button>
												<button class="btn btn-warning" onclick="history.go(-1)">
													<i class="fa fa-close"></i>&nbsp;&nbsp;返 回&nbsp;&nbsp;
												</button>
											</div>
										</div>
											</div>
										</div>
										<div id="tab-3" class="tab-pane">
											<div class="panel-body">
												
												<div class="form-group">
													<label class="col-sm-2 control-label">商品规格：</label>
													<div class="col-sm-8">
														<div class="row">
															<div class="col-md-4">
																<input type="text" name="goods_spec" id="goods_spec"
																	class="form-control" value="${dataModel.goods_spec}" />
																<span class="help-block m-b-none">商品规格</span>
															</div>
															<div class="col-sm-4">
																<input type="text" name="goods_weight" id="goods_weight"
																	class="form-control" value="${dataModel.goods_weight}" />
																<span class="help-block m-b-none">商品重量（克）</span>
															</div>
															<div class="col-sm-4">
																<input type="text" name="goods_unit" id="goods_unit"
																	class="form-control" value="${dataModel.goods_unit}" />
																<span class="help-block m-b-none">计量单位</span>
															</div>
														</div>
													</div>
												</div>
												
												<div class="form-group">
													<label class="col-sm-2 control-label">其他信息：</label>
													<div class="col-sm-8">
														<label class="checkbox-inline"> <input
															type="checkbox" name="goods_state" id="goods_state">立刻上架
														</label> <label class="checkbox-inline"> <input
															type="checkbox" name="is_virtual" id="is_virtual">虚拟商品
														</label> <label class="checkbox-inline"> <input
															type="checkbox" name="ship_free" id="ship_free">免运费
														</label> <label class="checkbox-inline"> <input
															type="checkbox" name="is_del" id="is_del">放入回收站
														</label>
													</div>
												</div>




												<div class="form-group">
													<label class="col-sm-2 control-label">销售类型：</label>
													<div class="col-sm-8">
														<label class="radio-inline"> <input type="radio"
															value="1"
															<c:if test="${dataModel.goods_sale_type==1}"> checked="checked"</c:if>
															name="goods_sale_type" id="goods_sale_type">普通商品
														</label> <label class="radio-inline"> <input type="radio"
															value="2"
															<c:if test="${dataModel.goods_sale_type==2}"> checked="checked"</c:if>
															name="goods_sale_type" id="goods_sale_type">竞拍商品
														</label> <label class="radio-inline"> <input type="radio"
															value="3"
															<c:if test="${dataModel.goods_sale_type==3}"> checked="checked"</c:if>
															name="goods_sale_type" id="goods_sale_type">夺宝商品
														</label>
													</div>
												</div>
												
												<div class="form-group">
													<label class="col-sm-2 control-label">分享描述：</label>
													<div class="col-sm-8">
														<input type="text" name="goods_desc_wx" id="goods_desc_wx"
															class="form-control" value="${dataModel.goods_desc_wx}" />
													</div>
												</div>
												
												<div class="form-group">
													<label class="col-sm-2 control-label">商品简述：</label>
													<div class="col-sm-8">
														<input type="text" name="goods_short_desc"
															id="goods_short_desc" class="form-control"
															value="${dataModel.goods_short_desc}" />
													</div>
												</div>
												<div class="form-group">
											<div class="col-sm-4 col-sm-offset-3">
												<button class="btn btn-primary" type="button" onclick="testFun()">
													<i class="fa fa-check"></i>&nbsp;&nbsp;提 交&nbsp;&nbsp;
												</button>
												<button class="btn btn-warning" onclick="history.go(-1)">
													<i class="fa fa-close"></i>&nbsp;&nbsp;返 回&nbsp;&nbsp;
												</button>
											</div>
										</div>
											</div>
										</div>
										<div id="tab-4" class="tab-pane">
											<div class="panel-body">
						                        <div class="row">
								                    <div class="col-sm-12">
														<div class="file-box">
								                            <div class="file">
								                                <a href="file_manager.html#">
								                                    <span class="corner"></span>
								
								                                    <div class="image">
								                                        <img alt="image" class="img-responsive" src="img/p1.jpg">
								                                    </div>
								                                    <div class="file-name">
								                                        Italy street.jpg
								                                        <br/>
								                                        <small>添加时间：2014-10-13</small>
								                                    </div>
								                                </a>
								                            </div>
								                        </div>
														
													</div>
							                    </div>
							                        
							            <div class="form-group">
											<div class="col-sm-4">
												<button class="btn btn-success btn-lg" type="button" id="test1">
													<i class="fa fa-upload"></i>&nbsp;&nbsp;上传图片&nbsp;&nbsp;
												</button>
											</div>
										</div>
							                        
											</div>
										</div>
										
										
												
								</div>
									

							</div>

						</div>
					</div>
					</form>
				</div>
			</div>
		</div>

	</div>
	<!-- basic scripts -->
	<!-- 页面底部js¨ -->
	<%@ include file="../common/foot.jsp"%>
	<script src="/assets/js/bootstrap-fileupload.js"></script>
	<script src="/statics/plugin/region.js"></script>
	<script src="/assets/js/plugins/clockpicker/clockpicker.js"></script>
	<script src="/assets/js/plugins/datapicker/bootstrap-datepicker.js"></script>
<!-- 自定义js -->
	<script src="/assets/js/plugins/summernote/summernote.min.js"></script>
    <script src="/assets/js/plugins/summernote/summernote-zh-CN.js"></script>
    <script>
    
    $(function(){
		$(".fileupload-exists").click(function(){
    		$(this).parents(".fileupload").children().eq(0).remove();
    	});
   });
    
    $('#test1').on('click', function(){
    	layer.open({
            type: 2,
            title: '上传图片',
            maxmin: true,
            shadeClose: true, //点击遮罩关闭层
            area : ['650px' , '400px'],
            content: "/uploadImgPage"
        });
    });
		$(document).ready(function() {
			$("#htmlEditor").summernote({
				lang : "zh-CN"
			})
		});
		var html="";
		function testFun(){
			html=$("#htmlEditor").code();
			alert(html);
		}
		function testFun1(){
			$("#htmlEditor").code(html);
		}
	</script>
	<script type="text/javascript">
		$(".clockpicker").clockpicker();
		$(".input-group.date").datepicker({
	        todayBtn: "linked",
	        keyboardNavigation: false,
	        forceParse: false,
	        calendarWeeks: true,
	        autoclose: true
	    });
		$('#datetimepicker').datetimepicker();
	</script>
</body>
</html>