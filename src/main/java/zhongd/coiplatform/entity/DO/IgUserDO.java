package zhongd.coiplatform.entity.DO;

public class IgUserDO extends BaseDO{
	private Integer igUserId;
	private	String username;
	private String password;
	private String gender;
	private String realname;
	private String email;
	private String tel;
	private Integer igOrgId;
	public Integer getIgUserId() {
		return igUserId;
	}
	public void setIgUserId(Integer igUserId) {
		this.igUserId = igUserId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Integer getIgOrgId() {
		return igOrgId;
	}
	public void setIgOrgId(Integer igOrgId) {
		this.igOrgId = igOrgId;
	}
	
}
