package poc;
/* 
 참고 : Postgresql test table 
 
CREATE TABLE info
(
  id serial NOT NULL,
  name character(20),
  job character(20),
  CONSTRAINT id_pkey PRIMARY KEY (id)
)
*/
public class Info {
	private long id;	   
    private String name;   
    private String job;
   
    public Info() {
           this(0, "", "");
    }
   
    public Info(long id, String name, String job) {
           this.id = id;
           this.name = name;
           this.job = job;
    }
    
    public long getId() {
           return id;
    }
 
    public void setId(long id) {
           this.id = id;
    }
 
    public String getName() {
           return name;
    }
 
    public void setName(String name) {
           this.name = name;
    }
 
    public String getJob() {
           return job;
    }
 
    public void setJob(String job) {
           this.job = job;
    }
}
