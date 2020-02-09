<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
<meta name="description"
	content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/normalize.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/htmleaf-demo.css">
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700'
	rel='stylesheet' type='text/css'>
<link href="${pageContext.request.contextPath}/css/plugins/footable/footable.core.css" rel="stylesheet">
<link rel="shortcut icon" href="favicon.ico">
<link href="${pageContext.request.contextPath}/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
<!-- Data Tables -->
<link href="${pageContext.request.contextPath}/css/plugins/dataTables/dataTables.bootstrap.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/animate.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/style.min_user.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css?v=3.3.5" rel="stylesheet">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>

<!-- gy 添加表单验证 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Validator/bootstrapValidator.min.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Validator/bootstrapValidator.min.js"></script>

</head>
<style>
th
{
    text-align:center;
}
td
{
    text-align:center;
}
</style>
<!-- WK -->

<body class="gray-bg">
	<div class="wrapper wrapper-content animated ">

		<div class="row">
			<div class="col-sm-12">

				<!--添加按钮 -->
				<div class="modal inmodal fade" id="myModal6" tabindex="-1">
					<div class="modal-dialog" style="width:500px;">
						<div class="modal-content">
							<form id="addUserForm" class="form-horizontal" action="saveUser.action"
								method="post">
								<div class="modal-body">
									<div class="ibox-title">
										<h5>新增用户信息</h5>
									</div>
									<div class="ibox-content">

										<div class="row">
											<!-- 角色 -->
											<div class="form-group">
												<label class="col-sm-4 control-label">角色名：</label>
												<div class="col-sm-6">
													<select class="form-control" name="fkRoleId">
														<c:forEach items="${roles}" var="roles">
															<option value="${roles.id}">${roles.roleName }</option>
														</c:forEach>
													</select>
												</div>
											</div>

											<!-- 用户名 -->
											<div class="form-group">
												<label class="col-sm-4 control-label">用户名：</label>
												<div class="col-sm-6">
													<input type="text" class="form-control" name="userName" />
												</div>
											</div>

											<!-- 密码 -->
											<div class="form-group">
												<label class="col-sm-4 control-label">密&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
												<div class="col-sm-6">
													<input class="form-control" name="passWord" type="text"  onfocus="this.type='passWord'" autocomplete="off" />
												</div>
											</div>

											<!-- 确认密码-->
											<div class="form-group">
												<label class="col-sm-4 control-label">确认密码：</label>
												<div class="col-sm-6">
													<input class="form-control" name="pwd2" type="text"  onfocus="this.type='passWord'" autocomplete="off"/>
												</div>
											</div>
										</div>

										<!-- 页脚-->
										<div class="modal-footer">
											<button type="submit" class="btn btn-primary">保存</button>
											<button type="button" class="btn btn-danger"
												data-dismiss="modal">关闭</button>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- 点击"修改"按钮弹出来的form表单 -->
		<div class="modal inmodal fade" id="myModal1" tabindex="-1">
			<div class="modal-dialog" style="width:500px;">
				<div class="modal-content">

					<!-- 开始 -->
					<form id="editUserForm" class="form-horizontal" action="updateUser.action"
						method="post">
						<div class="modal-body">
							<div class="ibox-title">
								<h5>修改用户信息</h5>
							</div>
							<div class="ibox-content">
								<input type="hidden" name="id" id="id" /> 
								<input type="hidden" name="userName" id="nameUpdate" />
									
								<div class="row">
									<!-- 角色 -->
									<div class="form-group">
										<label class="col-sm-4 control-label">角色名：</label>
										<div class="col-sm-6">
												<select class="form-control" name="fkRoleId" id="fkRoleId">
													<c:forEach items="${roles}" var="roles">
														<option value="${roles.id}">${roles.roleName }</option>
													</c:forEach>
												</select>
												<%-- <select class="form-control" name="fkRoleId" id="fkRoleId" disabled>
													<c:forEach items="${roles}" var="roles">
														<option value="${roles.id}">${roles.roleName }</option>
													</c:forEach>
												</select> --%>
												<!-- <input type="text" class="form-control" readonly id="roleName" name="roleName" /> -->
										</div>
									</div>

									<!-- 用户名 -->
									<div class="form-group">
										<label class="col-sm-4 control-label">用户名：</label>
										<div class="col-sm-6">
											<span id="name2"></span>
										</div>
									</div>

									<!-- 原密码 -->
									<div class="form-group">
										<label class="col-sm-4 control-label">原密码：</label>
										<div class="col-sm-6">
											<input class="form-control" id="oldPwd" name="old"
												type="text"  onfocus="this.type='password'" autocomplete="off"/>
										</div>
									</div>

									<!-- 新密码 -->
									<div class="form-group">
										<label class="col-sm-4 control-label">新密码：</label>
										<div class="col-sm-6">
											<input class="form-control" id="pwd3" name="passWord"
												type="text" onfocus="this.type='passWord'" autocomplete="off" />
										</div>
									</div>

									<!-- 密码确认 -->
									<div class="form-group">
										<label class="col-sm-4 control-label">密码确认:</label>
										<div class="col-sm-6">
											<input class="form-control" id="pwd4" name="pwd4"
												type="text" onfocus="this.type='passWord'"  autocomplete="off"/>
										</div>
									</div>

									<!-- 模态框 页脚 -->
									<div class="modal-footer">
										<button type="submit" class="btn btn-primary">保存</button>
										<button type="button" class="btn btn-danger"
											data-dismiss="modal">关闭</button>
									</div></div></div></div>
					</form>
					<!-- 结束 -->
					
				</div>
			</div>
		</div>

		
		<div class="row">
		<div class="ibox-title">
			<h5>用户管理</h5>
		</div>
		<div class="ibox-content">
			<!-- <shiro:hasPermission name="user:editrole"> -->
				<button id="btn_add" type="button" class="btn btn-primary"
							data-toggle="modal" data-target="#myModal6">添&nbsp;&nbsp;&nbsp;加</button>&nbsp;
			<!-- </shiro:hasPermission> -->
			<table id="table1" class="table table-bordered table-hover">
				<thead data-align="center">
					<tr data-align="center">
						<th>用户名</th>
						<th>角色名称</th>
						<th>备注</th>
						<th class="text-center">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${users}" var="users">
						<tr>
							<td>${users.userName}</td>
							<td>${users.roleName}</td>
<%-- 							<td><c:if test="${users.isEnable==1}">
									<label class="switch-btn"> <input class="checked-switch"
										type="checkbox" name='${users.userName}' checked="checked"
										onchange="statenow('${users.id}','${users.isEnable}','${users.userName}')" />
										<span class="text-switch" data-yes="使用" data-no="禁用"></span> <span
										class="toggle-btn"></span>
									</label>
								</c:if> <c:if test="${users.isEnable==0}">
									<label class="switch-btn"> <input class="checked-switch"
										type="checkbox" name='${users.userName}'
										onchange="statenow('${users.id}','${users.isEnable}','${users.userName}')" />
										<span class="text-switch" data-yes="使用" data-no="禁用"></span> <span
										class="toggle-btn"></span>
									</label>
								</c:if></td>
 --%>							<td>${users.remark}</td>
							<td class="text-center"><button id="btn-update" type="button"
									class="btn btn-success btn-sm" data-toggle="modal"
									data-target="#myModal1"
									onclick="update('${users.id}','${users.fkRoleId}','${users.userName}','${users.passWord}')">修改</button>
								<button type="button" class="btn btn-danger btn-sm"
									onclick="delete1('${users.id}','${users.userName}')">删除</button></td>					
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<form id="deleteForm" action="deleteUser.action" method="post">
		<input type="hidden" id="deleteId" name="id">
	</form>
	</div>

	<script src="${pageContext.request.contextPath}/js/plugins/sweetalert/sweetalert.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js?v=3.3.5"></script>
	<script src="${pageContext.request.contextPath}/js/plugins/jeditable/jquery.jeditable.js"></script>
	<script src="${pageContext.request.contextPath}/js/plugins/dataTables/jquery.dataTables.js"></script>
	<script src="${pageContext.request.contextPath}/js/plugins/dataTables/dataTables.bootstrap.js"></script>
	<script src="${pageContext.request.contextPath}/js/plugins/footable/footable.all.min.js"></script>


	<script>
	
	$(document).ready(function() {
		//加载footable的功能
	    $('#table1').DataTable({
		    "pagingType" : "full_numbers",
		    ordering:false,
		    searching:true,
		    bLengthChange:false,
		    iDisplayLength:6
	    });
	});
	
	function statenow(id,state,name) {
		 
		swal({
			title : "您确定更改该用户状态吗",
			type : "warning",
			showCancelButton : true,
			cancelButtonText : "取消",
			cancelButtonColor:"red"
		}, function(isConfirm) {
			
			//ajax修改用户状态
			/* var url="updateState";
			$.post(url,{"id":id},{"name":name},function(result){
				swal({
					title : result,
					type : "success"
				});
			}); */
			if(isConfirm) {
			  $.ajax({
		            //提交数据的类型 POST GET
		            type:"POST",
		            //提交的网址
		            url:"updateState",
		            //提交的数据
		            data:{"id":id,"name":name},
		            //返回数据的格式
		            datatype: "text",//"xml", "html", "script", "json", "jsonp", "text".

		            //成功返回之后调用的函数             
		            success:function(data){
		            	 console.log("返回数据");
		            	 console.log(data);
		           /*   if(data != null && data != "null"){
								if (data.indexOf("成功")>0) {
								swal({
									title : data,
									type : "success"
								});
							}else{
								swal({
									title :data,
									type : "error"
								});
							}
							} */
		            	/* if(data!==null){ 
		            		*/
		            	/* sweetAlert({
		    				title : " w",
		    				text : " 状态修改成功",
		    				type : "warning",
 
		    				 
		    			});  */
		            	//}  
		         
		            } , 
		            //调用执行后调用的函数
		             complete: function(XMLHttpRequest, textStatus){
		            	 console.log("调用成功！");
		                // 获取当前状态值和响应数据。 
		                /* var req=XMLHttpRequest.responseText;
		                var state=textStatus;
		               //  alert(req);
		                 //alert(state);
		               swal({
									title : "成功",
									type : "warning"
								}); 
		                 HideLoading();  */
	 
		             }, 
		            // 调用出错执行的函数
		            error: function(){
		                //请求出错处理
		                console.log("出现错误");
		            	 swal({
								title : "出现错误！",
								type : "warning"
							}); 
		            }         
		         });
			}else {
				//临时状态
				    
				 //alert("取消操作"+id+name);
			  //  禁用状态 $("input[name="+name+"]").removeAttr("checked");
			     $("input[name="+name+"]").click();
			// alert 启用状态($("input[name="+name+"]").attr("checked"));
			//alert($("input[name="+name+"]").html());
				// }else{
				 	// changeStatus($("input[name="+name+"]"));   
				  }
			// }
		});
	}
	

		//点击"修改"按钮
		function update(id, fkRoleId, userName, pass) {
			$("#id").val(id);
			$("#fkRoleId").val(fkRoleId);
			$("#nameUpdate").val(userName);
			$("#name2").html(userName);
			$("#oldPwd").val(pass);  
			
		};
	 
		//点击"删除"按钮
		function delete1(id, userName) {
			swal({
				title : "您确定删除 " + userName + " 的数据吗?",
				type : "warning",
				confirmButtonColor: "#DD6B55",
				confirmButtonText: "确认",
				showCancelButton : true,
				cancelButtonText : "取消"
			}, function() {
				$("#deleteId").val(id);
				//提交表单
				$("#deleteForm").submit();
			});
		}
		//页面加载完成后执行,获取执行状态
		$(document).ready(function(){
			console.log("获取执行状态");	
			var result = '<%=request.getAttribute("result")%>';
			if(result != null && result != "null"){
				if (result.indexOf("成功")>0) {
				swal({
					title : result,
					type : "success"
				});
			}else{
				swal({
					title :result,
					type : "error"
				});
			}
			}
		});
	</script>

	<script type="text/javascript">
		/*添加用户界面的表单验证*/
		$('#btn_add').click(function() {
			$('#addUserForm').bootstrapValidator({
				feedbackIcons : {/*输入框不同状态，显示图片的样式*/
					valid : 'glyphicon glyphicon-ok',
					invalid : 'glyphicon glyphicon-remove',
					validating : 'glyphicon glyphicon-refresh'
				},
				/*生效规则：字段值一旦变化就触发验证*/
				live : 'enabled',
				/*当表单验证不通过时，该按钮为disabled*/
				// 				submitButtons : 'button[type="submit"]',
				/*验证*/
				fields : {
					userName : {/*键名username和input name值对应*/
						message : 'The username is not valid',
						validators : {
							notEmpty : {/*非空提示*/
								message : '用户名不能为空'
							}
						}
					},
					passWord : {
						message : 'The username is not valid',
						validators : {
							notEmpty : {/*非空提示*/
								message : '密码不能为空'
							}
						}
					},
					passWord : {
						message : '密码无效',
						validators : {
							notEmpty : {
								message : '密码不能为空'
							},
							stringLength : {
								min : 6,
								max : 30,
								message : '长度必须在6到30之间'
							}
						}
					},
					pwd2 : {
						validators : {
							notEmpty : {
								message : '密码不能为空'
							},
							stringLength : {
								min : 6,
								max : 30,
								message : '长度必须在6到30之间'
							},
							identical : {//相同
								field : 'passWord',
								message : '两次密码不一致'
							}
						}
					}
				}
			});
		});

		/*用户信息修改界面 表单验证*/
		$(document).ready(function() {
			$('#editUserForm').bootstrapValidator({
				feedbackIcons : {/*输入框不同状态，显示图片的样式*/
					valid : 'glyphicon glyphicon-ok',
					invalid : 'glyphicon glyphicon-remove',
					validating : 'glyphicon glyphicon-refresh'
				},
				/*生效规则：字段值一旦变化就触发验证*/
				live : 'enabled',
				/*当表单验证不通过时，该按钮为disabled*/
				submitButtons : 'button[type="submit"]',
				/*验证*/
				fields : {
					passWord : {
						validators : {
							notEmpty : {/*非空提示*/
								message : '密码不能为空'
							},
							stringLength : {
								min : 6,
								max : 30,
								message : '长度必须在6到30之间'
							},
							identical : {//相同
								field : 'pwd4',
								message : '两次密码不一致'
							}
						}
					},
					passWord : {
						validators : {
							notEmpty : {/*非空提示*/
								message : '密码不能为空'
							},
							stringLength : {
								min : 6,
								max : 30,
								message : '长度必须在6到30之间'
							}
						}
					},
					pwd4 : {
						validators : {
							notEmpty : {/*非空提示*/
								message : '密码不能为空'
							},
							stringLength : {
								min : 6,
								max : 30,
								message : '长度必须在6到30之间'
							},
							identical : {//相同
								field : 'passWord',
								message : '两次密码不一致'
							}
						}
					}
				}
			});
		});
	</script>
</body>

</html>