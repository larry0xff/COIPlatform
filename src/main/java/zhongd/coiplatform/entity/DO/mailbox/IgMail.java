package zhongd.coiplatform.entity.DO.mailbox;

import zhongd.coiplatform.entity.DO.BaseDO;

import javax.persistence.Id;
import java.util.Date;

/**
 * @Author xiezd
 * @Date 2017-12-30 22:27
 * @Description
 */
public class IgMail extends BaseDO{
    @Id
    private Integer IgMailId;
    private Integer IgOrgId;
    private String title;
    private String content;
    private String isRead;
    private String reply;

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
