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
 * day04 QBC�Ĳ�ѯ
 * */

public class Demo4 {
	
	/**
	 * �򵥵Ĳ�ѯ
	 */
	@Test
	public void demo1() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		//����ʹ��JPA�淶��CriteriaQuery
		CriteriaQuery<Customer> criteria = HibernateUtils.getCriteriaQuery(session, Customer.class);
		criteria.from(Customer.class);
		Query<Customer> query = session.createQuery(criteria);
		
		List<Customer> list = query.list();
		list.forEach(System.out::println);
		tx.commit();
	}
	
	@Test
	/**
	 * ������ѯ
	 */
	public void demo0(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		//����ʹ��JPA�淶��CriteriaQuery
//        CriteriaQuery<Customer> crq = HibernateUtils.getCriteriaQuery(session, Customer.class);
		//1.����CriteriaBuilder,���ڴ�����ѯ
        CriteriaBuilder crb = session.getCriteriaBuilder();
        //2.����CriteriaQuery,�������ò�ѯ������Ϣ
        CriteriaQuery<Customer> crq = crb.createQuery(Customer.class);
        Root<Customer> root=crq.from(Customer.class);
//        crq.select(root);
        crq.where(crb.like(root.get("cust_name"),"��%"));
        List<Customer> list = session.createQuery(crq).getResultList();
		for (Customer customer : list) {
			System.out.println(customer);
		}
		tx.commit();
	}
	
	
	
	@Test
	/**
	 * ���� ����
	 */
	public void test() {
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
        //1.����CriteriaBuilder,���ڴ�����ѯ
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        //2.����CriteriaQuery,�������ò�ѯ������Ϣ
        CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
        //3.�����ѯ��From�Ӿ����ܳ��ֵ�����,Ҳ������root.get()��ȡ�����ĳ������
        Root<Customer> root = criteriaQuery.from(Customer.class);
        //4.����WHERE�־���������˴�����Ϊscore<= 98
	    // ��������:	=   eq �� >   gt�� >=  ge�� <   lt�� <=  le�� <>  ne��like�� in�� and�� or
//        criteriaQuery.where(
//        		 criteriaBuilder.and(
//        		  criteriaBuilder.like(root.get("cust_name"),"��%"), 
//        		  criteriaBuilder.equal(root.get("id"), 1l)
//        		));
        criteriaQuery.where(criteriaBuilder.lt(root.get("cust_id"), 100));
//        criteriaQuery.where(criteriaBuilder.equal(root.get("id"), 1l));
        //5.���������׼������ʽ
        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("cust_id")));
        
        //6.����Query���󲢻�ȡ�����list
        Query<Customer> query = session.createQuery(criteriaQuery);
        List<Customer> list = query.list();
        //7.���������
        list.forEach(System.out::println);
        tx.commit();
	}
	
	
	@Test
	/**
	 * ��ҳ��ѯ
	 */
	public void demo3(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		//����ʹ��JPA�淶��CriteriaQuery
		CriteriaQuery<LinkMan> criteria = HibernateUtils.getCriteriaQuery(session, LinkMan.class);
		criteria.from(LinkMan.class);
		
		Query<LinkMan> query = session.createQuery(criteria);
		List<LinkMan> list = query.list();
		list.forEach(System.out::println);
		tx.commit();
		
		// ��ҳ��ѯ
//		Criteria criteria = session.createCriteria(LinkMan.class);
//		criteria.setFirstResult(10);
//		criteria.setMaxResults(10);
//		List<LinkMan> list = criteria.list();
	}
	
	
	@Test
	/**
	 * ͳ�Ʋ�ѯ
	 */
	public void demo5(){
		Session session = HibernateUtils.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		CriteriaBuilder builder = session.getCriteriaBuilder();
        // ����Ϊ��ѯ�Ľ������
//		CriteriaQuery<Long> criteria = HibernateUtils.getCriteriaQuery(session, Long.class);
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        // ��ʲô���ѯ
		Root<Customer> root = criteria.from(Customer.class);
        // ����sql select ֮������ 
		criteria.select(builder.count(root));
		criteria.where(builder.lt(root.get("cust_id"), 100));
        // ʹ��query ʵ�ֲ�ѯ
		Query<Long> query = session.createQuery(criteria);
        // �����
		Long result = query.uniqueResult();
		System.out.println(result.intValue());
		tx.commit();
		
	}
	
	@Test
	/**
	 * ����������ѯ
	 */
	public void demo6(){
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
		detachedCriteria.add(Restrictions.like("cust_name", "��%"));
		
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
