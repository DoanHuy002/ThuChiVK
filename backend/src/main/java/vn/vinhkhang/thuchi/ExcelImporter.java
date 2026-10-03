package vn.vinhkhang.thuchi;

import java.io.InputStream;
import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.util.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/** Reads primary company ledgers; cached formula values require Excel to save first. */
final class ExcelImporter {
 static String norm(String s){return Normalizer.normalize(s,Normalizer.Form.NFD).replaceAll("\\p{M}","").toLowerCase(Locale.ROOT).replace("đ","d").trim();}
 static final DataFormatter FORMAT=new DataFormatter(Locale.forLanguageTag("vi-VN"));
 static Cell cell(Row r,int c){return r==null?null:r.getCell(c);}
 static String text(Cell c){return c==null?"":FORMAT.formatCellValue(c).trim();}
 static boolean numeric(Cell c){return c!=null&&(c.getCellType()==CellType.NUMERIC||c.getCellType()==CellType.FORMULA&&c.getCachedFormulaResultType()==CellType.NUMERIC);}
 static long number(Cell c,List<String> issues){if(c==null||c.getCellType()==CellType.BLANK||text(c).isEmpty())return 0;if(!numeric(c)){issues.add(c.getCellType()==CellType.FORMULA?"FORMULA_NO_CACHE":"NO_AMOUNT");return 0;}double n=c.getNumericCellValue();if(!Double.isFinite(n)||n<0||n!=Math.rint(n)||n>1000000000000L){issues.add("NO_AMOUNT");return 0;}return (long)n;}
 static List<Map<String,Object>> read(InputStream input)throws Exception {
  List<Map<String,Object>> result=new ArrayList<>();
  try(var book=new XSSFWorkbook(input)){
   if(book.getNumberOfSheets()>100)throw new IllegalArgumentException("Too many sheets");
   for(Sheet sheet:book){String name=sheet.getSheetName();String normalized=norm(name);if(!normalized.contains("cong ty")&&!normalized.contains("cty"))continue;
    if(sheet.getLastRowNum()>100000)throw new IllegalArgumentException("Too many rows");
    for(int h=0;h<Math.min(10,sheet.getLastRowNum()+1);h++){Row header=sheet.getRow(h);if(header==null)continue;
     for(int col=0;col<Math.min(100,header.getLastCellNum());col++){
      if(!norm(text(cell(header,col))).startsWith("ngay"))continue;
      if(!norm(text(cell(header,col+1))).contains("dien giai")||!norm(text(cell(header,col+2))).equals("thu")||!norm(text(cell(header,col+3))).equals("chi")||!norm(text(cell(header,col+4))).contains("ton"))continue;
      for(int i=h+1;i<=sheet.getLastRowNum();i++){Row row=sheet.getRow(i);String description=text(cell(row,col+1));String dnorm=norm(description);
       // Secondary payroll/meal summaries have no running balance.
       if(description.isEmpty()||dnorm.startsWith("tong")||dnorm.startsWith("ton")||dnorm.startsWith("so du")||!numeric(cell(row,col+4)))continue;
       List<String> issues=new ArrayList<>();long income=number(cell(row,col+2),issues),expense=number(cell(row,col+3),issues);if(income==0&&expense==0&&issues.isEmpty())continue;
       if(income>0&&expense>0)issues.add("BOTH_IN_AND_OUT");
       String date="";Cell dc=cell(row,col);if(numeric(dc)&&DateUtil.isCellDateFormatted(dc))try{date=dc.getLocalDateTimeCellValue().toLocalDate().toString();}catch(Exception ignored){}
       if(date.isEmpty())issues.add("MISSING_DATE");
       String key=name+":"+(i+1)+":"+(col+1);String id=HexFormat.of().formatHex(MessageDigest.getInstance("SHA-256").digest(key.getBytes(StandardCharsets.UTF_8))).substring(0,20);
       var item=new LinkedHashMap<String,Object>();item.put("id",id);item.put("sheet",name);item.put("row",i+1);item.put("range",CellReference.convertNumToColString(col)+(i+1)+":"+CellReference.convertNumToColString(col+4)+(i+1));item.put("date",date);item.put("description",description);item.put("type",income>0?"RECEIPT":"EXPENSE");item.put("amount",income>0?income:expense);item.put("suggestedFund",col>=6?"CASH":"BANK");item.put("issues",issues);result.add(item);
       if(result.size()>10000)throw new IllegalArgumentException("Too many candidates");
      }
     }
    }
   }
  }
  return result;
 }
}
