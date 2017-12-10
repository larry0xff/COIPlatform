package zhongd.coiplatform.entity.DO.member;

import zhongd.coiplatform.entity.DO.BaseDO;

/**
 * @Author xiezd
 * @Description
 * @Date Created in  15:31 星期日 2017/12/10/010
 */
public class IgMemberBulkRecord extends BaseDO {
    private Integer igMemberBulkRecordId;
    private String description;
    private String fileName;

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
}
