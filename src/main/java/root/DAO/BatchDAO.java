package root.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import root.Bean.BatchBean;
import root.Bean.QuizBean;
import root.Utils.DBConnection;
import root.Utils.GenrateMathodsUtils;

public class BatchDAO {

	ResultSet rs = null;
	PreparedStatement pstmt = null;
	Connection conn = null;

	
	public boolean insert(String facultyId, String batchname) {

		String sql = "insert into batch(facultyid,batchname,batchid) values(?,?,?)";
		conn = DBConnection.getConnection();
		String id = GenrateMathodsUtils.getRandomString(15);
		if (conn != null) {
			try {
				conn.setAutoCommit(false);
				String batchid = GenrateMathodsUtils.getRandomString(15);
				BatchBean obj = new BatchBean();
				obj.setBatchid(batchid);
				obj.setBatchname(batchname);
				obj.setFacultyid(facultyId);
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, facultyId);
				pstmt.setString(2, batchname);
				pstmt.setString(3, batchid);
				if (pstmt.executeUpdate() == 0) {
					conn.rollback();
					return false;
				} else {
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
	
	public ArrayList<QuizBean> quizList(String batchid) {

		ArrayList<QuizBean> arraylist = new ArrayList<QuizBean>();

		String ssql = "select batchid,q.name as qname,u.name as uname,status,quizid,createdby,userid from users u,quiz q where batchid='"
				+ batchid + "' and q.createdby=u.userid";
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				pstmt = conn.prepareStatement(ssql);
				rs = pstmt.executeQuery();
				QuizBean bean = new QuizBean();
				while (rs.next()) {
					bean = new QuizBean();
					bean.setBatchId(rs.getString("batchid"));
					bean.setName(rs.getString("qname"));
					bean.setCreatedBy(rs.getString("uname"));
					bean.setStatus(rs.getString("status"));
					bean.setQuizId(rs.getString("quizid"));
					arraylist.add(bean);
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
		return arraylist;
	}
}
