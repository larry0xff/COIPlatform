package zhongd.coiplatform.service.advice;

import zhongd.coiplatform.entity.DO.advice.IgAdviceCollection;
import zhongd.coiplatform.entity.DTO.advice.IgAdviceCollectionDTO;

import java.util.HashMap;
import java.util.List;

/**
 * @Author xiezd
 * @Date 下午 9:38 星期二 2017/12/12 0012
 * @Description
 */
public interface IgAdviceCollectionService {
    boolean save(IgAdviceCollection igAdviceCollection);

    boolean deleteById(Integer igAdviceCollectionId);

    List<IgAdviceCollectionDTO> list(IgAdviceCollectionDTO paramMap);
}
