package zhongd.coiplatform.entity.DO.org;

import zhongd.coiplatform.entity.DO.BaseDO;

import javax.persistence.Id;

/**
 * @Author xiezd
 * @Date 下午 9:14 星期六 2017/12/2 0002
 * @Description
 */
public class IgOrg extends BaseDO{
    @Id
    private Integer igOrgId;
    private String name;
    private Integer managerId;
    private String description;

    public Integer getIgOrgId() {
        return igOrgId;
    }

    public void setIgOrgId(Integer igOrgId) {
        this.igOrgId = igOrgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
