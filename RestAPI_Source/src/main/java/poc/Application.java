package poc;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
 
/* 제작 개요
폴더 : RestAPI 
날짜 : 오전 9:30 2019-05-11
개요 : JAVA SPRING 구조로 RestAPI 구현, main 은 POC package 기준임
환경 : jdk1.8.0_162
	  spring-tool-suite-3.8.4.RELEASE-e4.6.3-win32-x86_64
	  postgresql-9.3-1102.jdbc41
기타 : 
	 1.postgresql 설치가 안되어 있다면 아래 주석 검색해서 삭제하고 테스트하면 됨	 
	 // postgresql test code was be added  (excute.java, infoController.java)
	   
	 2.상세 테스트 방법은 RestAPI_Result 폴더에 설명함
	 3.환경설정에 필요한 파일은 Setting 파일에 두었음
	 	- Advanced Restapi Client 툴로 테스트할수 있도록 Setting 폴더에 복사함. (arc-setup.exe)
	 	- Postgresql 테이블은 info.sql 사용하면 됨 
*/  

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);        
    }
}