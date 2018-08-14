package com.eqy.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.eqy.utils.DateTimeUtil;
import com.eqy.web.dao.BookingResultBeanMapper;
import com.eqy.web.dao.PackBeanMapper;
import com.eqy.web.dao.PackCarBeanMapper;
import com.eqy.web.dao.UserBookingBeanMapper;
import com.eqy.web.dao.UserInfoMapper;
import com.eqy.web.pojo.BookingResultBean;
import com.eqy.web.pojo.PackBean;
import com.eqy.web.pojo.PackCarBean;
import com.eqy.web.pojo.UserBookingBean;
import com.eqy.web.pojo.UserInfoBean;
import com.eqy.web.service.IPackService;

/**
 * 
 * @author daiguanghao
 *
 */

@Service("userService")
public class PackServiceImpl implements IPackService {

	@Autowired
    protected PackBeanMapper packBeanMapper;
	@Autowired
    protected UserInfoMapper userInfoMapper;
	@Autowired
    protected UserBookingBeanMapper userBookingBeanMapper;
	@Autowired
	protected BookingResultBeanMapper bookingResultBeanMapper;
	@Autowired
	protected PackCarBeanMapper packCarBeanMapper;
	
	@Override
	public List<PackBean> selectAllPackList() {
		// TODO Auto-generated method stub
		return packBeanMapper.selectAllPackList();
	}
	@Override
	public PackBean selectDetailPackById(String id) {
		// TODO Auto-generated method stub
		return packBeanMapper.selectDetailPackById(id);
	}
	@Override
	public List<UserInfoBean> selectUserCarList(String openid) {
		// TODO Auto-generated method stub
		return userInfoMapper.selectAllUserInfoList(openid);
	}
	@Override
	public List<UserBookingBean> selectUserBookingList(String openid) {
		// TODO Auto-generated method stub
		return userBookingBeanMapper.selectUserBookingList(openid);
	}
	@Override
	public List<UserBookingBean> selectUserBookingList(String openid,String carnum,String date) {
		// TODO Auto-generated method stub
		return userBookingBeanMapper.selectUserCarnumBookingList(openid,carnum,date);
	}
	@Transactional
	@Override
	public int insertUserBooking(UserBookingBean userBookingBean) {
		return userBookingBeanMapper.insertUserBooking(userBookingBean);
	}
	@Transactional
	@Override
	public int insertUserInfo(UserInfoBean userInfoBean) {
		// TODO Auto-generated method stub
		return userInfoMapper.insertUserInfo(userInfoBean);
	}
	@Transactional
	@Override
	public int updateUserBooking(String openid, int id) {
		if(id == 0){
			return 0;
		}
		
		try {
			userBookingBeanMapper.updateUserBookingStatus(openid, id);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}
	@Transactional
	@Override
	public int updateUserBookinDelete(String openid, int id) {
		if(id == 0){
			return 0;
		}
		try {
			userBookingBeanMapper.updateUserBookingDelete(openid, id);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}
	@Override
	public List<UserBookingBean> selectUserBookingListBytime(String openid,
			String date) {
		// TODO Auto-generated method stub
		return userBookingBeanMapper.selectUserBookingListByEndtime(openid, date);
	}
	@Transactional
	@Override
	public int updateUserBookingPay(String openid, String id,String money) {
		//1.查询是否分配了停车位
		List<BookingResultBean> bookingResult = bookingResultBeanMapper.selectBookingResultList(openid, id);
		System.out.println("--user--------------------");
		System.out.println(bookingResult.size());
		String date = DateTimeUtil.getTodayChar14_();  //获取当前时间为支付时间
		
		//1.1未分配停车位 执行一步操作
		if(bookingResult.size() == 0){
			try {
				userBookingBeanMapper.updatePayMent(date, money, openid, id);
				return 1;
			} catch (Exception e) {
				return 0;
			}
		}else{
		//1.2已分配停车位  执行两步操作
			try {
				userBookingBeanMapper.updatePayMent(date, money, openid, id);
				bookingResultBeanMapper.updatePayMent(openid, id);
				return 1;
			} catch (Exception e) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return 0;
			}
		}
	}
	/**
	 * 获取审核通过的并且未分配车位的预约
	 */
	@Override
	public List<UserBookingBean> selectUserBookingListByStatus() {
		// TODO Auto-generated method stub
		return userBookingBeanMapper.selectUserBookingListByStatus();
	}
	@Transactional
	@Override
	public int matchCarForBooking(int bookingId,String packId,String packCarType,String openid,String isPay) {
		
		try {
			//随机查出一个空的车位
			PackCarBean packCar = packCarBeanMapper.selectByPackIdAndType(packId, packCarType);
//			System.out.println(packCar);
			if(packCar == null){
				return 2;//由于缺少车位导致预约失败
			}
			
				//将查询出的车位状态变为不可再预约
				packCarBeanMapper.updatePackCarStatus(packCar.getId());

				//2.将车位的id写入t_booking_log表并在booking_result生成新的纪录
				userBookingBeanMapper.updateCarId(packCar.getId(), bookingId);

				BookingResultBean br = new BookingResultBean();
				br.setOpenid(openid);
				br.setBookingLogId(bookingId+"");
				br.setPackId(packId);
				br.setIsPay(isPay);
				br.setPackCarId(packCar.getId()+"");
				//插入booking_result表
				bookingResultBeanMapper.insertBookingResultList(br);
//				System.out.println("---------4--------------");
		

			//3.减少对应车库车位的数量packId,packCarType
			if(packCarType.equals("1")){
				//普通车位减1
				packBeanMapper.updateGratotalUsenum(packId);

			}else if(packCarType.equals("0")){
				//机械车位减1
				packBeanMapper.updateMactotalUsenum(packId);

			}else{
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//				System.out.println("没此类型车位");
			}
			return 1;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return 0;//流程未执行出错导致失败
		}
	}
	@Override
	public List<UserBookingBean> selectUserBookingForApi() {
		
		return userBookingBeanMapper.selectUserBookingListForApi();
	}
	@Override
	public int updateUserBookingIsPost(int id) {
		
		return userBookingBeanMapper.updateIsPost(id);
	}

}
