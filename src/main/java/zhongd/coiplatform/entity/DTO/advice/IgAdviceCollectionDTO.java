package zhongd.coiplatform.entity.DTO.advice;

import java.util.Date;

/**
 * @Author xiezd
 * @Date 下午 10:51 星期二 2017/12/12 0012
 * @Description
 */
public class IgAdviceCollectionDTO {
    private Integer igAdviceCollectionId;
    private String subject;
    private String content;
    private String deadline;
    private Integer status;
    private String attachmentUrl;
    private String resultAttachmentUrl;
    private String createTime;
    private String updateTime;
    private String orgName;
    private String createBy;
    private String advicesAttachmentUrl;

    public String getAdvicesAttachmentUrl() {
        return advicesAttachmentUrl;
    }

    public void setAdvicesAttachmentUrl(String advicesAttachmentUrl) {
        this.advicesAttachmentUrl = advicesAttachmentUrl;
    }
    public String getResultAttachmentUrl() {
        return resultAttachmentUrl;
    }

    public void setResultAttachmentUrl(String resultAttachmentUrl) {
        this.resultAttachmentUrl = resultAttachmentUrl;
    }
    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIgAdviceCollectionId() {
        return igAdviceCollectionId;
    }

    public void setIgAdviceCollectionId(Integer igAdviceCollectionId) {
        this.igAdviceCollectionId = igAdviceCollectionId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAttachmentUrl() {
        return attachmentUrl;
    }

    public void setAttachmentUrl(String attachmentUrl) {
        this.attachmentUrl = attachmentUrl;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
}
