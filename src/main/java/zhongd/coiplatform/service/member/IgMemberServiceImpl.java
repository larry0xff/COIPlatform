package zhongd.coiplatform.service.member;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import zhongd.coiplatform.dao.member.IgMemberMapper;
import zhongd.coiplatform.entity.DO.member.IgMember;
import zhongd.coiplatform.entity.DTO.member.IgMemberDTO;
import zhongd.coiplatform.entity.DTO.user.IgUserLoginDTO;
import zhongd.coiplatform.entity.ReturnObj;
import zhongd.coiplatform.utils.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;

/**
 * @Author xiezd
 * @Description
 * @Date Created in  16:20 星期六 2017/12/2/002
 */
@Service
public class IgMemberServiceImpl implements IgMemberService {
    @Value("${fileupload.path}")
    private String path;
    @Autowired
    IgMemberMapper igMemberMapper;

    @Override
    public Map<String, Object> getMemberList(HttpServletRequest request) {
        Map<String, Object> param = new HashMap<>();
        String condition = request.getParameter("condition")==null?"":request.getParameter("condition");
        if(StringUtil.isNum(condition))
            param.put("igMemberId", Integer.parseInt(condition));
        param.put("username", "%" + condition + "%");
        param.put("realname", "%" + condition + "%");
        param.put("tel", "%" + condition + "%");
        param.put("email", "%" + condition + "%");
        int page = Integer.parseInt(request.getParameter("page"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        page = (page - 1) * pageSize;
        param.put("page", page);
        param.put("pageSize", pageSize);
        Map<String, Object> data = new HashMap<>();
        List<IgMemberDTO> list = igMemberMapper.getMemberList(param);
        data.put("list", list);
        data.put("count", list.size());
        return data;
    }

    public int deleteById(int igMemberId){
        return igMemberMapper.deleteByPrimaryKey(igMemberId);
    }

    public int insert(IgMemberDTO dto){
        IgMember igMember = ConvertTools.convert(IgMember.class, dto);
        igMember.setCreateBy(((IgUserLoginDTO) SecurityUtils.getSubject().getSession().getAttribute("currentUser")).getIgUserDO().getIgUserId());
        igMember.setCreateTime(new Date());
        igMember.setPassword(PasswordHandler.encodePassword("123456", igMember.getUsername(), Constant.MD5_STR));
        return igMemberMapper.insertSelective(igMember);
    }

    @Override
    public int resetPassword(Integer igMemberId, String username) {
        IgMember member = new IgMember();
        member.setIgMemberId(igMemberId);
        member.setPassword(PasswordHandler.encodePassword("123456", username, Constant.MD5_STR));
        return igMemberMapper.updateByPrimaryKeySelective(member);
    }

    @Override
    public Map<String, Object> searchMemberList(String condition) {
        Map<String, Object> param = new HashMap<>();
        if(StringUtil.isNum(condition))
            param.put("idcondition", Integer.parseInt(condition));
        param.put("condition", "%" + condition + "%");
        Map<String, Object> data = new HashMap<>();
        List<IgMemberDTO> list = igMemberMapper.searchMemberList(param);
        data.put("list", list);
        data.put("count", list.size());
        return data;
    }

    @Override
    public ReturnObj bulkInsert(String filepath, ReturnObj obj) throws Exception{
        String msg  = "";
        File file = new File(path + filepath);
        if(file.exists() && file.isFile()){
            FileInputStream fis = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();
            while(rows.hasNext()){
                Row row = rows.next();
                Iterator<Cell> cells = row.cellIterator();
                while(cells.hasNext()){
                    msg += (" " + cells.next());
                }
            }
            obj.setReturnCode(ReturnCode.SUCCESS);
        }else{
            msg += "找不到上传的文件，请重新上传文件！";
            obj.setReturnCode(ReturnCode.FAIL);
        }
        obj.setMsg(msg);
        return obj;
    }
}
