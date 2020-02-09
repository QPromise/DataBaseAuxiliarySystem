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
				<h5>数据录入</h5>
			</div>

			<!-- 内容开始 -->
			<div class="ibox-content">
				<form id="fileForm" class="form-horizontal" method="post"
					<%-- 					action="${pageContext.request.contextPath}/DataController/uploadFile.action" --%>
					enctype="multipart/form-data">
					<div class="row">
						<div class="form-group">
							<div class="col-sm-12">
								<label class="col-sm-1 control-label">选择文件:</label>
								<div style="padding-left: 1px;" class="col-sm-2">
									<input id="file" type="file" name="file" width="120px">
								</div>
								<div style="padding-left: 3px;" class="col-sm-1">
									<input id="btn_save" type="button" class="btn btn-primary"
										value="上传">
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
		<!-- 第一个row结束 -->
		<div class="row">
			<div class="ibox-title">
				<h5>导入数据校验</h5>
			</div>
			<div class="ibox-content">
				<table id="table_checkout" class="table table-hover">
				</table>
			</div>
		</div>
		<div class="modal inmodal fade" id="modal_wait" tabindex="-1"
			role="dialog" aria-hidden="true" data-backdrop="static"
			data-keyboard="false">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-body">
						<div class="ibox-content">
							<span style="text-align: center;display:block;">数据加载中，请稍后。</span>
							<div class="spiner-example">
								<div class="sk-spinner sk-spinner-wave">
									<div class="sk-rect1"></div>
									<div class="sk-rect2"></div>
									<div class="sk-rect3"></div>
									<div class="sk-rect4"></div>
									<div class="sk-rect5"></div>
								</div>
							</div>
						</div>
					</div>
					<!-- /.modal-body -->
				</div>
				<!-- /.modal-content -->
			</div>
		</div>
	</div>


	<script type="text/javascript">
		$('#btn_save').click(function() {
			//1.获取
			var file = new FormData($('#fileForm')[0]);
			$.ajax({
				dataType : 'json',
				// 				async : false,
				cache : false,
				contentType : false,
				processData : false,
				type : 'POST',
				url : '${pageContext.request.contextPath}/DataController/uploadFile.action',
				data : file,
				beforeSend : function() {
					$('#modal_wait').modal('show');
				},
				success : function(data) {
					if (data.error != null) {
						swal({
							title : "数据重复录入！",
							text : "重复年份：" + data.error,
							type : "warning",
							icon : "error"
						});
					}
					if (data.msg != null) {
						$("#table_checkout").bootstrapTable('destroy');
						//填充table数据
						$('#table_checkout').bootstrapTable({
							data : data.msg,
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
									return index + 1; //返回行号  
								}
							},
								{
									field : 'designation',
									title : '指标名称'
								},
								{
									field : 'year',
									title : '年份'
								},
								{
									field : 'message',
									title : '增长率状况'
								}
							],
						});
					}
	
				},
				complete : function() { //ajax得到请求结果后的操作  
					$('#modal_wait').modal('hide');
				},
			});
		});
	</script>
</body>

</html>