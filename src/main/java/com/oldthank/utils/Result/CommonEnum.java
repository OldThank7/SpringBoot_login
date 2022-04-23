package com.oldthank.utils.Result;

public enum CommonEnum implements BaseErrorInfoInterface {
	// 数据操作错误定义
	SUCCESS("20000", "成功!"),
	LOGOUTSUCCESS("20001", "成功退出登陆!"),
	BODY_NOT_MATCH("400","请求的数据格式不符!"),
	SIGNATURE_NOT_MATCH("401","请求的数字签名不匹配!"),
	NOT_FOUND("404", "未找到该资源!"), 
	INTERNAL_SERVER_ERROR("500", "服务器内部错误!"),
	SERVER_BUSY("503","服务器正忙，请稍后再试!"),
	ACCESSDENIED("504","用户访问无权限处理"),

	;

	/** 错误码 */
	private String resultCode;

	/** 错误描述 */
	private String resultMsg;

	CommonEnum(String resultCode, String resultMsg) {
		this.resultCode = resultCode;
		this.resultMsg = resultMsg;
	}

	@Override
	public String getResultCode() {
		return resultCode;
	}

	@Override
	public String getResultMsg() {
		return resultMsg;
	}

}
