package postgresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// cylim 20190511 
// POC 로 진행하기에, postgresql 접속 정보는 별도로 properties 로 두지 않음.retry 는 제외.
// 테이블 명세는 아래와 같습니다
	/*
	CREATE TABLE info
	(
	  id serial NOT NULL,
	  name character(20),
	  job character(20),  
	  CONSTRAINT id_pkey PRIMARY KEY (id)
	)	
	 */
// 향후에 백업,백큠,파티셔닝,sp 등 트랜잭션 혹은 롤백 상황을 고려햐여 따로 만드는 것이 좋습니다.
public class excute {	
	
    // 
	// 실행 쿼리 함수입니다.
	// 데이타를 가져오는 쿼리에 사용합니다. (select) 
	//
    public ResultSet Query(String sql)
    {    	
    	//
    	// postgresql test code was be added
    	// return null;
    	
    	ResultSet resultSet = null;
    	try (Connection connection =
				DriverManager.getConnection
				("jdbc:postgresql://localhost:5432/try_postgre", "postgres", "admin")) 
		{  
    		System.out.println(sql); 
            Statement statement = connection.createStatement();            		
            resultSet = statement.executeQuery(sql);
            //while (resultSet.next()) {
            //    System.out.printf("%-30.30s  %-30.30s %-30.30s %n", 
            //    		resultSet.getString("id"), resultSet.getString("name"), resultSet.getString("job"));
            //} 
            return resultSet;
        }  
		catch (SQLException e){
			
            System.out.println("Connection failure.");
            e.printStackTrace();
            return resultSet;
        }   
        
    }
    
    //
    // 명령형 쿼리 함수입니다.
    // non 쿼리 함수 입니다. (insert,delete,update)
    //
    public boolean NonQuery(String sql)
    {	  	
    	//
    	// postgresql test code was be added
    	// return false; 
    	
    	System.out.println(sql); 
    	try (Connection connection =
				DriverManager.getConnection
				("jdbc:postgresql://localhost:5432/try_postgre", "postgres", "admin")) 
		{   
            Statement statement = connection.createStatement();
            return statement.execute(sql); 
        }  
		catch (SQLException e){
            System.out.println("Connection failure.");
            e.printStackTrace();
            return false;
        }  
          	
    }
}
