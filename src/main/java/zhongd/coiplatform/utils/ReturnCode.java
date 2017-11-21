package zhongd.coiplatform.utils;

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
	public static int REQUEST_NOT_FOUND = 404;
	public static int SUCCESS = 200;
	public static int NO_AUTORITY = 401;
}
