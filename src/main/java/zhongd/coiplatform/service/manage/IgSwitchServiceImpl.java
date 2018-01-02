package zhongd.coiplatform.service.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zhongd.coiplatform.dao.manage.IgSwitchMapper;
import zhongd.coiplatform.entity.DO.manage.IgSwitch;

import java.util.List;

/**
 * @Author xiezd
 * @Date 2018-01-01 13:29
 * @Description
 */
@Service
public class IgSwitchServiceImpl implements IgSwitchService {
    @Autowired
    IgSwitchMapper igSwitchMapper;

    public List<IgSwitch> getSwitchList(){
        return igSwitchMapper.selectAll();
    }

    @Override
    public boolean changeStatus(Integer igSwitchId, Integer status) {
        IgSwitch updateObj = igSwitchMapper.selectByPrimaryKey(igSwitchId);
        updateObj.setStatus(status);
        return igSwitchMapper.updateByPrimaryKeySelective(updateObj) > 0;
    }
}
