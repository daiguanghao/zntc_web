package com.eqy.web.dao;

import java.util.List;

import com.eqy.web.pojo.UserBookingBean;


/**
 * 
 * @author daiguanghao
 *
 */
public interface UserBookingBeanMapper {
	/**
	 * 
	 * @param openid
	 * @return
	 */
	public List<UserBookingBean> selectUserBookingList(String openid);
	
	/**
	 * 根据结束时间查询预约信息
	 * @param openid
	 * @param date
	 * @return
	 */
	public List<UserBookingBean> selectUserBookingListByEndtime(String openid,String date);
	
	/**
	 * 
	 * @param openid
	 * @return
	 */
	public List<UserBookingBean> selectUserCarnumBookingList(String openid,String carnum,String date);
	/**
	 * 车位预约
	 * @param userBookingBean
	 * @return
	 */
	public int insertUserBooking(UserBookingBean userBookingBean);
	
	/**
	 * 取消预约
	 * @param openid
	 * @param id
	 * @return
	 */
	public int updateUserBookingStatus(String openid,int id);
	
	/**
	 * 删除已取消的预约
	 * @param openid
	 * @param id
	 * @return
	 */
	public int updateUserBookingDelete(String openid,int id);
	
	/**
	 * 更新支付状态
	 * @param openid
	 * @param id
	 * @return
	 */
	public int updatePayMent(String date,String money,String openid,String id);
	
	/**
	 * 根据openid和id查询预约
	 * @param openid
	 * @param id
	 * @return
	 */
	public List<UserBookingBean> selectUserBookingListById(String openid,String id);
	
	/**
	 * 查询所有审核通过但未分配车位的预约
	 * @return
	 */
	public List<UserBookingBean> selectUserBookingListByStatus();
	
	/**
	 * 插入分配的车位ID
	 * @param id
	 * @return
	 */
	public int updateCarId(int id,int bookingId);
	
	/**
	 * 查询预约成功的车辆交给闸机进行识别
	 * @return
	 */
	public List<UserBookingBean> selectUserBookingListForApi();
	
	/**
	 * 提交车牌号成功后更新状态
	 * @param id
	 * @return
	 */
	public int updateIsPost(int id);
}
