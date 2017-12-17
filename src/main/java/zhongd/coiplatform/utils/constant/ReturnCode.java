package zhongd.coiplatform.utils.constant;

public class ReturnCode {
	/**
	 * 用户名不存在
	 */
	public static final Integer LOGIN_ERROR_USER_NOT_EXIST = 10001;
	/**
	 * 密码错误
	 */
	public static final Integer LOGIN_ERROR_PASSWORD_INCORRECT = 10002;
	/**
	 * 登录成功
	 */
	public static final Integer LOGIN_SUCCESS = 10000;

	/**
	 * 服务出错
	 */
	public static final Integer FAIL = 500;
	/**
	 * 找不到请求
	 */
	public static final int REQUEST_NOT_FOUND = 404;
	/**
	 * 参数错误
	 */
	public static final int PARAMETERS_ERROR = 405;
	/**
	 * 处理成功
	 */
	public static final int SUCCESS = 200;
	/**
	 * 没有登录
	 */
	public static final int NO_AUTORITY = 401;
	/**
	 * 没有权限
	 */
	public static final int NO_PERMISSION = 403;
}
