package zhongd.coiplatform.service.config;

import zhongd.coiplatform.entity.DTO.config.IgConfigDTO;

import java.util.List;
import java.util.Map;

/**
 * @author xiezd 2018-05-17 14:07
 */
public interface IgConfigService {
    List<IgConfigDTO> list();

    boolean update(Map<String, String> param, Integer igConfigId, Integer igUserId);

    boolean saveConfig(Map<String, String> param, Integer igUserId);

    boolean delete(Integer igConfigId);
}
