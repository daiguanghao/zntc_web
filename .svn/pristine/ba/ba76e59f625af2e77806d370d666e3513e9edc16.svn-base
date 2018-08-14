package com.eqy.web.dao;

import java.util.List;

import com.eqy.web.pojo.BookingResultBean;

public interface BookingResultBeanMapper {
	
	/**
	 * 查询预约订单是否匹配车辆
	 * @return
	 */
	public List<BookingResultBean> selectBookingResultList(String openid,String id);
	
	/**
	 * 更新预约订单支付状态
	 * @param openid
	 * @param bookingLogId
	 * @return
	 */
	public int updatePayMent(String openid,String bookingLogId);
	
	/**
	 * 插入匹配的车位与预约信息
	 * @param bookingResultBean
	 * @return
	 */
	public int insertBookingResultList(BookingResultBean bookingResultBean);

}
