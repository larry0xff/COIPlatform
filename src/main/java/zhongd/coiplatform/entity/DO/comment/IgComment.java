package zhongd.coiplatform.entity.DO.comment;

import zhongd.coiplatform.entity.DO.BaseDO;

import javax.persistence.Id;

/**
 * @author xiezd 2018-01-06 10:32
 */
public class IgComment extends BaseDO{
    @Id
    private Integer igCommentId;
    private String content;
    private Integer against;
    private Integer agree;
    private Integer replyId;
    private String isRead;

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }

    public Integer getIgCommentId() {
        return igCommentId;
    }

    public void setIgCommentId(Integer igCommentId) {
        this.igCommentId = igCommentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getAgainst() {
        return against;
    }

    public void setAgainst(Integer against) {
        this.against = against;
    }

    public Integer getAgree() {
        return agree;
    }

    public void setAgree(Integer agree) {
        this.agree = agree;
    }

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }
}
