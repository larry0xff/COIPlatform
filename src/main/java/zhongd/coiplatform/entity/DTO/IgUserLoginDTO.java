package zhongd.coiplatform.entity.DTO;

import java.util.Date;

import zhongd.coiplatform.entity.DO.user.IgUserDO;

public class IgUserLoginDTO {
	private IgUserDO igUserDO;
	public IgUserDO getIgUserDO() {
		return igUserDO;
	}
	public void setIgUserDO(IgUserDO igUserDO) {
		this.igUserDO = igUserDO;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	private Date loginTime;
}
