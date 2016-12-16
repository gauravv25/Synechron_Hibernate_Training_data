import util.HibernateUtil;
import demo.Dept;
import demo.DeptDAO;


public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DeptDAO dao = new DeptDAO();
		for(int i = 1; i<= 10; i+=1)
		{
			Dept d = new Dept();
			d.setDeptno(i);
			d.setDname("Dnameof" + i );
			if ((i % 20)==0)
				d.setLoc("Pune" );
			else
				d.setLoc("Hyd");
			dao.create(d);
		}
		Dept d = new Dept();
		d.setDeptno(10); d.setDname("HR"); d.setLoc("BLR");
		dao.update(d);
		
		dao.delete(20);
		dao.list();
		HibernateUtil.getSessionFactory().close();
	}

}
