package root.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import root.Bean.QuizContentBean;
import root.Bean.StudentQuizBean;
import root.Utils.DBConnection;
import root.Utils.GenrateMathodsUtils;

public class QuizContentDAO {
	ResultSet rs = null,rs1=null;
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
				+ "option1=?,option2=?,option3=?,option4=?,answer=?," + "mark=?,difficulty=? where quizcontentid=?";
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

	public boolean answerFirst(String studentId, String quizContentId, String selectedOption, String quizid) {

		String delSql = "delete from studentquizcontent where studentid='" + studentId + "' and quizid = '" + quizid
				+ "'";
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				conn.setAutoCommit(false);

				pstmt = conn.prepareStatement(delSql);
				int rowAffected = pstmt.executeUpdate();

				String insertSql = "insert into studentquizcontent(studentquizcontentid,studentid,quizcontentid,selectedoption,quizid) values(?,?,?,?,?)";

				pstmt = null;
				pstmt = conn.prepareStatement(insertSql);

				pstmt.setString(1, GenrateMathodsUtils.getRandomString(15));
				pstmt.setString(2, studentId);
				pstmt.setString(3, quizContentId);
				pstmt.setString(4, selectedOption);
				pstmt.setString(5, quizid);

				rowAffected = pstmt.executeUpdate();
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

	public boolean answerCurr(String studentId, String quizContentId, String selectedOption, String quizId) {
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				conn.setAutoCommit(false);

				String insertSql = "insert into studentquizcontent(studentquizcontentid,studentid,quizcontentid,selectedoption,quizid) values(?,?,?,?,?)";

				pstmt = null;
				pstmt = conn.prepareStatement(insertSql);

				pstmt.setString(1, GenrateMathodsUtils.getRandomString(15));
				pstmt.setString(2, studentId);
				pstmt.setString(3, quizContentId);
				pstmt.setString(4, selectedOption);
				pstmt.setString(5, quizId);

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

	public StudentQuizBean generateMarks(String studentId, String quizId) {

		StudentQuizBean bean = new StudentQuizBean();
		String sql = "select * from studentquizcontent s,quizcontent q,quiz qu where s.quizcontentid=q.quizcontentid "
				+ "and studentid=? and q.quizid=? and qu.quizid=q.quizid";
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				int total = 0;
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, studentId);
				pstmt.setString(2, quizId);
				rs = pstmt.executeQuery();
				while (rs.next()) {

					String ans = rs.getString("answer");
					String selectedOption = rs.getString("selectedoption");

					if (ans.equalsIgnoreCase(selectedOption)) {
						total += Integer.parseInt(rs.getString("mark"));
					}

					bean.setName(rs.getString("name"));
					bean.setBatchId(rs.getString("batchid"));
					bean.setQuizId(rs.getString("quizid"));
				}
				bean.setTotal(total + "");

				String insertSql = "insert into studentquiz(studentquizid,studentid,total,quizid) values(?,?,?,?)";

				pstmt = null;
				pstmt = conn.prepareStatement(insertSql);
				String id = GenrateMathodsUtils.getRandomString(15);
				bean.setStudentid(studentId);
				bean.setStudentquizid(id);
				bean.setReaponseStatus(true);
				pstmt.setString(1, id);
				pstmt.setString(2, studentId);
				pstmt.setInt(3, total);
				pstmt.setString(4, quizId);

				int rowAffected = pstmt.executeUpdate();
				if (rowAffected > 0) {
					return bean;
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
		return null;

	}

	public HashMap<String, Object> getList(String quizid) {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		ArrayList<QuizContentBean> list = new ArrayList<QuizContentBean>();
		QuizContentBean bean = new QuizContentBean();
		String sql = "select * from quizcontent where quizid=?";
		conn = DBConnection.getConnection();

		boolean flag = false;
		if (conn != null) {

			try {

				pstmt = conn.prepareStatement("select * from quiz where quizid=?");
				pstmt.setString(1, quizid);
				rs1 = pstmt.executeQuery();
				if (rs1.next()) {
					
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, quizid);
					rs = pstmt.executeQuery();

					while (rs.next()) {
						bean = new QuizContentBean();
						bean.setQuizContentId(rs.getString("quizcontentid"));
						bean.setQuizId(rs.getString("quizid"));
						bean.setQuestion(rs.getString("questioncontent"));
						bean.setOption1(rs.getString("option1"));
						bean.setOption2(rs.getString("option2"));
						bean.setOption3(rs.getString("option3"));
						bean.setOption4(rs.getString("option4"));
						bean.setAnswer(rs.getString("answer"));
						bean.setMark(rs.getString("mark"));
						bean.setDifficulty(rs.getString("difficulty"));
						bean.setIsAvailable(rs.getString("isavailable"));
						list.add(bean);
						flag = true;
					}
					if (flag == true) {
						hash.put("quizcontent", list);
						hash.put("name",rs1.getString("name"));
						hash.put("starttime",rs1.getString("starttime"));
						hash.put("endtime",rs1.getString("endtime"));
						hash.put("createdby",rs1.getString("createdby"));
					}
					hash.put("responseStatus", flag);
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
		return hash;
	}

}
