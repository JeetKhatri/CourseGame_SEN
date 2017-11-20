package root.WebServices;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;


import root.Bean.BatchBean;
import root.Bean.TABean;
import root.Bean.UserBean;
import root.DAO.TADAO;
import root.DAO.UserDAO;

@Path("/ta")
public class TAWebService {

	@POST
	@Path("/ta-insert")
	@Produces("application/json")
	public Response insertAlbum(@FormParam("emailid") String emailId,@FormParam("name") String userName,@FormParam("batchId") String batchId) {
		
		UserBean userBean = new UserBean();

		userBean.setEmailId(emailId);
		userBean.setUserName(userName);

		return Response.ok(new UserDAO().insertTA(userBean, batchId)).header("Access-Control-Allow-Origin", "*").build();
	}
	/*@POST
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
	}*/

	
	@GET
	@Path("/ta-list")
	@Produces("application/json")
	public Response taList(@Context UriInfo info) {
		String batchId = info.getQueryParameters().getFirst("batchid");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		ArrayList<TABean> beans = new TADAO().taList(batchId);
		if (beans == null || beans.size() == 0) {
			hashMap.put("responseStatus", false);
		} else {
			hashMap.put("responseStatus", true);
			hashMap.put("TABeans", beans);
		}

		return Response.ok(hashMap).header("Access-Control-Allow-Origin", "*").build();
	}

	@GET
	@Path("/batch-list")
	@Produces("application/json")
	public Response batchList(@Context UriInfo info) {
		
		String userId = info.getQueryParameters().getFirst("userid");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		ArrayList<BatchBean> beans = new TADAO().batchList(userId);
		
		if (beans == null || beans.size() == 0) {
			hashMap.put("responseStatus", false);
		} else {
			hashMap.put("responseStatus", true);
			hashMap.put("batchData", beans);
		}

		return Response.ok(hashMap).header("Access-Control-Allow-Origin", "*").build();
	}
	
}
