package root.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import root.Bean.FacultyBean;
import root.Bean.StudentBean;
import root.Bean.TABean;
import root.Bean.UserBean;
import root.Utils.DBConnection;
import root.Utils.GenrateMathodsUtils;

public class LoginDAO {

	ResultSet rs = null;
	PreparedStatement pstmt = null;
	Connection conn = null;

	public StudentBean studentLogin(String emailId, String password) {
		StudentBean bean = new StudentBean();
		String sql = "select * from users u,student s where emailid=? and password=? and u.userId=s.userId and role='Student'";
		conn = DBConnection.getConnection();

		if (conn != null) {

			try {

				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, emailId);
				pstmt.setString(2, GenrateMathodsUtils.makeSHA512(password));
				rs = pstmt.executeQuery();

				while (rs.next()) {
					bean.setEmailId(rs.getString("emailid"));
					bean.setUserId(rs.getString("userId"));
					bean.setUserIsAvailable(rs.getString("isAvailable"));
					bean.setUserName(rs.getString("name"));
					bean.setUserRole(rs.getString("role"));
					bean.setResponseStatus(true);
					bean.setBatchId(rs.getString("batchid"));
					bean.setStudentId(rs.getString("studentid"));
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
		return bean;

	}

	public FacultyBean facultyLogin(String emailId, String password) {
		FacultyBean facultyBean = new FacultyBean();
		String sql = "select * from users u,faculty f where emailid=? and password=? and u.userid=f.userid and isavailable='Y' and role='Faculty'";

		conn = DBConnection.getConnection();

		if (conn != null) {

			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, emailId);
				pstmt.setString(2, GenrateMathodsUtils.makeSHA512(password));
				rs = pstmt.executeQuery();

				while (rs.next()) {
					facultyBean.setDegree(rs.getString("degree"));
					facultyBean.setFacultyId(rs.getString("facultyid"));
					facultyBean.setIsApproved(rs.getString("isApproved"));
					facultyBean.setEmailId(rs.getString("emailid"));
					facultyBean.setUserId(rs.getString("userid"));
					facultyBean.setUserIsAvailable(rs.getString("isavailable"));
					facultyBean.setUserName(rs.getString("name"));
					facultyBean.setUserRole(rs.getString("role"));
					facultyBean.setResponseStatus(true);
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
		return facultyBean;

	}

	public UserBean adminLogin(String emailId, String password) {
		UserBean albumBean = new UserBean();
		String sql = "select * from users where emailid=? and password=? and role='Admin'";
		conn = DBConnection.getConnection();

		boolean flag = false;
		if (conn != null) {

			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, emailId);
				pstmt.setString(2, GenrateMathodsUtils.makeSHA512(password));
				rs = pstmt.executeQuery();

				while (rs.next()) {
					albumBean = new UserBean();
					albumBean.setEmailId(rs.getString("emailid"));
					albumBean.setUserId(rs.getString("userId"));
					albumBean.setUserIsAvailable(rs.getString("isAvailable"));
					albumBean.setUserName(rs.getString("name"));
					albumBean.setUserRole(rs.getString("role"));
					albumBean.setResponseStatus(true);
					flag = true;
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
		return albumBean;

	}

	public TABean taLogin(String emailId, String password) {

		TABean bean = new TABean();
		UserBean albumBean = new UserBean();
		String sql = "select * from users u,ta t where emailid=? and password=? and u.userid = t.userId and role='TA'";
		conn = DBConnection.getConnection();

		boolean flag = false;
		if (conn != null) {

			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, emailId);
				pstmt.setString(2, GenrateMathodsUtils.makeSHA512(password));
				rs = pstmt.executeQuery();

				while (rs.next()) {
					albumBean = new UserBean();
					albumBean.setEmailId(rs.getString("emailid"));
					albumBean.setUserId(rs.getString("userid"));
					albumBean.setUserIsAvailable(rs.getString("isAvailable"));
					albumBean.setUserName(rs.getString("name"));
					albumBean.setUserRole(rs.getString("role"));
					albumBean.setResponseStatus(true);
					bean.setUserBean(albumBean);
					bean.setTaid(rs.getString("taid"));
					bean.setUserid(rs.getString("userid"));
					bean.setBatchid(rs.getString("batchid"));
					flag = true;
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
		return bean;
	}

	public UserBean login(String emailId, String password) {
		UserBean albumBean = new UserBean();
		String sql = "select * from users where emailid=? and password=?";
		conn = DBConnection.getConnection();

		boolean flag = false;
		if (conn != null) {

			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, emailId);
				pstmt.setString(2, GenrateMathodsUtils.makeSHA512(password));
				rs = pstmt.executeQuery();

				while (rs.next()) {
					albumBean = new UserBean();
					albumBean.setEmailId(rs.getString("emailid"));
					albumBean.setUserId(rs.getString("userId"));
					albumBean.setUserIsAvailable(rs.getString("isAvailable"));
					albumBean.setUserName(rs.getString("name"));
					albumBean.setUserRole(rs.getString("role"));
					albumBean.setResponseStatus(true);
					flag = true;
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
		return albumBean;
	}
	
}
