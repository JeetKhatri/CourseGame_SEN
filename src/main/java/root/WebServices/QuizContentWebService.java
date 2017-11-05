package root.WebServices;

import java.util.ArrayList;

import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import root.Bean.BatchBean;
import root.Bean.QuizContentBean;
import root.DAO.BatchDAO;
import root.DAO.FacultyDAO;
import root.DAO.QuizContentDAO;
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

}
