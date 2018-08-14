package com.eqy.web.pojo;
/**
 * 用户登记车辆信息，包括用户的一些基本信息和车辆信息
 * @author daiguanghao
 *
 */
public class UserInfoBean {
	private int id;
	private String openId;
	//用户车牌号
	private String carnum;
	private String dlPic1;
	private String dlPic2;
	//用户绑定的一卡通号码，后续需求可能需要使用
	private String cardNum;
	private String userPhoneNum;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getCarnum() {
		return carnum;
	}
	public void setCarnum(String carnum) {
		this.carnum = carnum;
	}
	public String getDlPic1() {
		return dlPic1;
	}
	public void setDlPic1(String dlPic1) {
		this.dlPic1 = dlPic1;
	}
	public String getDlPic2() {
		return dlPic2;
	}
	public void setDlPic2(String dlPic2) {
		this.dlPic2 = dlPic2;
	}
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	public String getUserPhoneNum() {
		return userPhoneNum;
	}
	public void setUserPhoneNum(String userPhoneNum) {
		this.userPhoneNum = userPhoneNum;
	}
	@Override
	public String toString() {
		return "UserInfoBean [id=" + id + ", openId=" + openId + ", carnum="
				+ carnum + ", dlPic1=" + dlPic1 + ", dlPic2=" + dlPic2
				+ ", cardNum=" + cardNum + ", userPhoneNum=" + userPhoneNum
				+ "]";
	}
	
}
