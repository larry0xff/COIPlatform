package zhongd.coiplatform.service.org;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zhongd.coiplatform.dao.org.IgOrgMapper;
import zhongd.coiplatform.entity.DO.org.IgOrg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author xiezd
 * @Date 下午 9:44 星期六 2017/12/2 0002
 * @Description
 */
@Service
public class IgOrgServiceImpl implements IgOrgService{
    @Autowired
    IgOrgMapper igOrgMapper;
    @Override
    public Map<String, Object> getOrgList() {
        Map<String, Object> data = new HashMap<>();
        List<IgOrg> list = igOrgMapper.getOrgList();
        data.put("list", list);
        data.put("count", list.size());
        return data;
    }

    @Override
    public int deleteOrgById(Integer igOrgId) {
        return igOrgMapper.deleteByPrimaryKey(igOrgId);
    }

    @Override
    public int updateOrg(IgOrg igOrg) {
        return igOrgMapper.updateByPrimaryKeySelective(igOrg);
    }

    @Override
    public int saveOrg(IgOrg igOrg){
        return igOrgMapper.insertSelective(igOrg);
    }
}
