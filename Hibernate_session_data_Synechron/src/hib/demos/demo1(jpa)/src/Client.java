import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import demo.Dept;


public class Client {
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("myunitname");
	public static void create(Dept d)
	{
		EntityManager em=null;
		EntityTransaction tx =null;
		try{
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			em.persist(d);
			tx.commit();
		}catch(Exception e)
		{
			System.out.println("Error "  + e);
			tx.rollback();
		}
		finally{
			em.close();
		}
	}
	public static void retrieve()
	{
		EntityManager em=null;
		try{
			em = emf.createEntityManager();
			List<Dept> list = em.createQuery("select d from Dept d").getResultList();
			for (Dept dept : list) {
				System.out.println(dept);
			}
		}catch(Exception e)
		{
			System.out.println("Error "  + e);
		}
		finally{
			em.close();
		}
	}
	public static void update(Dept d){
		EntityManager em=null;
		EntityTransaction tx =null;
		try{
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			em.merge(d);
			tx.commit();
		}catch(Exception e)
		{
			System.out.println("Error "  + e);
			tx.rollback();
		}
		finally{
			em.close();
		}
	}
	public static void delete(int deptno){
		EntityManager em=null;
		EntityTransaction tx =null;
		try{
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			Dept d= em.find(Dept.class,deptno);
			em.remove(d);
			tx.commit();
		}catch(Exception e)
		{
			System.out.println("Error "  + e);
			tx.rollback();
		}
		finally{
			em.close();
		}
	}
	public static void main(String[] args) {
		for(int i = 1; i<= 5; i++)
		{
			Dept d = new Dept();
			d.setDeptno(i*10); d.setDname("HR"); d.setLoc("Pune");
			create(d);
		}
		Dept d= new Dept();
		d.setDeptno(10); d.setDname("ITRaining"); d.setLoc("Hyd");
		update(d);

		delete(20);
		
		retrieve();
	}

}
