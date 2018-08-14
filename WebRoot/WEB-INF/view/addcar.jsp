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
		<title>添加车辆</title>
		<link rel="stylesheet" href="<%=basePath%>css/mui.min.css"  />
		<link rel="stylesheet" href="<%=basePath%>css/public.css" />
		<link rel="stylesheet" href="<%=basePath%>css/addcar.css" />
		<script type="text/javascript" src="<%=basePath%>js/mui.min.js" ></script>
		<script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
			
			$("#idc1").change(function () {
	           var obj=$("#idc1")[0].files[0];
	           var fr=new FileReader();
	           fr.onload=function () {
	               $("#avatarPreview").attr('src',this.result);
	               //console.log(this.result);
	               $("#idcF").val(this.result);
			      };
			      fr.readAsDataURL(obj);
			    });
			    $("#idc2").change(function () {
	           var obj=$("#idc2")[0].files[0];
	           var fr=new FileReader();
	           fr.onload=function () {
	               $("#avatarPreviewS").attr('src',this.result);
	               //console.log(this.result);
	               //$("#idcS").val(this.result);
			      };
			      fr.readAsDataURL(obj);
			    });
			    
			    $('.addcar_btn').click(function(){
			    	var carnum = $('#carnum').val();
			    	var express = /^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$/;
			    	
			    	 if (carnum =='' || carnum.length != 7 || !express.test(carnum)){
			    		mui.toast('请输入正确的车牌号');return;
			    	} 
			    	
			    	if($("#idc1")[0].files[0] == undefined){mui.toast('缺少图片 ');return;}
			    	if($("#idc2")[0].files[0] == undefined){mui.toast('缺少图片');return;}
			    	
			    	var formData = new FormData();
			    	formData.append("carnum", $('#carnum').val());
					formData.append("file", $("#idc1")[0].files[0]);
					formData.append("file", $("#idc2")[0].files[0]);
					
		        $.ajax({
		            type: "post",
		            url: "<%=basePath%>user/uploadcar",
		            //dataType: "json",
		            data: formData,
		            async: false,
		            cache: false,
		            contentType: false,
		            processData: false,
		            success: function(data){
		            	console.log(data);
		            	if(data.code == "0000"){
		            	mui.toast(data.msg);
		            	setTimeout(function(){
		            	window.location.href=document.referrer;
		            	}, 800);
						  	
				 		  }else{
				 			  mui.toast(data.msg);
				 		  }
		            },
		            error: function(err) {
		                mui.toast("服务器无响应");
		            }
		        });
			    	
			    });
			});
			
		</script>
	</head>
	<body>
	<form action="" enctype="multipart/form-data" method="post" id='carForm'>
		<div class="addcar_all">
			<div class="addcar_t">
				车牌号录入
			</div>
			<div class="addcar_num">
				<div class="addcar_num_1">
					<span>*</span>车牌号：
				</div>
				<div class="addcar_num_2">
					<input type="text" name="carnum" id="carnum" value="" />
				</div>
			</div>
			<div class="addcar_t">
				上传行驶证<span>（请确保上传照片清晰）</span>
			</div>
			
			<div class="addcar_img" onclick="idc1.click()" >
			<input name='idCard1' type='file' id='idc1' hidden="hidden" accept="image/png, image/jpeg, image/jpg" />
			
			<img src="<%=basePath%>img/index/Group5z.png" id="avatarPreview"/>
			</div>
			
			<div class="addcar_img" onclick="idc2.click()">
			<input name='idCard2' type='file' id='idc2' hidden="hidden" accept="image/png, image/jpeg, image/jpg" />
			
				<img src="<%=basePath%>img/index/Group5.png" id='avatarPreviewS'/>
			</div>
			<div class="addcar_ts">
				翼企云将为您提供信息安全
			</div>
			<div class="addcar_btn">
				确认添加
			</div>
		</div>
		</form>
	</body>
</html>
