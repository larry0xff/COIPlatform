package zhongd.coiplatform.entity.DO.advice;

import zhongd.coiplatform.entity.DO.BaseDO;

import javax.persistence.Id;
import java.util.Date;

/**
 * @Author xiezd
 * @Date 下午 8:48 星期二 2017/12/12 0012
 * @Description
 */
public class IgAdviceCollection extends BaseDO{
    @Id
    private Integer igAdviceCollectionId;
    private String subject;
    private String content;
    private Integer igOrgId;
    private Date deadline;
    private Integer status;
    private String attachmentUrl;

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

    public Integer getIgOrgId() {
        return igOrgId;
    }

    public void setIgOrgId(Integer igOrgId) {
        this.igOrgId = igOrgId;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
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
}
