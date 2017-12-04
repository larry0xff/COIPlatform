package zhongd.coiplatform.entity.DTO.member;

/**
 * @Author xiezd
 * @Description
 * @Date Created in  10:39 星期六 2017/12/2/002
 */
public class IgMemberDTO {
    private Integer igMemberId;
    private String username;
    private String realname;
    private String email;
    private String tel;
    private String gender;
    private Integer igOrgId;

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
