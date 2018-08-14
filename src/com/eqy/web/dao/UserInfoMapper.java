package com.eqy.web.dao;

import java.util.List;

import com.eqy.web.pojo.UserInfoBean;

/**
 * 和用户相关的操作
 * @author Administrator
 *
 */
public interface UserInfoMapper {
	/**
	 * 
	 * @param openid
	 * @return
	 */
	List<UserInfoBean> selectAllUserInfoList(String openid);
	
	/**
	 * 插入车辆需信息
	 * @param userInfoBean
	 * @return
	 */
	int insertUserInfo(UserInfoBean userInfoBean);
}
