package root.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import root.Bean.StudentBean;
import root.Utils.DBConnection;

public class StudentDAO {

	ResultSet rs = null;
	PreparedStatement pstmt = null;
	Connection conn = null;

	public ArrayList<StudentBean> studentList(String batchid) {
		ArrayList<StudentBean> arrayList = new ArrayList<StudentBean>();

		String sql = "select * from student s,users u where s.userid=u.userid and s.batchid='" + batchid + "'";
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				StudentBean bean = new StudentBean();
				while (rs.next()) {
					bean = new StudentBean();
					bean.setBatchId(rs.getString("batchid"));
					bean.setStudentId(rs.getString("studentid"));
					bean.setEmailId(rs.getString("emailid"));
					bean.setUserId(rs.getString("userid"));
					bean.setUserIsAvailable(rs.getString("isavailable"));
					bean.setUserName(rs.getString("name"));
					bean.setUserRole(rs.getString("role"));

					arrayList.add(bean);
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
