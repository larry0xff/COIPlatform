package zhongd.coiplatform.entity.DO.advice;

import zhongd.coiplatform.entity.DO.BaseDO;

/**
 * @Author xiezd
 * @Description
 * @Date Created in  23:04 星期日 2017/12/17/017
 */
public class IgAdviceRecord extends BaseDO{
    private Integer igAdviceRecordId;
    private Integer igAdviceCollectionId;
    private String content;

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
}
