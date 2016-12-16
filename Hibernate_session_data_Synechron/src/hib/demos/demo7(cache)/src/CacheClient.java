import org.hibernate.Cache;
import org.hibernate.SessionFactory;
import org.hibernate.stat.QueryStatistics;
import org.hibernate.stat.Statistics;

import util.HibernateUtil;
import demo.Dept;
import demo.DeptDAO;


public class CacheClient {
	public static void printstats() {
		Statistics stats = HibernateUtil.getSessionFactory().getStatistics();
		System.out.println(" Put Count = " + stats.getSecondLevelCachePutCount());
		System.out.println(" Hit Count = " + stats.getSecondLevelCacheHitCount());
		System.out.println(" Miss Count = " + stats.getSecondLevelCacheMissCount());
	
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		DeptDAO dao = new DeptDAO();
		System.out.println("Listing First Time .........................");
	//	dao.listwithparam("Pune");
		dao.list();
	//	SessionFactory sf = HibernateUtil.getSessionFactory();
	//	Cache cache = sf.getCache();
//		dao.listwithparam("Hyd");
		printstats();
		HibernateUtil.getSessionFactory().close();
	}

}
