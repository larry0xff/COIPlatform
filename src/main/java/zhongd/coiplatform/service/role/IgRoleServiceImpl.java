package zhongd.coiplatform.service.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zhongd.coiplatform.dao.user.IgRoleMapper;
import zhongd.coiplatform.dao.user.IgUserMapper;
import zhongd.coiplatform.entity.DO.user.IgRole;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zdxie
 * @Date 下午 10:19 2017/11/23 0023
 * @Description
 */
@Service
public class IgRoleServiceImpl implements IgRoleService {
    @Autowired
    IgRoleMapper igRoleMapper;
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
}
