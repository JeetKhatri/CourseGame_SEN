package root.WebServices;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import root.Bean.StatusBean;
import root.Bean.StudentBean;
import root.Bean.UserBean;
import root.DAO.LoginDAO;
import root.DAO.UserDAO;

@Path("/student")
public class StudentWebService {

	@POST
	@Path("/student-insert")
	@Produces("application/json")
	public Response insert(@Context UriInfo info) {

		String emailId = info.getQueryParameters().getFirst("emailid");
		String userName = info.getQueryParameters().getFirst("name");
		String batchId = info.getQueryParameters().getFirst("batchid");

		StudentBean studentBean = new StudentBean();
		studentBean.setBatchId(batchId);
		studentBean.setEmailId(emailId);
		studentBean.setUserName(userName);
		StatusBean list = new UserDAO().insertStudent(studentBean);
		return Response.ok(list).header("Access-Control-Allow-Origin", "*").build();
	}
}
