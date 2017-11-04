package root.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	static Connection connection = null;

	public static Connection getConnection() {

		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql://ec2-107-20-193-74.compute-1.amazonaws.com:5432/dco88md9862m6k?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory","gbbqoijsobpteu", "33c58383428b29b3a9f869afaf11e6b9ebdc262777ccfa1a55bee243c0e70b8e");
			//connection = DriverManager.getConnection("jdbc:mysql://coursegame.cpdne152fxns.ap-south-1.rds.amazonaws.com:3306/CourseGame", "root", "rootroot");
			if (connection != null) {
			} else {

			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public static void main(String[] args) {
		System.out.println(getConnection());
	}

}
