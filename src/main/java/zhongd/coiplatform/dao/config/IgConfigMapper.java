package zhongd.coiplatform.dao.config;

import org.apache.ibatis.annotations.Mapper;
import zhongd.coiplatform.dao.BaseMapper;
import zhongd.coiplatform.entity.DO.config.IgConfig;
import zhongd.coiplatform.entity.DTO.config.IgConfigDTO;

import java.util.List;

/**
 * @author xiezd 2018-05-17 14:15
 */
@Mapper
public interface IgConfigMapper extends BaseMapper<IgConfig>{

    List<IgConfigDTO> list();
}
