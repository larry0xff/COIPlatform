package zhongd.coiplatform.dao.member;

import zhongd.coiplatform.dao.BaseMapper;
import zhongd.coiplatform.entity.DO.member.IgMemberBulkRecord;
import zhongd.coiplatform.entity.DTO.member.IgMemberBulkRecordDTO;

import java.util.List;
import java.util.Map;

/**
 * @Author xiezd
 * @Description
 * @Date Created in  15:39 星期日 2017/12/10/010
 */
public interface IgMemberBulkRecordMapper extends BaseMapper<IgMemberBulkRecord> {
    List<IgMemberBulkRecordDTO> list();
}
