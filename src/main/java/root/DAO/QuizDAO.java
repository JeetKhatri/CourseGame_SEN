package root.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import root.Bean.QuizBean;
import root.Bean.StatusBean;
import root.Bean.UserBean;
import root.Utils.DBConnection;
import root.Utils.GenrateMathodsUtils;

public class QuizDAO {

	ResultSet rs = null;
	PreparedStatement pstmt = null;
	Connection conn = null;

	public boolean insert(QuizBean quiz) {

		String sql = "insert into quiz(quizid,name,status,batchid,starttime,endtime,createdby) values(?,?,?,?,?,?,?)";
		conn = DBConnection.getConnection();
		String id = GenrateMathodsUtils.getRandomString(15);

		if (conn != null) {
			try {
				conn.setAutoCommit(false);

				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setString(2, quiz.getName());
				pstmt.setString(3, "N");
				pstmt.setString(4, quiz.getBatchId());
				pstmt.setTimestamp(5, GenrateMathodsUtils.convertStringDateToTimeStamp(quiz.getStartTime()));
				pstmt.setTimestamp(6, GenrateMathodsUtils.convertStringDateToTimeStamp(quiz.getEndTime()));
				pstmt.setString(7, quiz.getCreatedBy());

				int no = pstmt.executeUpdate();

				if (no != 0) {
					return true;
				}

			} catch (SQLException e) {
				e.printStackTrace();
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

	public boolean update(QuizBean quiz) {

		String sql = "update quiz set name=?,status=?,batchid=?,starttime=?,endtime=?,createdby=? where quizid=?";
		conn = DBConnection.getConnection();

		if (conn != null) {
			try {
				conn.setAutoCommit(false);

				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, quiz.getName());
				pstmt.setString(2, quiz.getStatus());
				pstmt.setString(3, quiz.getBatchId());
				pstmt.setTimestamp(4, GenrateMathodsUtils.convertStringDateToTimeStamp(quiz.getStartTime()));
				pstmt.setTimestamp(5, GenrateMathodsUtils.convertStringDateToTimeStamp(quiz.getEndTime()));
				pstmt.setString(6, quiz.getCreatedBy());
				pstmt.setString(7, quiz.getQuizId());

				int no = pstmt.executeUpdate();

				if (no != 0) {
					return true;
				}

			} catch (SQLException e) {
				e.printStackTrace();
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

	public StatusBean quizActivation(String batchId, String quizId) {
		String sql = "update quiz set status='N' where batchid=?";
		conn = DBConnection.getConnection();
		StatusBean objStatus = new StatusBean();
		if (conn != null) {
			try {
				conn.setAutoCommit(false);

				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, batchId);
				int no = pstmt.executeUpdate();
				if (no != 0) {
					sql = "update quiz set status='Y' where quizId=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, quizId);
					no = pstmt.executeUpdate();

					if (no != 0) {
						objStatus.setResponseStatus(true);
					}
				}

			} catch (SQLException e) {
				e.printStackTrace();
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
		return objStatus;
	}

	public HashMap<String, Object> batchWiseQuiz(String batchId) {

		String sql = "select * from quiz where batchid=? and status=?";
		conn = DBConnection.getConnection();
		QuizBean quizBean = new QuizBean();
		ArrayList<QuizBean> obj = new ArrayList<QuizBean>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, batchId);
				pstmt.setString(2, "Y");
				rs = pstmt.executeQuery();

				boolean flag = false;
				while (rs.next()) {
					flag = true;
					quizBean = new QuizBean();
					quizBean.setBatchId(rs.getString("batchId"));
					quizBean.setCreatedBy(rs.getString("createdBy"));
					quizBean.setEndTime(rs.getString("endTime"));
					quizBean.setName(rs.getString("name"));
					quizBean.setBatchId(rs.getString("batchId"));
					quizBean.setReaponseStatus(true);
					quizBean.setStartTime(rs.getString("startTime"));
					quizBean.setStatus(rs.getString("status"));
					quizBean.setQuizId(rs.getString("quizId"));
					obj.add(quizBean);
				}
				if (flag) {
					map.put("status", true);
					map.put("quizList", obj);
				} else
					map.put("status", false);

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
		return map;
	}
}
