import java.util.Arrays;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.hibernate.stat.QueryStatistics;
import org.hibernate.stat.Statistics;

import util.HibernateUtil;
import demo.Dept;
import demo.Emp;

public class Client {
	static SessionFactory sf = HibernateUtil.getSessionFactory();

	public static void printstats() {
		Statistics stats = sf.getStatistics();
		System.out.println("Number of Open Session calls ="
				+ stats.getSessionOpenCount());
		System.out.println("Number of Close Session calls = "
				+ stats.getSessionCloseCount());
		
		String[] queries = stats.getQueries();
		System.out.println("QueryString\t\t\t Execution Count \t\t\t Row Count \t\t\t Avg Time ");
		for (int i = 0; i < queries.length; i++) {
			System.out.println(queries[i]);
			QueryStatistics qstats = stats.getQueryStatistics(queries[i]);
			System.out.println("\t\t\t\tt\t\t" + qstats.getExecutionCount()  + "\t"+ qstats.getExecutionRowCount() + "\t"+ qstats.getExecutionAvgTime());
		}
		
	}

	public static void listwhere() {
		Session session = null;
		try {
			session = sf.openSession();
			Query q = session
					.createQuery("select e from Emp e where e.salary >= 2000 and e.salary <= 5000");
			List<Emp> list = q.list();
			for (Emp e : list) {
				System.out.println(e);
			}

		} catch (Exception e) {
			System.out.println(" Exception :  " + e);
		} finally {
			session.close();
		}
	}

	public static void listbetween() {
		Session session = null;
		try {
			session = sf.openSession();
			Query q = session	.createQuery("select e from Emp e where e.salary between 2000 and 5000");
			List<Emp> list = q.list();
			for (Emp e : list) {
				System.out.println(e);
			}

		} catch (Exception e) {
			System.out.println(" Exception :  " + e);
		} finally {
			session.close();
		}
	}

	public static void list() {
		Session session = null;
		try {
			session = sf.openSession();
			Query q = session.createQuery("select e from Emp e");
			List<Dept> depts = session.createQuery("select d from Dept d").list();
			List<Emp> list = q.list();
			for (Emp e : list) {
				System.out.println(e);
				System.out.println("\t\t" + e.getDepartment());
			}

		} catch (Exception e) {
			System.out.println(" Exception :  " + e);
		} finally {
			session.close();
		}
	}

	public static void query1() {
		Session session = null;
		try {
			session = sf.openSession();
			// Query 1
			// Query q =
			// session.createQuery("select d.loc, count(d)  from Dept d group by d.loc");
			// Query 2
			// Query q =
			// session.createQuery("select d.loc, count(e.empno) from Emp e, Dept d where e.department.deptno= d.deptno group by d.loc");
			// QUery 3
			// Query q =
			// session.createQuery("select d.deptno, count(e) from Dept d left join d.emps e on d.deptno = e.department.deptno group by d.deptno");
			Query q = session
					.createQuery("select d.deptno,count(e)  from Dept  d left  join  d.emps   e  group by d.deptno");

			ScrollableResults sr = q.scroll();
			while (sr.next()) {
				System.out.println(Arrays.toString(sr.get()));
			}
		} catch (Exception e) {
			System.out.println(" Exception :  " + e);
		} finally {
			session.close();
		}
	}

	public static void create() {
		Session session = null;
		Transaction tx = null;
		try {
			session = sf.openSession();
			tx = session.beginTransaction();
			Dept d10 = new Dept(10, "HR", "Pune");
			Dept d20 = new Dept(20, "IT", "Blr");
			Dept d30 = new Dept(30, "Fin", "Pune");
			Dept d40 = new Dept(40, "Support", "Hyd");

			Emp e101 = new Emp(101, "AAA", 1000);
			e101.setDepartment(d10);

			Emp e102 = new Emp(102, "BBB", 2000);
			e102.setDepartment(d20);

			Emp e103 = new Emp(103, "CCC", 3000);
			e103.setDepartment(d20);

			Emp e104 = new Emp(104, "DDD", 4000);
			e104.setDepartment(d40);

			Emp e105 = new Emp(105, "EEE", 5000);
			e105.setDepartment(d40);

			Emp e106 = new Emp(106, "FFF", 6000);
			e106.setDepartment(d40);

			session.save(d10);
			session.save(d20);
			session.save(d30);
			session.save(d40);

			session.save(e101);
			session.save(e102);
			session.save(e103);
			session.save(e104);
			session.save(e105);
			session.save(e106);

			tx.commit();

		} catch (Exception e) {
			System.out.println(" Exception :  " + e);
			tx.rollback();
		} finally {
			session.close();
		}
	}

	public static void main(String[] args) {
		// create();
		// query1();
		list();
		listbetween();
		listwhere();
		printstats();
		// deptempcount();
		HibernateUtil.getSessionFactory().close();
	}

}
