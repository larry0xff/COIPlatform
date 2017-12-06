package zhongd.coiplatform.testPOI;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.time.Clock;
import java.util.Iterator;
import java.util.UUID;

/**
 * @Author xiezd
 * @Description
 * @Date Created in  15:00 星期三 2017/12/6/006
 */
public class Demo {

    public static void main(String[] a){
        try{
//            File file = new File("G:/temp/test.xlsx");
//            FileInputStream fis = new FileInputStream(file);
//            XSSFWorkbook workbook = new XSSFWorkbook(fis);
//            XSSFSheet sheet = workbook.getSheetAt(0);
//            Iterator<Row> rows = sheet.iterator();
//            while(rows.hasNext()){
//                Row row = rows.next();
//                Iterator<Cell> cells = row.cellIterator();
//                while(cells.hasNext()){
//                    System.out.print((int)(cells.next().getNumericCellValue()) + " ");
//                }
//                System.out.println();
//            }

            for(int i = 0; i < 20; i++){
                String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                System.out.println(uuid);
            }
        }catch(Exception e){
            System.out.println(e);
        }

    }
}
