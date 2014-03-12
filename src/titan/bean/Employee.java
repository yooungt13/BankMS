package titan.bean;

/**
 * Employee entity. @author MyEclipse Persistence Tools
 */

public class Employee implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer type;
	private String name;
	private Short bank;
	private Short department;

	// Constructors

	/** default constructor */
	public Employee() {
	}

	/** full constructor */
	public Employee(Integer type, String name, Short bank, Short department) {
		this.type = type;
		this.name = name;
		this.bank = bank;
		this.department = department;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Short getBank() {
		return this.bank;
	}

	public void setBank(Short bank) {
		this.bank = bank;
	}

	public Short getDepartment() {
		return this.department;
	}

	public void setDepartment(Short department) {
		this.department = department;
	}

}