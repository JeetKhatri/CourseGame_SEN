package root.Bean;

public class Mail {

	private String subject;
	private String to;
	private String message;
	
	public Mail(String subject, String to, String message) {
		this.subject = subject;
		this.to = to;
		this.message = message;
	}
	
	public String getSubject() {
		return subject;
	}
	public String getTo() {
		return to;
	}
	public String getMessage() {
		return message;
	}
}
