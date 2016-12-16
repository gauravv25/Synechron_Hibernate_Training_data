import util.HibernateUtil;
import demo.Dept;
import demo.DeptDAO;


public class ListClient {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		DeptDAO dao = new DeptDAO();
	/*	for(int i = 1; i<= 10; i+=1)
		{
			Dept d = new Dept();
			d.setDeptno(i);
			d.setDname("Dnameof" + i );
			if ((i % 2)==0)
				d.setLoc("Pune" );
			else
				d.setLoc("Hyd");
			dao.create(d);
		}
		*/
		System.out.println( "List One ....");
			dao.list();
			System.out.println( "Sleeping for 10 seconds ....");	
		//Thread.sleep(10000);
			System.out.println( "List Two ....");
			dao.list();
		HibernateUtil.getSessionFactory().close();
	}

}
