package poc;
import postgresql.excute;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

// cylim 20190511 
// 개요 
// 	- CRUD 를 지원하는 서비스 로직에 해당함. 자체 Heap에서 해쉬맵으로 내부적으로 관리하도록 한다.  
// 	- Backend 용으로 내부 로직을 위한 서버 용도 , 즉  내부 계산 용도 혹은 실시간 용도로 확장해서 사용 (KPI)
// 기타
//  - 현재는 데이타베이스 jdbc는 1개만 추가함 (Postgresql) 
//	- 서비스에 추가하는 방식임.
//  - Vaccuum, 통계처리 등과 같은 트랜잭션이 오래 걸리는 경우는 제외해서 사용해야 함.

@Service("RestService")
public class InfoService {	
	private final ConcurrentHashMap<Long, Info> InfoHashMap = new ConcurrentHashMap<Long, Info>();
	private excute pg_ext = new excute();

	// CREATE : POST
    public Info createRest(long id, String name, String job) { 
    	String sql = String.format("insert into info(id, name, job) values (%s, '%s', '%s')",id,name,job);    	
    	pg_ext.NonQuery(sql);
    	return InfoHashMap.put(id, new Info(id, name, job));
    }
    
    // READ(SELECT) : GET
    public Info selectRest(long id){
    	String sql = String.format("select * from info where id=%s", id);
    	pg_ext.Query(sql);
    	return InfoHashMap.get(id);
    }
      
    // UPDATE : PUT
    public Info updateRest(long id, String name, String job) {
    	String sql = String.format("update info set name='%s',job='%s' from info where id=%s",id,name,job);    	
    	pg_ext.NonQuery(sql);
    	return InfoHashMap.replace(id, new Info(id, name, job));
    }
   
    // DELETE
    public Info deleteRest(long id) {
    	String sql = String.format("delete from info where id=%s",id);    	
    	pg_ext.NonQuery(sql);
    	return InfoHashMap.remove(id);
    }
    
    // CLEAR
    public boolean clearRest() {    	  
    	String sql = String.format("delete from info");    	
    	pg_ext.NonQuery(sql);
    	InfoHashMap.clear();
    	System.out.println("All Datas were Clear!");
    	return true;
    }
  
    public boolean InitRest() throws SQLException {
    	InfoHashMap.clear();
    	String sql = String.format("select * from info");
    	ResultSet rSet = pg_ext.Query(sql);
    	while (rSet.next()) {
    		long id = Long.parseLong(rSet.getString("id"));
    		String name =rSet.getString("name");
    		String job= rSet.getString("job");
    		Info info = new Info(id,name,job);
    		InfoHashMap.put(id,info);
        } 
    	return true;
    }
}
