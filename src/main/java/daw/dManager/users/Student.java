package daw.dManager.users;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import daw.dManager.services.Answer;

@Entity
public class Student extends User {
	
	@OneToMany
	private List<Answer> answer = new ArrayList<Answer>();
	
	public Student(){}
	
	public Student(String name, String password) {
		setName(name);
		setPasswordHash(new BCryptPasswordEncoder().encode(password));
		setRoles(Arrays.asList("ROLE_USER"));
	}
	
	

}
