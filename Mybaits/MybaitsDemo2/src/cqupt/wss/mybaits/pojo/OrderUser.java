package cqupt.wss.mybaits.pojo;

/**
 * 订单关联用户的pojo
 * @author wss
 *
 */
public class OrderUser extends Order {
	private String username;
	private String address;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "OrderUser [username=" + username + ", address=" + address + ", getId()=" + getId() + ", getUserId()="
				+ getUserId() + ", getNumber()=" + getNumber() + ", getCreatetime()=" + getCreatetime() + ", getNote()="
				+ getNote() + "]";
	}

}
