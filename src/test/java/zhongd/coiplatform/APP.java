package zhongd.coiplatform;

import java.util.Calendar;
import java.util.TimeZone;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;

import zhongd.coiplatform.entity.DO.user.IgUser;
import zhongd.coiplatform.entity.DTO.user.IgUserDTO;
import zhongd.coiplatform.utils.Constant;
import zhongd.coiplatform.utils.ConvertTools;

public class APP {
	@Test
	public void ggg() {
		RandomNumberGenerator rd = new SecureRandomNumberGenerator();
		String algorithmName = Constant.MD5_STR;
		int hashIterations = 1;
		// 以用户名作为'盐'
		String password = new SimpleHash(algorithmName, "xxx123",
                ByteSource.Util.bytes(1), hashIterations).toHex();
		System.out.println(password);
    }
	@Test
	public void hhh() {
		Calendar cal = Calendar.getInstance();
		TimeZone timeZone = cal.getTimeZone();
		System.out.println(timeZone.getID());
		System.out.println(timeZone.getDisplayName());
		get();
	}
	public void get() {
		System.out.println(getClass());;
	}
	@Test
	public void iii() {
		IgUser user = new IgUser();
		user.setIgUserId(123);
		user.setEmail("111a");
		user.setTel("1234444");
		IgUserDTO dto = ConvertTools.convert(IgUserDTO.class, user);
		System.out.println(dto);
	}
}
