package vn.vinhkhang.warehouse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Value;
import java.nio.file.*;import java.util.*;import java.math.*;import java.time.*;import java.util.concurrent.*;
/** Local, read-only accounting export. It never changes warehouse records. */
@Component public class FinanceSnapshot {
 public static void main(String[] args)throws Exception{if(args.length!=1)throw new IllegalArgumentException("Data directory required");var ds=new org.springframework.jdbc.datasource.DriverManagerDataSource("jdbc:h2:file:"+Path.of(args[0]).toAbsolutePath().resolve("warehouse")+";ACCESS_MODE_DATA=r;IFEXISTS=TRUE","sa","");var snapshot=new FinanceSnapshot(new JdbcTemplate(ds),new ObjectMapper(),new org.springframework.jdbc.datasource.DataSourceTransactionManager(ds),args[0]);snapshot.publish();snapshot.stop();} final org.springframework.transaction.support.TransactionTemplate exportTx;final JdbcTemplate db;final ObjectMapper json;final Path data;final ScheduledExecutorService timer=Executors.newSingleThreadScheduledExecutor(r->{Thread t=new Thread(r,"finance-snapshot");t.setDaemon(true);return t;});
 public FinanceSnapshot(JdbcTemplate db,ObjectMapper json,org.springframework.transaction.PlatformTransactionManager manager,@Value("${VK_DATA_DIR:./data}") String data){this.exportTx=new org.springframework.transaction.support.TransactionTemplate(manager);exportTx.setReadOnly(true);exportTx.setIsolationLevel(org.springframework.transaction.TransactionDefinition.ISOLATION_REPEATABLE_READ);this.db=db;this.json=json;this.data=Path.of(data).toAbsolutePath();}
 @PostConstruct public void start(){timer.scheduleWithFixedDelay(this::publish,2,10,TimeUnit.SECONDS);}
 @PreDestroy public void stop(){timer.shutdownNow();}
 public synchronized void publish(){try{exportTx.executeWithoutResult(status->{try{doPublish();}catch(Exception e){throw new IllegalStateException(e);}});}catch(Exception e){System.err.println("Finance export unavailable: "+e.getClass().getSimpleName());}}
 private void doPublish()throws Exception {
  Files.createDirectories(data);Path identity=data.resolve("finance-source.id");if(!Files.exists(identity))Files.writeString(identity,UUID.randomUUID().toString(),StandardOpenOption.CREATE_NEW);
  var documents=db.queryForList("SELECT d.id,d.code,d.status,d.deleted,d.supplier_id,d.created_at,d.note,s.code supplier_code,s.name supplier_name,s.phone,s.address FROM stock_documents d LEFT JOIN suppliers s ON s.id=d.supplier_id WHERE d.type='IN' ORDER BY d.id");
  var out=new ArrayList<Map<String,Object>>();
  for(var d:documents){var row=new LinkedHashMap<String,Object>();row.put("id",d.get("ID").toString());row.put("code",d.get("CODE"));row.put("status",d.get("STATUS"));row.put("deleted",d.get("DELETED"));row.put("supplier_id",Objects.toString(d.get("SUPPLIER_ID"),""));row.put("supplier_code",Objects.toString(d.get("SUPPLIER_CODE"),""));row.put("supplier_name",Objects.toString(d.get("SUPPLIER_NAME"),""));row.put("phone",Objects.toString(d.get("PHONE"),""));row.put("address",Objects.toString(d.get("ADDRESS"),""));Object created=d.get("CREATED_AT");row.put("date",created instanceof OffsetDateTime t?t.atZoneSameInstant(ZoneId.of("Asia/Ho_Chi_Minh")).toLocalDate().toString():created.toString().substring(0,10));row.put("note",Objects.toString(d.get("NOTE"),""));
   var lines=db.queryForList("SELECT p.code,p.name,p.unit,l.quantity,l.unit_cost FROM stock_document_lines l JOIN parts p ON p.id=l.part_id WHERE l.document_id=? ORDER BY l.id",d.get("ID"));var items=new ArrayList<Map<String,Object>>();BigDecimal total=BigDecimal.ZERO;boolean priced=!lines.isEmpty();
   for(var l:lines){var item=new LinkedHashMap<String,Object>();item.put("code",l.get("CODE"));item.put("name",l.get("NAME"));item.put("unit",l.get("UNIT"));item.put("quantity",l.get("QUANTITY"));item.put("unit_cost",l.get("UNIT_COST"));items.add(item);if(l.get("UNIT_COST")==null)priced=false;else total=total.add(((BigDecimal)l.get("QUANTITY")).multiply((BigDecimal)l.get("UNIT_COST")));}
   row.put("items",items);row.put("priced",priced);row.put("amount",total.setScale(0,RoundingMode.HALF_UP).longValueExact());out.add(row);
  }
  var payload=Map.of("format","VK-WAREHOUSE-FINANCE-1","source_id",Files.readString(identity).trim(),"generated_at",Instant.now().toString(),"documents",out);
  Path temp=data.resolve("finance-sync.tmp");Files.write(temp,json.writeValueAsBytes(payload));try{Files.move(temp,data.resolve("finance-sync.json"),StandardCopyOption.REPLACE_EXISTING,StandardCopyOption.ATOMIC_MOVE);}catch(AtomicMoveNotSupportedException e){Files.move(temp,data.resolve("finance-sync.json"),StandardCopyOption.REPLACE_EXISTING);}

 }
}
