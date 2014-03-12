package titan.bean;

/**
 * Login entity. @author MyEclipse Persistence Tools
 */

public class Login implements java.io.Serializable {

	// Fields

	private Integer loginid;
	private String password;
	private Integer type;
	private String name;
	private String department;
	private String bank;

	// Constructors

	/** default constructor */
	public Login() {
	}

	/** full constructor */
	public Login(String password, Integer type, String name, String department,
			String bank) {
		this.password = password;
		this.type = type;
		this.name = name;
		this.department = department;
		this.bank = bank;
	}

	// Property accessors

	public Integer getLoginid() {
		return this.loginid;
	}

	public void setLoginid(Integer id) {
		this.loginid = id;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getBank() {
		return this.bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

}