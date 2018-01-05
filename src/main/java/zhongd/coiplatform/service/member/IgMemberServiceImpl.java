package zhongd.coiplatform.service.member;

import com.sun.org.apache.regexp.internal.RE;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import zhongd.coiplatform.dao.member.IgMemberBulkRecordMapper;
import zhongd.coiplatform.dao.member.IgMemberMapper;
import zhongd.coiplatform.dao.org.IgOrgMapper;
import zhongd.coiplatform.entity.DO.member.IgMember;
import zhongd.coiplatform.entity.DO.member.IgMemberBulkRecord;
import zhongd.coiplatform.entity.DTO.member.IgMemberBulkRecordDTO;
import zhongd.coiplatform.entity.DTO.member.IgMemberDTO;
import zhongd.coiplatform.entity.DTO.member.IgMemberLoginDTO;
import zhongd.coiplatform.entity.DTO.user.IgUserLoginDTO;
import zhongd.coiplatform.entity.ReturnObj;
import zhongd.coiplatform.utils.*;
import zhongd.coiplatform.utils.constant.Constant;
import zhongd.coiplatform.utils.constant.ReturnCode;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.util.*;

/**
 * @Author xiezd
 * @Description
 * @Date Created in  16:20 星期六 2017/12/2/002
 */
@Service
public class IgMemberServiceImpl implements IgMemberService {
    @Value("${file.uploadpath}")
    private String path;
    @Autowired
    IgMemberMapper igMemberMapper;
    @Autowired
    IgOrgMapper igOrgMapper;
    @Autowired
    IgMemberBulkRecordMapper igMemberBulkRecordMapper;

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
        igMember.setUpdateTime(new Date());
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


    /*
    rollbackFor

    该属性用于设置需要进行回滚的异常类数组，当方法中抛出指定异常数组中的异常时，则进行事务回滚。例如：

    指定单一异常类：@Transactional(rollbackFor=RuntimeException.class)

    指定多个异常类：@Transactional(rollbackFor={RuntimeException.class, Exception.class})
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public ReturnObj bulkInsert(String filepath, ReturnObj obj) throws Exception{
        String msg  = "";
        File file = new File(path + filepath);
        if(file.exists() && file.isFile()){
            List<IgMember> list = new ArrayList<>();
            FileInputStream fis = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();
            while(rows.hasNext()){
                Row row = rows.next();
                if(row.getRowNum() == 0){
                    continue;
                }
                try{
                    //先判断单元格有没有空，有直接抛异常，记录信息。
                    IgMember igMember = new IgMember();
                    for(int i = 0; i < 5; i++){
                        if(row.getCell(i) == null || row.getCell(i).getCellType() == HSSFCell.CELL_TYPE_BLANK){
                            throw new Exception("格式错误");
                        }
                    }
                    igMember.setUsername(row.getCell(0).getStringCellValue());
                    igMember.setRealname(row.getCell(1).getStringCellValue());
                    igMember.setGender(row.getCell(2).getStringCellValue());
                    igMember.setTel((new DecimalFormat("#").format(row.getCell(3).getNumericCellValue())));
                    igMember.setEmail(row.getCell(4).getStringCellValue());
                    igMember.setIgOrgId(Integer.parseInt((new DecimalFormat("#").format(row.getCell(5).getNumericCellValue()))));
                    igMember.setCreateBy(((IgUserLoginDTO) SecurityUtils.getSubject().getSession().getAttribute("currentUser")).getIgUserDO().getIgUserId());
                    igMember.setCreateTime(new Date());
                    igMember.setUpdateTime(new Date());
                    igMember.setPassword(PasswordHandler.encodePassword("123456", igMember.getUsername(), Constant.MD5_STR));
                    if(igMemberMapper.select(new IgMember(igMember.getUsername())).size() > 0){
                        throw new Exception("用户名已存在");
                    }
                    if(igOrgMapper.selectByPrimaryKey(igMember.getIgOrgId()) == null){
                        throw new Exception("组织id不存在");
                    }
                    igMemberMapper.insert(igMember);
                    list.add(igMember);
                }catch(Exception e){
                    msg +=  ("【第" + (row.getRowNum() + 1)+ "行：" + e.getMessage() + "】");
                }
            }
            //判断有没有异常信息，有的话抛出，传给前端，没有的话保存一条批量导入记录。
            if(StringUtil.isEmpty(msg)){
                IgMemberBulkRecord mbr = new IgMemberBulkRecord();
                mbr.setDescription("批量导入" + list.size() + "条成员信息");
                mbr.setFileName(filepath);
                mbr.setCreateBy(((IgUserLoginDTO) SecurityUtils.getSubject().getSession().getAttribute("currentUser")).getIgUserDO().getIgUserId());
                mbr.setCreateTime(new Date());
                igMemberBulkRecordMapper.insertSelective(mbr);
                obj.setMsg("成功导入：" + list.size() + "条数据！");
                obj.setData(list);
                obj.setReturnCode(ReturnCode.SUCCESS);
            }else{
                throw new Exception(msg);
            }
        }else{
            msg += "找不到上传的文件，请重新上传文件！";
            obj.setReturnCode(ReturnCode.FAIL);
            obj.setMsg(msg);
        }

        return obj;
    }

    @Override
    public List<IgMemberBulkRecordDTO> getBulkRecords() {
        return igMemberBulkRecordMapper.list();
    }

    @Override
    public ReturnObj login(HttpServletRequest request) {
        ReturnObj obj = new ReturnObj();
        Subject subject = SecurityUtils.getSubject();
        String verifyCode = request.getParameter("verifyCode");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //校验验证码
        if(!verifyCode.equalsIgnoreCase((String)subject.getSession().getAttribute("loginVerifyCode"))){
            obj.setReturnCode(ReturnCode.PARAMETERS_ERROR);
            obj.setMsg("验证码不正确！");
            return obj;
        }
        IgMember member = new IgMember();
        member.setUsername(username);
        IgMember result = igMemberMapper.selectOne(member);
        if(result != null){
            if(result.getPassword().equals(PasswordHandler.encodePassword(password, result.getUsername(), Constant.MD5_STR))) {
                UsernamePasswordToken token = new UsernamePasswordToken(result.getUsername(), result.getPassword());
                subject.login(token);
                // 将登录信息放进Session中
                IgMemberLoginDTO loginDTO = new IgMemberLoginDTO(result, new Date());
                subject.getSession().setAttribute("currentMember", loginDTO);
                obj.setData(result);
                obj.setMsg("登录成功");
                obj.setReturnCode(ReturnCode.LOGIN_SUCCESS);
            }else{
                obj.setReturnCode(ReturnCode.LOGIN_ERROR_USER_NOT_EXIST);
                obj.setMsg("密码错误");
            }
        }else{
            obj.setReturnCode(ReturnCode.LOGIN_ERROR_USER_NOT_EXIST);
            obj.setMsg("用户不存在");
        }
        return obj;
    }
}
