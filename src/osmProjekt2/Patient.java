package osmProjekt2;

import java.io.Serializable;

public class Patient implements GetSetId, Serializable {
	
	//use for serialization
	private static final long serialVersionUID = 200906292211L;
	
	private String name;
	private String surname; 	
	private int age; 		
	private String gender; 	
	private String pesel; 
	
	transient private int id;
	
	Patient(String name, String surname, int age, String gender, String pesel){
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.gender = gender;
		this.pesel = pesel;
	}
	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;

	}
	
	String getName()
	{
		return name;
	}
	
	String getSurname()
	{
		return surname;
	}
	
	int getAge()
	{
		return age;
	}
	
	String getPesel()
	{
		return pesel;
	}
	
	String getGender()
	{
		return gender;
	}
	
	void setName(String name)
	{
		this.name = name;
	}
	
	void setSurname(String surname)
	{
		this.surname = surname;
	}
	
	void setAge(int age)
	{
		this.age = age;
	}
	
	void setGender(String gender)
	{
		this.gender = gender;
	}
	
	void setPesel(String pesel)
	{
		this.pesel = pesel;
	}
	
	public String toString()
	{
		return "[" + id + "]" + " " + name + "" + surname + "" + age + "" + gender + "" + pesel;
	}
	
	boolean sqlInsert() {
		PatientTable patientTable = PatientTable.getInstance();
		return patientTable.sqlInsert(this);
	}
	
	
	
}
