package cn.cqupt.wss;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.junit.Test;

import cn.cqupt.wss.domain.Customer;
import cn.cqupt.wss.domain.LinkMan;
import cn.cqupt.wss.utils.HibernateUtils;

/**
 * day04 QBC的查询
 * */

public class Demo4 {
	
	/**
	 * 简单的查询
	 */
	@Test
	public void demo1() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		//这里使用JPA规范的CriteriaQuery
		CriteriaQuery<Customer> criteria = HibernateUtils.getCriteriaQuery(session, Customer.class);
		criteria.from(Customer.class);
		Query<Customer> query = session.createQuery(criteria);
		
		List<Customer> list = query.list();
		list.forEach(System.out::println);
		tx.commit();
	}
	
	@Test
	/**
	 * 条件查询
	 */
	public void demo0(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		//这里使用JPA规范的CriteriaQuery
//        CriteriaQuery<Customer> crq = HibernateUtils.getCriteriaQuery(session, Customer.class);
		//1.创建CriteriaBuilder,用于创建查询
        CriteriaBuilder crb = session.getCriteriaBuilder();
        //2.创建CriteriaQuery,用于设置查询语句的信息
        CriteriaQuery<Customer> crq = crb.createQuery(Customer.class);
        Root<Customer> root=crq.from(Customer.class);
//        crq.select(root);
        crq.where(crb.like(root.get("cust_name"),"王%"));
        List<Customer> list = session.createQuery(crq).getResultList();
		for (Customer customer : list) {
			System.out.println(customer);
		}
		tx.commit();
	}
	
	
	
	@Test
	/**
	 * 排序 条件
	 */
	public void test() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
        //1.创建CriteriaBuilder,用于创建查询
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        //2.创建CriteriaQuery,用于设置查询语句的信息
        CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
        //3.定义查询的From子句中能出现的类型,也可以用root.get()获取具体的某个属性
        Root<Customer> root = criteriaQuery.from(Customer.class);
        //4.设置WHERE字句的条件，此处条件为score<= 98
	    // 设置条件:	=   eq 。 >   gt。 >=  ge。 <   lt。 <=  le。 <>  ne。like。 in。 and。 or
//        criteriaQuery.where(
//        		 criteriaBuilder.and(
//        		  criteriaBuilder.like(root.get("cust_name"),"王%"), 
//        		  criteriaBuilder.equal(root.get("id"), 1l)
//        		));
        criteriaQuery.where(criteriaBuilder.lt(root.get("cust_id"), 100));
//        criteriaQuery.where(criteriaBuilder.equal(root.get("id"), 1l));
        //5.设置排序标准与排序方式
        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("cust_id")));
        
        //6.创建Query对象并获取结果集list
        Query<Customer> query = session.createQuery(criteriaQuery);
        List<Customer> list = query.list();
        //7.遍历结果集
        list.forEach(System.out::println);
        tx.commit();
	}
	
	
	@Test
	/**
	 * 分页查询
	 */
	public void demo3(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		//这里使用JPA规范的CriteriaQuery
		CriteriaQuery<LinkMan> criteria = HibernateUtils.getCriteriaQuery(session, LinkMan.class);
		criteria.from(LinkMan.class);
		
		Query<LinkMan> query = session.createQuery(criteria);
		List<LinkMan> list = query.list();
		list.forEach(System.out::println);
		tx.commit();
		
		// 分页查询
//		Criteria criteria = session.createCriteria(LinkMan.class);
//		criteria.setFirstResult(10);
//		criteria.setMaxResults(10);
//		List<LinkMan> list = criteria.list();
	}
	
	
	@Test
	/**
	 * 统计查询
	 */
	public void demo5(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		CriteriaBuilder builder = session.getCriteriaBuilder();
        // 参数为查询的结果类型
//		CriteriaQuery<Long> criteria = HibernateUtils.getCriteriaQuery(session, Long.class);
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        // 从什么表查询
		Root<Customer> root = criteria.from(Customer.class);
        // 就是sql select 之后的语句 
		criteria.select(builder.count(root));
		criteria.where(builder.lt(root.get("cust_id"), 100));
        // 使用query 实现查询
		Query<Long> query = session.createQuery(criteria);
        // 结果集
		Long result = query.uniqueResult();
		System.out.println(result.intValue());
		tx.commit();
		
	}
	
	@Test
	/**
	 * 离线条件查询
	 */
	public void demo6(){
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
		detachedCriteria.add(Restrictions.like("cust_name", "李%"));
		
		Session session = HibernateUtils.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		
		Criteria criteria = detachedCriteria.getExecutableCriteria(session);
		@SuppressWarnings("unchecked")
		List<Customer> list = criteria.list();
		for (Customer customer : list) {
			System.out.println(customer);
		}
		transaction.commit();
	}
}
