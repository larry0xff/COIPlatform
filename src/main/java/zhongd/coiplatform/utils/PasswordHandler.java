package zhongd.coiplatform.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import zhongd.coiplatform.utils.constant.Constant;

public class PasswordHandler {
	static final int HASH_ITERATIONS = 2;
	/**
	 * 密码加密方法
	 * @param password 要加密的密码
	 * @param salt 盐，传用户名
	 * @param algorithmName 算法名称：MD5、SHA..
	 * @return
	 */
	public static String encodePassword(String password, String salt, String algorithmName ) {
		String newPassword = new SimpleHash(algorithmName, password,
                ByteSource.Util.bytes(salt), HASH_ITERATIONS).toHex();
		return newPassword;
	}

	public static String encodePassword(String password, Integer salt, String algorithmName ) {
		String newPassword = new SimpleHash(algorithmName, password,
				ByteSource.Util.bytes(String.valueOf(salt)), HASH_ITERATIONS).toHex();
		return newPassword;
	}
	
	public static void main(String[] args) {
		System.out.println(encodePassword("admin", "admin", Constant.MD5_STR));
	}
}
