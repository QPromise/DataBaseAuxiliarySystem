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
</head>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<!-- 标题 -->
			<div class="ibox-title">
				<h5>数据分析</h5>
			</div>

			<!-- 内容开始 -->
			<div class="ibox-content">
				<form id="queryForm" class="form-horizontal" method="post">
					<div class="row">
						<div class="form-group">
							<div class="col-sm-12">
								
								<label style="padding-left: 1px;" class="col-sm-1 control-label">指标选择：</label>
								<div class="col-sm-1">
									<select id="sel_province"
										class="form-control js-example-basic-single">
										<option value="">省份</option>
										<option value="">天津</option>
									</select>
								</div>
								<div class="col-sm-2">
									<select id="sel_first_index"
										class="form-control js-example-basic-single">
									</select>
								</div>
								<div class="col-sm-2">
									<select id="sel_second_index"
										class="form-control js-example-basic-single">
										<option value="">一级指标</option>
									</select>
								</div>
								<div class="col-sm-2">
									<select id="sel_third_index"
										class="form-control js-example-basic-single">
										<option value="" selected>二级指标</option>
									</select>
								</div>
								<div class="col-sm-2">
									<select id="sel_fourth_index"
										class="form-control js-example-basic-single">
										<option value="" selected>三级指标</option>
									</select>
								</div>
								<!-- 查询按钮 -->
								<div class="col-sm-2">
									<button id="btn_query" type="button" onclick="queryAccountRecord()" class="btn btn-primary">查询</button>
<!-- 									<button id="btn_query_comprehensive" type="button" class="btn btn-primary">综合指标</button> -->
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
		<!-- 第一个row结束 -->

		<!-- 第二个row开始 -->
		<div class="row">
			<div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>折线图</h5>
                    <div class="ibox-tools">
                        <a class="collapse-link">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                        <a class="dropdown-toggle" data-toggle="dropdown" href="graph_flot.html#">
                            <i class="fa fa-wrench"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-user">
                            <li><a href="graph_flot.html#">选项1</a>
                            </li>
                            <li><a href="graph_flot.html#">选项2</a>
                            </li>
                        </ul>
                        <a class="close-link">
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content" >
                    <div class="echarts" id="echarts-line-chart" style="height:300px;"></div>
                </div>
            </div>
            <!-- <div class="col-sm-12" >
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>折线图</h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="dropdown-toggle" data-toggle="dropdown" href="graph_flot.html#">
                                <i class="fa fa-wrench"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-user">
                                <li><a href="graph_flot.html#">选项1</a>
                                </li>
                                <li><a href="graph_flot.html#">选项2</a>
                                </li>
                            </ul>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content" >
                        <div class="echarts" id="echarts-line-chart" style="height:300px;"></div>
                    </div>
                </div>
            </div> -->
        </div>
		<!-- 第二个row结束 -->
	</div>

	<script type="text/javascript">
		//初始化所有的select2
		$(document).ready(function() {
			$.ajax({
				dataType : 'json',
				type : 'POST',
				url : '${pageContext.request.contextPath}/DataController/listFirstIndexMetadata.action',
				success : function(data) {
					$('#sel_first_index').select2({
						data : data,
						language : "zh-CN"
					});
				}
			});
		 	$('#sel_second_index').select2({
				language : "zh-CN"
			});
			$('#sel_third_index').select2({
				language : "zh-CN"
			});
			$('#sel_fourth_index').select2({
				language : "zh-CN"
			});  
			$('#sel_province').select2({
				language : "zh-CN"
			});  
			/* $('#sel_company').select2({
				language : "zh-CN"
			}); */
		});
	
	
		$("#sel_first_index").on("select2:select", function(e) {
			var designation = $("#sel_first_index option:selected").text();
			if (designation != '一级指标') {
				
				$.ajax({
					dataType : 'json',
					type : 'POST',
					url : '${pageContext.request.contextPath}/DataController/listSecondIndexMetadata.action',
					data : "designation=" + designation,
					success : function(data) {
						//将市清空并赋值
						$("#sel_second_index").empty();
						$("#sel_second_index").select2({
							data : data,
							language : "zh-CN"
						})
					},
				});
			}
		});
	
		$("#sel_second_index").on("select2:select", function(e) {
			var designation = $("#sel_second_index option:selected").text();
			if (designation != '二级指标') {
				
				$.ajax({
					dataType : 'json',
					type : 'POST',
					url : '${pageContext.request.contextPath}/DataController/listThirdIndexMetadata.action',
					data : "designation=" + designation,
					success : function(data) {
						//将市清空并赋值
						$("#sel_third_index").empty();
						$("#sel_third_index").select2({
							data : data,
							language : "zh-CN"
						})
					},
				});
			}
		});	
		
		$("#sel_third_index").on("select2:select", function(e) {
			var designation = $("#sel_third_index option:selected").text();
			if (designation != '三级指标') {
				
				$.ajax({
					dataType : 'json',
					type : 'POST',
					url : '${pageContext.request.contextPath}/DataController/listFourthIndexMetadata.action',
					data : "designation=" + designation,
					success : function(data) {
						//将市清空并赋值
						$("#sel_fourth_index").empty();
						$("#sel_fourth_index").select2({
							data : data,
							language : "zh-CN"
						})
					},
				});
			}
		});	
		
		function showSwal(data, type) {
			swal({
				title : data,
				type : type,
			});
		}
		
		//查询
		function queryAccountRecord(){
			var designation = $("#sel_fourth_index option:selected").text();
			var id = 4;//确定当时传入值是哪一指标
			
			if(designation == '四级指标'){
				designation = $("#sel_third_index option:selected").text();
				id = 3;
				if(designation == '三级指标'){
					designation = $("#sel_second_index option:selected").text();
					id = 2;
					if(designation == '二级指标'){
						designation = $("#sel_first_index option:selected").text();
						id = 1;
						if(designation == '一级指标'){
							showSwal("请选择指标","warning");
							return;
						}
					}
				}
			}
			
			/* if(designation == ''){
				alert("四级指标不能为空");
				return;
			} */
			
			$.ajax({
				dataType : 'json',
				type : 'POST',
				url : '${pageContext.request.contextPath}/DataController/listIndexDataByDesignation.action',
				data : "designation=" + designation + "&id=" + id,
				success : function(data) {
					var e = echarts.init(document.getElementById("echarts-line-chart")),
					a = {
						title : {
							text : data["title"]
						},
						tooltip : {
							trigger : "axis"
						},
						legend : {
							data : [ "天津市" ]
						},
						grid : {
							x : 40,
							x2 : 40,
							y2 : 24
						},
						calculable : !0,
						xAxis : [ {
							type : "category",
							boundaryGap : !1,
							data : data["transverseValues"]
						} ],
						yAxis : [ {
							type : "value",
							axisLabel : {
								formatter : "{value}"
							}
						} ],
						series : [ {
							name : "山东省",
							type : "line",
							data : data["values"],
							markPoint : {
								data : [ {
									type : "max",
									name : "最大值"
								}, {
									type : "min",
									name : "最小值"
								} ]
							},
							markLine : {
								data : [ {
									type : "average",
									name : "平均值"
								} ]
							}
						}/*,  {
							name : "北京",
							type : "line",
							data : [ 1, -2, 2, 5, 3, 2, 0 ],
							markPoint : {
								data : [ {
									name : "周最低",
									value : -2,
									xAxis : 1,
									yAxis : -1.5
								} ]
							},
							markLine : {
								data : [ {
									type : "average",
									name : "平均值"
								} ]
							}
						} , {
							name : "中午气温气温",
							type : "line",
							data : [ 1000, -2000, 2000, 5000, 3000, 2000, 0 ],
							markPoint : {
								data : [ {
									name : "周最低",
									value : -2,
									xAxis : 1,
									yAxis : -1.5
								} ]
							},
							markLine : {
								data : [ {
									type : "average",
									name : "平均值"
								} ]
							}
						} */]
					};
					//初始化
					e.setOption(a), $(window).resize(e.resize);
				}
			});
		}
		
	</script>	
	<script type="text/javascript">
		$("#btn_query_comprehensive").click(function(){
			$.ajax({
				dataType : 'json',
				type : 'POST',
				url : '${pageContext.request.contextPath}/DataController/comprehensiveIndexQuery.action',
				success : function(data) {
					var e = echarts.init(document.getElementById("echarts-line-chart")),
					a = {
						title : {
							text : data["title"]
						},
						tooltip : {
							trigger : "axis"
						},
						legend : {
							data : [ "天津市" ]
						},
						grid : {
							x : 40,
							x2 : 40,
							y2 : 24
						},
						calculable : !0,
						xAxis : [ {
							type : "category",
							boundaryGap : !1,
							data : data["transverseValues"]
						} ],
						yAxis : [ {
							type : "value",
							axisLabel : {
								formatter : "{value}"
							}
						} ],
						series : [ {
							name : "天津市",
							type : "line",
							data : data["values"],
							markPoint : {
								data : [ {
									type : "max",
									name : "最大值"
								}, {
									type : "min",
									name : "最小值"
								} ]
							},
							markLine : {
								data : [ {
									type : "average",
									name : "平均值"
								} ]
							}
						}]
					};
					//初始化
					e.setOption(a), $(window).resize(e.resize);
				}
			});
		});
	</script>
</body>
</html>