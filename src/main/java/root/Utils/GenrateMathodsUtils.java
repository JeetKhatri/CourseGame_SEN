package root.Utils;

import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.sql.Timestamp;

public class GenrateMathodsUtils {

	public static String makeSHA512(String input) {
		String output = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".getBytes("UTF-8"));
			byte[] bytes = md.digest(input.getBytes("UTF-8"));
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++)
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			output = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;
	}

	public static String getRandomString(int length) {
		Random r = new Random();
		String output = "";
		String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		for (int i = 0; i < 5; i++) {
			List<Character> characters = new ArrayList<Character>();
			for (char c : alpha.toCharArray())
				characters.add(c);
			StringBuilder out = new StringBuilder(alpha.length());
			while (characters.size() != 0)
				out.append(characters.remove((int) (Math.random() * characters.size())));
			alpha = out.toString();
		}
		for (int i = 0; i < length; i++)
			output += alpha.charAt(r.nextInt(length));
		return output;
	}

	public static String getRandomPass(int length) {
		Random r = new Random();
		String output = "";
		String alpha = "A!Bo34wCDE@1234pqrHsR01(k2ShTdfjfdyg6HYFHG5687dsf1569413dsjfffU#VtuvFMNO$PQW)XYZ567abdndhuyvfekfo083375%^#$khdbvf$%sjkcbd&djnj&kjbdv**%cdhjkGH=e(^fgJ89K)&mnx*yz";
		for (int i = 0; i < 5; i++) {
			List<Character> characters = new ArrayList<Character>();
			for (char c : alpha.toCharArray())
				characters.add(c);
			StringBuilder out = new StringBuilder(alpha.length());
			while (characters.size() != 0)
				out.append(characters.remove((int) (Math.random() * characters.size())));
			alpha = out.toString();
		}
		for (int i = 0; i < length; i++)
			output += alpha.charAt(r.nextInt(length));
		return output;
	}

	public static String getRandomSpecialString(int length) {
		Random r = new Random();
		String output = "";
		String alpha = "!@#$%+--^)#*^&!#*%)$&($&(#@($*%$%)@*$&*()";
		for (int i = 0; i < 5; i++) {
			List<Character> characters = new ArrayList<Character>();
			for (char c : alpha.toCharArray())
				characters.add(c);
			StringBuilder out = new StringBuilder(alpha.length());
			while (characters.size() != 0)
				out.append(characters.remove((int) (Math.random() * characters.size())));
			alpha = out.toString();
		}
		for (int i = 0; i < length; i++)
			output += alpha.charAt(r.nextInt(length));
		return output;
	}

	public static String convertDateSQL(String date) {
		String temp[] = date.split("/");
		return temp[2] + temp[0] + temp[1];
	}

	public static String convertDate(String date) {
		String temp[] = date.split("-");
		return temp[1] + "/" + temp[2] + "/" + temp[0];
	}
	
	public static Timestamp convertStringDateToTimeStamp(String date) {
		Date d = new Date();
		try {
			d = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss.S").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Timestamp timeStamp = new Timestamp(d.getTime());
		return timeStamp;
	}
}
