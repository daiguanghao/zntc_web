package com.eqy.web.dao;

import java.util.List;

import com.eqy.web.pojo.PackBean;

/**
 * 
 * @author daiguanghao
 * 
 */
public interface PackBeanMapper {
	/**
	 * @Title: selectUserList
	 * @Description: 查询所有用户数据
	 * @param @return 设定文件
	 * @return List<UserBean> 返回类型
	 * @throws
	 */
	List<PackBean> selectAllPackList();
	
	/**
	 * 
	 * @return
	 */
	PackBean	selectDetailPackById(String id);
	
	/**
	 * 增加机械车位使用量
	 * @param id
	 * @return
	 */
	public int updateMactotalUsenum(String id);
	
	/**
	 * 增加普通车位使用量
	 * @param id
	 * @return
	 */
	public int updateGratotalUsenum(String id);
	
	
}
