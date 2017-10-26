package root.DAO;

import org.springframework.jdbc.core.JdbcTemplate;

public class UserDAO {

	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	

}
