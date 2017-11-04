package root.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import root.Bean.BatchBean;
import root.Utils.DBConnection;
import root.Utils.GenrateMathodsUtils;

public class BatchDAO {

	ResultSet rs = null;
	PreparedStatement pstmt = null;
	Connection conn = null;

	
	public ArrayList<BatchBean> insert(String facultyId, String batchname) {

		ArrayList<BatchBean> arrayList = new ArrayList<BatchBean>();
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
					arrayList.add(new BatchBean());
				} else {
					arrayList.add(obj);
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
		return arrayList;
	}
}
