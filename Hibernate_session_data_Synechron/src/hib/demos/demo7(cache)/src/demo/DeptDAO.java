package demo;

import java.util.List;

import org.hibernate.CacheMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import util.HibernateUtil;


public class DeptDAO {
	private static SessionFactory sf = HibernateUtil.getSessionFactory();
	public void create(Dept d){
		Session session=null;
		Transaction tx = null;
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			session.save(d);
			System.out.println("after save ..................");
			tx.commit();
			System.out.println(d);
		} catch (Exception e) {
			System.out.println(" Exception :  " + e);
			tx.rollback();
		}finally{
			session.close();
		}
	}
	
	public void list(){
		Session session=null;
		try {
			session = sf.openSession();
			Query query =  session.createQuery("select d from Dept d");
		query.setCacheable(true);
			
			List<Dept> list = query.list();
			for (Dept dept : list) {
				System.out.println("\t\t\t\t" + dept);
			}
			
			Query query1 =   session.createQuery("select d from Dept d");
			query1.setCacheable(true);

			List<Dept> list1 = query1.list();
			for (Dept dept : list1) {
				System.out.println("\t\t\t\t" + dept);
			}
			
			
		} catch (Exception e) {
			System.out.println(" Exception :  " + e);
		}finally{
			session.close();
		}
		
	}
	public void listwithparam(String p){
		Session session=null;
		try {
			session = sf.openSession();
			Query query =  session.getNamedQuery("myqwithparam");
			query.setString("loc", p);
			List<Dept> list = query.list();
			for (Dept dept : list) {
				System.out.println("\t\t\t\t" + dept);
			}
			Query query1 =  session.getNamedQuery("myqwithparam");
			query1.setString("loc","BLR");
			List<Dept> list1 = query1.list();
			for (Dept dept : list1) {
				System.out.println("\t\t\t\t" + dept);
			}
			
			
		} catch (Exception e) {
			System.out.println(" Exception :  " + e);
		}finally{
			session.close();
		}
		
	}
	
	 public void update(Dept newdept){
		 Session session=null;
			Transaction tx = null;
			try {
				session = sf.openSession();
				tx = session.beginTransaction();
				session.update(newdept);
				tx.commit();
			} catch (Exception e) {
				System.out.println(" Exception :  " + e);
				tx.rollback();
			}finally{
				session.close();
			}
	 } 
	public void delete(int deptno){
		Session session=null;
		Transaction tx = null;
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			Dept d= new Dept();
			d.setDeptno(deptno);
			session.delete(d);
			tx.commit();
		} catch (Exception e) {
			System.out.println(" Exception :  " + e);
			tx.rollback();
		}finally{
			session.close();
		}
	}
}
