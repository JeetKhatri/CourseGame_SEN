package root.WebServices;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import root.Bean.BatchBean;
import root.Bean.QuizBean;
import root.DAO.BatchDAO;

@Path("/batch")
public class BatchWebService {

	@POST
	@Path("/batch-insert")
	@Produces("application/json")
	public boolean insert(@Context UriInfo info) {

		String userId = info.getQueryParameters().getFirst("userid");
		String batchname = info.getQueryParameters().getFirst("batchName");
		
		return new BatchDAO().insert(userId, batchname);
	}

	@POST
	@Path("/batch-quiz")
	@Produces("application/json")
	public Response quizList(@Context UriInfo info) {

		String batchid = info.getQueryParameters().getFirst("batchid");

		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		ArrayList<QuizBean> beans = new BatchDAO().quizList(batchid);
		if (beans == null || beans.size() == 0) {
			hashMap.put("responseStatus", false);
		} else {
			hashMap.put("responseStatus", true);
			hashMap.put("quizBeans", beans);
		}

		return Response.ok(hashMap).header("Access-Control-Allow-Origin", "*").build();
	}
	
}
