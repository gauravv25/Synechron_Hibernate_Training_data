

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

public class Client {
	static SessionFactory sf = HibernateUtil.getSessionFactory();
	public static void insert()
	{
		Session session=null;
		Transaction tx = null;
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
		for(int i =1; i<= 20; i++)
		{
			Emp e = new Emp();
			e.setEmpno(i);
			e.setEname("nameof"+i);
			e.setSalary(i * 1000);
			session.save(e);
		}
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
	
	public static void scroll()
	{
		Session session=null;
		try {
			session = sf.openSession();
			Query query =  session.createQuery("select e.empno, e.ename, e.salary from Emp e");
			ScrollableResults results = query.scroll(ScrollMode.FORWARD_ONLY);
			while(results.next())
			{
				System.out.println( results.get(0) + "\t\t " + results.get(1) + " \t\t" + results.get(2));
				}
		} catch (Exception e) {
			System.out.println(" Exception :  " + e);
	  }finally
	  {
		  session.close();
	  }
	}
		
	public static void iterate()
	{
		Session session=null;
		try {
			session = sf.openSession();
			Query query =  session.createQuery("select e from Emp e");
			Iterator<Emp> emps = query.iterate();
			while(emps.hasNext())
			{
				System.out.println(emps.next());	
			}
			
		} catch (Exception e) {
			System.out.println(" Exception :  " + e);
	  }finally
	  {
		  session.close();
	  }
	}

	
	public static void main(String[] args) {
	//	insert();
	//	list();
	//scroll();
		iterate();
		HibernateUtil.getSessionFactory().close();
}
}
