package zhongd.coiplatform.service.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zhongd.coiplatform.dao.advice.IgAdviceCollectionMapper;
import zhongd.coiplatform.entity.DO.advice.IgAdviceCollection;
import zhongd.coiplatform.entity.DTO.advice.IgAdviceCollectionDTO;

import java.util.HashMap;
import java.util.List;

/**
 * @Author xiezd
 * @Date 下午 9:39 星期二 2017/12/12 0012
 * @Description
 */
@Service
public class IgAdviceCollectionServiceImpl implements IgAdviceCollectionService {

    @Autowired
    IgAdviceCollectionMapper igAdviceCollectionMapper;
    @Override
    public boolean save(IgAdviceCollection igAdviceCollection) {
        Integer igAdviceCollectionId = igAdviceCollection.getIgAdviceCollectionId();
        if(igAdviceCollectionId != null
                && igAdviceCollectionId > 0){
            IgAdviceCollection DO = igAdviceCollectionMapper.selectByPrimaryKey(igAdviceCollectionId);
            if(DO != null){
                igAdviceCollection.setIgAdviceCollectionId(DO.getIgAdviceCollectionId());
                return igAdviceCollectionMapper.updateByPrimaryKeySelective(igAdviceCollection) > 0;
            }else{
                return false;
            }
        }else{
            return igAdviceCollectionMapper.insertSelective(igAdviceCollection) > 0;
        }
    }

    @Override
    public boolean deleteById(Integer igAdviceCollectionId) {
        return igAdviceCollectionMapper.deleteByPrimaryKey(igAdviceCollectionId) > 0;
    }

    @Override
    public List<IgAdviceCollectionDTO> list(IgAdviceCollectionDTO paramMap) {
        return igAdviceCollectionMapper.list(paramMap);
    }
}
