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

import org.springframework.util.StringUtils;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExcleUtil {

	public static void main(String[] args) {
		bbb();
	}
	
	/**
	 * 根据列规则文档，生成列规则脚本
	 * @Description	: 
	 * @Author		: 
	 * @CreateTime	: 2018年12月14日 下午4:46:38
	 */
	public static void columnRuleSql(){
		File file = new File("D:/tmp/20181214003.xls");
		File file1 = new File("D:/tmp/20181214004.sql");
		List<List<String>> excelList = readExcel(file);
		String[] column = new String[]{"BASIC_ACTIVE_CODE","BASIC_ACTIVE_NAME","TABLE_CODE","TABLE_NAME","COLUMN_CODE",
				"COLUMN_NAME","RULE_CODE","RULE_NAME","RULE_COMMENT","P_RANGE_CODE","P_RANGE_NAME","P_CONTAIN","P_LENGTH","P_FORMAT",
				"P_MAX_VALUE","P_MIN_VALUE","P_PRECISION","P_RANGE_MORE","P_REF_COLUMN","DEFINED10"};
		int index = 0;
		try {
			if(!file1.exists()){
				file1.createNewFile();
			}
	        FileOutputStream out=new FileOutputStream(file1,false);
	        StringBuffer stringBuffer = new StringBuffer();
	        stringBuffer.append("DELETE FROM QC_BPID_PUB_COLUMN_RULE;");
			stringBuffer.append("\r\n");
			for(List<String> list : excelList){
				if(index != 0){
					int number = 0;
					StringBuffer insert = new StringBuffer();
					insert.append("INSERT INTO QC_BPID_PUB_COLUMN_RULE(ID");
					StringBuffer values = new StringBuffer();
					values.append("values('"+java.util.UUID.randomUUID().toString().substring(0, 31)+"'");
					for(String tmp : list){
						if(number == column.length){
							break;
						}
						insert.append(","+column[number]+"");
						if(16 == number){
							values.append(",'"+(null != list.get(16) && !"".equals(list.get(16)) ? list.get(16) : "0") +"'");
						}else{
							values.append(",'"+tmp+"'");
						}
						
						number++;
					}
					insert.append(",USED_FLAG,CREATE_DATETIME,DATA_TYPE)");
					values.append(",'1',sysdate,'pt');");
					stringBuffer.append(insert.toString()+values.toString()+"\r\n");
					stringBuffer.append("UPDATE qc_bpid_pub_column_rule A SET A.BUSINESS_ID = (SELECT B.ID FROM qc_bpid_pub_table_infor B WHERE A.TABLE_CODE = B.TABLE_CODE);");
					stringBuffer.append("\r\n");
					stringBuffer.append("commit;");
				}
				index ++;
			}
			out.write(stringBuffer.toString().getBytes("utf-8"));
			out.flush();
			out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * 根据表规则文档生成表规则脚本
	 * @Description	: 
	 * @Author		: 
	 * @CreateTime	: 2018年12月14日 下午4:06:55
	 */
	public static void tableRuleSql(){
		File file = new File("D:/tmp/20181214003.xls");
		File file1 = new File("D:/tmp/20181214003.sql");
		int index = 0;
		List<List<String>> excelList = readExcel2(file);
		try {
			if(!file1.exists()){
				file1.createNewFile();
			}
	        FileOutputStream out=new FileOutputStream(file1,false);
	        StringBuffer stringBuffer = new StringBuffer();
	        stringBuffer.append("DELETE FROM QC_BPID_PUB_TABLE_RULE;");
			stringBuffer.append("\r\n");
	        for(List<String> list : excelList){
				if (index < 2){
					index ++;
					continue;
				}
				if(StringUtils.isEmpty(list.get(0))){
					break;
				}
				StringBuffer insert = new StringBuffer();
				insert.append("INSERT INTO QC_BPID_PUB_TABLE_RULE(ID,CLASS_CODE,CLASS_NAME,BASIC_ACTIVE_CODE"
								+ ",BASIC_ACTIVE_NAME,TABLE_CODE,TABLE_NAME,COLUMN_CODE,COLUMN_NAME,RULE_CODE,RULE_NAME,RULE_COMMENT"
								+ ",P_REF_TABLE,P_REF_COLUMN,DEFINED10,USED_FLAG) values (");

				insert.append("'"+java.util.UUID.randomUUID().toString().substring(0, 31)+"',");
				insert.append("'"+list.get(0)+"',");
				insert.append("'"+list.get(1)+"',");
				insert.append("'"+list.get(2)+"',");
				insert.append("'"+list.get(3)+"',");
				insert.append("'"+list.get(4)+"',");
				insert.append("'"+list.get(5)+"',");
				insert.append("'"+list.get(6)+"',");
				insert.append("'"+list.get(7)+"',");
				insert.append("'"+list.get(8)+"',");
				insert.append("'"+list.get(9)+"',");
				insert.append("'"+list.get(10)+"',");
				insert.append("'"+list.get(11)+"',");
				insert.append("'"+list.get(12)+"',");
				insert.append("'"+list.get(13)+"',");
				insert.append("'"+list.get(14)+"'");
				insert.append(");");
				stringBuffer.append(insert.toString()+"\r\n");
				index ++;
			}
	        stringBuffer.append("UPDATE QC_BPID_PUB_TABLE_RULE A SET A.BUSINESS_ID = (SELECT B.ID FROM qc_bpid_pub_table_infor B WHERE A.TABLE_CODE = B.TABLE_CODE);");
			stringBuffer.append("\r\n");
	        stringBuffer.append("COMMIT;");
			out.write(stringBuffer.toString().getBytes("utf-8"));
			out.flush();
			out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * 根据业务域对照文档生成业务域脚本
	 * @Description	: 
	 * @Author		: 
	 * @CreateTime	: 2018年12月14日 下午3:25:31
	 */
	public static void yewuyuSql(){
		File file = new File("D:/tmp/20181214002.xls");
		File file1 = new File("D:/tmp/20181214002.sql");
		int index = 0;
		List<List<String>> excelList = readExcel(file);
		try {
			if(!file1.exists()){
				file1.createNewFile();
			}
	        FileOutputStream out=new FileOutputStream(file1,false);
	        StringBuffer stringBuffer = new StringBuffer();
	        stringBuffer.append("DELETE FROM QC_BPID_PUB_TABLE_INFOR;");
			stringBuffer.append("\r\n");
	        for(List<String> list : excelList){
				if (index < 2){
					index ++;
					continue;
				}
				if(StringUtils.isEmpty(list.get(0))){
					break;
				}
				StringBuffer insert = new StringBuffer();
				insert.append("INSERT INTO QC_BPID_PUB_TABLE_INFOR(ID,CLASS_CODE,CLASS_NAME,BASIC_ACTIVE_CODE"
								+ ",BASIC_ACTIVE_NAME,TABLE_CODE,TABLE_NAME,USED_FLAG,MASTER_SLAVE,DEFINED2) values (");

				insert.append("'"+java.util.UUID.randomUUID().toString().substring(0, 31)+"',");
				insert.append("'"+list.get(0)+"',");
				insert.append("'"+list.get(1)+"',");
				insert.append("'"+list.get(2)+"',");
				insert.append("'"+list.get(3)+"',");
				insert.append("'"+list.get(4)+"',");
				insert.append("'"+list.get(5)+"',");
				insert.append("'"+list.get(6)+"',");
				insert.append("'"+list.get(7)+"',");
				insert.append("'"+list.get(9)+"'");
				insert.append(");");
				stringBuffer.append(insert.toString()+"\r\n");
				index ++;
			}
	        stringBuffer.append("COMMIT;");
			out.write(stringBuffer.toString().getBytes("utf-8"));
			out.flush();
			out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * 根据文档生成值域对照表脚本
	 * @Description	: 
	 * @Author		: 
	 * @CreateTime	: 2018年12月14日 下午3:20:48
	 */
	public static void rangeCompareSql(){
		File file = new File("D:/tmp/20181214001.xls");
		File file1 = new File("D:/tmp/20181214001.sql");
		int index = 0;
		List<List<String>> excelList = readExcel(file);
		try {
			if(!file1.exists()){
				file1.createNewFile();
			}
	        FileOutputStream out=new FileOutputStream(file1,false);
	        StringBuffer stringBuffer = new StringBuffer();
	        stringBuffer.append("DELETE FROM RANGE_COMPARE;");
			stringBuffer.append("\r\n");
	        for(List<String> list : excelList){
				if (index < 2){
					index ++;
					continue;
				}
				StringBuffer insert = new StringBuffer();
				insert.append("INSERT INTO RANGE_COMPARE(LV_ONE_CODE,LV_ONE_NAME,LV_ONE_VALUE,LV_ONE_COMMENT"
								+ ",LV_TWO_CODE,LV_TWO_NAME,LV_TWO_VALUE,LV_TWO_COMMENT) values (");
				insert.append("'"+list.get(0)+"',");
				insert.append("'"+list.get(1)+"',");
				insert.append("'"+list.get(2)+"',");
				insert.append("'"+list.get(3)+"',");
				insert.append("'"+list.get(5)+"',");
				insert.append("'"+list.get(6)+"',");
				insert.append("'"+list.get(7)+"',");
				insert.append("'"+list.get(8)+"'");
				insert.append(");");
				stringBuffer.append(insert.toString()+"\r\n");
				index ++;
			}
	        stringBuffer.append("COMMIT;");
			out.write(stringBuffer.toString().getBytes("utf-8"));
			out.flush();
			out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}
	public static void aaa(){
		File file = new File("D:/tmp/2222.xls");
		File file1 = new File("D:/tmp/2.sql");
		List<List<String>> excelList = readExcel(file);
		String[] column = new String[]{"BASIC_ACTIVE_CODE","BASIC_ACTIVE_NAME","TABLE_CODE","TABLE_NAME","COLUMN_CODE",
				"COLUMN_NAME","RULE_CODE","RULE_NAME","RULE_COMMENT","P_RANGE_CODE","P_RANGE_NAME","P_CONTAIN","P_LENGTH","P_FORMAT",
				"P_MAX_VALUE","P_MIN_VALUE","P_PRECISION","P_REF_COLUMN"};
		createSqlByExcle(excelList,file1,column);
	}
	public static void bbb(){
		File file = new File("D:/tmp/20181214003.xls");
		File file1 = new File("D:/tmp/20181214004.sql");
		List<List<String>> excelList = readExcel(file);
		String[] column = new String[]{"BASIC_ACTIVE_CODE","BASIC_ACTIVE_NAME","TABLE_CODE","TABLE_NAME","COLUMN_CODE",
				"COLUMN_NAME","RULE_CODE","RULE_NAME","RULE_COMMENT","P_RANGE_CODE","P_RANGE_NAME","P_CONTAIN","P_LENGTH","P_FORMAT",
				"P_MAX_VALUE","P_MIN_VALUE","P_PRECISION","P_RANGE_MORE","P_REF_COLUMN","DEFINED10"};
		createSqlByExcle(excelList,file1,column);
	}
	
	public static void createSqlByExcle(List<List<String>> excelList,File file,String[] str){
		
		int index = 0;
		try {
			if(!file.exists()){
				file.createNewFile();
			}
	        FileOutputStream out=new FileOutputStream(file,false);
	        StringBuffer stringBuffer = new StringBuffer();
			for(List<String> list : excelList){
				if(index != 0){
					int number = 0;
					StringBuffer insert = new StringBuffer();
					insert.append("INSERT INTO QC_BPID_PUB_COLUMN_RULE(ID");
					StringBuffer values = new StringBuffer();
					values.append("values('"+java.util.UUID.randomUUID().toString().substring(0, 31)+"'");
					for(String tmp : list){
						if(number == str.length){
							break;
						}
						insert.append(","+str[number]+"");
						if(16 == number){
							values.append(",'"+(null != list.get(16) && !"".equals(list.get(16)) ? list.get(16) : "0") +"'");
						}else{
							values.append(",'"+tmp+"'");
						}
						
						number++;
					}
					insert.append(",USED_FLAG,CREATE_DATETIME,DATA_TYPE)");
					values.append(",'1',sysdate,'pt');");
					stringBuffer.append(insert.toString()+values.toString()+"\r\n");
				}
				index ++;
			}
			out.write(stringBuffer.toString().getBytes("utf-8"));
			out.flush();
			out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	
	// 去读Excel的方法readExcel，该方法的入口参数为一个File对象
	//jxl目前只支持xls扩展名的文件
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

 // 去读Excel的方法readExcel，该方法的入口参数为一个File对象
 	//jxl目前只支持xls扩展名的文件
     public static List<List<String>> readExcel2(File file) {
         try {
             // 创建输入流，读取Excel
             InputStream is = new FileInputStream(file.getAbsolutePath());
             // jxl提供的Workbook类
             Workbook wb = Workbook.getWorkbook(is);
             // Excel的页签数量
             int sheet_size = wb.getNumberOfSheets();
                 List<List<String>> outerList=new ArrayList<List<String>>();
                 // 每个页签创建一个Sheet对象
                 Sheet sheet = wb.getSheet(1);
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
