<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>输入预约信息</title>
<link rel="stylesheet" href="<%=basePath%>css/mui.min.css" />
<link rel="stylesheet" href="<%=basePath%>css/mui.picker.min.css" />
<link rel="stylesheet" href="<%=basePath%>css/public.css" />
<link rel="stylesheet" href="<%=basePath%>css/appointmsg.css" />
<script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/mui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/mui.picker.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/appointmsg.js"></script>
<script type="text/javascript">
	function booking() {
		//车牌号码
		var carnum =$("[ischoose=1]").find("span").html();
		
		//用户姓名
		var name = document.getElementById("name").value;
		if(name == ''){mui.toast('姓名不能为空');return;}
		var phone = document.getElementById("phone").value;
		var re = /^1[3,4,5,6,7,8,9]\d{9}$/;
		if(!re.test(phone)){
			mui.toast('手机号不正确');return;
		}
		var timebegin = document.getElementById("timebegin").value;
		if(timebegin == ''){mui.toast('预约时间不能为空');return;}
		var timeend = document.getElementById("timeend").value;
		if(timeend == ''){mui.toast('预约时间不能为空');return;}
				if(timebegin >= timeend){
			mui.toast('时间段不正确');return;
		}

		var packId = $('#pack_id').html();
		var flag = $('#pack_flag').html();
		
       	$.ajax({
				url:"<%=basePath%>pack/booking",
		        type:'POST',
		        contentType : "application/x-www-form-urlencoded;charset=UTF-8",
				dataType: "json",
		  	  	data:{
		     		carnum:carnum,
		     		name:name,
		     		phone:phone,
		     		timebegin:timebegin,
		     		timeend:timeend,
		     		packId:packId,
		     		flag:flag
		 			},
		  	  	success:function(data){
		  		  if(data.code == "0000"){
				  	window.location.href="<%=basePath%>pack/bookingresult";
		 		  }else{
		 			  mui.toast(data.msg);
		 		  }
		 	  },
		 	  error:function(XMLHttpRequest, textStatus, errorThrown){
					/* alert(XMLHttpRequest.status);  
                    alert(XMLHttpRequest.readyState);  
                    alert(textStatus);  */
                    mui.toast('预约失败'); 
				}
	      }); 
	}
	
	$(document).ready(function(){
				  $(".addcar_bc").click(function(){
				    window.location.href="<%=basePath%>user/addcar";
				  });
			});
</script>

</head>

<body>
<div id='pack_id' style='display:none'>${pack.packId}</div>
<div id='pack_flag' style='display:none'>${pack.flag}</div>
	<div class="appointmsg_all">
		<div class="appointmsg_title">我的车辆</div>
		<ul id="carlist" class="mui-table-view">
			<c:forEach items="${userinfolist}" var="coll" varStatus="status">
				<li class="mui-table-view-cell">
					<div class="mui-slider-right mui-disabled">
						<a class="mui-btn mui-btn-red">删除</a>
					</div>
					<div class="mui-slider-handle"
						<c:if test="${status.index==0}"> ischoose="1"</c:if>
						<c:if test="${status.index!=0}"> ischoose="0"</c:if>
						data-id="${status.index}">
						<c:if test="${status.index==0}">
							<img src="<%=basePath%>img/index/Group7C.png" />
						</c:if>
						<c:if test="${status.index!=0}">
							<img src="<%=basePath%>img/index/Group7C2.png" />
						</c:if>
						<span>${coll.carnum}</span>
					</div></li>
			</c:forEach>
		</ul>
		<div class="addcar_bc">
			<div class="addcar">+添加车辆</div>
		</div>


		<div class="appointmsg_title">根据时间选择为你匹配最合适车位</div>
		<div id='appointmsg_form' class="mui-input-group">
			<div class="mui-input-row">
				<div class="appointmsg_form_lf">
					<span>*</span>联系人
				</div>
				<div class="appointmsg_form_rg">
					<input id='name' type="text" style="font-size: 0.9em;"
						placeholder="请输入联系人姓名" />
				</div>
			</div>
			<div class="mui-input-row">
				<div class="appointmsg_form_lf">
					<span>*</span>联系电话
				</div>
				<div class="appointmsg_form_rg">
					<input id='phone' type="tel" style="font-size: 0.9em;"
						placeholder="请输入手机号码" />
				</div>
			</div>
			<div class="mui-input-row">
				<div class="appointmsg_form_lf">
					<span>*</span>预约开始时间
				</div>
				<div class="appointmsg_form_rg">
					<input id='timebegin' data-options='{}' class="btn" type="tel"
						style="font-size: 0.9em;" placeholder="请选择时间" readonly="readonly" />
				</div>
			</div>
			<div class="mui-input-row">
				<div class="appointmsg_form_lf">
					<span>*</span>预约结束时间
				</div>
				<div class="appointmsg_form_rg">
					<input id='timeend' data-options='{}' class="btn" type="tel"
						style="font-size: 0.9em;" placeholder="请选择时间" readonly="readonly" />
				</div>
			</div>
		</div>
		<div class="appointmsg_xy">
			<img src="<%=basePath%>img/index/Group7C.png" />同意<span>《车位预约协议》</span>
		</div>
		<div class="appointmsg_sure" onclick="booking()">立即预约</div>
	</div>
</body>
</html>
