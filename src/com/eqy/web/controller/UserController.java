package com.eqy.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.eqy.utils.DateTimeUtil;
import com.eqy.utils.MD5;
import com.eqy.web.pojo.UserBookingBean;
import com.eqy.web.pojo.UserInfoBean;
import com.eqy.web.service.IPackService;

import java.util.regex.*;

/**
 * @classname:UserCOntroller
 * @Description: TODO(用户相关信息)
 * @author Administrator
 * @date: 2018-6-29 下午14:36:12
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
	protected static final Logger logger = Logger.getLogger(UserController.class);
	@Autowired
	protected IPackService packService;
	/**
	 * 我的预约查看
	 * @return
	 */
	@RequestMapping("/tomypoint")
	public String myPoint(HttpServletRequest request,HttpServletResponse response ,Model model){
		
		String openid = (String)request.getSession().getAttribute("OPENID");
		if(openid == null){
			return "error";
		}
		
		List<UserBookingBean> userBookingList = packService.selectUserBookingList(openid);
//		System.out.println(userBookingList.size());
		model.addAttribute("bookingList",userBookingList);
		
//		System.out.println(userBookingList);
		
		return "mypoint";
		
	}
	
	/**
	 * 取消预约
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/canclePoint")
	public Map<String, String> canclePoint(HttpServletRequest request){		
		Map<String, String> map = new HashMap<String, String>();
		String openid = (String)request.getSession().getAttribute("OPENID");
		String pointId = request.getParameter("pointId");
		int id = 0;
		try {
			id = Integer.parseInt(pointId);
		} catch (Exception e) {
			map.put("code", "0001");
			map.put("msg", "没有查到此预约");
			return map;
		}
		int res = packService.updateUserBooking(openid, id);
		if(res == 1){
			map.put("code", "0000");
			map.put("msg", "已取消");
		}else{
			map.put("code", "0001");
			map.put("msg", "取消失败");
		}
		return map;
	}
	
	/**
	 * 删除已取消的预约
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deletePoint")
	public Map<String, String> deletePoit(HttpServletRequest request){
		Map<String, String> map = new HashMap<String, String>();
		String openid = (String)request.getSession().getAttribute("OPENID");
		String pointId = request.getParameter("pointId");
		int id = 0;
		
		if(openid == null){
			map.put("code", "0001");
    		map.put("msg", "未获取到用户信息");
    		return map;
		}else if(pointId == null){
			map.put("code", "0001");
    		map.put("msg", "请选择要取消的订单");
    		return map;
		}

		try {
			id = Integer.parseInt(pointId);
		} catch (Exception e) {
			map.put("code", "0001");
			map.put("msg", "取消失败");
			return map;
		}
		int res = packService.updateUserBookinDelete(openid, id);
		if(res == 1){
			map.put("code", "0000");
			map.put("msg", "已删除");
		}else{
			map.put("code", "0001");
			map.put("msg", "删除失败");
		}
		return map;
	}
	
	/**
	 * 跳转到添加车辆信息界面
	 * @return
	 */
	@RequestMapping("/addcar")
	public String insertCarInfo(){
		return "addcar";
	}
	
	/**
	 * 添加车辆信息
	 * @param request
	 * @param response
	 * @param model
	 * @return 
	 */
	@ResponseBody
	@RequestMapping("/uploadcar")
	public Map<String, String> uploadCar(@RequestParam("file") MultipartFile[] multipartfiles,HttpServletRequest request,HttpServletResponse response ,Model model){
				
		int i=0;
		String[] imgFilePath = new String[2];
		Map<String, String> map = new HashMap<String, String>();//定义返回值
		String dir = "upload\\";
		String path = request.getSession().getServletContext().getRealPath("/")+dir;//定义图片保存路径
		String carnum = request.getParameter("carnum");
		String openid = (String)request.getSession().getAttribute("OPENID");
		String pattern = "^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$";
		
		if(openid == null){
			map.put("code", "0001");
    		map.put("msg", "未获取到用户信息");
    		return map;
		}
		
		if(carnum == null || !Pattern.matches(pattern, carnum)){
			map.put("code", "0001");
    		map.put("msg", "车牌号格式不正确");
    		return map;
		}
		
		if(multipartfiles != null && multipartfiles.length != 0){
            if(null != multipartfiles && multipartfiles.length > 0){
                //遍历并保存文件
                for(MultipartFile file : multipartfiles){
                	
                	if(file.getSize() > 5242880){
                		map.put("code", "0001");
                		map.put("msg", "请上传小于5M的图片");
                		return map;
                	}else{
                		String date = DateTimeUtil.getTodayChar17();
            			int num = (int)(Math.random()*(100-10)+10);
            			String name = MD5.Md5(date+""+num);
                        imgFilePath[i] = (String) (dir+name+".jpg");//新生成的图片
                        
                        try {
    						file.transferTo(new File(path+name+".jpg"));
    					}catch (IOException e) {
    						logger.error("上传图片出错");
    					}
                	}
                	++i;
                }
            }
        }else{
        	map.put("code", "0001");
        	map.put("msg", "请选择图片");
        	return map;
        }
		
		UserInfoBean user = new UserInfoBean();
		user.setOpenId(openid);
		user.setCarnum(carnum);
		user.setDlPic1(imgFilePath[0]);
		user.setDlPic2(imgFilePath[1]);
		
		int res = packService.insertUserInfo(user);
		if(res == 1){
			map.put("code", "0000");
			map.put("msg", "添加成功");
		}else{
			map.put("code", "0001");
			map.put("msg", "请重新添加");
		}
		
		System.out.println(user);
		
		return map;
	}
	
	/**
	 * 去支付
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/toPay")
	public Map<String, String> toPay(HttpServletRequest request){
		Map<String, String> map = new HashMap<String, String>();
		
		String pointId = request.getParameter("pointId");
		String openid = (String)request.getSession().getAttribute("OPENID");
		
		if(openid == null){
			map.put("code", "0001");
			map.put("msg", "未获取到用户信息");
			return map;
		}
		if(pointId == null){
			map.put("code", "0001");
			map.put("msg", "请选择订单");
			return map;
		}
		int res =packService.updateUserBookingPay(openid, pointId,"1");
		if(res == 1){
			map.put("code", "0000");
			map.put("msg", "支付成功");
		}else{
			map.put("code", "0001");
			map.put("msg", "支付失败");
		}
		return map;
	}

}
