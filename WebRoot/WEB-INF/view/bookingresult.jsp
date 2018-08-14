<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
		<title>预约成功</title>
		<link rel="stylesheet" href="<%=basePath%>css/mui.min.css"  />
		<link rel="stylesheet" href="<%=basePath%>css/public.css" />
		<link rel="stylesheet" href="<%=basePath%>css/success.css" />
		<script type="text/javascript" src="<%=basePath%>js/mui.min.js" ></script>
		<script type="text/javascript" src="<%=basePath%>js/jquery-2.1.0.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				  $(".success_fh").click(function(){
				    window.location.href="<%=basePath%>index";
				  });
				  $(".success_ck").click(function(){
				   window.location.href="<%=basePath%>user/tomypoint";
				  });
			});
		</script>
		
	</head>
	<body>
		<div class="success_all">
			<div class="success_1">
				<img src="<%=basePath%>img/index/icon_g.png"/>
				<div class="success_1_t">
					预约成功
				</div>
				<div class="success_1_c">
					您已预约成功，审核通过后将为您保留合适的车位。<br />
					如有疑问可拨打电话：400-928-1000
				</div>
			</div>
			<div class="success_x">
				<div class="success_x_div">
					您还可以
				</div>
			</div>
			<div class="success_ck">
				<img src="<%=basePath%>img/index/Group.png"/>
				<div class="to_mypoints">
					查看我的预约
				</div>
			</div>
			<div class="success_fh">
				<img src="<%=basePath%>img/index/Group4.png"/>
				<div class="back_index">
					返回到首页
				</div>
			</div>
			
		</div>
	</body>
</html>

