package zhongd.coiplatform.entity.DTO.comment;

/**
 * @author xiezd 2018-01-06 10:54
 */
public class IgCommentDTO {
    private Integer igCommentId;
    private String content;
    private Integer against;
    private Integer agree;
    private String createBy;
    private Integer createById;
    private String createTime;
    private String isRead;

    public Integer getCreateById() {
        return createById;
    }

    public void setCreateById(Integer createById) {
        this.createById = createById;
    }

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

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
