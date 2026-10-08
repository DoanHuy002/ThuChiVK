package vn.vinhkhang.thuchi;
import java.sql.*;import java.nio.charset.StandardCharsets;import org.springframework.core.io.ClassPathResource;
final class DatabaseUpgrade {
 static void upgrade(Connection c)throws Exception {int version;try(var s=c.createStatement();var r=s.executeQuery("PRAGMA user_version")){if(!r.next())throw new IllegalStateException("Missing database version");version=r.getInt(1);}if(version<1||version>5)throw new IllegalStateException("Unsupported database version");
 for(int next=version+1;next<=5;next++){String sql=new String(new ClassPathResource("migration-v"+next+".sql").getInputStream().readAllBytes(),StandardCharsets.UTF_8);c.setAutoCommit(false);try(var s=c.createStatement()){for(String line:sql.split("\\R"))if(!line.isBlank())s.execute(line);c.commit();}catch(Exception e){c.rollback();throw e;}finally{c.setAutoCommit(true);}}
 boolean hasJobTitle=false;try(var s=c.createStatement();var rows=s.executeQuery("PRAGMA table_info(contacts)")){while(rows.next())if(rows.getString("name").equals("job_title"))hasJobTitle=true;}
 if(!hasJobTitle)try(var s=c.createStatement()){s.execute("ALTER TABLE contacts ADD COLUMN job_title TEXT NOT NULL DEFAULT ''");}
 }
}
