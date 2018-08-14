package com.eqy.web.dao;

import com.eqy.web.pojo.PackCarBean;

public interface PackCarBeanMapper {
	
	/**
	 * 随机选取一个车位
	 * @param packId
	 * @param packCarType
	 * @return
	 */
	public PackCarBean selectByPackIdAndType(String packId,String packCarType);
	
	/**
	 * 更新随机选中的车位状态
	 * @param id
	 * @return
	 */
	public int updatePackCarStatus(int id);

}
