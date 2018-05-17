package zhongd.coiplatform.entity.DTO.config;

import java.util.Date;

/**
 * @author xiezd 2018-05-17 14:08
 */
public class IgConfigDTO {
    private Integer igConfigId;
    private String name;
    private String value;
    private String description;
    private String updateBy;
    private Date updateTime;

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

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
