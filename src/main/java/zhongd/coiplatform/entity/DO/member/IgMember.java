package zhongd.coiplatform.entity.DO.member;

import zhongd.coiplatform.entity.DO.BaseDO;

import javax.persistence.Id;

/**
 * @Author xiezd
 * @Date 下午 10:20 星期五 2017/12/1 0001
 * @Description
 */
public class IgMember extends BaseDO {
    @Id
    private Integer igMemberId;
    private String username;
    private String password;
    private String realname;
    private String email;
    private String tel;
    private String gender;
    private Integer igOrgId;
    public IgMember(){}
    public IgMember(String username) {
        this.username = username;
    }

    public Integer getIgMemberId() {
        return igMemberId;
    }

    public void setIgMemberId(Integer igMemberId) {
        this.igMemberId = igMemberId;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getIgOrgId() {
        return igOrgId;
    }

    public void setIgOrgId(Integer igOrgId) {
        this.igOrgId = igOrgId;
    }
}
