package titan.bean;

/**
 * RelationshipId entity. @author MyEclipse Persistence Tools
 */

public class Relationship implements java.io.Serializable {

	// Fields

	private Integer acId;
	private Integer userId;

	// Constructors

	/** default constructor */
	public Relationship() {
	}

	/** full constructor */
	public Relationship(Integer acId, Integer userId) {
		this.acId = acId;
		this.userId = userId;
	}

	// Property accessors

	public Integer getAcId() {
		return this.acId;
	}

	public void setAcId(Integer acId) {
		this.acId = acId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Relationship))
			return false;
		Relationship castOther = (Relationship) other;

		return ((this.getAcId() == castOther.getAcId()) || (this.getAcId() != null
				&& castOther.getAcId() != null && this.getAcId().equals(
				castOther.getAcId())))
				&& ((this.getUserId() == castOther.getUserId()) || (this
						.getUserId() != null && castOther.getUserId() != null && this
						.getUserId().equals(castOther.getUserId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getAcId() == null ? 0 : this.getAcId().hashCode());
		result = 37 * result
				+ (getUserId() == null ? 0 : this.getUserId().hashCode());
		return result;
	}

}