package daw.dManager.services;


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
	
	private String img;
	
	public Concept() {}

	public Concept(String title) {
		super();
		this.title = title;
		this.img="";
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
	
	public Unit getUnit() {
		return unit;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

}
