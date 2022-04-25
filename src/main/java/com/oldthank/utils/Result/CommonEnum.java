package com.oldthank.utils.Result;


import javax.servlet.http.HttpServletResponse;

/**
 *
 * 1 表示消息
 * 2 表示成功
 * 3 表示重定向
 * 4 表示请求错误
 * 5 表示服务器错误
 *
 * 1、
 * 100（客户端继续发送请求，这是临时响应）：这个临时响应是用来通知客户端它的部分请求已经被服务器接收，且仍未被拒绝。客户端应当继续发送请求的剩余部分，或者如果请求已经完成，忽略这个响应。服务器必须在请求完成后向客户端发送一个最终响应
 * 101：服务器根据客户端的请求切换协议，主要用于websocket或http2升级
 *
 * 2、
 * 200（成功）：请求已成功，请求所希望的响应头或数据体将随此响应返回
 * 201（已创建）：请求成功并且服务器创建了新的资源
 * 202（已创建）：服务器已经接收请求，但尚未处理
 * 203（非授权信息）：服务器已成功处理请求，但返回的信息可能来自另一来源
 * 204（无内容）：服务器成功处理请求，但没有返回任何内容
 * 205（重置内容）：服务器成功处理请求，但没有返回任何内容
 * 206（部分内容）：服务器成功处理了部分请求
 *
 * 3、
 * 300（多种选择）：针对请求，服务器可执行多种操作。服务器可根据请求者 (user agent) 选择一项操作，或提供操作列表供请求者选择
 * 301（永久移动）：请求的网页已永久移动到新位置。服务器返回此响应（对 GET 或 HEAD 请求的响应）时，会自动将请求者转到新位置
 * 302（临时移动）：服务器目前从不同位置的网页响应请求，但请求者应继续使用原有位置来进行以后的请求
 * 303（查看其他位置）：请求者应当对不同的位置使用单独的 GET 请求来检索响应时，服务器返回此代码
 * 305 （使用代理）：请求者只能使用代理访问请求的网页。如果服务器返回此响应，还表示请求者应使用代理
 * 307 （临时重定向）：服务器目前从不同位置的网页响应请求，但请求者应继续使用原有位置来进行以后的请求
 *
 * 4、
 * 400（错误请求）：服务器不理解请求的语法
 * 401（未授权）：请求要求身份验证。对于需要登录的网页，服务器可能返回此响应。
 * 403（禁止）：服务器拒绝请求
 * 404（未找到）：服务器找不到请求的网页
 * 405（方法禁用）：禁用请求中指定的方法
 * 406（不接受）：无法使用请求的内容特性响应请求的网页
 * 407（需要代理授权）：此状态代码与 401（未授权）类似，但指定请求者应当授权使用代理
 * 408（请求超时）：服务器等候请求时发生超时
 *
 * 5、
 * 500（服务器内部错误）：服务器遇到错误，无法完成请求
 * 501（尚未实施）：服务器不具备完成请求的功能。例如，服务器无法识别请求方法时可能会返回此代码
 * 502（错误网关）：服务器作为网关或代理，从上游服务器收到无效响应
 * 503（服务不可用）：服务器目前无法使用（由于超载或停机维护）
 * 504（网关超时）：服务器作为网关或代理，但是没有及时从上游服务器收到请求
 * 505（HTTP 版本不受支持）：服务器不支持请求中所用的 HTTP 协议版本
 *
 * 适用场景：
 * 100：客户端在发送POST数据给服务器前，征询服务器情况，看服务器是否处理POST的数据，如果不处理，客户端则不上传POST数据，如果处理，则POST上传数据。常用于POST大数据传输
 * 206：一般用来做断点续传，或者是视频文件等大文件的加载
 * 301：永久重定向会缓存。新域名替换旧域名，旧的域名不再使用时，用户访问旧域名时用301就重定向到新的域名
 * 302：临时重定向不会缓存，常用 于未登陆的用户访问用户中心重定向到登录页面
 * 304：协商缓存，告诉客户端有缓存，直接使用缓存中的数据，返回页面的只有头部信息，是没有内容部分
 * 400：参数有误，请求无法被服务器识别
 * 403：告诉客户端禁止访问该站点或者资源，如在外网环境下，然后访问只有内网IP才能访问的时候则返回
 * 404：服务器找不到资源时，或者服务器拒绝请求又不想说明理由时
 * 503：服务器停机维护时，主动用503响应请求或 nginx 设置限速，超过限速，会返回503
 * 504：网关超时
 *
 */

public enum CommonEnum implements BaseErrorInfoInterface {
	// 数据操作错误定义
	SUCCESS(200, "成功!"),
	LOGOUTSUCCESS(200, "成功退出登陆!"),
	BODY_NOT_MATCH(400,"请求的数据格式不符!"),
	SIGNATURE_NOT_MATCH(401,"请求的数字签名不匹配!"),
	NOT_FOUND(404, "未找到该资源!"),
	BADCREDENTIALSEXCEPTION(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"不良凭证异常"),
	INTERNAL_SERVER_ERROR(500, "服务器内部错误!"),
	SERVER_BUSY(503,"服务器正忙，请稍后再试!"),
	ACCESSDENIED(504,"用户访问无权限处理"),

	;

	/** 错误码 */
	private Integer resultCode;

	/** 错误描述 */
	private String resultMsg;

	CommonEnum(Integer resultCode, String resultMsg) {
		this.resultCode = resultCode;
		this.resultMsg = resultMsg;
	}

	@Override
	public Integer getResultCode() {
		return resultCode;
	}

	@Override
	public String getResultMsg() {
		return resultMsg;
	}

}

