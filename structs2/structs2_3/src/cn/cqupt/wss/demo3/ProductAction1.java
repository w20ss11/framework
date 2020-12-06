package cn.cqupt.wss.demo3;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import cn.cqupt.wss.domain.Product;
/**
 * 复杂类型的数据封装：封装到List集合
 *
 */
public class ProductAction1 extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Product> products;
	// 提供集合的set方法:
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public List<Product> getProducts() {
		return products;
	}

	@Override
	public String execute() throws Exception {
		for (Product product : products) {
			System.out.println(product);
		}
		return NONE;
	}

	
}
