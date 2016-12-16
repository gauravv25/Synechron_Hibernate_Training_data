import util.HibernateUtil;
import demo.Dept;
import demo.DeptDAO;


public class UpdateClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		DeptDAO dao = new DeptDAO();
		System.out.println("Listing First Time .........................");
		dao.list();
		
		Dept d = new Dept();
			d.setDeptno(2);
			d.setDname("Two");
				d.setLoc("Pune");
			dao.update(d);
			
			dao.list();
		HibernateUtil.getSessionFactory().close();
	}

}
