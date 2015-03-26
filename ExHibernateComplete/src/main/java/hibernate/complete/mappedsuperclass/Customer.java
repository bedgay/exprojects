package hibernate.complete.mappedsuperclass;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
@AttributeOverrides({ @AttributeOverride(name = "createDate", column = @Column(name = "BirthDay")) })
public class Customer extends BasicMember {

	@Column(name = "Name")
	private String name;

	@Column(name = "Address")
	private String address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}