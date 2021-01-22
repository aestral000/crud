package wasidevestak.dao;

import java.util.List;

import wasidevestak.entity.Person;

public interface PersonDAO {
	
	public boolean insertPerson(Person person);
	
	public List<Person> selectPerson();
	
	public boolean editPerson(Integer id, String name);
	
	public boolean deletePerson(Integer id);
	
	public List<Integer> getAllId();

}
