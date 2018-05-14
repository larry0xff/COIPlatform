package zhongd.coiplatform.entity.DTO.mail;

import java.util.Date;

/**
 * @author xiezd 2018-05-14 14:42
 */
public class IgMailDTO {
    private Integer IgMailId;
    private Integer IgOrgId;
    private String title;
    private String content;
    private String isRead;
    private String reply;
    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public Integer getIgMailId() {
        return IgMailId;
    }

    public void setIgMailId(Integer igMailId) {
        IgMailId = igMailId;
    }

    public Integer getIgOrgId() {
        return IgOrgId;
    }

    public void setIgOrgId(Integer igOrgId) {
        IgOrgId = igOrgId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }
}
