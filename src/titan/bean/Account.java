package titan.bean;

/**
 * Account entity. @author MyEclipse Persistence Tools
 */

public class Account implements java.io.Serializable {

	// Fields

	private Integer id;
	private String identifier;
	private String username;
	private Float balance;
	private String type;
	private String password;
	private Boolean sts;

	// Constructors

	/** default constructor */
	public Account() {
	}

	/** full constructor */
	public Account(Integer id, String identifier, String username,
			Float balance, String type, String password, Boolean sts) {
		this.id = id;
		this.identifier = identifier;
		this.username = username;
		this.balance = balance;
		this.type = type;
		this.password = password;
		this.sts = sts;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIdentifier() {
		return this.identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Float getBalance() {
		return this.balance;
	}

	public void setBalance(Float balance) {
		this.balance = balance;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getSts() {
		return this.sts;
	}

	public void setSts(Boolean sts) {
		this.sts = sts;
	}

}