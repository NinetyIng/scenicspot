<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../common/top.jsp"%>
	<style type="text/css">
	
	
	
	</style>
</head>
<body>
 <div class="wrapper wrapper-content animated fadeInUp">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox">
					<div class="ibox-title">
						<h5>树状列表</h5>
						<div class="ibox-tools">
						<button onclick="test()" class="btn btn-primary btn-xs"><i class="fa fa-plus"></i>&nbsp;表格</button>
						</div>
					</div>
					<div class="ibox-content">
						<form action="lot/list.do" method="post" name="Form" id="Form">
							<div class="project-list">
 								<table id="simple-table" class="center table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th>商品名称</th>
											<th>商品编号</th>
											<th class="center" style="width: 85px">操作</th>
										</tr>
									</thead>

									<tbody class="tree-grid">
										<tr data-pid="0" data-id="150">
											<td>0:内蒙古</td>
											<td>150</td>
											<td class="center">
												<a class="btn btn-primary btn-sm" title="编辑">
               										<i class="fa fa-pencil"></i>
           										</a>
           										<a  class="btn btn-warning btn-sm" title="删除">
               										<i class="fa fa-trash"></i>
           										</a>
           									</td>
										</tr>
										<tr data-pid="800" data-id="823">
											<td>2:乌拉特前旗</td>
											<td>823</td>
											<td class="center">
												<a class="btn btn-primary btn-sm" title="编辑">
               										<i class="fa fa-pencil"></i>
           										</a>
           										<a  class="btn btn-warning btn-sm" title="删除">
               										<i class="fa fa-trash"></i>
           										</a>
           									</td>
										</tr>
										<tr data-pid="150" data-id="121">
											<td>1:呼和浩特</td>
											<td>121</td>
											<td class="center">
												<a class="btn btn-primary btn-sm" title="编辑">
               										<i class="fa fa-pencil"></i>
           										</a>
           										<a  class="btn btn-warning btn-sm" title="删除">
               										<i class="fa fa-trash"></i>
           										</a>
           									</td>
										</tr>
										
										
										<tr data-pid="150" data-id="800">
											<td>1:巴彦那尔</td>
											<td>800</td>
											<td class="center">
												<a class="btn btn-primary btn-sm" title="编辑">
               										<i class="fa fa-pencil"></i>
           										</a>
           										<a  class="btn btn-warning btn-sm" title="删除">
               										<i class="fa fa-trash"></i>
           										</a>
           									</td>
										</tr>
										<tr data-pid="500" data-id="528">
											<td>2:镶黄旗</td>
											<td>528</td>
											<td class="center">
												<a class="btn btn-primary btn-sm" title="编辑">
               										<i class="fa fa-pencil"></i>
           										</a>
           										<a  class="btn btn-warning btn-sm" title="删除">
               										<i class="fa fa-trash"></i>
           										</a>
           									</td>
										</tr>
										
										
										<tr data-pid="800" data-id="826">
											<td>2:杭锦后旗</td>
											<td>826</td>
											<td class="center">
												<a class="btn btn-primary btn-sm" title="编辑">
               										<i class="fa fa-pencil"></i>
           										</a>
           										<a  class="btn btn-warning btn-sm" title="删除">
               										<i class="fa fa-trash"></i>
           										</a>
           									</td>
										</tr>
										<tr data-pid="150" data-id="500">
											<td>1:锡林郭勒盟</td>
											<td>500</td>
											<td class="center">
												<a class="btn btn-primary btn-sm" title="编辑">
               										<i class="fa fa-pencil"></i>
           										</a>
           										<a  class="btn btn-warning btn-sm" title="删除">
               										<i class="fa fa-trash"></i>
           										</a>
           									</td>
										</tr>
										
										<tr data-pid="500" data-id="529">
											<td>2:正镶白旗</td>
											<td>529</td>
											<td class="center">
												<a class="btn btn-primary btn-sm" title="编辑">
               										<i class="fa fa-pencil"></i>
           										</a>
           										<a  class="btn btn-warning btn-sm" title="删除">
               										<i class="fa fa-trash"></i>
           										</a>
           									</td>
										</tr>
										
										<tr data-pid="800" data-id="802">
											<td>2:临河区</td>
											<td>529</td>
											<td class="center">
												<a class="btn btn-primary btn-sm" title="编辑">
               										<i class="fa fa-pencil"></i>
           										</a>
           										<a  class="btn btn-warning btn-sm" title="删除">
               										<i class="fa fa-trash"></i>
           										</a>
           									</td>
										</tr>
										
										
									</tbody>
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
	<script src="/statics/plugin/treeGrid.js"></script>
	<script type="text/javascript">
		function test(){
			swal({title: "Are you sure?",
				text: "You will not be able to recover this imaginary file!",
				type: "warning",   
				showCancelButton: true,   
				confirmButtonColor: "#DD6B55",   
				confirmButtonText: "Yes, delete it!",   
				cancelButtonText: "No, cancel plx!",   
				closeOnConfirm: true,   
				closeOnCancel: false }, 
				function(isConfirm){   
					if (isConfirm) {     
						swal("Deleted!", "Your imaginary file has been deleted.", "success");
					} else {     
						swal("Cancelled", "Your imaginary file is safe :)", "error");   
					} 
				});
		}
	</script>
</body>
</html>