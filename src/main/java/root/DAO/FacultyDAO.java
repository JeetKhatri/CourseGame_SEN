package root.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import root.Bean.UserBean;
import root.Controller.SendEmail;
import root.Utils.DBConnection;
import root.Utils.GenrateMathodsUtils;

public class FacultyDAO {

	ResultSet rs = null;
	PreparedStatement pstmt = null,pstmt1=null;
	Connection conn = null;

	public boolean insert(String id, String degree) {

		String sql = "insert into Faculty(userId,degree,isApproved) value(?,?,?)";
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
			//	conn.setAutoCommit(false);
				pstmt = conn.prepareStatement(sql);
				if (pstmt.executeUpdate() != 0) {
					String random = GenrateMathodsUtils.getRandomString(7);
					System.out.println(random+" "+facultyId);
					pstmt1 = conn.prepareStatement("update users set password=? where userid=(select userid from faculty where facultyid=?)");
					pstmt1.setString(1, random);
					pstmt1.setString(2, facultyId);
					System.out.println(pstmt1.toString());
					int no = pstmt1.executeUpdate();
					System.out.println(no);
					if(no==0){
						System.out.println("rollback");
				//		conn.rollback();
					}else {
						SendEmail obj = new SendEmail();
						obj.SendEmail("Request accepted", getFacultyEmail(facultyId),
								"Request arrive, we accept your requst & password is "+random);
						System.out.println("mail");
					//	conn.commit();
					//	conn.setAutoCommit(true);
						return true;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			} finally {
				
			}

		}
		return false;
	}

	public String getFacultyEmail(String id) {
		String sql = "select * from faculty,users where users.userid = faculty.userid and facultyid = ?";
		conn = DBConnection.getConnection();

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
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return null;

	}

}