package zhongd.coiplatform.dao.org;

import org.apache.ibatis.annotations.Mapper;
import zhongd.coiplatform.dao.BaseMapper;
import zhongd.coiplatform.entity.DO.org.IgOrg;

import java.util.List;

/**
 * @Author xiezd
 * @Date 下午 9:41 星期六 2017/12/2 0002
 * @Description
 */
@Mapper
public interface IgOrgMapper extends BaseMapper<IgOrg> {
    List<IgOrg> getOrgList();
}
