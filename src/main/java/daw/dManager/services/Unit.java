package daw.dManager.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

//import org.hibernate.annotations.CascadeType;

@Entity
public class Unit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private long id;
	//@OneToMany(cascade=CascadeType.ALL) 
	//@LazyCollection(LazyCollectionOption.FALSE)//cannot uses fetch type eager
	//@JoinColumn(name="concept_id")
	//@OnDelete(action=OnDeleteAction.CASCADE)
	
	String title;
	
	@OneToMany(mappedBy="unit")
	private List<Concept> concepts = new ArrayList<>();
	
	public Unit() {}
	
	public Unit(String title) {
		super();
		this.title = title;
	}
	
	public String getUnitTitle() {
		return title;
	}

	public void setUnitTitle(String title) {
		this.title = title;
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
	
	public void setConcepts(List<Concept> concepts) {
		this.concepts = concepts;
	}

	@Override
	public String toString() {
		return "Concept [id=" + id + ", title=" + title + "]";
	} 

}
