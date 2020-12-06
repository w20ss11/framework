package cqupt.wss.mybaits.Mapper;

import java.util.List;

import cqupt.wss.mybaits.pojo.QueryVo;
import cqupt.wss.mybaits.pojo.User;

/**
 * 用户信息持久化接口
 * */
public interface UserMapper {
	/**
	 * 根据用户id查询用户信息
	 * @param id 用户id
	 * */
	User getUserById(Integer id);
	
	/**
	 * 根据用户名查找用户列表
	 * @param userName 用户名
	 * */
	List<User> getUserByUserName(String userName);
	
	/**
	 * 添加用户
	 * @param user 被添加的用户
	 * */
	void insertUser(User user);
	
	//day02
	/**
	 * 传递包装pojo
	 * @param vo
	 * */
	List<User> getUserByQueryVo(QueryVo vo);
	
	/**
	 * 查询用户总记录数
	 * *
	 * @return
	 */
	Integer getUserCount(); 

	List<User> getUserByPojo(User user);
	
	List<User> getUserByIds(QueryVo queryVo);
	
	List<User> getUserOrderMap();
}
