

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Query;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import demo.Emp;
import util.HibernateUtil;

public class PagingClient {
	static SessionFactory sf = HibernateUtil.getSessionFactory();
	
	public static void list() throws Exception
	{
		Session session=null;
		try {
			session = sf.openSession();
			Query query = session.createQuery("select e from Emp e");
			query.setFirstResult(0);
			query.setMaxResults(5);
			Scanner scanner= new Scanner(System.in);
			int i = 1;
			while(i != 0)
			{
				List<Emp> emps = query.list();
				if (emps.size()==0) 
						break;
				for (Emp emp : emps) {
					System.out.println(emp);
				}
				System.out.println("Press 1 to continue and zero stop");
				i = scanner.nextInt();
				if ( i == 1)
				{
					query.setFirstResult(query.getFirstResult() + query.getMaxResults());
				}
			}
			
		} catch (Exception e) {
			System.out.println(" Exception :  " + e);
	  }finally
	  {
		  session.close();
	  }
	}
	
	
	
	public static void main(String[] args) throws Exception {
		list();
		HibernateUtil.getSessionFactory().close();
}
}
