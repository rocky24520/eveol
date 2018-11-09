package cn.com.rockySun.eve.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.FileSystemNotFoundException;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExcleUtil {

	public static void main(String[] args) {
		File file = new File("D:/tmp/2222.xls");
		File file1 = new File("D:/tmp/jiaoyan.sql");
		List<List<String>> excelList = readExcel(file);
        createSqlByExcle(excelList,file1);

	}
	
	public static void createSqlByExcle(List<List<String>> excelList,File file){
		String[] column = new String[]{"BASIC_ACTIVE_CODE","BASIC_ACTIVE_NAME","TABLE_CODE","TABLE_NAME","COLUMN_CODE",
				"COLUMN_NAME","RULE_CODE","RULE_NAME","RULE_COMMENT","P_RANGE_CODE","P_RANGE_NAME","P_CONTAIN","P_LENGTH","P_FORMAT",
				"P_MAX_VALUE","P_MIN_VALUE","P_REF_COLUMN"};
		int index = 0;
		try {
			if(!file.exists()){
				file.createNewFile();
			}
	        FileOutputStream out=new FileOutputStream(file,false);
	        StringBuffer stringBuffer = new StringBuffer();
			for(List<String> list : excelList){
				if(index != 0){
	        		stringBuffer.append("INSERT INTO QC_BPID_PUB_COLUMN_RULE(ID,BASIC_ACTIVE_CODE,BASIC_ACTIVE_NAME,TABLE_CODE,TABLE_NAME,COLUMN_CODE"
	            		+ ",COLUMN_NAME,RULE_CODE,RULE_NAME,RULE_COMMENT,P_RANGE_CODE,P_RANGE_NAME,P_CONTAIN,P_LENGTH,P_FORMAT,P_MAX_VALUE"
	            		+ ",P_MIN_VALUE,P_REF_COLUMN,USED_FLAG,P_PRECISION,CREATE_DATETIME,DATA_TYPE)values(");
	        		stringBuffer.append("'"+java.util.UUID.randomUUID()+"',");
	        		stringBuffer.append("'"+list.get(0)+"',");
	        		stringBuffer.append("'"+list.get(1)+"',");
	        		stringBuffer.append("'"+list.get(2)+"',");
	        		stringBuffer.append("'"+list.get(3)+"',");
	        		stringBuffer.append("'"+list.get(4)+"',");
	        		stringBuffer.append("'"+list.get(5)+"',");
	        		stringBuffer.append("'"+list.get(6)+"',");
	        		stringBuffer.append("'"+list.get(7)+"',");
	        		stringBuffer.append("'"+list.get(8)+"',");
	        		stringBuffer.append("'"+list.get(9)+"',");
	        		stringBuffer.append("'"+list.get(10)+"',");
	        		stringBuffer.append("'"+list.get(11)+"',");
	        		stringBuffer.append("'"+list.get(12)+"',");
	        		stringBuffer.append("'"+list.get(13)+"',");
	        		stringBuffer.append("'"+list.get(14)+"',");
	        		stringBuffer.append("'"+list.get(15)+"',");
	        		stringBuffer.append("'"+list.get(16)+"',");
	        		stringBuffer.append("'1',");
	        		stringBuffer.append("'0',");
	        		stringBuffer.append("sysdate,");
	        		stringBuffer.append("'pt');");
	        		stringBuffer.append("\r\n");
				}
				index ++;
			}
			out.write(stringBuffer.toString().getBytes("utf-8"));
			out.flush();
			out.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileSystemNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
	
	
	// 去读Excel的方法readExcel，该方法的入口参数为一个File对象
    public static List<List<String>> readExcel(File file) {
        try {
            // 创建输入流，读取Excel
            InputStream is = new FileInputStream(file.getAbsolutePath());
            // jxl提供的Workbook类
            Workbook wb = Workbook.getWorkbook(is);
            // Excel的页签数量
            int sheet_size = wb.getNumberOfSheets();
            for (int index = 0; index < sheet_size; index++) {
                List<List<String>> outerList=new ArrayList<List<String>>();
                // 每个页签创建一个Sheet对象
                Sheet sheet = wb.getSheet(index);
                // sheet.getRows()返回该页的总行数
                for (int i = 0; i < sheet.getRows(); i++) {
                    List innerList=new ArrayList();
                    // sheet.getColumns()返回该页的总列数
                    for (int j = 0; j < sheet.getColumns(); j++) {
                        String cellinfo = sheet.getCell(j, i).getContents();
                        if(cellinfo.isEmpty()){
                            innerList.add("");
                        }else{
                        	innerList.add(cellinfo);
                        }
                        System.out.print(cellinfo);
                    }
                    outerList.add(i, innerList);
                    System.out.println();
                }
                return outerList;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
