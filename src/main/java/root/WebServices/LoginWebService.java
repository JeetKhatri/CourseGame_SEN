package root.WebServices;


import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import root.Bean.FacultyBean;
import root.Bean.StudentBean;
import root.Bean.TABean;
import root.Bean.UserBean;
import root.DAO.LoginDAO;

@Path("/login")
public class LoginWebService {

	@POST
	@Path("/student")
	@Produces("application/json")
	public Response studentLogin(@Context UriInfo info) {

		String emailId = info.getQueryParameters().getFirst("emailid");
		String password = info.getQueryParameters().getFirst("password");

		StudentBean list = new LoginDAO().studentLogin(emailId, password);
		return Response.ok(list).header("Access-Control-Allow-Origin", "*").build();

		// return new LoginDAO().studentLogin(emailId,password);
	}

	@POST
	@Path("/ta")
	@Produces("application/json")
	public Response taLogin(@Context UriInfo info) {

		String emailId = info.getQueryParameters().getFirst("emailid");
		String password = info.getQueryParameters().getFirst("password");

		TABean list = new LoginDAO().taLogin(emailId, password);
		return Response.ok(list).header("Access-Control-Allow-Origin", "*").build();

		// return new LoginDAO().studentLogin(emailId,password);
	}

	@POST
	@Path("/faculty")
	@Produces("application/json")
	public Response facultyLogin(@Context UriInfo info) {

		String emailId = info.getQueryParameters().getFirst("emailid");
		String password = info.getQueryParameters().getFirst("password");

		FacultyBean list = new LoginDAO().facultyLogin(emailId, password);
		return Response.ok(list).header("Access-Control-Allow-Origin", "*").build();

		// return new LoginDAO().studentLogin(emailId,password);
	}

	@POST
	@Path("/admin")
	@Produces("application/json")
	public Response adminLogin(@Context UriInfo info) {

		String emailId = info.getQueryParameters().getFirst("emailid");
		String password = info.getQueryParameters().getFirst("password");

		UserBean list = new LoginDAO().adminLogin(emailId, password);
		return Response.ok(list).header("Access-Control-Allow-Origin", "*").build();

		// return new LoginDAO().studentLogin(emailId,password);
	}

}
