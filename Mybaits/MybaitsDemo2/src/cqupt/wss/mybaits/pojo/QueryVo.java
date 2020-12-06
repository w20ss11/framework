package cqupt.wss.mybaits.pojo;

import java.util.List;

/**
 * 包装的pojo
 * @author wss
 *
 */
public class QueryVo {//对象QueryVo中还有对象User
	private User user;
	private List<Integer> ids;
	
	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
