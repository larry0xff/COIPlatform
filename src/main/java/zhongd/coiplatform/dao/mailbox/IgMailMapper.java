package zhongd.coiplatform.dao.mailbox;

import zhongd.coiplatform.dao.BaseMapper;
import zhongd.coiplatform.entity.DO.mailbox.IgMail;

import java.util.List;
import java.util.Map;

/**
 * @Author xiezd
 * @Date 2017-12-30 22:27
 * @Description
 */
public interface IgMailMapper extends BaseMapper<IgMail>{
    List<IgMail> list(Map<String, Object> paramMap);
}
