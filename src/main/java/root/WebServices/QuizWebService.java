package root.WebServices;

import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import root.Bean.QuizBean;
import root.DAO.QuizDAO;

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
}
