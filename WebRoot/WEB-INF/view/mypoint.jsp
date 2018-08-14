<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
		<title>车位详情</title>
		<link rel="stylesheet" href="<%=basePath%>css/mui.min.css"  />
		<link rel="stylesheet" href="<%=basePath%>css/public.css" />
		<link rel="stylesheet" href="<%=basePath%>css/mypoint.css" />
		<script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/mui.min.js" ></script>
		<script type="text/javascript">
		function canclePoint(pointId){
		var btnArray = ['否', '是'];
			mui.confirm('确认取消？', '提示',btnArray, function(e) {
				if (e.index == 1) {
	            	$.ajax({
						url:"<%=basePath%>user/canclePoint",
				        type:'POST',
				        contentType : "application/x-www-form-urlencoded;charset=UTF-8",
						dataType: "json",
				  	  	data:{
				     		pointId:pointId
				 			},
				  	  	success:function(data){
				  		  if(data.code == "0000"){
						  	window.location.reload(true);
				 		  }else{
				 			  mui.toast(data.msg);
				 		  }
				 	  },
				 	  error:function(XMLHttpRequest, textStatus, errorThrown){
							alert(XMLHttpRequest.status);  
		                    alert(XMLHttpRequest.readyState);  
		                    alert(textStatus);  
						}
			      });
		        } else {
		            
		        }
			});
			
		}
		
	  function delPoint(pointId){
		var btnArray = ['否', '是'];
			mui.confirm('确认删除？', '提示',btnArray, function(e) {
				if (e.index == 1) {
	            	$.ajax({
						url:"<%=basePath%>user/deletePoint",
				        type:'POST',
				        contentType : "application/x-www-form-urlencoded;charset=UTF-8",
						dataType: "json",
				  	  	data:{
				     		pointId:pointId
				 			},
				  	  	success:function(data){
				  		  if(data.code == "0000"){
						  	window.location.reload(true);
				 		  }else{
				 			  mui.toast(data.msg);
				 		  }
				 	  },
				 	  error:function(XMLHttpRequest, textStatus, errorThrown){
							alert(XMLHttpRequest.status);  
		                    alert(XMLHttpRequest.readyState);  
		                    alert(textStatus);  
						}
			      });
		        } else {
		            
		        }
			});
		}
		
		function toPay(id){
			if(id == ''){
				mui.toast('请选择需要支付的预约');
			}
			
			var btnArray = ['否', '是'];
			mui.confirm('确认支付？', '提示',btnArray, function(e) {
				if (e.index == 1) {
	            	$.ajax({
						url:"<%=basePath%>user/toPay",
				        type:'POST',
				        contentType : "application/x-www-form-urlencoded;charset=UTF-8",
						dataType: "json",
				  	  	data:{
				     		pointId:id
				 			},
				  	  	success:function(data){
				  		  if(data.code == "0000"){
				  		    mui.toast(data.msg);
						    setTimeout(function(){
						    	window.location.reload(true);
						    }, 500);
				 		  }else{
				 			  mui.toast(data.msg);
				 		  }
				 	  },
				 	  error:function(XMLHttpRequest, textStatus, errorThrown){
							alert(XMLHttpRequest.status);  
		                    alert(XMLHttpRequest.readyState);  
		                    alert(textStatus);  
						}
			      });
		        } else {
		            
		        }
			});
		}
		
		</script>
	</head>
	<body>
		<div class="mypoint_all">
		<c:forEach items="${bookingList}" var="coll" varStatus="status">
			<div class="mypoint_li">
				<div class="mypoint_li_top">
					<div class="mypoint_li_top_c">
						<div class="mypoint_li_top_c_1">
							<img src="<%=basePath%>img/index/ic_alarm_on.png"/>我的预约
						</div>
						<div class="mypoint_li_top_c_2">
						
						<%-- ${fn:substring(coll.bookingTime, 5, 16)}~${fn:substring(coll.bookingEndTime, 5, 16)} --%>
							
							${fn:replace(fn:replace(fn:substring(coll.bookingTime, 5, 16), 
                                '-', '月'), 
                                ' ', '日')}~${fn:replace(fn:replace(fn:substring(coll.bookingEndTime, 5, 16), 
                                '-', '月'), 
                                ' ', '日')}
						</div>
					</div>
					
					 <c:if test="${coll.bookingStatus==0}">
					 <div class="mypoint_li_top_stat bg_g">
						已取消
					</div>
					</c:if>
					<c:if test="${coll.bookingStatus==1}">
					 <div class="mypoint_li_top_stat bg_y">
						预约审核中
					</div>
					</c:if>
					<c:if test="${coll.bookingStatus==2}">
					 <div class="mypoint_li_top_stat bg_y">
						审核通过
					</div>
					</c:if>
					<c:if test="${coll.bookingStatus==3}">
					 <div class="mypoint_li_top_stat bg_y">
						预约成功
					</div>
					</c:if> 
					<c:if test="${coll.bookingStatus==4}">
					 <div class="mypoint_li_top_stat bg_g">
						审核不通过
					</div>
					</c:if>
					
				</div>
				<div class="mypoint_li_C">
					<div class="mypoint_li_C_li">
						<div class="mypoint_li_C_li_1 bg_b">
							
						</div>
						<div class="mypoint_li_C_li_2">
							车牌号码
						</div>
						<div class="mypoint_li_C_li_3">
							${coll.userCarNum}
						</div>
					</div>
					<div class="mypoint_li_C_li">
						<div class="mypoint_li_C_li_1 bg_l">
							
						</div>
						<div class="mypoint_li_C_li_2">
							联系人
						</div>
						<div class="mypoint_li_C_li_3">
							${coll.userName}
						</div>
					</div>
					<div class="mypoint_li_C_li ">
						<div class="mypoint_li_C_li_1 bg_r">
							
						</div>
						<div class="mypoint_li_C_li_2">
							联系电话
						</div>
						<div class="mypoint_li_C_li_3">
							${coll.userPhoneNum}
						</div>
					</div>
					<div class="mypoint_li_C_li">
						<div class="mypoint_li_C_li_1 bg_y">
							
						</div>
						<div class="mypoint_li_C_li_2">
							位置
						</div>
						<div class="mypoint_li_C_li_3">
							${coll.packBean.packAddress}
						</div>
					</div>
					<c:if test="${coll.bookingStatus != 1}">
					<div class="mypoint_li_C_li">
						<div class="mypoint_li_C_li_1 bg_y">
							
						</div>
						<div class="mypoint_li_C_li_2">
							车位
						</div>
						<div class="mypoint_li_C_li_3">
							
							<c:if test="${coll.packCarBean.packCarNumber == null}">
							分配车位中...
							</c:if>
							${coll.packCarBean.packCarNumber}
						</div>
					</div>
					</c:if>
				</div>
				
				 <c:if test="${coll.bookingStatus==1}">
					 <div class="mypoint_li_btn bg_b" onclick="canclePoint('${coll.id}')">
					取消预约
					</div>
				</c:if>
				<c:if test="${coll.bookingStatus==2}">
				<c:if test="${coll.paymentStaus == 0}">
					<div class="mypoint_li_btn bg_b" onclick="toPay(${coll.id})">
					去支付
					</div>
				</c:if>
				</c:if> 
				<c:if test="${coll.bookingStatus==3}">
				<c:if test="${coll.paymentStaus == 0}">
					<div class="mypoint_li_btn bg_b" onclick="toPay(${coll.id})">
					去支付
					</div>
				</c:if>
				</c:if> 
				<c:if test="${coll.bookingStatus==0}">
					 <div class="mypoint_li_btn bg_b" onclick="delPoint('${coll.id}')">
					删除
					</div>
				</c:if> 
				<c:if test="${coll.bookingStatus==4}">
					 <div class="mypoint_li_btn bg_b" onclick="delPoint('${coll.id}')">
					删除
					</div>
				</c:if> 
				
				
			</div>
		</c:forEach>
		</div>
	</body>
</html>
