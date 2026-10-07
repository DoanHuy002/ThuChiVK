package vn.vinhkhang.thuchi;
import java.sql.*;
import java.nio.charset.StandardCharsets;
import org.springframework.core.io.ClassPathResource;
final class DatabaseUpgrade {
 static void upgrade(Connection c)throws Exception {
  int version;try(var s=c.createStatement();var r=s.executeQuery("PRAGMA user_version")){if(!r.next())throw new IllegalStateException("Missing database version");version=r.getInt(1);}
  if(version==2)return;if(version!=1)throw new IllegalStateException("Unsupported database version");
  String sql=new String(new ClassPathResource("migration-v2.sql").getInputStream().readAllBytes(),StandardCharsets.UTF_8);
  c.setAutoCommit(false);try(var s=c.createStatement()){for(String statement:sql.split(";"))if(!statement.isBlank())s.execute(statement);c.commit();}catch(Exception e){c.rollback();throw e;}finally{c.setAutoCommit(true);}
 }
}
