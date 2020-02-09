<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<link rel="shortcut icon" href="favicon.ico">
<link
	href="${pageContext.request.contextPath}/css/style.min.css?v=4.0. 0"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/css/bootstrap.min.css?v=3.3.5"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/css/plugins/sweetalert/sweetalert.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/css/font-awesome.min.css?v=4.4.0"
	rel="stylesheet">

<link href="${pageContext.request.contextPath}/css/animate.min.css"
	rel="stylesheet">

<!-- js文件 -->
<script
	src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script
	src="${pageContext.request.contextPath}/js/plugins/sweetalert/sweetalert.min.js"></script>
<script
	src="${pageContext.request.contextPath}/js/bootstrap.min.js?v=3.3.5"></script>

<!-- select2 -->
<link href="${pageContext.request.contextPath}/css/select2.css"
	rel="stylesheet" />
<script src="${pageContext.request.contextPath}/js/select2.js"></script>
<script src="${pageContext.request.contextPath}/js/zh-CN.js"></script>

<!-- 弹框  -->
<link
	href="${pageContext.request.contextPath}/css/plugins/sweetalert/sweetalert.css"
	rel="stylesheet">
<script
	src="${pageContext.request.contextPath}/js/plugins/sweetalert/sweetalert.min.js"></script>

<!-- 表单验证 -->
<link
	href="${pageContext.request.contextPath}/css/Validator/bootstrapValidator.min.css" />
<script
	src="${pageContext.request.contextPath}/js/Validator/bootstrapValidator.min.js"></script>

<!-- bootstrap table -->
<link
	href="${pageContext.request.contextPath}/css/plugins/bootstrap-table/bootstrap-table.min.css"
	rel="stylesheet">
<script
	src="${pageContext.request.contextPath}/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>

<script
	src="${pageContext.request.contextPath}/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
</head>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">

		<div class="row">
			<!-- 标题 -->
			<div class="ibox-title">
				<h5>权重查询</h5>
			</div>

			<!-- 内容开始 -->
			<div class="ibox-content">
				<form id="queryForm" class="form-horizontal" method="post">
					<div class="row">
						<div class="form-group">
							<div class="col-sm-12">
								<!-- 业务员  可编辑下拉框-->
								<label class="col-sm-1 control-label">指标级别：</label>
								<div style="padding-left: 1px;" class="col-sm-2">
									<select id="sel_weight_index"
										class="form-control js-example-basic-single">
										<option value="1" selected>一级指标</option>
										<option value="2" selected>二级指标</option>
										<option value="3" selected>三级指标</option>
										<option value="4" selected>四级指标</option>
									</select>
								</div>
								<!-- 查询按钮 -->
								<button id="btn_query" type="button"
									onclick="queryAccountRecord()" class="btn btn-primary">查询</button>
							</div>
						</div>
					</div>
				</form>
			</div>

		</div>
		<!-- 第一个row结束 -->

		<!-- 第二个row开始 -->
		<div class="row">
			<!-- 标题 -->
			<div class="ibox-title">
				<h5>权重信息</h5>
			</div>

			<!-- 内容开始 -->
			<div class="ibox-content">
				<table id="table_weight" class="table table-hover">
					<thead>
					</thead>
					<tbody>
					</tbody>
					<tfoot>
					</tfoot>
				</table>
			</div>
		</div>
		<!-- 第二个row结束 -->


		<!-- 第三个row开始 -->
		<div class="row">
			<!-- 模态弹框显示发货详情 -->
			<div class="modal inmodal fade" id="modal_index_weight" tabindex="-1"
				role="dialog" aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-body">
							<div class="ibox-title">
								<h5>指标权重信息</h5>
								<!-- 订单状态追加文本 -->
								<label class="col-sm-2 control-label" id="audit_status_text"></label>
							</div>
							<div class="ibox-content">
								<form id="table_reduce_account_base_info"
									class="form-horizontal" method="post">
									<div class="row">
										<div class="form-group">
											<div class="col-sm-12">
												<label class="col-sm-2 control-label">指标名称：</label>
												<div class="col-sm-3">
													<input type="hidden" class="form-control"
														name="index_weight_id" id="index_weight_id"> <input
														type="text" disabled="disabled" class="form-control"
														name="designation" id="designation">
												</div>
												<label class="col-sm-2 control-label">权重值：</label>
												<div class="col-sm-3">
													<input type="text" class="form-control" name="weightValue"
														id="weightValue">
												</div>
											</div>
										</div>
									</div>
								</form>
								<div class="modal-footer">
									<button id="btn_update" type="button" class="btn btn-primary"
										data-dismiss="modal">修改</button>
									<button type="button" class="btn btn-danger"
										data-dismiss="modal">关闭</button>
								</div>
							</div>
						</div>
						<!-- /.modal-body -->
					</div>
					<!-- /.modal-content -->
				</div>
			</div>
		</div>
		<!-- 第三个row结束 -->
	</div>

	<script type="text/javascript">
		//初始化所有的select2
		$(document).ready(function() {});
	
	
		//查询
		function queryAccountRecord() {
			//1.获取查询的指标级别
			var id = $("#sel_weight_index option:selected").val();
	
			index = id; //用于记录全局指标
	
			//2.ajax获取数据
			$.ajax({
				dataType : 'json',
				type : 'POST',
				url : '${pageContext.request.contextPath}/CountyDataController/listWeightValueByIndex.action',
				data : "id=" + id,
				success : function(data) {
	
					//3.处理json数据，将数据填充到table
					//bootstrap-table 
					//先销毁表格再填充数据，避免第二次查询无法显示
					$("#table_weight").bootstrapTable('destroy');
					//填充table数据
					$('#table_weight').bootstrapTable({
						data : data,
						dataType : "json",
						//是否显示行间隔色  
						striped : true,
						//是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）       
						cache : false,
						//是否显示分页（*）    
						pagination : true,
						//是否启用排序    
						sortable : false,
						//排序方式   
						sortOrder : "desc",
						//初始化加载第一页，默认第一页  
						//我设置了这一项，但是貌似没起作用，而且我这默认是0,- -  
						pageNumber : 1,
						//每页的记录行数（*）     
						pageSize : 10,
						search : false, //是否启用查询 
						//可供选择的每页的行数（*）      
						pageList : [ 5, 10 ],
						//分页方式：client客户端分页，server服务端分页（*）  
						sidePagination : "client",
						//是否显示搜索 搜索input name= searchText 服务端获取该值查询即可  
						search : false,
						columns : [ {
							field : "index",
							title : "序号",
							align : "center",
							edit : false,
							formatter : function(value, row, index) {
								return row.index = index + 1; //返回行号  
							}
						},
							{
								field : 'id',
								title : '指标id',
								visible : false
							}, {
								field : 'designation',
								align : "center",
								title : '指标名称'
							}, {
								field : 'weightValue',
								align : "center",
								title : '指标权重'
							}, {
								title : '操作',
								align : "center",
								formatter : operateFormatter,
								events : operateEvents
							}
						],
					});
				//bootstrap table结束
				},
			});
		}
	
		function operateFormatter(value, row, index) {
			return [
				'<button type="button" class="IndexWeightDetail btn btn-primary  btn-sm">修改权重</button>',
			].join('');
		}
	
		window.operateEvents = {
			//value:当前单元格的值；row:当前行；index:当前行的索引
			'click .IndexWeightDetail' : function(e, value, row, index) {
	
				$("#index_weight_id").val(row.id);
				$("#designation").val(row.designation);
				$("#weightValue").val(row.weightValue);
	
				$('#modal_index_weight').modal('show');
			}
		};
	
		//指标，用于记录本次查询数据的指标级别
		var index;
	
		$("#btn_update").click(function() {
			//1.获取修改后的值
			var id = $("#index_weight_id").val();
			var weightValue = $("#weightValue").val();
	
			if (weightValue == '') {
				//alert("权重值不能为空");
				showSwal("权重值不能为空", "warning");
				return;
			}
	
			if (weightValue > 1) {
				//alert("权重值不合法");
				showSwal("权重值不合法", "warning");
				return;
			}
	
			//2.ajax获取数据
			$.ajax({
				dataType : 'json',
				type : 'POST',
				url : '${pageContext.request.contextPath}/CountyDataController/updateIndexWeightById.action',
				data : "index=" + index + "&id=" + id + "&weightValue=" + weightValue,
				success : function(data) {
					if (data == '修改成功') {
						showSwal(data, "success");
						queryAccountRecord();
					} else {
						showSwal(data, "error");
					}
				}
			});
		});
	
	
		function showSwal(data, type) {
			swal({
				title : data,
				type : type,
			});
		}
	</script>

</body>
</html>