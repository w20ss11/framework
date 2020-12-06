package cqupt.wss.mybaits.Mapper;

import java.util.List;

import cqupt.wss.mybaits.pojo.Order;
import cqupt.wss.mybaits.pojo.OrderUser;

public interface OrderMapper {
	List<Order> getOrderList();
	//ResultMap使用
	List<Order> getOrderListMap();
	//一对一关联 使用resultType实现
	List<OrderUser> getOrderUser();
	//一对一关联 使用resultMap实现
	List<Order> getOrderUserMap();
}
