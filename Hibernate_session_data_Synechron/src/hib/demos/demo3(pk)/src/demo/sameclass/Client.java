package demo.sameclass;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import util.HibernateUtil;

public class Client {
	static SessionFactory sf = HibernateUtil.getSessionFactory();
	public static void main(String[] args) {
		Session session=null;
		Transaction tx = null;
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			Emp e = new Emp();
			e.setFname("vaishali" );
			e.setLname("tapaswi" );
			e.setSalary(1111);
			session.save(e);
			tx.commit();
		} catch (Exception e) {
			System.out.println(" Exception :  " + e);
			tx.rollback();
	  }
		Emp e= new Emp();
		e.setFname("vaishali" ); e.setLname("tapaswi" );
		e = (Emp)session.get(Emp.class, e);
				System.out.println("After get " + e);
		
		
		
}
}
