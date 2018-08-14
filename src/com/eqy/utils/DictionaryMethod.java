package com.eqy.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能简述：静态数据使用类(自主添加)
 * 
 * @author luoyb
 * @version 2017-4-20
 * @see DictionaryMethod
 * @since
 */
public class DictionaryMethod {
	/**
	 * 页面展示数量
	 */
	public final static int size = 10;
	/**
	 * 返回标志
	 */
	public static String staticType = "1";
	/**
	 * 通用券、合作券的状态
	 */
	public static String cardStatus = "";
	/**
	 * 父级目录跳转链接
	 */
	public static String parDirUrl = "";
	/**
	 * 父级目录名称
	 */
	public static String parDirName = "";
	/**
	 * 卡券类型:ty代表通用券,hz代表合作券
	 */
	public static String kind = "";
	/**
	 * 店铺渠道
	 */
	public static String channelName = "";
	/**
	 * 返回页面的页码
	 */
	public static String pageNum = "1";
	/**
	 * 用户角色
	 */
	public static Map<String, String> roleMap = new HashMap<String, String>();
	static {
		roleMap.put("0", "系统管理员");
		roleMap.put("1", "地市操作员");
		roleMap.put("2", "省级操作员");
		roleMap.put("3", "地市审核员");
		roleMap.put("4", "省级审核员");
	}
	public static Map<String, String> cardBrandMap = new HashMap<String, String>();
	static {
		cardBrandMap.put("1", "QQT");
		cardBrandMap.put("2", "SZX");
		cardBrandMap.put("3", "DGDD");
		cardBrandMap.put("4", "ALL");
	}
	public static Map<String, String> channelMap1 = new HashMap<String, String>();
	static {
		channelMap1.put("1", "obsh_activity");
		channelMap1.put("2", "wap_activity");
		channelMap1.put("3", "jsmcc");
		channelMap1.put("4", "school_activity");
		channelMap1.put("8", "none");
	}
	public static Map<String, String> channelNameMap = new HashMap<String, String>();
	static {
		channelNameMap.put("obsh_activity", "网厅");
		channelNameMap.put("wap_activity", "掌厅");
		channelNameMap.put("school_activity", "掌厅校园版");
		channelNameMap.put("none", "均不可见");
	}
	public static Map<String, String> channelMap2 = new HashMap<String, String>();
	static {
		channelMap2.put("1", "obsh");
		channelMap2.put("2", "wap");
		channelMap2.put("3", "sms");
		channelMap2.put("4", "jsmcc");
		channelMap2.put("5", "activity");
		channelMap2.put("8", "none");
	}
	public static Map<String, String> cardKindMap = new HashMap<String, String>();
	static {
		cardKindMap.put("1", "通用券");
		cardKindMap.put("2", "合作券");
		cardKindMap.put("3", "店铺券");
		cardKindMap.put("4", "购机券");
		cardKindMap.put("5", "理财券");
	}
	public static Map<String, String> cardStatusMap = new HashMap<String, String>();
	static {
		cardStatusMap.put("01", "待提交");
		cardStatusMap.put("02", "已提交待审核");
		cardStatusMap.put("03", "待测试");
		cardStatusMap.put("04", "待发布");
		cardStatusMap.put("05", "已发布");
		cardStatusMap.put("06", "审核未通过");
		cardStatusMap.put("07", "待撤回");
		cardStatusMap.put("08", "申请发布待审核");
		cardStatusMap.put("09", "已审核待发布");
	}
	public static Map<String, String> cityMap = new HashMap<String, String>();
	static {
		cityMap.put("99", "江苏省");
		cityMap.put("14", "南京市");
		cityMap.put("11", "苏州市");
		cityMap.put("12", "淮安市");
		cityMap.put("13", "宿迁市");
		cityMap.put("15", "连云港市");
		cityMap.put("16", "徐州市");
		cityMap.put("17", "常州市");
		cityMap.put("18", "镇江市");
		cityMap.put("19", "无锡市");
		cityMap.put("20", "南通市");
		cityMap.put("21", "泰州市");
		cityMap.put("22", "盐城市");
		cityMap.put("23", "扬州市");
		cityMap.put("0", "系统");
	}
	public static Map<String, String> useTypeMap = new HashMap<String, String>();
	static {
		// 卡券使用类型(0:图片类;1扫码类;2:编码类;3:普通类;4:地图类,7:洗车类,8:购机类,9:理财类)
		useTypeMap.put("0", "图片类");
		useTypeMap.put("1", "扫码类");
		useTypeMap.put("2", "编码类");
		useTypeMap.put("3", "普通类");
		useTypeMap.put("4", "地图类");
		useTypeMap.put("7", "洗车类");
		useTypeMap.put("8", "购机类");
		useTypeMap.put("9", "理财类");
	}
	public static Map<String, String> categoryMap = new HashMap<String, String>();
	static {
		// 卡券品类(1:流量;2:话费;3:语音;4:宽带;5:购机;6:直接使用;7:合作券;8:店铺券;9:积分充值券;10:积分券;11:内容券;12:业务券)
		categoryMap.put("1", "流量");
		categoryMap.put("2", "话费");
		categoryMap.put("3", "语音");
		categoryMap.put("4", "宽带");
		categoryMap.put("5", "购机");
		categoryMap.put("6", "充值");
		categoryMap.put("7", "合作券");
		categoryMap.put("8", "店铺券");
		categoryMap.put("9", "积分充值券");
		categoryMap.put("10", "积分券");
		categoryMap.put("11", "内容券");
		categoryMap.put("12", "业务券");
	}
	// 是否启用
	public static Map<String, String> useStatusMap = new HashMap<String, String>();
	static {
		useStatusMap.put("0", "已启用");
		useStatusMap.put("1", "已停用");
	}
	public static Map<String, String> receiveTypeMap = new HashMap<String, String>();
	static {
		// 领取方式(1:充值;2:套内流量;3:积分;4:E币;5:业务(套餐升档);6:活动)
		receiveTypeMap.put("1", "充值");
		receiveTypeMap.put("2", "套内流量");
		receiveTypeMap.put("3", "积分");
		receiveTypeMap.put("4", "E币");
		receiveTypeMap.put("5", "业务(套餐升档)");
		receiveTypeMap.put("6", "活动");
	}
	public static Map<String, String> receiveChannelMap = new HashMap<String, String>();
	static {
		// 领取渠道(obsh:网厅;wap:掌厅;sms短厅;jsmcc:客户端;activity:营销活动模版化)
		receiveChannelMap.put("obsh", "网厅");
		receiveChannelMap.put("wap", "掌厅");
		receiveChannelMap.put("sms", "短厅");
		receiveChannelMap.put("jsmcc", "客户端");
		receiveChannelMap.put("activity", "营销活动模版化");
	}
	public static Map<String, String> useTypeDoMap = new HashMap<String, String>();
	static {
		// 卡券使用类型(1:CRM核销;2:券码核销;3:直接核销;4:异业核销;5:扫二维码核销)
		useTypeDoMap.put("1", "CRM核销");
		useTypeDoMap.put("2", "券码核销");
		useTypeDoMap.put("3", "直接核销");
		useTypeDoMap.put("4", "异业核销");
		useTypeDoMap.put("5", "扫码核销");
	}
}
