package daw.dManager.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import daw.dManager.users.Student;


@Entity
public class Answer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private long id = -1;
	
	private String text;
	private Boolean Answer;
	
	@OneToMany
	private List<Justification> justifications = new ArrayList<>();
	
	@ManyToOne
	private Concept concept;
	
	@ManyToOne
	private Student student;
	
	public Answer() {}
	
	public Answer(String text, Boolean answer, Concept concept) {
		super();
		this.text = text;
		Answer = answer;
		this.concept = concept;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Boolean getAnswer() {
		return Answer;
	}
	public void setAnswer(Boolean answer) {
		Answer = answer;
	}
	public List<Justification> getJustifications() {
		return justifications;
	}
	public void setJustifications(List<Justification> justifications) {
		this.justifications = justifications;
	}
	
	
	
	

}
