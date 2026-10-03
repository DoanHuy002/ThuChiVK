package vn.vinhkhang.thuchi;
import java.nio.file.*;
import javax.sql.DataSource;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
@SpringBootApplication
public class Application {
 public static final Path DATA=Path.of(System.getenv().getOrDefault("VK_DATA_DIR","data")).toAbsolutePath();
 public static void main(String[]args)throws Exception {Files.createDirectories(DATA); var context=SpringApplication.run(Application.class,args);if("true".equals(System.getenv("VK_DESKTOP"))){Thread watcher=new Thread(()->{try{while(System.in.read()!=-1){}context.close();}catch(Exception e){context.close();}},"desktop-lifecycle");watcher.setDaemon(true);watcher.start();}}
 @Bean DataSource dataSource() {var d=new DriverManagerDataSource();d.setDriverClassName("org.sqlite.JDBC");d.setUrl("jdbc:sqlite:"+DATA.resolve("thuchi.sqlite")+"?foreign_keys=on&busy_timeout=10000");return d;}
}
