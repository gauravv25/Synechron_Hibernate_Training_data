package demo.differntclass;

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
			EmpPK pk= new EmpPK();
			pk.setFname("vaishali" );
			pk.setLname("tapaswi" );
			e.setPk(pk);
			e.setSalary(1111);
			session.save(e);
			
			tx.commit();
		} catch (Exception e) {
			System.out.println(" Exception :  " + e);
			tx.rollback();
	  }
		EmpPK pk= new EmpPK();
		pk.setFname("vaishali" );
		pk.setLname("tapaswi" );
		
		Emp e = (Emp)session.get(Emp.class, pk);
				System.out.println("After get " + e);
		
		
		
}
}
