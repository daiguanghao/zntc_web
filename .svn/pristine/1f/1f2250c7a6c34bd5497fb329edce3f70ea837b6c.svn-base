package com.eqy.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eqy.constants.SystemConstants;
import com.eqy.utils.DateTimeUtil;
import com.eqy.web.pojo.PackBean;
import com.eqy.web.pojo.UserBookingBean;
import com.eqy.web.pojo.UserInfoBean;
import com.eqy.web.service.IPackService;

/**
 * 
 * @ClassName: LoginController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author:  戴光浩
 * @date: 2018-6-26 上午11:36:12
 */
@Controller
@RequestMapping(value = "/pack")
public class PackInfoController {
	
	
	protected static final Logger logger = Logger.getLogger(PackInfoController.class);
	@Autowired
    private IPackService packService;
    /**
     * 方法描述: 
     * 
     * @param request
     * @return
     * @see
     */
    @RequestMapping(value="/detail")
    public String index(HttpServletRequest request,HttpServletResponse response ,Model model,
                                                                        @RequestParam(required = false) String id)
    {
    	System.out.println("------------:"+id);
    	try{
            if(id == null ){
               logger.error("id信息不能为空");
            }
            PackBean pack= packService.selectDetailPackById(id);
            if(pack == null){
            	logger.debug("获取停车场信息失败");
            }
            model.addAttribute("pack",pack);
            return "choose";
        }catch(Exception e){
            logger.error("服务器出现异常！",e);
            return "error";
        }
    }
    
    /**
     * 方法描述: 准备进入预约表单页面
     * 
     * @param request
     * @return
     * @see
     */
    @RequestMapping(value="/tobooking")
    public String index(HttpServletRequest request,HttpServletResponse response ,Model model,
                                                                        @RequestParam(required = false) String id,@RequestParam(required = false) String flag)
    {
    	System.out.println("停车场ID:"+id);
    	System.out.println("车位标识符:"+flag);
    	try{
            if(id == null || flag == null){
               logger.error("id或者flag信息不能为空");
            }
            String openid = (String) request.getSession().getAttribute("OPENID");
            if(openid == null ){
                logger.error("没有获取到用户信息");
             }
            //获取用户当前等级的车辆信息
            List<UserInfoBean> userInfoList = packService.selectUserCarList(openid);
//            System.out.println(userInfoList.size());
            model.addAttribute("userinfolist",userInfoList);
            Map<String, String> map = new HashMap<String, String>();
            map.put("packId", id);
            map.put("flag", flag);
            model.addAttribute("pack", map);
            
            return "appointmsg";
        }catch(Exception e){
            logger.error("服务器出现异常！",e);
            return "error";
        }
    }
    
    /**
     * 停车位预约
     * @param request
     * @param response
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/booking")
    public Map<String,Object>  booking(HttpServletRequest request,HttpServletResponse response ,Model model)
    {
    	Map<String,Object> map = new HashMap<String,Object>();
		String openid = (String)request.getSession().getAttribute("OPENID");
		String date = DateTimeUtil.getTodayChar12_(); 
		String regex = "^1[3,4,5,6,7,8,9]\\d{9}$";
		if(openid == null){
			map.put("code", "0001");
			map.put("msg",  "没取到当前微信用户信息");
			return map;
		}else{
			String packid = (String)request.getParameter("packId");
			String flag = (String)request.getParameter("flag");
			String carnum = (String)request.getParameter("carnum");
			String name = (String)request.getParameter("name");
			String phone = (String)request.getParameter("phone");
			String timebegin = (String)request.getParameter("timebegin");
			String timeend = (String)request.getParameter("timeend");
			System.out.println("获取到用户预约信息：carnum="+carnum+",name="+name+",phone="+phone+",timebegin="+timebegin+",timeend="+timeend);
			if(carnum == null || name == null ||phone == null ||timebegin == null ||timeend == null ){
				//请填写完整信息
				map.put("code", "1004");
				map.put("msg",  "请填写完整信息");
				return map;
			}
			if(!Pattern.matches(regex, phone)){
				map.put("code", "1005");
				map.put("msg",  "手机号不正确");
				return map;
			}
			if(timebegin.compareTo(timeend) >= 0){
				map.put("code", "1006");
				map.put("msg",  "预约时间段不正确");
				return map;
			}
			if(timebegin.compareTo(date) < 0){
				map.put("code", "1007");
				map.put("msg",  "预约时间不正确");
				return map;
			}
			
			//判断未过期的预约数量是否超过
			
			List<UserBookingBean> ubbList = packService.selectUserBookingListBytime(openid, date);
			
			if(ubbList != null && ubbList.size() == SystemConstants.MAX_BOOKING_NUM_FORUSER ){
				//如果用户预约车位超过规定数量
				map.put("code", "1001");
				map.put("msg",  "当前已预约车位超过最大预约数量");
				return map;
			}
			ubbList = packService.selectUserBookingList(openid,carnum,date);
			System.out.println(ubbList);
			if(ubbList != null && ubbList.size() == SystemConstants.MAX_BOOKING_NUM_FORCARNUM ){
				//当前车牌号已经预约了车位
				map.put("code", "1002");
				map.put("msg",  "当前车牌号已经预约了车位");
				return map;
			}
			UserBookingBean ubb = new UserBookingBean();
			ubb.setUserOpenid(openid);
			ubb.setUserCarNum(carnum);
			ubb.setUserName(name);
			ubb.setUserPhoneNum(phone);
			ubb.setBookingTime(timebegin);
			ubb.setBookingEndTime(timeend);
			ubb.setOptTime(DateTimeUtil.getTodayChar17());
			ubb.setPackId(packid);
			ubb.setPackCarType(flag);
			int  resultCode  = packService.insertUserBooking(ubb);
			
			if( resultCode == 1){
				map.put("code", "0000");
				map.put("msg",  "success");
				return map;
			}else{
				map.put("code", "1003");
				map.put("msg",  "预约数据入库异常");
				return map;
			}
			
		}
    	
    }
    
    /**
     * 停车位预约结果
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value="/bookingresult")
    public String  bookingresult(HttpServletRequest request,HttpServletResponse response ,Model model)
    {
    	Map<String,Object> map = new HashMap<String,Object>();
		String openid = (String)request.getSession().getAttribute("OPENID");
		if(openid == null){
			return "error";
		}
		return "bookingresult";
		
    	
    }
}
