package com.eqy.web.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eqy.web.pojo.PackBean;
import com.eqy.web.pojo.UserBookingBean;
import com.eqy.web.service.IPackService;

/**
 * 
 * @ClassName: LoginController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author:  戴光浩
 * @date: 2018-5-11 上午11:36:12
 */
@Controller
@RequestMapping(value = "/")
public class LoginController
{
	protected static final Logger logger = Logger.getLogger(LoginController.class);

	@Autowired
    private IPackService packService;
    /**
     * 方法描述: 微信授权登录，跟进code获取用户的openid
     * 
     * @param request
     * @return
     * @see
     */
    @RequestMapping(value="/index")
    public String index(HttpServletRequest request,HttpServletResponse response ,Model model,
                                                                        @RequestParam(required = false) String code,
                                                                        @RequestParam(required = false) String openId)
    {
    	
    	try{
            if(openId == null ){
               //openId = HttpGetOpenId.getOpenIdSendUrl(code);
            	openId = "daiguanghao";
               logger.error("调用接口生成  openId=="+openId);
            }
            if(openId ==null || openId.equals("")){
                logger.error("所传参数 activityCode 或 openId 为空==="+openId);
                return "error";
            }
            request.getSession().setAttribute("OPENID", openId);
            System.out.println("当前用户"+openId+"存入会话中");
            model.addAttribute("openId",openId);
            //获取停车场信息
            List<PackBean> packList = packService.selectAllPackList();
            if(packList == null){
            	logger.debug("获取停车场信息失败");
            }
            logger.debug("获取停车场信息:"+packList.size());
            System.out.println(packList.size());
            model.addAttribute("packlist",packList);
            return "index";
        }catch(Exception e){
            logger.error("服务器出现异常！",e);
            e.printStackTrace();
            return "error";
        }
    }
   
}
