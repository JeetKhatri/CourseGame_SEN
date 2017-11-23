package root.WebServices;

import java.util.HashMap;

import javax.ws.rs.FormParam;
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
import root.Bean.StudentQuizBean;
import root.DAO.QuizContentDAO;
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

	@POST
	@Path("/submit")
	@Produces("application/json")
	public Response quizSubmit(@FormParam("studentid") String studentId, @FormParam("quizid") String quizId,
			@FormParam("answers") String submittedAnswers) {

		/*
		 * String[] answers=submittedAnswers.split(";"); for(String
		 * answer:answers){ String[] key_value=answer.split(",");
		 * 
		 * String quizContentId=key_value[0]; answer=key_value[1];
		 * 
		 * boolean flag = new QuizContentDAO().answerAll(studentId,
		 * quizContentId, answer, quizId); }
		 */
		StatusBean st = new StatusBean();
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		st.setResponseStatus(new QuizContentDAO().answer(studentId, quizId, submittedAnswers));
		hashMap.put("responseStatus", st.isResponseStatus()?"true":"false");
		StudentQuizBean bean = new QuizContentDAO().generateMarks(studentId, quizId);
		if (bean != null)
			return Response.ok(bean).header("Access-Control-Allow-Origin", "*").build();
		else {
			return Response.ok(hashMap).header("Access-Control-Allow-Origin", "*").build();
		}
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

	@POST
	@Path("/check-quiz")
	@Produces("application/json")
	public Response checkQuiz(@FormParam("studentid") String studentId, @FormParam("quizid") String quizId) {

		HashMap<String, Object> list = new QuizDAO().checkQuiz(studentId, quizId);
		return Response.ok(list).header("Access-Control-Allow-Origin", "*").build();
	}
}
