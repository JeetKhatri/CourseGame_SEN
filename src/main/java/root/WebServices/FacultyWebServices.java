package root.WebServices;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import root.Bean.BatchBean;
import root.Bean.FacultyBean;
import root.Bean.UserBean;
import root.DAO.FacultyDAO;
import root.DAO.UserDAO;

@Path("/faculty")
public class FacultyWebServices {


	@PUT
	@Path("/faculty-approved")
	@Produces("application/json")
	public boolean facultyApproved(@Context UriInfo info) {

		String facultyId = info.getQueryParameters().getFirst("facultyid");
		return new FacultyDAO().approve(facultyId);
	}
	
	@POST
	@Path("/faculty-insert")
	@Produces("application/json")
	public boolean insert(@Context UriInfo info) {

		String emailId = info.getQueryParameters().getFirst("emailid");
		String userName = info.getQueryParameters().getFirst("name");
		String degree = info.getQueryParameters().getFirst("degree");

		UserBean userBean = new UserBean();

		userBean.setEmailId(emailId);
		userBean.setUserName(userName);
		
		return new UserDAO().insertFaculty(userBean,degree);
	}


	@POST
	@Path("/faculty-update")
	@Produces("application/json")
	public boolean updateFaculty(@Context UriInfo info) {

		String facultyId = info.getQueryParameters().getFirst("facultyid");
		String userId = info.getQueryParameters().getFirst("userid");
		String userName = info.getQueryParameters().getFirst("name");
		String userEmailId = info.getQueryParameters().getFirst("emailid");
		String userRole = info.getQueryParameters().getFirst("role");
		String isAvailable = info.getQueryParameters().getFirst("isAvailable");
		String isApproved = info.getQueryParameters().getFirst("isApproved");
		String degree = info.getQueryParameters().getFirst("degree");

		
		FacultyBean bean = new FacultyBean();
		bean.setFacultyId(facultyId);
		bean.setDegree(degree);
		bean.setIsApproved(isApproved);
		bean.setEmailId(userEmailId);
		bean.setUserId(userId);
		bean.setUserIsAvailable(isAvailable);
		bean.setUserName(userName);
		bean.setUserRole(userRole);

		return new FacultyDAO().update(bean);
	}

	@POST
	@Path("/faculty-batch")
	@Produces("application/json")
	public Response facultyBatch(@Context UriInfo info) {

		String userId = info.getQueryParameters().getFirst("userid");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		ArrayList<BatchBean> beans = new FacultyDAO().facultyBatch(userId);
		if (beans == null || beans.size() == 0) {
			hashMap.put("responseStatus", false);
		} else {
			hashMap.put("responseStatus", true);
			hashMap.put("batchBeans", beans);
		}

		return Response.ok(hashMap).header("Access-Control-Allow-Origin", "*").build();
	}

	@GET
	@Path("/faculty-list")
	@Produces("application/json")
	public Response facultyList(@Context UriInfo info) {

		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		ArrayList<FacultyBean> beans = new FacultyDAO().facultyList();
		if (beans == null || beans.size() == 0) {
			hashMap.put("responseStatus", false);
		} else {
			hashMap.put("responseStatus", true);
			hashMap.put("facutlyBeans", beans);
		}

		return Response.ok(hashMap).header("Access-Control-Allow-Origin", "*").build();
	}

}
