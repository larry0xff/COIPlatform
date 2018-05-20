package zhongd.coiplatform.service.manage;

import zhongd.coiplatform.entity.DO.manage.IgSwitch;

import java.util.List;

/**
 * @Author xiezd
 * @Date 2018-01-01 13:27
 * @Description
 */
public interface IgSwitchService {
    List<IgSwitch> getSwitchList();

    boolean changeStatus(Integer igSwitchId, Integer status);

    IgSwitch getById(String name);
}
