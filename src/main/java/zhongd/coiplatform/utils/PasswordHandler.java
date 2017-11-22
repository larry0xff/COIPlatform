package zhongd.coiplatform.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class PasswordHandler {
	/**
	 * 密码加密方法
	 * @param password 要加密的密码
	 * @param salt 盐，传用户名
	 * @param algorithmName 算法名称：MD5、SHA..
	 * @return
	 */
	public static String encodePassword(String password, String salt, String algorithmName ) {
		int hashIterations = 2;
		String newPassword = new SimpleHash(algorithmName, password,
                ByteSource.Util.bytes(salt), hashIterations).toHex();
		return newPassword;
	}
	
	public static void main(String[] args) {
		System.out.println(encodePassword("xxx123", "tim", Constant.MD5_STR));
	}
}
