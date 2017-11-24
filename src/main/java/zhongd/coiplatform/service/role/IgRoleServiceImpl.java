package zhongd.coiplatform.service.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zhongd.coiplatform.dao.role.IgRoleMapper;
import zhongd.coiplatform.dao.role.RoleJoinMapper;
import zhongd.coiplatform.entity.DO.user.IgPermission;
import zhongd.coiplatform.entity.DO.user.IgRole;

import java.util.*;

/**
 * @author zdxie
 * @Date 下午 10:19 2017/11/23 0023
 * @Description
 */
@Service
public class IgRoleServiceImpl implements IgRoleService {
    @Autowired
    IgRoleMapper igRoleMapper;
    @Autowired
    RoleJoinMapper joinMapper;
    @Override
    public int insertRole(IgRole igRole) {
        return igRoleMapper.insert(igRole);
    }

    @Override
    public int deleteRole(Integer igRoleId) {
        return igRoleMapper.deleteByPrimaryKey(igRoleId);
    }

    @Override
    public Map<String, Object> list() {
        List<IgRole> list = igRoleMapper.selectAll();
        Map<String, Object> data = new HashMap<>();
        data.put("list", list);
        data.put("count", list.size());
        return data;
    }

    @Override
    public int setPermission(Integer igRoleId, Integer igPermissionId, Integer currentUserId) {
        Map<String, Object> param = new HashMap<>();
        param.put("igRoleId", igRoleId);
        param.put("igPermissionId", igPermissionId);
        param.put("updateBy", currentUserId);
        param.put("createBy", currentUserId);
        param.put("updateTime", new Date());
        param.put("createTime", new Date());
        return joinMapper.setPermission(param);
    }

    @Override
    public int rmPermission(Integer igRoleId, Integer igPermissionId) {
        Map<String, Object> param = new HashMap<>();
        param.put("igPermissionId", igPermissionId);
        param.put("igRoleId", igRoleId);
        return joinMapper.rmPermission(param);
    }

    @Override
    public Map<String, Object> getRolePermissionSet(Integer igRoleId) {
        Set<IgPermission> set = joinMapper.getPermissionSet(igRoleId);
        Map<String, Object> data = new HashMap<>();
        data.put("set", set);
        data.put("count", set.size());
        return data;
    }
}
