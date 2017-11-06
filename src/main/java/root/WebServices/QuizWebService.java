package root.WebServices;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import root.Bean.QuizBean;
import root.Bean.StatusBean;
import root.DAO.QuizDAO;
import root.DAO.UserDAO;

@Path("/quiz")
public class QuizWebService {

	@POST
	@Path("/quiz-insert")
	@Produces("application/json")
	public boolean AddQuize(@Context UriInfo info) {

		QuizBean quiz = new QuizBean();

		quiz.setName(info.getQueryParameters().getFirst("name"));
		quiz.setBatchId(info.getQueryParameters().getFirst("batchid"));
		quiz.setStartTime(info.getQueryParameters().getFirst("starttime"));
		quiz.setEndTime(info.getQueryParameters().getFirst("endtime"));
		quiz.setCreatedBy(info.getQueryParameters().getFirst("createdby"));

		return new QuizDAO().insert(quiz);
	}

	@PUT
	@Path("/quiz-update")
	@Produces("application/json")
	public boolean editQuize(@Context UriInfo info) {

		QuizBean quiz = new QuizBean();

		quiz.setQuizId(info.getQueryParameters().getFirst("quizid"));
		quiz.setName(info.getQueryParameters().getFirst("name"));
		quiz.setStatus(info.getQueryParameters().getFirst("status"));
		quiz.setBatchId(info.getQueryParameters().getFirst("batchid"));
		quiz.setStartTime(info.getQueryParameters().getFirst("starttime"));
		quiz.setEndTime(info.getQueryParameters().getFirst("endtime"));
		quiz.setCreatedBy(info.getQueryParameters().getFirst("createdby"));

		return new QuizDAO().update(quiz);
	}
	
	@POST
	@Path("/quiz-activation")
	@Produces("application/json")
	public Response availableQuiz(@Context UriInfo info) {

		String batchId = info.getQueryParameters().getFirst("batchid");
		String quizId = info.getQueryParameters().getFirst("quizid");
		StatusBean list = new QuizDAO().quizActivation(batchId, quizId);
		return Response.ok(list).header("Access-Control-Allow-Origin", "*").build();
	}
	
	@GET
	@Path("/batch-wise-quiz")
	@Produces("application/json")
	public Response batchWiseQuiz(@Context UriInfo info) {

		String batchId = info.getQueryParameters().getFirst("batchid");
		HashMap<String, Object> list = new QuizDAO().batchWiseQuiz(batchId);
		return Response.ok(list).header("Access-Control-Allow-Origin", "*").build();
	}
}
