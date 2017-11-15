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

import root.Bean.BatchBean;
import root.Bean.QuizContentBean;
import root.Bean.StatusBean;
import root.Bean.StudentQuizBean;
import root.Bean.UserBean;
import root.DAO.BatchDAO;
import root.DAO.FacultyDAO;
import root.DAO.QuizContentDAO;
import root.DAO.QuizDAO;
import root.DAO.UserDAO;
import root.Utils.GenrateMathodsUtils;

@Path("/quiz-content")
public class QuizContentWebService {
	@POST
	@Path("/quiz-content-insert")
	@Produces("application/json")
	public Response insertQuizContent(@Context UriInfo info) {

		String quizid = info.getQueryParameters().getFirst("quizid");
		String question = info.getQueryParameters().getFirst("question");
		String option1 = info.getQueryParameters().getFirst("option1");
		String option2 = info.getQueryParameters().getFirst("option2");
		String option3 = info.getQueryParameters().getFirst("option3");
		String option4 = info.getQueryParameters().getFirst("option4");
		String answer = info.getQueryParameters().getFirst("answer");
		String mark = info.getQueryParameters().getFirst("mark");
		String difficulty = info.getQueryParameters().getFirst("difficulty");

		QuizContentBean bean = new QuizContentBean();
		bean.setAnswer(answer);
		bean.setDifficulty(difficulty);
		bean.setOption1(option1);
		bean.setOption2(option2);
		bean.setOption3(option3);
		bean.setOption4(option4);
		bean.setMark(mark);
		bean.setQuestion(question);
		bean.setQuizId(quizid);
		bean.setQuizContentId(GenrateMathodsUtils.getRandomString(15));
		return Response.ok(new QuizContentDAO().insert(bean)).header("Access-Control-Allow-Origin", "*").build();
	}

	@PUT
	@Path("/quiz-content-update")
	@Produces("application/json")
	public Response updateQuizContent(@Context UriInfo info) {

		String quizid = info.getQueryParameters().getFirst("quizid");
		String question = info.getQueryParameters().getFirst("question");
		String option1 = info.getQueryParameters().getFirst("option1");
		String option2 = info.getQueryParameters().getFirst("option2");
		String option3 = info.getQueryParameters().getFirst("option3");
		String option4 = info.getQueryParameters().getFirst("option4");
		String answer = info.getQueryParameters().getFirst("answer");
		String mark = info.getQueryParameters().getFirst("mark");
		String difficulty = info.getQueryParameters().getFirst("difficulty");
		String quizcontentid = info.getQueryParameters().getFirst("quizcontentid");

		QuizContentBean bean = new QuizContentBean();
		bean.setAnswer(answer);
		bean.setDifficulty(difficulty);
		bean.setOption1(option1);
		bean.setOption2(option2);
		bean.setOption3(option3);
		bean.setOption4(option4);
		bean.setMark(mark);
		bean.setQuestion(question);
		bean.setQuizId(quizid);
		bean.setQuizContentId(quizcontentid);
		return Response.ok(new QuizContentDAO().update(bean)).header("Access-Control-Allow-Origin", "*").build();
	}

	@PUT
	@Path("/quiz-content-blocked")
	@Produces("application/json")
	public boolean insertAlbum(@Context UriInfo info) {
		String questionContentId = info.getQueryParameters().getFirst("quizcontentid");
		return new QuizContentDAO().questionBlocked(questionContentId);
	}

	@POST
	@Path("/play-quiz")
	@Produces("application/json")
	public Response playQuiz(@Context UriInfo info) {
		String batchId = info.getQueryParameters().getFirst("batchid");

		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		boolean b = false;

		ArrayList<QuizContentBean> contentBeans = new QuizContentDAO().listQuetion(batchId);

		if (contentBeans.size() != 0) {
			b = true;
			hashMap.put("responcestatus", b);
			hashMap.put("questionList", contentBeans);
		} else {
			hashMap.put("responcestatus", b);
		}

		return Response.ok(hashMap).header("Access-Control-Allow-Origin", "*").build();
	}
	
	@POST 
	@Path("/answer-quiz")
	@Produces("application/json") 
	public HashMap<String, Object> answerQuizContent(@Context UriInfo info) {
		StatusBean st = new StatusBean();
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		String studentId = info.getQueryParameters().getFirst("studentid");
		String quizId = info.getQueryParameters().getFirst("quizid");
		String quizContentId = info.getQueryParameters().getFirst("quizcontentid");
		String selectedOption = info.getQueryParameters().getFirst("selectedoption");
		String var = info.getQueryParameters().getFirst("var");

		if (var.equalsIgnoreCase("start")) {
			st.setResponseStatus(new QuizContentDAO().answerFirst(studentId, quizContentId, selectedOption, quizId));
			hashMap.put("responseStatus", st.isResponseStatus());

		} else if (var.equalsIgnoreCase("finish")) {
			st.setResponseStatus(new QuizContentDAO().answerCurr(studentId, quizContentId, selectedOption, quizId));
			hashMap.put("responseStatus", st.isResponseStatus());
			StudentQuizBean bean = new QuizContentDAO().generateMarks(studentId, quizId);
			hashMap.put("studentQuizDetail", bean);
		} else {
			st.setResponseStatus(new QuizContentDAO().answerCurr(studentId, quizContentId, selectedOption, quizId));
			hashMap.put("responseStatus", st.isResponseStatus());
		}

		return hashMap;
	}

	
	@GET
	@Path("/questions-list")
	@Produces("application/json")
	public Response getList(@Context UriInfo info) {
		String quizid = info.getQueryParameters().getFirst("quizid");
		HashMap<String, Object> list = new QuizContentDAO().getList(quizid);
		return Response.ok(list).header("Access-Control-Allow-Origin", "*").build();
	}
	
	@GET
	@Path("/batch-wise-result")
	@Produces("application/json")
	public Response leaderBoardStudent(@Context UriInfo info) {

		String quizid = info.getQueryParameters().getFirst("quizid");
		String batchid = info.getQueryParameters().getFirst("batchid");
		return Response.ok(new QuizDAO().leaderBoardStudent(quizid, batchid)).header("Access-Control-Allow-Origin", "*").build();
	}
}
