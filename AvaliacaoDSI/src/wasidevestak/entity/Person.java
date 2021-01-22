package wasidevestak.entity;

public class Person {

	private String name;
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Person() {
		// TODO Auto-generated constructor stub
	}

	public Person(String name) {
		this.name = name;
	}

	public String getName() {

		return name;
	}

	public void setName(String diretoria) {

		this.name = diretoria;
	}

	@Override
	public String toString() {
		return "Nome = " + name + " | id = " + id ;
	}

}
