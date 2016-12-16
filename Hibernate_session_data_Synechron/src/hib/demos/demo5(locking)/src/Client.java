import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import util.HibernateUtil;
import demo.Dept;
import demo.DeptDAO;

class Helper extends Thread {
	private Dept d;
	private Session session;

	public Helper(Dept d, Session session) {
		this.d = d;
		this.session = session;
	}

	public void run() {
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Dept d1 = (Dept) session.load(Dept.class, d.getDeptno());
			d1.setDname(d.getDname());
			d1.setLoc(d.getLoc());
			session.update(d1);
			System.out.println("modified " + d.getLoc()
					+ " , waiting for 5 seconds before commit");
			Thread.sleep(5000);
			tx.commit();
			System.out.println("after commit for " + d.getLoc());
		} catch (Exception e) {
			System.out.println(" Exception :  " + e);
			tx.rollback();
		} finally {
			session.close();
		}
	}

}

public class Client {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		SessionFactory sf = HibernateUtil.getSessionFactory();
		/*
		 * Dept d = new Dept(); d.setDeptno(1); d.setDname("ITTT" );
		 * d.setLoc("PPP" );
		 * DeptDAO dao = new DeptDAO(); dao.create(d);
		 */
		Dept d = new Dept();
		d.setDeptno(1);
		d.setDname("Test");
		d.setLoc("Pune");

		Dept d1 = new Dept();
		d1.setDeptno(1);
		d1.setDname("Training......");
		d1.setLoc("Hyd");

		Helper h = new Helper(d, sf.openSession());
		Helper h1 = new Helper(d1, sf.openSession());
		h.start();
		h1.start();
		h.join();
		h1.join();
		HibernateUtil.getSessionFactory().close();
	}

}
