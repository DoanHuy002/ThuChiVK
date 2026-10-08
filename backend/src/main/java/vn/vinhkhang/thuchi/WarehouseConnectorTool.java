package vn.vinhkhang.thuchi;
import java.nio.file.*;import java.util.zip.*;import java.io.*;
/** Installs only the read-only export component, preserving all existing warehouse classes. */
final class WarehouseConnectorTool {
 static void install(String file)throws Exception {
  Path jar=Path.of(file).toAbsolutePath();if(!jar.getFileName().toString().equals("backend.jar"))throw new IllegalArgumentException("Chọn đúng app Kho");
  String entry="BOOT-INF/classes/vn/vinhkhang/warehouse/FinanceSnapshot.class";byte[] payload;
  try(var in=WarehouseConnectorTool.class.getResourceAsStream("/warehouse/FinanceSnapshot.class")){if(in==null)throw new IllegalStateException("Thiếu thành phần kết nối");payload=in.readAllBytes();}
  try(var zip=new ZipFile(jar.toFile())){if(zip.getEntry("BOOT-INF/classes/vn/vinhkhang/warehouse/Api.class")==null)throw new IllegalArgumentException("Đây không phải app Kho Vĩnh Khang");if(zip.getEntry(entry)!=null){byte[] installed;try(var current=zip.getInputStream(zip.getEntry(entry))){installed=current.readAllBytes();}if(new String(installed,java.nio.charset.StandardCharsets.ISO_8859_1).contains("CARRIER-"))return;} /* Keep the exporter shipped with newer warehouse versions. */}
  Path backup=jar.resolveSibling("backend-before-thuchi-connect.jar");if(!Files.exists(backup))Files.copy(jar,backup);Path temp=jar.resolveSibling("backend-connect.tmp");
  try(var zip=new ZipFile(jar.toFile());var out=new ZipOutputStream(Files.newOutputStream(temp))){var entries=zip.entries();while(entries.hasMoreElements()){var old=entries.nextElement();if(old.getName().equals(entry))continue;var next=new ZipEntry(old.getName());next.setMethod(old.getMethod());if(old.getMethod()==ZipEntry.STORED){next.setSize(old.getSize());next.setCompressedSize(old.getSize());next.setCrc(old.getCrc());}out.putNextEntry(next);try(var in=zip.getInputStream(old)){in.transferTo(out);}out.closeEntry();}out.putNextEntry(new ZipEntry(entry));out.write(payload);out.closeEntry();}
  try{Files.move(temp,jar,StandardCopyOption.REPLACE_EXISTING);}catch(Exception e){Files.deleteIfExists(temp);throw new IOException("Đóng app Kho rồi kết nối lại. Bản gốc đã được sao lưu.",e);}
 }
}
