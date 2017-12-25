package zhongd.coiplatform.service.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import zhongd.coiplatform.utils.StringUtil;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * @Author xiezd
 * @Description
 * @Date Created in  16:29 星期三 2017/12/6/006
 */
@Service
public class FileServiceImpl implements FileService {
    @Value("${file.downloadpath}")
    private String path;
    @Override
    public String save(MultipartFile file) throws Exception{
        String[] strs = file.getOriginalFilename().split("\\.");
        String fileName = StringUtil.getUUIDString();
        String suffix = strs[strs.length - 1];
        String fullName = fileName + "." + suffix;
        String pathname = path + fullName;
        BufferedOutputStream bos =
                new BufferedOutputStream(new FileOutputStream(new File(pathname)));
        bos.write(file.getBytes());
        bos.close();
        return fullName;
    }

    @Override
    public File get(String filename) {
        String pathname = path + filename;
        File rtFile = new File(pathname);
        return rtFile;
    }
}
