package eu.trentorise.smartcampus.universiadi.model;

public class Faq {
	
	private String question;
	private String answer;
	
	
	public Faq(String question, String answer) {
		super();
		this.question = question;
		this.answer = answer;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}

}
