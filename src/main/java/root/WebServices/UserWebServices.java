package root.WebServices;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import root.Bean.FacultyBean;
import root.Bean.UserBean;
import root.DAO.UserDAO;
import root.Utils.GenrateMathodsUtils;

@Path("/user")
public class UserWebServices {

	@GET
	@Path("/user-list")
	@Produces("application/json")
	public Response getList() {
		ArrayList<UserBean> list = new UserDAO().getList();
		return Response.ok(list).header("Access-Control-Allow-Origin", "*").build();
	}


	@POST
	@Path("/forgot-pass")
	@Produces("application/json")
	public Response forgotPassword(@FormParam("emailid") String emailId) {
		boolean result = new UserDAO().forgotPass(emailId);
		return Response.ok(result).header("Access-Control-Allow-Origin", "*").build();
	}
}
