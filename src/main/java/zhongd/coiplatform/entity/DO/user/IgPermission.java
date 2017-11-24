package zhongd.coiplatform.entity.DO.user;

import javax.persistence.Id;
import java.util.Date;

/**
 * @Author xiezd
 * @Date 下午 8:19 星期五 2017/11/24 0024
 * @Description
 */
public class IgPermission {
    @Id
    private Integer igPermissionId;
    private String name;
    private String permissionCode;
    private String description;
    private Integer createBy;
    private Date createTime;
    private Integer updateBy;
    private Date updateTime;

    public Integer getIgPermissionId() {
        return igPermissionId;
    }

    public void setIgPermissionId(Integer igPermissionId) {
        this.igPermissionId = igPermissionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
