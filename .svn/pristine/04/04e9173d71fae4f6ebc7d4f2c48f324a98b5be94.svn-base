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

<!--e-chart  -->
<script
	src="${pageContext.request.contextPath}/js/plugins/echarts/echarts-all.js"></script>

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
<%--导致bootstrap table分页不好用 
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/edittable/plus/table/bootstrap-table-edit.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/edittable/plus/bootstrap-select.js"></script> --%>
<style type="text/css">
td, th {
	text-align: center;
}
</style>
</head>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">

		<div class="row">
			<!-- 标题 -->
			<div class="ibox-title">
				<h5>查询</h5>
			</div>

			<!-- 内容开始 -->
			<div class="ibox-content">
				<form id="queryForm" class="form-horizontal" method="post">
					<div class="row">
						<div class="form-group">
							<div class="col-sm-12">
								<label style="padding-left: 1px;" class="col-sm-1 control-label">年份：</label>
								<div style="padding-left: 1px;" class="col-sm-1">
									<select name="year" id="sel_year"
										class="form-control js-example-basic-single">
										<option value="">2012</option>
										<option value="">2013</option>
										<option value="">2014</option>
									</select>
								</div>
								<label style="padding-left: 1px;" class="col-sm-1 control-label">省份1：</label>
								<div style="padding-left: 1px;" class="col-sm-1">
									<select name="provinceA" id="sel_a"
										class="form-control js-example-basic-single">
										<!-- 										<option value="A">A</option> -->
										<!-- 										<option value="B">B</option> -->
										<!-- 										<option value="C">C</option> -->
									</select>
								</div>
								<label style="padding-left: 1px;" class="col-sm-1 control-label">省份2：</label>
								<div style="padding-left: 1px;" class="col-sm-1">
									<select name="provinceB" id="sel_b"
										class="form-control js-example-basic-single">
										<option value="" selected>省份2</option>
										<!-- 										<option value="0">A</option> -->
										<!-- 										<option value="B">B</option> -->
										<!-- 										<option value="2">C</option> -->
									</select>
								</div>
								<label style="padding-left: 1px;" class="col-sm-1 control-label">省份3：</label>
								<div style="padding-left: 1px;" class="col-sm-1">
									<select name="provinceC" id="sel_c"
										class="form-control js-example-basic-single">
										<!-- 										<option value="0">A</option> -->
										<!-- 										<option value="1">B</option> -->
										<option value="" selected>省份3</option>
									</select>
								</div>
								<!-- 查询按钮 -->
								<button id="btn_query" type="button" class="btn btn-primary">查询</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
		<!-- 第一个row结束 -->

		<!-- 第二个row开始 -->
		<div class="row">
			<div class="ibox-title">
				<h5>评价结果对比</h5>
			</div>
			<div class="ibox-content">
				<span>经过Kendall一致性检验，对六种评价过程得分进行标准化处理，得到如下表所示的结果。</span>
				<table id="table1" class="table table-bordered table-hover">
					<thead>
						<tr>
							<th>年份</th>
							<th colspan="3" id="table_year"></th>
						</tr>
					</thead>
					<tbody>
<!-- 						<tr> -->
<!-- 							<td>省份</td> -->
<!-- 							<td id="table_provinceA"></td> -->
<!-- 							<td id="table_provinceB"></td> -->
<!-- 							<td id="table_provinceC"></td> -->
<!-- 						</tr> -->
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<!-- 第二个row结束 -->
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

	<script type="text/javascript">
		$(document).ready(function() {
	
			$('#sel_a').select2({
				data : [ {
					id : '0',
					text : '省份1'
				}, {
					id : 'A',
					text : 'A'
				}, {
					id : 'B',
					text : 'B'
				}, {
					id : 'C',
					text : 'C'
				} ],
				language : "zh-CN"
			});
			$('#sel_b').select2({
				language : "zh-CN",
			});
			$('#sel_c').select2({
				language : "zh-CN"
			});
		});
		$("#sel_a").on("select2:select", function(e) {
			var data1 = [ {
				id : 'A',
				text : 'A'
			}, {
				id : 'B',
				text : 'B'
			} ];
			var data2 = [ {
				id : 'A',
				text : 'A'
			}, {
				id : 'C',
				text : 'C'
			} ];
			var data3 = [ {
				id : 'B',
				text : 'B'
			}, {
				id : 'C',
				text : 'C'
			} ];
			var selected = $("#sel_a").select2().val();
			var data5 = "";
			if (selected == "A") {
				data5 = data3;
			} else if (selected == "B") {
				data5 = data2;
			} else {
				data5 = data1;
			}
			$('#sel_b').select2({
				data : data5,
				language : "zh-CN"
			});
		});
	
		$("#sel_b").on("select2:select", function(e) {
			var selectedA = $("#sel_a").select2().val();
			var selectedB = $("#sel_b").select2().val();
			var dataC = "";
			if (selectedA == "A" && selectedB == "B") {
				dataC = [ {
					id : 'C',
					text : 'C'
				} ];
			} else if (selectedA == "A" && selectedB == "C") {
				dataC = [ {
					id : 'B',
					text : 'B'
				} ];
			} else if (selectedA == "B" && selectedB == "C") {
				dataC = [ {
					id : 'A',
					text : 'A'
				} ];
			} else if (selectedA == "B" && selectedB == "A") {
				dataC = [ {
					id : 'C',
					text : 'C'
				} ];
			} else if (selectedA == "C" && selectedB == "A") {
				dataC = [ {
					id : 'B',
					text : 'B'
				} ];
			} else if (selectedA == "C" && selectedB == "B") {
				dataC = [ {
					id : 'A',
					text : 'A'
				} ];
			}
			$('#sel_c').select2({
				data : dataC,
				language : "zh-CN"
			});
	
		});
		$("#btn_query").click(function() {
			var selectedA = $("#sel_a").select2().val();
			var selectedB = $("#sel_b").select2().val();
			var selectedC = $("#sel_c").select2().val();
			var year = $("#sel_year option:selected").text();
			$("#table_year").html(year);
			$("#table_provinceA").html(selectedA);
			$("#table_provinceB").html(selectedB);
			$("#table_provinceC").html(selectedC);
			$.ajax({
				dataType : 'json',
				type : 'POST',
				url : '${pageContext.request.contextPath}/DataController/compare.action',
				data : "year=" + year + "&provinceA=" + selectedA + "&provinceB=" + selectedB + "&provinceC=" + selectedC,
				beforeSend : function() {
					$('#modal_wait').modal('show');
				},
				success : function(data) {
					$("#table1 tbody").empty();
					var add = "<tr><td>省份</td><td id='table_provinceA'>"+selectedA+"</td><td id='table_provinceB'>"+selectedB+"</td><td id='table_provinceC'>"+selectedC+"</td></tr>";
					$("#table1 tbody").append(add);
					for (var i = 0; i < data.length; i++) {
						$("#table1 tbody").append('<tr><td>' + data[i].name + '</td><td>' + data[i].data1 + '</td><td>' + data[i].data2 + '</td><td>' + data[i].data3 + '</td></tr>');
					}
				},
				complete : function() { //ajax得到请求结果后的操作  
					$('#modal_wait').modal('hide');
				}
			});
		});
	</script>
</body>
</html>