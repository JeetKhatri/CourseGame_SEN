package root.Controller;

import java.util.Queue;

import root.Bean.Mail;

public class ThreadImplement implements Runnable {
	
	Queue<Mail> q;
	SendEmail send;
	
	public ThreadImplement(Queue<Mail> q) {
		 this.q = q;
		 send = new SendEmail();
	}
	
	public void run() {

		while(q.size()>0){
			Mail m = q.poll();
			System.out.println(m.getTo()+ " start");
			send.sendEmail(m.getSubject(), m.getTo(), m.getMessage());
			System.out.println(m.getTo()+ " done");
		}
	}

}
