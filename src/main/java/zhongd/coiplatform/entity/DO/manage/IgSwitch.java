package zhongd.coiplatform.entity.DO.manage;

import zhongd.coiplatform.entity.DO.BaseDO;

import javax.persistence.Id;

/**
 * @Author xiezd
 * @Date 2018-01-01 13:30
 * @Description
 */
public class IgSwitch extends BaseDO{
    @Id
    private Integer IgSwitchId;
    private String name;
    private String description;
    private String message;
    private Integer status;

    public Integer getIgSwitchId() {
        return IgSwitchId;
    }

    public void setIgSwitchId(Integer igSwitchId) {
        IgSwitchId = igSwitchId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
