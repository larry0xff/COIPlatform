package zhongd.coiplatform.entity.DO.config;

import javax.persistence.Id;
import java.util.Date;

/**
 * @author xiezd 2018-05-17 14:17
 */
public class IgConfig {
    @Id
    private Integer igConfigId;
    private String name;
    private String value;
    private String description;
    private Integer updateBy;
    private Date updateTime;
    private Integer createBy;
    private Date createTime;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIgConfigId() {
        return igConfigId;
    }

    public void setIgConfigId(Integer igConfigId) {
        this.igConfigId = igConfigId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
