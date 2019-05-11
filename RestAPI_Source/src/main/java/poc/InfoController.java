package poc;

import poc.InfoService;

//import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping; // @RequestMapping
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody; // @ResponseBody

import java.sql.SQLException;

import org.springframework.web.bind.annotation.PathVariable; // @PathVariable
import org.springframework.web.bind.annotation.RequestMethod; // method=RequestMethod.GET

@RestController
@RequestMapping("/info")
public class InfoController {
	//
	// 기본 생성자 및 멤버 변수
	//
	private InfoService Service = new InfoService();
	private static final String template = "your id : %s";
	
	//
	// 동작 기본 테스트용 
	// 실행 : http://localhost:8080/info/ (GET) 
	// 실행 : http://localhost:8080/info?id=1001 (GET)
	//
	@RequestMapping(method=RequestMethod.GET)
	public String info(
		@RequestParam(value="id", defaultValue="None") String id) throws SQLException
    {	
		// 동작 기본 테스트 외에 초기화 하고 싶다면 아래 실행
		// 실행 : http://localhost:8080/info (GET)
		// 실행 : http://localhost:8080/info?id=clear (GET)		
		if(id.equals("clear"))
			Service.clearRest();	
	
		//
    	// postgresql test code was be added
		// 		
		Service.InitRest();
		
		return (String.format(template, id));
    }	

	// Restful 서비스
	// 아래는 각각 테스트용 링크
	
	// CREATE : POST
	// 실행 : http://localhost:8080/info/1/lim/clouder (POST)
	@RequestMapping(value="/{id}/{name}/{job}", method=RequestMethod.POST)
    @ResponseBody
    public Info putRest(
		@PathVariable("id") long id, 
		@PathVariable("name") String name,
		@PathVariable("job") String job) 
	{		
		return Service.createRest(id, name, job);
    }
		
	// READ(SELECT) : GET
	// 실행 : http://localhost:8080/info/1 (GET)
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
    @ResponseBody
    public Info getRest(
    	@PathVariable("id") long id) 
	{           
		return Service.selectRest(id);
    }	
	
    // UPDATE : PUT
	// 실행 : http://localhost:8080/info/1/lim/system administrator (PUT)
	// 실행 : http://localhost:8080/info/1 (GET)
	@RequestMapping(value="/{id}/{name}/{job}", method=RequestMethod.PUT)
    @ResponseBody
    public Info updateRest(
		@PathVariable("id") long id,
		@PathVariable("name") String name,
		@PathVariable("job") String job) 
	{
		return Service.updateRest(id, name, job);
    }
	
    // DELETE
	// 실행 : http://localhost:8080/info/1 (DELETE)
	// 실행 : http://localhost:8080/info/1 (GET)
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	@ResponseBody
	public Info deleteRest(@PathVariable("id") long id) 
	{	
		return Service.deleteRest(id);
	}
}
