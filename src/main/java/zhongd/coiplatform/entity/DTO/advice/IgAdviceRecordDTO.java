package zhongd.coiplatform.entity.DTO.advice;

/**
 * @Author xiezd
 * @Description
 * @Date Created in  0:12 星期一 2017/12/18/018
 */
public class IgAdviceRecordDTO {
    private Integer igAdviceRecordId;
    private Integer igAdviceCollectionId;
    private String content;
    private String collectionSubject;
    private String createBy;
    private String createTime;

    public Integer getIgAdviceRecordId() {
        return igAdviceRecordId;
    }

    public void setIgAdviceRecordId(Integer igAdviceRecordId) {
        this.igAdviceRecordId = igAdviceRecordId;
    }

    public Integer getIgAdviceCollectionId() {
        return igAdviceCollectionId;
    }

    public void setIgAdviceCollectionId(Integer igAdviceCollectionId) {
        this.igAdviceCollectionId = igAdviceCollectionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCollectionSubject() {
        return collectionSubject;
    }

    public void setCollectionSubject(String collectionSubject) {
        this.collectionSubject = collectionSubject;
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
