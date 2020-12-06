package cn.cqupt.wss.utils;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hibernateπ§æﬂ¿‡
 *
 */
public class HibernateUtils {

	public static final Configuration cfg;
	public static final SessionFactory sf;
	
	static{
		cfg = new Configuration().configure();
		sf = cfg.buildSessionFactory();
	}
	
	public static Session openSession(){
		return sf.openSession();
	}
	
	public static Session getCurrentSession() {
		return sf.getCurrentSession();
	}
	
	public static<T> CriteriaQuery<T> getCriteriaQuery(Session session, Class<T> type) {
        return session.getCriteriaBuilder().createQuery(type);
    }
}
