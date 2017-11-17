package root.WebServices;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.*;

import root.Bean.StatusBean;
import root.Bean.StudentBean;
import root.Bean.UserBean;
import root.DAO.LoginDAO;
import root.DAO.StudentDAO;
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
	
	@GET
	@Path("/student-list")
	@Produces("application/json")
	public Response facultyList(@Context UriInfo info) {

		String batchId = info.getQueryParameters().getFirst("batchid");
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		ArrayList<StudentBean> beans = new StudentDAO().studentList(batchId);
		if (beans == null || beans.size() == 0) {
			hashMap.put("responseStatus", false);
		} else {
			hashMap.put("responseStatus", true);
			hashMap.put("studentBeans", beans);
		}

		return Response.ok(hashMap).header("Access-Control-Allow-Origin", "*").build();
	}
	
	@POST
	@Path("/student-remove")
	@Produces("application/json")
	public Response studentRemove(@Context UriInfo info) {
		String userId = info.getQueryParameters().getFirst("userid");

		StatusBean list = new UserDAO().remove(userId);
		return Response.ok(list).header("Access-Control-Allow-Origin", "*").build();
	}
	
	/*@POST
	@Path("/student-csv")
	@Produces("application/json")
	public Response studentCSV(@FormParam("student") String studentList) {
		//String studentList = info.getQueryParameters().getFirst("student");
		HashMap<String, String> obj = new HashMap<String, String>();
		obj.put("data",studentList);
		return Response.ok(obj).header("Access-Control-Allow-Origin", "*").build();
	}*/
	
	
	@POST
	@Path("/student-csv")
	@Produces("application/json")
	public Response studentCSV(@FormParam("student") String studentList) {
		//String studentList = info.getQueryParameters().getFirst("student");
		HashMap<String, String> obj = new HashMap<String, String>();
		obj.put("data",studentList);
		return Response.ok(obj).header("Access-Control-Allow-Origin", "*").build();
	}

}
