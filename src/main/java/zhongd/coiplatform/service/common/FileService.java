package zhongd.coiplatform.service.common;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author xiezd
 * @Description
 * @Date Created in  16:22 星期三 2017/12/6/006
 */

public interface FileService {

    String save(MultipartFile file) throws Exception;
}
