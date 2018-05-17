package zhongd.coiplatform.service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zhongd.coiplatform.dao.config.IgConfigMapper;
import zhongd.coiplatform.entity.DO.config.IgConfig;
import zhongd.coiplatform.entity.DTO.config.IgConfigDTO;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author xiezd 2018-05-17 14:13
 */

@Service
public class IgConfigServiceImpl implements IgConfigService {

    @Autowired
    private IgConfigMapper igConfigMapper;
    @Override
    public List<IgConfigDTO> list() {
        return igConfigMapper.list();
    }

    @Override
    public boolean update(Map<String, String> param, Integer igConfigId, Integer igUserId) {
        IgConfig igConfig = igConfigMapper.selectByPrimaryKey(igConfigId);
        igConfig.setName(param.get("name"));
        igConfig.setValue(param.get("value"));
        igConfig.setDescription(param.get("description"));
        igConfig.setUpdateBy(igUserId);
        igConfig.setUpdateTime(new Date());
        return igConfigMapper.updateByPrimaryKeySelective(igConfig) > 0;
    }

    @Override
    public boolean saveConfig(Map<String, String> param, Integer igUserId) {
        IgConfig igConfig = new IgConfig();
        igConfig.setUpdateTime(new Date());
        igConfig.setUpdateBy(igUserId);
        igConfig.setCreateBy(igUserId);
        igConfig.setCreateTime(new Date());
        igConfig.setName(param.get("name"));
        igConfig.setValue(param.get("value"));
        igConfig.setDescription(param.get("description"));
        return igConfigMapper.insert(igConfig) > 0;
    }

    @Override
    public boolean delete(Integer igConfigId) {
        return igConfigMapper.deleteByPrimaryKey(igConfigId) > 0;
    }
}
