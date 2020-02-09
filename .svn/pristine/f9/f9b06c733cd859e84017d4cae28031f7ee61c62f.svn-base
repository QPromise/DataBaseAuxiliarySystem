<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<title>海洋强国</title>

<link href="css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
<!-- <link rel="shortcut icon" href="favicon.ico"> -->
<link href="css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
<!-- 左侧导航栏的样式 -->
<link href="css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
<!-- 左侧导航栏隐藏按钮的样式 -->
<link href="css/animate.min.css" rel="stylesheet">
<!-- 注掉就没有跳转到主页的按钮的动画效果了 -->
<!-- <link href="css/style.min.css?v=4.0.0" rel="stylesheet"> -->

<!-- <script type="text/javascript" src="js/jquery-2.0.0.min.js"></script> -->

<!-- Gritter -->
<link href="js/plugins/gritter/jquery.gritter.css" rel="stylesheet">

<!--  <link href="css/animate.min.css" rel="stylesheet"> -->
<link href="css/style.min.css?v=4.0.0" rel="stylesheet">
<!--导入界面点击js事件 -->
<script src="js/modernizr.custom.js"></script>
</head>
<body class="fixed-sidebar full-height-layout gray-bg"
	style="overflow: hidden">

	<div id="wrapper">
		<!--左侧导航开始-->
		<nav class="navbar-default navbar-static-side" role="navigation">
		<div class="nav-close">
			<i class="fa fa-times-circle"></i>
		</div>

		<div class="sidebar-collapse">
			<ul class="nav" id="side-menu">
				<li class="nav-header">
					<div class="dropdown profile-element" style="text-align:center;">
						<span><img alt="image" class="img-circle"
							src="${pageContext.request.contextPath}/img/profile_small.png"></span>
						<a data-toggle="dropdown" class="dropdown-toggle" href="#"
							aria-expanded="false"> <span class="clear"> <span
								class="text-muted text-xs block"> <strong
									class="font-bold"> <!-- 登录人 --> <shiro:principal />
								</strong> <b class="caret"></b>
							</span>
						</span>
						</a>
						<ul class="dropdown-menu animated fadeInRight m-t-xs">
							<li class="divider"></li>
							<li><a href="logout.action">安全退出</a></li>
						</ul>
					</div>
				</li>

				<li><a href="#"><i class="fa fa-file-text"></i> <span
						class="nav-label">数据录入</span><span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a class="J_menuItem"
							href="${pageContext.request.contextPath}/pages/province/add.jsp">海洋强省数据录入</a></li>
						<li><a class="J_menuItem"
							href="${pageContext.request.contextPath}/pages/city/add.jsp">海洋强市数据录入</a></li>
						<li><a class="J_menuItem"
							href="${pageContext.request.contextPath}/pages/county/add.jsp">海洋强县数据录入</a></li>

					</ul></li>
				<li><a href="#"><i class="fa fa-bar-chart"></i> <span
						class="nav-label">数据分析</span><span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a class="J_menuItem"
							href="${pageContext.request.contextPath}/pages/province/analysis.jsp">海洋强省数据分析</a></li>
						<li><a class="J_menuItem"
							href="${pageContext.request.contextPath}/pages/city/analysis.jsp">海洋强市数据分析</a></li>
						<li><a class="J_menuItem"
							href="${pageContext.request.contextPath}/pages/county/analysis.jsp">海洋强县数据分析</a></li>
					</ul></li>
				<li><a href="#"><i class="fa fa-check-square-o"></i> <span
						class="nav-label">评价方法</span><span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a class="J_menuItem"
							href="${pageContext.request.contextPath}/pages/result.jsp">海洋强省评价结果</a></li>
					</ul></li>
				<li><a href="#"><i class="fa fa-pencil-square-o"></i> <span
						class="nav-label">指标权重修改</span><span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a class="J_menuItem"
							href="${pageContext.request.contextPath}/pages/province/weight.jsp">海洋强省权重修改</a></li>
						<li><a class="J_menuItem"
							href="${pageContext.request.contextPath}/pages/city/weight.jsp">海洋强市权重修改</a></li>
						<li><a class="J_menuItem"
							href="${pageContext.request.contextPath}/pages/county/weight.jsp">海洋强县权重修改</a></li>
					</ul></li>
				<li><a href="#"><i class="fa fa-pencil"></i> <span
						class="nav-label">数据校正</span><span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a class="J_menuItem"
							href="${pageContext.request.contextPath}/pages/province/revise.jsp">海洋强省数据校正</a></li>
						<li><a class="J_menuItem" href="${pageContext.request.contextPath}/pages/city/revise.jsp">海洋强市数据校正</a></li>
						<li><a class="J_menuItem" href="${pageContext.request.contextPath}/pages/county/revise.jsp">海洋强县数据校正</a></li>
					</ul></li>
				<li><a href="#"><i class="fa fa-line-chart"></i> <span
						class="nav-label">校正数据分析</span><span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a class="J_menuItem"
							href="${pageContext.request.contextPath}/pages/province/analysisAlter.jsp">海洋强省数据分析</a></li>
						<li><a class="J_menuItem"
							href="${pageContext.request.contextPath}/pages/city/analysisAlter.jsp">海洋强市数据分析</a></li>
						<li><a class="J_menuItem" href="${pageContext.request.contextPath}/pages/county/analysisAlter.jsp">海洋强县数据分析</a></li>
					</ul></li>
				<li><a href="#"><i class="fa fa-users"></i> <span
						class="nav-label">用户管理</span><span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a class="J_menuItem"
							href="${pageContext.request.contextPath}/getAllUser.action">用户管理</a></li>
					</ul></li>
			</ul>
		</div>
		</nav>
		<div id="page-wrapper" class="gray-bg dashbard-1">


			<div class="row content-tabs">
				<!--  修改隐藏按钮的位置到导航栏-->
				<div class="roll-nav"
					style="width: 50px; padding-top: 7px; margin-left: 5px;">
					<a class="navbar-minimalize minimalize-styl-1 btn btn-primary "
						href="#"><i class="fa fa-bars"></i> </a>
				</div>
				<!--  修改结束-->
				<!-- 向左的双箭头 -->
				<button class="roll-nav roll-left J_tabLeft"
					style="margin-left: 60px;">
					<i class="fa fa-backward"></i>
				</button>

				<nav class="page-tabs J_menuTabs" style="margin-left:100px;">
				<div class="page-tabs-content">

					<a href="javascript:;" class="active J_menuTab" data-id="Public">首页</a>

				</div>
				</nav>
				<button class="roll-nav roll-right J_tabRight">
					<i class="fa fa-forward"></i>
				</button>

				<div class="btn-group roll-nav roll-right">

					<button class="dropdown J_tabClose" data-toggle="dropdown">
						关闭操作<span class="caret"></span>

					</button>
					<ul role="menu" class="dropdown-menu dropdown-menu-right">
						<li class="J_tabShowActive"><a>定位当前选项卡</a></li>
						<li class="divider"></li>
						<li class="J_tabCloseAll"><a>关闭全部选项卡</a></li>
						<li class="J_tabCloseOther"><a>关闭其他选项卡</a></li>
					</ul>
				</div>

				<a class="roll-nav roll-right J_tabExit" id="exit"> <i
					class="fa fa fa-sign-out"></i> 退出
				</a>
			</div>
			<div class="row">
				<img alt="" src="img/4.png" style="width:100%;">
			</div>
			<div class="row J_mainContent" id="content-main" style="height: 81%">
				<iframe class="J_iframe" name="iframe0" width="100%" height=100%
					src="public.html" frameborder="0" data-id="Public" seamless>
				</iframe>


			</div>
			<div class="footer  navbar-fixed-bottom" style="height: 35px">
				<div class="col-sm-offset-2 ">


					<div class="pull-right">
						<span>联系人：吴克俭 | 电话：0532-66782270 | 传真：0532-66782270 | </span> <span>Email：kejianwu@ouc.edu.cn
							| 单位：中国海洋大学 |</span> <span>地址：山东省青岛市松岭路268号海洋与大气学院 | 邮编：266100</span>
					</div>
				</div>
			</div>
		</div>
		<!--右侧部分结束-->

	</div>
	<script src="js/jquery.min.js?v=2.1.4"></script>
	<script src="js/bootstrap.min.js?v=3.3.5"></script>
	<!-- SweetAlert 提示框 -->
	<script src="js/plugins/sweetalert/sweetalert.min.js"></script>
	<script src="js/plugins/metisMenu/jquery.metisMenu.js"></script>
	<!-- 左边导航栏的收起以及上下滚动 -->
	<script src="js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
	<!--不打开新界面  -->
	<script src="js/plugins/layer/layer.min.js"></script>
	<!--不打开新界面，且左侧导航栏无样式  -->
	<script src="js/hplus.min.js?v=4.0.0"></script>
	<!-- 左侧导航栏无样式 -->
	<script type="text/javascript" src="js/contabs.min.js"></script>
	<script src="js/plugins/pace/pace.min.js"></script>
	<!-- 右侧边栏显示/隐藏 -->
	<script>
		/* 打开新界面 */
		function onclickOfAlarm() {
			var oBtn = document.getElementById('getAlarmDetails');
			oBtn.click();
			$.gritter.removeAll();
		}
	</script>
	<script src="js/plugins/gritter/jquery.gritter.min.js"></script>
	<!-- 点击退出连接 -->
	<script type="text/javascript">
		$("#exit").click(function() {
			sweetAlert({
				title : "确定退出？",
				text : "将退出系统并返回登录界面",
				type : "warning",
				showCancelButton : true,
				confirmButtonColor : "#DD6B55",
				confirmButtonText : "确定",
				cancelButtonText : "取消",
				closeOnConfirm : true
			}, function() {
	
				$("#exit").attr("href", "logout.action");
				var exit = document.getElementById("exit");
				exit.click();
			});
		})
		// 代码转移到后台
	</script>
	<script>
		//格式化字符串
		function StringBuffer() {
			this._strs = new Array;
		}
		StringBuffer.prototype.append = function(str) {
			this._strs.push(str);
		};
		StringBuffer.prototype.toString = function() {
			return this._strs.join("");
		};
	</script>

	<script language=javascript>
		function movr(src) {
			src.onselectstart = new Function("return false");
			with (src.style) {
	
			borderLeft = "4px solid buttonhighlight";
			borderRight = "4px solid buttonshadow";
			borderTop = "4px solid buttonhighlight";
			borderBottom = "4px solid buttonshadow";
			padding = "0";
			cursor = "hand";
			}
		}
		function mdwn(src) {
			src.onselectstart = new Function("return false");
			with (src.style) {
	
			borderRight = "1px solid buttonhighlight";
			borderLeft = "1px solid buttonshadow";
			borderBottom = "1px solid buttonhighlight";
			borderTop = "1px solid buttonshadow";
			padding = "0";
			}
		}
	</script>

</body>

</html>
