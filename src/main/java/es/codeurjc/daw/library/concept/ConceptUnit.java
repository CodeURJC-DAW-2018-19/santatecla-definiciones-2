package es.codeurjc.daw.library.concept;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


//import org.hibernate.annotations.CascadeType;

@Entity
public class ConceptUnit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private long id;
	
	
	String title;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<Concept> concepts = new ArrayList<>();
	
	public ConceptUnit() {}
	
	public ConceptUnit(String title) {
		super();
		this.title = title;
	}
	
	public ConceptUnit(String title, Concept c ) {
		super();
		this.title = title;
		this.concepts.add(c);
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public List<Concept> getConcepts() {
		return concepts;
	}
	

	@Override
	public String toString() {
		return "Concept [id=" + id + ", title=" + title + "]";
	} 

}
