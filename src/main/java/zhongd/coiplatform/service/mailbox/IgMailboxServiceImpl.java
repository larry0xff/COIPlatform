package zhongd.coiplatform.service.mailbox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zhongd.coiplatform.dao.mailbox.IgMailMapper;
import zhongd.coiplatform.entity.DO.mailbox.IgMail;
import zhongd.coiplatform.entity.DTO.mail.IgMailDTO;

import java.util.*;

/**
 * @Author xiezd
 * @Date 2017-12-30 22:25
 * @Description
 */
@Service
public class IgMailboxServiceImpl implements IgMailboxService {
    @Autowired
    IgMailMapper igMailMapper;

    @Override
    public List<IgMailDTO> list(Integer igOrgId, String status) {
        Map<String, Object> paramMap = new HashMap<>();
        if(Objects.nonNull(igOrgId) && igOrgId != 1){
            paramMap.put("igOrgId", igOrgId);
        }
        //筛选未回复的
        if(status.equals("waitting")){
            paramMap.put("status", "waitting");
        } else {
            paramMap.put("status", "done");
        }
        return igMailMapper.list(paramMap);
    }

    @Override
    public boolean reply(String reply, Integer igMailId, Integer igUserId) {
        IgMail updateObj = igMailMapper.selectByPrimaryKey(igMailId);
        updateObj.setIgMailId(igMailId);
        updateObj.setReply(reply);
        updateObj.setUpdateBy(igUserId);
        updateObj.setUpdateTime(new Date());
        return igMailMapper.updateByPrimaryKeySelective(updateObj) > 0;
    }

    @Override
    public boolean changeReadStatus(Integer igMailId, String isRead) {
        IgMail updateObj = igMailMapper.selectByPrimaryKey(igMailId);
        updateObj.setIsRead(isRead);
        return igMailMapper.updateByPrimaryKeySelective(updateObj) > 0;
    }
}
