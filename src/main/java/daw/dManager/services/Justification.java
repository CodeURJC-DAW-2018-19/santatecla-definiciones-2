package daw.dManager.services;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Justification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private long id = -1;
	
	private String text;
	private boolean valid;
	private String error = "error";
	
	@ManyToOne
	private Answer answer = null;
	
	public Justification() {}
	
	public Justification(String text, boolean valid) {
		super();
		this.text = text;
		this.valid = valid;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public boolean isValid() {
		return valid;
	}
	
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
	public String getError() {
		return error;
	}
	
	public void setError(String error) {
		this.error = error;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}
	
	
	
	
	

}
