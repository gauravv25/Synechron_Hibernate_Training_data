

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import demo.Emp;
import util.HibernateUtil;

public class UpdateClient {
	static SessionFactory sf = HibernateUtil.getSessionFactory();
	public static void update()
	{
		Session session=null;
		Transaction tx = null;
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			Query q =  session.createQuery("update Emp set salary= salary*1.1 ");
			int  i = q.executeUpdate();
			System.out.println(i + " Records Modified");
			tx.commit();
		} catch (Exception e) {
			System.out.println(" Exception :  " + e);
			tx.rollback();
	  }finally
	  {
		  session.close();
	  }
		
	}
	public static void list()
	{
		Session session=null;
		try {
			session = sf.openSession();
			List<Emp> emps = session.createQuery("select e from Emp e").list();
			for (Emp emp : emps) {
				System.out.println(emp);
			}
		} catch (Exception e) {
			System.out.println(" Exception :  " + e);
	  }finally
	  {
		  session.close();
	  }
	}
	

	
	public static void main(String[] args) {
	update();
	list();	
	HibernateUtil.getSessionFactory().close();
}
}
