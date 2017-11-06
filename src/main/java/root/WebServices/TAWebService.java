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
		String emailId = info.getQueryParameters().getFirst("emailid");
		String userName = info.getQueryParameters().getFirst("name");
		String batchId = info.getQueryParameters().getFirst("batchId");
		
		UserBean userBean = new UserBean();

		userBean.setEmailId(emailId);
		userBean.setUserName(userName);

		return Response.ok(new UserDAO().insertTA(userBean, batchId)).header("Access-Control-Allow-Origin", "*").build();
	}

}
