package root.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import root.Bean.QuizContentBean;
import root.Utils.DBConnection;

public class QuizContentDAO {
	ResultSet rs = null;
	PreparedStatement pstmt = null;
	Connection conn = null;

	public boolean insert(QuizContentBean bean) {

		String sql = "insert into quizcontent(quizcontentid,quizid,questioncontent,"
				+ "option1,option2,option3,option4,answer,"
				+ "mark,difficulty,isavailable) values(?,?,?,?,?,?,?,?,?,?,?)";
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				conn.setAutoCommit(false);
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, bean.getQuizContentId());
				pstmt.setString(2, bean.getQuizId());
				pstmt.setString(3, bean.getQuestion());
				pstmt.setString(4, bean.getOption1());
				pstmt.setString(5, bean.getOption2());
				pstmt.setString(6, bean.getOption3());
				pstmt.setString(7, bean.getOption4());
				pstmt.setString(8, bean.getAnswer());
				pstmt.setInt(9, Integer.parseInt(bean.getMark()));
				pstmt.setInt(10, Integer.parseInt(bean.getDifficulty()));
				pstmt.setInt(11, 1);
				int rowAffected = pstmt.executeUpdate();
				if (rowAffected > 0)
					return true;
				conn.rollback();
				return false;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			} finally {
				try {
					conn.commit();
					conn.setAutoCommit(true);
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	public boolean delete(String questionContentId) {

		String sql = "delete from quizcontent where quizcontentid=?";
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, questionContentId);
				int rowAffected = pstmt.executeUpdate();
				if (rowAffected > 0)
					return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	public boolean questionBlocked(String questionContentId) {

		String sql = "update quizcontent set isavailable=? where quizcontentid=?";
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, 0);
				pstmt.setString(2, questionContentId);
				int rowAffected = pstmt.executeUpdate();
				if (rowAffected > 0)
					return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	public boolean update(QuizContentBean bean) {

		String sql = "update quizcontent set quizcontentid=?,quizid=?,questioncontent=?,"
				+ "option1=?,option2=?,option3=?,option4=?,answer=?,"
				+ "mark=?,difficulty=? where quizcontentid=?";
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				conn.setAutoCommit(false);
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, bean.getQuizContentId());
				pstmt.setString(2, bean.getQuizId());
				pstmt.setString(3, bean.getQuestion());
				pstmt.setString(4, bean.getOption1());
				pstmt.setString(5, bean.getOption2());
				pstmt.setString(6, bean.getOption3());
				pstmt.setString(7, bean.getOption4());
				pstmt.setString(8, bean.getAnswer());
				pstmt.setInt(9, Integer.parseInt(bean.getMark()));
				pstmt.setInt(10, Integer.parseInt(bean.getDifficulty()));
				pstmt.setString(11, bean.getQuizContentId());
				int rowAffected = pstmt.executeUpdate();
				if (rowAffected > 0)
					return true;
				conn.rollback();
				return false;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			} finally {
				try {
					conn.commit();
					conn.setAutoCommit(true);
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	
	public ArrayList<QuizContentBean> listQuetion(String batchid) {

		ArrayList<QuizContentBean> arrayList = new ArrayList<QuizContentBean>();

		String sql = "select * from quiz q,quizcontent qc where batchid='" + batchid
				+ "' and q.quizid=qc.quizid and isavailable=1 and status='Y';";
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				QuizContentBean contentBean = new QuizContentBean();
				while (rs.next()) {
					contentBean = new QuizContentBean();
					contentBean.setOption1(rs.getString("option1"));
					contentBean.setOption2(rs.getString("option2"));
					contentBean.setOption3(rs.getString("option3"));
					contentBean.setOption4(rs.getString("option4"));
					contentBean.setAnswer(rs.getString("answer"));
					contentBean.setDifficulty(rs.getString("difficulty"));
					contentBean.setName(rs.getString("name"));
					contentBean.setBatchId(rs.getString("batchid"));
					contentBean.setMark(rs.getString("mark"));
					contentBean.setQuestion(rs.getString("questioncontent"));
					contentBean.setQuizContentId(rs.getString("quizcontentid"));
					contentBean.setQuizId(rs.getString("quizid"));
					arrayList.add(contentBean);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return arrayList;
	}


}
