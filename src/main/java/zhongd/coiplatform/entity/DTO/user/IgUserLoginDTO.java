package zhongd.coiplatform.entity.DTO.user;

import java.text.SimpleDateFormat;
import java.util.Date;

import zhongd.coiplatform.entity.DO.user.IgUser;

public class IgUserLoginDTO {
	private IgUser igUser;
	public IgUser getIgUserDO() {
		return igUser;
	}
	public void setIgUser(IgUser igUser) {
		this.igUser = igUser;
	}
	public String getLoginTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
		return sdf.format(loginTime);
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	private Date loginTime;
}
