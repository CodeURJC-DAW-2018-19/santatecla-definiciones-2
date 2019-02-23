package daw.gestor.concept;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Concept {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private long id = -1;
	
	@ManyToOne
	private Unit unit;
	
	private String title;
	
	public Concept() {}

	public Concept(String nombre) {
		super();
		this.title = nombre;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void SetUnit(Unit uE) {
		this.unit = uE;
		
	}


}
