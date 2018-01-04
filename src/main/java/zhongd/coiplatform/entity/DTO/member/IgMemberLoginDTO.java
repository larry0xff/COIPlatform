package zhongd.coiplatform.entity.DTO.member;

import zhongd.coiplatform.entity.DO.member.IgMember;

import java.util.Date;

/**
 * @Author xiezd
 * @Date 2018-01-04 11:16
 * @Description 成员登录信息DTO
 */
public class IgMemberLoginDTO {
    private IgMember igMember;
    private Date loginTime;
    public IgMemberLoginDTO(){};
    public IgMemberLoginDTO(IgMember member, Date date) {
        this.igMember = member;
        this.loginTime = date;
    }

    public IgMember getIgMember() {
        return igMember;
    }

    public void setIgMember(IgMember igMember) {
        this.igMember = igMember;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }
}
