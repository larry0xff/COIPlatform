package zhongd.coiplatform.service.role;

import zhongd.coiplatform.entity.DO.user.IgRole;

import java.util.Map;

/**
 * @author zdxie
 * @Date 下午 10:18 2017/11/23 0023
 * @Description
 */
public interface IgRoleService {
    int insertRole(IgRole igRole);

    int deleteRole(Integer igRoleId);

    Map<String,Object> list();
}
