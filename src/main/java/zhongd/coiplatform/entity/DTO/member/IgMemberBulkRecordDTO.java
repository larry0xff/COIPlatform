package zhongd.coiplatform.entity.DTO.member;

/**
 * @Author xiezd
 * @Description
 * @Date Created in  17:08 星期日 2017/12/10/010
 */
public class IgMemberBulkRecordDTO {
    private Integer igMemberBulkRecordId;
    private String description;
    private String fileName;
    private String createBy;
    private String createTime;

    public Integer getIgMemberBulkRecordId() {
        return igMemberBulkRecordId;
    }

    public void setIgMemberBulkRecordId(Integer igMemberBulkRecordId) {
        this.igMemberBulkRecordId = igMemberBulkRecordId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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
