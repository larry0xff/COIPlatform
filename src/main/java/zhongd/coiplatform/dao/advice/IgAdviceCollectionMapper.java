package zhongd.coiplatform.dao.advice;

import org.apache.ibatis.annotations.Mapper;
import zhongd.coiplatform.dao.BaseMapper;
import zhongd.coiplatform.entity.DO.advice.IgAdviceCollection;
import zhongd.coiplatform.entity.DTO.advice.IgAdviceCollectionDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author xiezd
 * @Date 下午 8:47 星期二 2017/12/12 0012
 * @Description
 */
@Mapper
public interface IgAdviceCollectionMapper extends BaseMapper<IgAdviceCollection> {
    List<IgAdviceCollectionDTO> list(Map<String, Object> paramMap);

    List<IgAdviceCollection> getAllCollectingCollection();

    IgAdviceCollectionDTO getAdviceCollectionById(Integer igAdviceCollectionId);
}
