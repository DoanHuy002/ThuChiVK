package vn.vinhkhang.thuchi;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.apache.poi.ss.usermodel.*;
import java.io.*;import java.util.*;
class SimpleImportTest {
 @Test void templatesHaveRequiredColumnsBordersTextIdentifiersAndNoSampleRecords()throws Exception {
  assertEquals(12,SimpleImport.SPECS.size());
  for(var spec:SimpleImport.SPECS){try(var w=WorkbookFactory.create(new ByteArrayInputStream(SimpleImport.templateBytes(spec)))){
   var s=w.getSheet("Nhap du lieu");assertNotNull(s);assertEquals(spec.columns().split("\\|").length,s.getRow(0).getLastCellNum());assertEquals(BorderStyle.THIN,s.getRow(0).getCell(0).getCellStyle().getBorderLeft());assertNotNull(s.getPaneInformation());assertEquals(2,w.getNumberOfSheets());
   for(var c:s.getRow(1))assertEquals(CellType.BLANK,c.getCellType());
   for(var c:s.getRow(0))if(c.getStringCellValue().contains("Số tài khoản")||c.getStringCellValue().contains("Điện thoại"))assertEquals("@",s.getRow(1).getCell(c.getColumnIndex()).getCellStyle().getDataFormatString());
  }}
 }
 @Test void dateAndMoneyUseStrictRules(){assertEquals("2026-10-08",SimpleImport.day(Map.of("ngay","08/10/2026"),"Ngày",true));assertThrows(Api.Fail.class,()->SimpleImport.day(Map.of("ngay","31/02/2026"),"Ngày",true));assertEquals(1000000,SimpleImport.money(Map.of("so tien (d)","1.000.000"),"Số tiền (đ)",true));assertThrows(Api.Fail.class,()->SimpleImport.money(Map.of("so tien (d)","1.5"),"Số tiền (đ)",true));assertEquals("so tien (d)",SimpleImport.key("Số tiền (đ) *"));}
}
