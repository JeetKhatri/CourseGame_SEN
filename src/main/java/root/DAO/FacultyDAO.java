package root.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import root.Bean.FacultyBean;
import root.Bean.UserBean;
import root.Controller.SendEmail;
import root.Utils.DBConnection;
import root.Utils.GenrateMathodsUtils;

public class FacultyDAO {

	ResultSet rs = null;
	PreparedStatement pstmt = null,pstmt1=null;
	Connection conn = null;

	public boolean insert(String id, String degree) {

		conn = DBConnection.getConnection();
		if (conn != null) {

			try {
				pstmt = conn.prepareStatement("insert into Faculty(userId,degree,isApproved) value(?,?,?)");
				pstmt.setString(1, id);
				pstmt.setString(2, degree);
				pstmt.setString(3, "N");
				if (pstmt.executeUpdate() != 0)
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

	public boolean approve(String facultyId) {

		String sql = "update faculty set isApproved='Y' where facultyid= '" + facultyId + "'";
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				conn.setAutoCommit(false);
				pstmt = conn.prepareStatement(sql);
				if (pstmt.executeUpdate() != 0) {
					String random = GenrateMathodsUtils.getRandomString(7);
					pstmt1 = conn.prepareStatement("update users set password=?,isavailable='Y' where userid=(select userid from faculty where facultyid=?)");
					pstmt1.setString(1, GenrateMathodsUtils.makeSHA512(random));
					pstmt1.setString(2, facultyId);
					int no = pstmt1.executeUpdate();
					if(no==0){
						conn.rollback();
					}else {
						SendEmail obj = new SendEmail();
						obj.SendEmail("Request accepted", getFacultyEmail(facultyId),
								"Request arrive, we accept your requst & password is "+random);
						conn.commit();
						conn.setAutoCommit(true);
						return true;
					}
				}
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

	public String getFacultyEmail(String id) {
		String sql = "select * from faculty,users where users.userid = faculty.userid and facultyid = ?";
	//	conn = DBConnection.getConnection();

		if (conn != null) {

			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					return rs.getString("emailid");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
			}

		}
		return null;

	}
	public boolean update(FacultyBean bean) {

		String sql = "update faculty set degree=?,isapproved=? where facultyid=?";
		conn = DBConnection.getConnection();
		if (conn != null) {

			try {
				conn.setAutoCommit(false);
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, bean.getDegree());
				pstmt.setString(2, bean.getIsApproved());
				pstmt.setString(3, bean.getFacultyId());
				System.out.println("Hi");
				int temp = pstmt.executeUpdate();
				if (temp > 0) {
					System.out.println("Hello");
					sql = "update users set " + "emailid=?,name=?,role=?,isavailable=? where userid=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, bean.getEmailId());
					pstmt.setString(2, bean.getUserName());
					pstmt.setString(3, bean.getUserRole());
					pstmt.setString(4, bean.getUserIsAvailable());
					pstmt.setString(5, bean.getUserId());
					temp = pstmt.executeUpdate();
					if (temp > 0)
						return true;
					conn.rollback();
					return false;
				} else{
					conn.rollback();
					return false;
				}
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
}