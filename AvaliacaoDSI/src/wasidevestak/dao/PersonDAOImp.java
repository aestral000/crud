package wasidevestak.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import wasidevestak.connection.ConnectionFactory;
import wasidevestak.entity.Person;

public class PersonDAOImp implements PersonDAO {

	private Connection connection = ConnectionFactory.getConnection();
	private static PersonDAOImp dao = null;
	
	public static PersonDAOImp getInstance() {
		
		if(dao == null) {
			return new PersonDAOImp();
		}
		return null;
	}
	
	@Override
	public boolean insertPerson(Person person) {

		String query = "INSERT INTO person (name) VALUES (?)";

		try {
			PreparedStatement stmt = connection.prepareStatement(query);

			stmt.setString(1, person.getName());
			stmt.execute();
			stmt.close();
			System.out.println("INSERTED");

		} catch (SQLException e) {
			System.out.println("NOT INSERTED");
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public List<Person> selectPerson() {
		String query = "SELECT person.id, person.name FROM person";

		try {
			PreparedStatement stmt = connection.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();

			List<Person> list = new ArrayList<>();
			while (rs.next()) {
				Person person = new Person();
				person.setName(rs.getString("name"));
				person.setId(rs.getInt("id"));
				list.add(person);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public boolean editPerson(Integer id, String name) {

		String query = "UPDATE person SET name = ?" + " WHERE id = ?";

		PreparedStatement pstm = null;

		try {

			pstm = connection.prepareStatement(query);

			pstm.setString(1, name);
			pstm.setInt(2, id);
			pstm.execute();
			return true;
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deletePerson(Integer id) {
		String query = "DELETE FROM person p WHERE p.id = ?";

		try {
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, id);
			stmt.execute();
			stmt.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public List<Integer> getAllId() {
		String query = "SELECT person.id FROM person";

		try {
			PreparedStatement stmt = connection.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();

			List<Integer> list = new ArrayList<>();
			while (rs.next()) {
				list.add(rs.getInt("id"));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
