package root.WebServices;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import root.Bean.FacultyBean;
import root.Bean.TABean;
import root.Bean.UserBean;
import root.DAO.UserDAO;
import root.Utils.GenrateMathodsUtils;

@Path("/ta")
public class TAWebService {

	@POST
	@Path("/ta-insert")
	@Produces("application/json")
	public Response insertAlbum(@Context UriInfo info) {
		System.out.println("here");
		String emailId = info.getQueryParameters().getFirst("emailid");
		String userPassword = info.getQueryParameters().getFirst("password");
		String userRole = info.getQueryParameters().getFirst("role");
		String userName = info.getQueryParameters().getFirst("name");
		String batchId = info.getQueryParameters().getFirst("batchId");
		
		System.out.println("ok  --> "+emailId);
		UserBean userBean = new UserBean();

		userBean.setEmailId(emailId);
		userBean.setUserRole(userRole);
		userBean.setUserName(userName);
		userBean.setUserPassword(userPassword);

		ArrayList<TABean> list = new UserDAO().insertTA(userBean, batchId);
		return Response.ok(list).header("Access-Control-Allow-Origin", "*").build();
	}

}
