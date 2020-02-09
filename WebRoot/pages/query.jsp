<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
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
				<h5>减账审核</h5>
			</div>

			<!-- 内容开始 -->
			<div class="ibox-content">
				<form id="queryForm" class="form-horizontal" method="post">
					<div class="row">
						<div class="form-group">
							<div class="col-sm-12">
								<!-- 业务员  可编辑下拉框-->
								<label class="col-sm-1 control-label">业务员：</label>
								<div style="padding-left: 1px;" class="col-sm-1">
									<select id="sel_salesman"
										class="form-control js-example-basic-single">
										<option value="0" selected>业务员</option>
									</select>
								</div>
								<!-- 发往单位 省市县联动-->
								<label style="padding-left: 1px;" class="col-sm-1 control-label">到款城市：</label>
								<div style="padding-left: 1px;" class="col-sm-1">
									<select id="sel_province"
										class="form-control js-example-basic-single">
										<option value="" selected>省份</option>
									</select>
								</div>
								<!--市 -->
								<div style="padding-left: 1px;" class="col-sm-1">
									<select id="sel_city"
										class="form-control js-example-basic-single">
										<option value="">地级市</option>
									</select>
								</div>
								<!--县/区-->
								<div style="padding-left: 1px;" class="col-sm-1">
									<select id="sel_district"
										class="form-control js-example-basic-single">
										<option value="" selected>无</option>
										<option value="" selected>县\区</option>
									</select>
								</div>
								<!--公司-->
								<div style="padding-left: 1px;" class="col-sm-2">
									<select id="sel_company"
										class="form-control js-example-basic-single">
										<option value="" selected>公司</option>
									</select>
								</div>

								<!-- 总编号 -->
								<label style="padding-left: 1px;" class="col-sm-1 control-label">总编号：</label>
								<div style="padding-left: 1px;" class="col-sm-2 ">
									<input type="text" class="form-control" name="totalNumber"
										id="totalNumber">
								</div>
							</div>
						</div>
					</div>

					<!-- 第二行 -->
					<div class="row">
						<div class="form-group">
							<div class="col-sm-12">
								<!-- 接单时间-->
								<label class="col-sm-1 control-label">到款日期：</label>
								<div style="padding-left: 1px; width: auto;"
									class="col-sm-1 form-inline">
									<input id="dateStart" type="text" readonly="readonly"
										class="form-control" name="dateStart"
										onfocus="WdatePicker({lang:'zh-cn',skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'dateEnd\')||\'new Date()\'}',autoPickDate:true})" />
									至 <input id="dateEnd" type="text" readonly="readonly"
										class="form-inline form-control" name="dateEnd"
										onfocus="WdatePicker({lang:'zh-cn',skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'%y-%M-%d',minDate:'#F{$dp.$D(\'dateStart\')}',autoPickDate:true})" />
								</div>

								<!-- 出库状态 -->
								<label style="padding-left: 1px; padding-right: 1px;"
									class="col-sm-1 control-label">审核状态：</label>
								<div style="padding-left: 1px;line-height:35px;">
									<div class="col-sm-1" style="width:95px;">
										<input type="checkbox" id="inlineCheckbox1" value="0">
										<label style="padding-left: 1px; padding-right: 1px;">未审核</label>
									</div>
								    <div class="col-sm-1" style="width:90px;">
										<input type="checkbox" id="inlineCheckbox2" value="1">
										<label style="padding-left: 1px; padding-right: 1px;">审核</label>
									</div>
									<div class="col-sm-2" style="width:120px;">
										<input type="checkbox" id="inlineCheckbox3" value="2">
										<label style="padding-left: 1px; padding-right: 1px;">审核不通过</label>
									</div>
	  								<div class="col-sm-1" style="width:90px;">
										<input type="checkbox" id="inlineCheckbox4" value="-1" onclick="checkAll(this)">
										<label style="padding-left: 1px; padding-right: 1px;">全选</label>
									</div>
								</div>
								<!-- 查询按钮 -->
								<button id="btn_query" type="button" onclick="queryAccountRecord()" class="btn btn-primary">查询</button>
							</div>
						</div>
					</div>
					<!-- 第二行 结束-->
				</form>
			</div>
		</div>
		<!-- 第一个row结束 -->

		<!-- 第二个row开始 -->
		<div class="row">
			<!-- 标题 -->
			<div class="ibox-title">
				<h5>发货单据信息</h5>
			</div>

			<!-- 内容开始 -->
			<div class="ibox-content">
				<table id="table_account_record" class="table table-hover">
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
			<div class="modal inmodal fade" id="modal_reduced_account_details" tabindex="-1"
				role="dialog" aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-body">
							<div class="ibox-title">
								<h5>减账详情信息</h5>
								<!-- 订单状态追加文本 -->
								<label class="col-sm-2 control-label" id="audit_status_text"></label>
							</div>
							<div class="ibox-content">
								<form id="table_reduce_account_base_info"
									class="form-horizontal" method="post">
									<div class="row">
										<div class="form-group">
											<div class="col-sm-12">
												<label class="col-sm-2 control-label" style="width:90px;">审核人：</label>
												<div class="col-sm-2">
													<input type="text" class="form-control"
														name="model_auditor" id="model_auditor"
														readonly="readonly" value=<shiro:principal />>
												</div>
												<label class="col-sm-2 control-label">审核时间：</label>
												<div class="col-sm-3">
													<input id="model_audit_date" readonly type="text" class="form-control"
														name="model_audit_date"
														onfocus="WdatePicker({lang:'zh-cn',skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:00',maxDate:'%y-%M-%d',autoPickDate:true,onpicked:function(dp){refreshValidator(inputDeliverGoodsForm, name.valueOf())}})" />
												</div>
											</div>
										</div>
									</div>
								</form>
								<table id="table_reduced_account_record" class="table table-hover">
									<thead>
									</thead>
									<tbody>
									</tbody>
									<tfoot>
									</tfoot>
								</table>
								<div class="modal-footer">
									<button id="btn_pass_audit" type="button"
										class="btn btn-primary" data-dismiss="modal">通过审核</button>
									<button id="btn_not_pass_audit" type="button" onclick=""
										class="btn btn-primary" data-dismiss="modal">不通过审核</button>
									<button id="btn_cancel_audit" type="button" onclick=""
										class="btn btn-primary" data-dismiss="modal">撤销审核</button>
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
		$(document).ready(function() {
			
			$.ajax({
				dataType : 'json',
				type : 'POST',
				url : '${pageContext.request.contextPath}/salesmanController/listSalesmansName.action',
				success : function(data) {
					$('#sel_salesman').select2({
						data : data,
						language : "zh-CN"
					});
				}
			});
	
			$('#sel_province').select2({
				language : "zh-CN"
			});
			$('#sel_city').select2({
				language : "zh-CN"
			});
			$('#sel_district').select2({
				language : "zh-CN"
			});
			/* $('#sel_company').select2({
				language : "zh-CN"
			}); */
		});
	
		//业务员选择后触发事件
		$("#sel_salesman").on("select2:select", function(e) {
			var id = $("#sel_salesman").select2().val();
	
			//当选中的不是 “业务员”时，触发ajax 去请求对应的省 key-value
			if (id != 0) {
				$.ajax({
					dataType : 'json',
					type : 'POST',
					url : '${pageContext.request.contextPath}/companyController/getProvince.action',
					data : "id=" + id,
					success : function(data) {
						$("#sel_province").empty();
						$("#sel_province").select2({
							data : data,
							language : "zh-CN"
						})
					},
				});
			}
		});
	
		//省份选择后的触发事件
		$("#sel_province").on("select2:select", function(e) {
			//获取业务员id
			var salesmanId = $("#sel_salesman").select2().val();
			//获取省份的名称
			var province = $("#sel_province option:selected").text();
	
			//当选中的不是省份时触发ajax 去请求对应的市
			if (province != '省份') {
				//触发ajax 去请求对应的市
				$.ajax({
					dataType : 'json',
					type : 'POST',
					url : '${pageContext.request.contextPath}/companyController/getCity.action',
					data : "salesmanId=" + salesmanId + "&province=" + province,
					success : function(data) {
						//将市清空并赋值
						$("#sel_city").empty();
						$("#sel_city").select2({
							data : data,
							language : "zh-CN"
						})
					},
				});
			}
		});
	
		//市选择完成后触发事件
		$("#sel_city").on("select2:select", function(e) {
			//获取业务员id
			var salesmanId = $("#sel_salesman").select2().val();
			//获取市的名称
			var province = $("#sel_province option:selected").text();
			var city = $("#sel_city option:selected").text();
	
			//当选择的不是“地级市”时，触发ajax 去请求对应的市
			if (city != '地级市') {
				//触发ajax 去请求对应的市
				$.ajax({
					dataType : 'json',
					type : 'POST',
					url : '${pageContext.request.contextPath}/companyController/getDistrict.action',
					data : "salesmanId=" + salesmanId + "&city=" + city,
					success : function(data) {
						//将区/县清空并赋值
						$("#sel_district").empty();
						$("#sel_district").select2({
							data : data,
							language : "zh-CN"
						})
					},
				});
				
				//5.根据省市信息加载公司
				$.ajax({
					dataType : 'json',
					type : 'POST',
					url : '${pageContext.request.contextPath}/companyController/getCompany.action',
					data : "salesmanId=" + salesmanId + "&province=" + province + "&city=" + city,
					success : function(data) {
						//根据省市查出的公司填充
						$("#sel_company").empty();
						$("#sel_company").select2({
							data : data,
							language : "zh-CN"
						})
					}
					
				});
			}
		});
	
		//根据县或区选择完成后去获取公司名称，此处需要注意一个逻辑是市级单位的县或区可能为空，例如青岛市XX局
		$("#sel_district").on("select2:select", function(e) {
			//获取业务员id
			var salesmanId = $("#sel_salesman").select2().val();
			//获取县、区的名称
			var district = $("#sel_district option:selected").text();
			//如果县\区为“无”，将“无”转换为"wu"传递到后台，避免传输中文
			if (district == '无') {
				district = "wu";
			}
	
			if (district != '县\区') {
				//触发ajax 去请求对应的公司名称
				$.ajax({
					dataType : 'json',
					type : 'POST',
					url : '${pageContext.request.contextPath}/companyController/getCompany.action',
					data : "salesmanId=" + salesmanId + "&district=" + district,
					success : function(data) {
						//将区/县清空并赋值
						$("#sel_company").empty();
						$("#sel_company").select2({
							data : data,
							language : "zh-CN"
						})
					},
				});
			}
		});
		
		
		//公司直接查询 -- 发往单位选择deliverCompany 监听 -an
		$("#sel_company").select2({
			width : "100%",
			placeholder:'请选择公司名称',
			//allowClear:true,//允许删除，选择框后面的小叉号
			language : "zh-CN",
			ajax: {
			    url: "${pageContext.request.contextPath}/companyController/getCompany.action",//请求的url
			    dataType: 'json',
			    type : 'POST',
			    delay: 250,//设置延时，超过为请求不到数据
			    data: function (params) {
			      console.log(params);
			      return { //return中的内容为参数，请求参数
			    	companyName : params.term,
			        salesmanId : ($("#sel_salesman").select2().val() == 0 ? null : $("#sel_salesman").select2().val()),
			        province : $("#sel_province option:selected").text(),
			        city : $("#sel_city option:selected").text(),
			        district : $("#sel_district option:selected").text()
			      };
			    },
			    processResults: function (data, params) {
			    	console.log(data);
			        return {//获取结果集，格式为json{"id":"1","text":"111";}
			          results: data
			       };
			    },
			    cache: true
			},
			escapeMarkup: function (markup) { return markup; }, 
			minimumInputLength: 1,
			templateResult: formatRepo, 
			templateSelection: formatRepo 
		});
		
		//将查到的数据添加到select的选项中
		function formatRepo(repo) {    
		    if (repo.loading) 
		   	  return repo.text;    
		    var markup = "<option>" + repo.text + "</option>"; 
		    return markup;    
		}
	
		
		function checkAll(res){
			var CheckBox = $('input:checkbox');
			if (res.checked) {
				for( i=0; i<CheckBox.length; i++) {
					CheckBox[i].checked = true;
				}
			} else {
				for( i=0; i<CheckBox.length; i++) {
					CheckBox[i].checked = false;
				}
			}
			
			
		}
		
		//查询
		function queryAccountRecord(){
			//1.获取到七个查询的值
			//a.业务员姓名
			var salesmanName = $("#sel_salesman option:selected").text();
			if (salesmanName == '业务员') {
				salesmanName = '';
			}
			//b.公司名称
			var comapny = $("#sel_company option:selected").text();
			if (comapny == '公司') {
				comapny = '';
			}
			//d.总编号
			var totalNumber = $("#totalNumber").val();
			//e.开始时间
			var dateStart = $("#dateStart").val();
			//f.结束时间
			var dateEnd = $("#dateEnd").val();
			
			var auditStatus = new Array;
			
			//获取多选框的值
			$.each($('input:checkbox:checked'),function(){
                //alert("你选了："+$('input[type=checkbox]:checked').length+"个，其中有："+$(this).val());
                if($(this).val() == -1){
                	return;
                }
                auditStatus.push($(this).val());
			});
			
			if ((dateStart != '' && dateEnd != '') && (comapny != '' || totalNumber != '' || salesmanName != '' || auditStatus.length != 0)) {
				//2.ajax发送数据
				$.ajax({
					dataType : 'json',
					type : 'POST',
					url : '${pageContext.request.contextPath}/reduceAccountController/listAccountRecordBase.action',
					//data : "deliveryBaseInfo.salesmanName=" + salesmanName + "&deliveryBaseInfo.deliverCompany=" + comapny + "&deliveryBaseInfo.outbroadStatus=" + outbroadStatus + "&deliveryBaseInfo.totalNumber=" + totalNumber + "&dateStart=" + dateStart + "&dateEnd=" + dateEnd + "&deliveryBaseInfo.courierNumber=" + courierNumber,
					data : "totalNumber=" + totalNumber +"&salesmanName="+salesmanName+"&comapny="+comapny+"&dateStart=" + dateStart + "&dateEnd=" + dateEnd + "&auditStatus=" + auditStatus,
					success : function(data) {
						//3.处理json数据，将数据填充到table
						//bootstrap-table 
						//先销毁表格再填充数据，避免第二次查询无法显示
						$("#table_account_record").bootstrapTable('destroy');
						//填充table数据
						$('#table_account_record').bootstrapTable({
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
							pageSize : 5,
							search : false, //是否启用查询 
							//可供选择的每页的行数（*）      
							pageList : [ 5, 10 ],
							//分页方式：client客户端分页，server服务端分页（*）  
							sidePagination : "client",
							//是否显示搜索 搜索input name= searchText 服务端获取该值查询即可  
							search : false,
							columns : [{
									field : "index",
									title : "序号",
									align : "center",
									edit : false,
									formatter : function(value, row, index) {
										return row.index = index+1; //返回行号  
									}
								},
							{
								field : 'deliverCompany',
								title : '公司名称'/* ,
								formatter : function(value, row, index) {
									var arr = new Array();
									arr = row.deliverCompany.split("#");
									return arr[1];
								} */
							}, {
								field : 'totalNumber',
								title : '总编号'
							}, {
								field : 'totalMoney',
								title : '总金额'
							},  {
								field : 'arrivalDate',
								title : '到款时间'
							}, {
								field : 'auditStatus',
								title : '审核状态',
								formatter : function(value, row, index) {
									if (row.auditStatus == '0') {
										return "未审核";
									} else if (row.auditStatus == '1') {
										return "审核";
									} else if (row.auditStatus == '2') {
										return "不通过";
									}
								}
							}, {
								field : 'auditor',
								title : '审核人'
							}, {
								field : 'auditDate',
								title : '审核时间'
							}, {
								field : 'formHolder',
								title : '录入人'
							}, {
								field : 'formDate',
								title : '录入时间'
							}, {
								field : 'salesmanName',
								title : '业务员'
							}, {
								title : '操作',
								formatter : operateFormatter,
								events : operateEvents
							}
							],
						});
					//bootstrap table结束
					},
				});
			} //查询条件判断结束
			//验证情况str
			var result = "";
			if (dateStart == '' || dateEnd == '') {
				result = "时间不能为空\n"
			}
			if (comapny == '' && totalNumber == '' && salesmanName == '' && auditStatus.length == 0) {
				result += "公司、总编号、业务员、审核状态至少填写一个";
			}
			if (result != '') {
				swal({
					title : result,
					type : "warning"
				});
			}
		}
		
		function operateFormatter(value, row, index) {
			return [
				'<button type="button" class="ReducedDetail btn btn-primary  btn-sm">详情</button>',
			].join('');
		}
		
		window.operateEvents = {
			//value:当前单元格的值；row:当前行；index:当前行的索引
			'click .ReducedDetail' : function(e, value, row, index) {
				
				//订单状态追加文本
				var auditStatusText;
				//按钮的可操作性行
				var btn_cancel_audit = $("#btn_cancel_audit");
				var btn_not_pass_audit = $("#btn_not_pass_audit");
				var btn_pass_audit = $("#btn_pass_audit");
				btn_cancel_audit.removeAttr("disabled");
				btn_not_pass_audit.removeAttr("disabled");
				btn_pass_audit.removeAttr("disabled");
				
				//按钮的可操作性情况
				if(row.auditStatus == 0){//btn_cancel_audit btn_pass_audit btn_not_pass_audit
					auditStatusText = "未审核";
					btn_cancel_audit.attr("disabled","disabled");
					//如果订单状态未审核，重新初始化时间
					setNowFormatDate();
				} else if (row.auditStatus == 1) {
					auditStatusText = "审核通过";
					btn_pass_audit.attr("disabled","disabled");
					btn_not_pass_audit.attr("disabled","disabled");
					//如果订单状态审核，时间为原来时间
					$("#model_audit_date").val(row.auditDate);
				} else if (row.auditStatus == 2) {
					auditStatusText = "审核不通过";
					btn_pass_audit.attr("disabled","disabled");
					btn_not_pass_audit.attr("disabled","disabled");
				} else {
					auditStatusText = "其他状态";
					btn_cancel_audit.attr("disabled","disabled");
					btn_pass_audit.attr("disabled","disabled");
					btn_not_pass_audit.attr("disabled","disabled");
					//如果订单状态审核，时间为原来时间
					$("#model_audit_date").val(row.auditDate);
				}
				//审核状态的追加
				$("#audit_status_text").replaceWith("<label id='audit_status_text' class='col-sm-2 control-label' style='color:red;'>*("+auditStatusText+")<label>");
				
				totalNumber = row.totalNumber;
				formDate = row.formDate;
				
				$.ajax({
					dataType : 'json',
					type : 'POST',
					url : '${pageContext.request.contextPath}/reduceAccountController/listAccountRecordDetail.action',
					data : "totalNumber=" + totalNumber + "&formDate=" + formDate,
					success : function(data) {
						num = null;
						num = data.length;
						$('#modal_reduced_account_details').modal('show');
						//先销毁表格再填充数据，避免第二次查询无法显示
						$("#table_reduced_account_record").bootstrapTable('destroy');
						//填充table数据
						$('#table_reduced_account_record').bootstrapTable({
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
							pageSize : 5,
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
									return row.index = index+1; //返回行号  
								}
							}, {
								field : 'materialName',
								title : '物料名称'
							},  {
								field : 'materialModel',
								title : '物料类型'
							},  {
								field : 'unitPrice',
								title : '单价'
							}, {
								field : 'unit',
								title : '单位'
							}, {
								field : 'reducedNumber',
								title : '减账数量'
							}, {
								field : 'reducedMoney',
								title : '减账金额'
							},
							],  
						}); 
					}
				});
			}
		};
		
		/* $("#btn_query").mouseup(function(){
			var $thr = $('table thead tr');  
            var $checkAllTh = $('<th style="text-align:center;"><input type="checkbox" id="checkAll" name="checkAll" /></th>');  
            $thr.prepend($checkAllTh);  
            var $checkAll = $thr.find('input');  
            $checkAll.click(function(event){  
                $tbr.find('input').prop('checked',$(this).prop('checked'));  
                if ($(this).prop('checked')) {  
                    $tbr.find('input').parent().parent().addClass('warning');  
                } else{  
                    $tbr.find('input').parent().parent().removeClass('warning');  
                }  
                event.stopPropagation();  
            });  
            $checkAllTh.click(function(){  
                $(this).find('input').click();  
            });  
            var $tbr = $('table tbody tr');  
            var $checkItemTd = $('<td style="text-align:center;"><input type="checkbox" name="checkItem" /></td>');  
            $tbr.prepend($checkItemTd);  
            $tbr.find('input').click(function(event){  
                $(this).parent().parent().toggleClass('warning');  
                $checkAll.prop('checked',$tbr.find('input:checked').length == $tbr.length ? true : false);  
                event.stopPropagation();  
            });  
            $tbr.click(function(){  
                $(this).find('input').click();  
            });  
		}); */
		
		var totalNumber;
		var formDate;
		var num;//详情数量
		//审核通过按钮监听
		$("#btn_pass_audit").click(function(){
			//alert("通过审核");
			showConfirmSwal("您确定通过审核吗?","warning",1);
			/* swal({
				title : "您确定通过审核吗?",
				type : "warning",
				showCancelButton : true,
				cancelButtonText : "取消"
			}, function() {
				updateAuditStatus(1);
			}); */
			
		});
		//审核不通过按钮监听
		$("#btn_not_pass_audit").click(function(){
			//alert("不通过审核");
			showConfirmSwal("您确定不通过审核吗?","warning",2);
			/* swal({
				title : "您确定不通过审核吗?",
				type : "warning",
				showCancelButton : true,
				cancelButtonText : "取消"
			}, function() {
				updateAuditStatus(2);
			}); */
		});
		//审核撤销按钮监听
		$("#btn_cancel_audit").click(function(){
			//alert("撤销审核");
			showConfirmSwal("您确定撤销审核吗?","warning",0);
			/* swal({
				title : "您确定撤销审核吗?",
				type : "warning",
				showCancelButton : true,
				cancelButtonText : "取消"
			}, function() {
				updateAuditStatus(0);
			}); */
		});
		
		function updateAuditStatus(auditStatus){
			
			var auditDate = $("#model_audit_date").val();
			var auditor = $("#model_auditor").val();
		
			//修改订单审核状态和审核时间和审核人
			$.ajax({
				dataType : 'json',
				type : 'POST',
				url : '${pageContext.request.contextPath}/reduceAccountController/updateAuditStatus.action',
				data : "totalNumber=" + totalNumber + "&formDate=" + formDate + "&auditStatus=" + auditStatus + "&num=" + num + "&auditor=" + auditor + "&auditDate=" + auditDate,
				success : function(data) {
					if(data == '修改成功'){
						showSwal(data, "success");
						queryAccountRecord();
					} else {
						showSwal(data, "error");
					}
				},
				error : function () {
					showSwal(data, "error");
				}
			});
		}
		
		function showSwal(data, type){
			swal({
				title : data,
				type : "success",
			});
		}
		
		function showConfirmSwal(title, type, status){
			swal({
				title : title,
				type : type,
				showCancelButton : true,
				cancelButtonText : "取消"
			}, function() {
				updateAuditStatus(status);
			});
		}
	</script>
	
	<!-- 取出对象，提交修改表单 -an -->
	<script type="text/javascript">
		/*1. 遍历表格,提取出来，用于也用于发货单打印 */
		function ergodicTable(tableObj) {
			//materialName materialModel unitPrice number unit remark
			var props = [ "materialName", "materialModel", "unitPrice",
					"number", "unit", "remark" ];
			var detailInfos = new Array();
			tableObj.each(function() {
				var detailInfo = new Object();
				$(this).find("td").each(function(n) {
					//索引和操作不传递
					if (n == 0 || n == 7)
						return;
					detailInfo[props[n - 1]] = $(this).html();
				});
	
				if (detailInfo["materialName"] != undefined) {
					detailInfos.push(detailInfo);
				}
			});
			
			return detailInfos;
		}
		
	</script>
	
	<!-- 设置填表时间和到款时间 -->
	<script type="text/javascript">
		/* 添加默认时间 为当前时间
			js获取 2017-11-4 10:40:00格式的时间
		 */
		function setNowFormatDate() {
			var auditDate = $("#model_audit_date");
		
			var date = new Date();
			var seperator1 = "-";
			var seperator2 = ":";
			var month = date.getMonth() + 1;
			var strDate = date.getDate();
			var strMinutes = date.getMinutes();
			if (month >= 1 && month <= 9) {
				month = "0" + month;
			}
			if (strDate >= 0 && strDate <= 9) {
				strDate = "0" + strDate;
			}
			if (strMinutes >= 0 && strMinutes <= 9) {
				strMinutes = "0" + strMinutes;
			}
			var currentdate = date.getFullYear() + seperator1 + month
					+ seperator1 + strDate + " " + date.getHours() + seperator2
					+ strMinutes + seperator2 + "00";
			auditDate.val(currentdate);
		}
	</script>
	
</body>
</html>