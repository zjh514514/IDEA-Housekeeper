package housekeeper.entities;
// Generated 2017-12-11 20:27:19 by Hibernate Tools 5.2.5.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * MemberQueryId generated by hbm2java
 */
@Embeddable
public class MemberQueryId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6825871198480278730L;

	private int memberId;
	private String name;
	private String username;
	private String password;
	private Double balance;
	private String role;
	private int familyId;
	private String familyName;

	public MemberQueryId() {
	}

	public MemberQueryId(int memberId, int familyId) {
		this.memberId = memberId;
		this.familyId = familyId;
	}

	public MemberQueryId(int memberId, String name, String username, String password, Double balance, String role,
			int familyId, String familyName) {
		this.memberId = memberId;
		this.name = name;
		this.username = username;
		this.password = password;
		this.balance = balance;
		this.role = role;
		this.familyId = familyId;
		this.familyName = familyName;
	}

	@Column(name = "MEMBER_ID", nullable = false)
	public int getMemberId() {
		return this.memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	@Column(name = "NAME")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "USERNAME")
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "PASSWORD")
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "BALANCE", precision = 22, scale = 0)
	public Double getBalance() {
		return this.balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	@Column(name = "ROLE")
	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Column(name = "FAMILY_ID", nullable = false)
	public int getFamilyId() {
		return this.familyId;
	}

	public void setFamilyId(int familyId) {
		this.familyId = familyId;
	}

	@Column(name = "FAMILY_NAME")
	public String getFamilyName() {
		return this.familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MemberQueryId))
			return false;
		MemberQueryId castOther = (MemberQueryId) other;

		return (this.getMemberId() == castOther.getMemberId())
				&& ((this.getName() == castOther.getName()) || (this.getName() != null && castOther.getName() != null
						&& this.getName().equals(castOther.getName())))
				&& ((this.getUsername() == castOther.getUsername()) || (this.getUsername() != null
						&& castOther.getUsername() != null && this.getUsername().equals(castOther.getUsername())))
				&& ((this.getPassword() == castOther.getPassword()) || (this.getPassword() != null
						&& castOther.getPassword() != null && this.getPassword().equals(castOther.getPassword())))
				&& ((this.getBalance() == castOther.getBalance()) || (this.getBalance() != null
						&& castOther.getBalance() != null && this.getBalance().equals(castOther.getBalance())))
				&& ((this.getRole() == castOther.getRole()) || (this.getRole() != null && castOther.getRole() != null
						&& this.getRole().equals(castOther.getRole())))
				&& (this.getFamilyId() == castOther.getFamilyId())
				&& ((this.getFamilyName() == castOther.getFamilyName())
						|| (this.getFamilyName() != null && castOther.getFamilyName() != null
								&& this.getFamilyName().equals(castOther.getFamilyName())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getMemberId();
		result = 37 * result + (getName() == null ? 0 : this.getName().hashCode());
		result = 37 * result + (getUsername() == null ? 0 : this.getUsername().hashCode());
		result = 37 * result + (getPassword() == null ? 0 : this.getPassword().hashCode());
		result = 37 * result + (getBalance() == null ? 0 : this.getBalance().hashCode());
		result = 37 * result + (getRole() == null ? 0 : this.getRole().hashCode());
		result = 37 * result + this.getFamilyId();
		result = 37 * result + (getFamilyName() == null ? 0 : this.getFamilyName().hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "MemberQueryId [memberId=" + memberId + ", name=" + name + ", username=" + username + ", password="
				+ password + ", balance=" + balance + ", role=" + role + ", familyId=" + familyId + ", familyName="
				+ familyName + "]";
	}

}
