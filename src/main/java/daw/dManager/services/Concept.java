package daw.dManager.services;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Concept {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private long id = -1;
	
	@ManyToOne
	private Unit unit;
	
	@OneToMany
	private List<Answer> answers = new ArrayList<Answer>();
	
	private String title;
	
	public Concept() {}

	public Concept(String title) {
		super();
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void SetUnit(Unit unit2) {
		this.unit = unit2;
		
	}

}
