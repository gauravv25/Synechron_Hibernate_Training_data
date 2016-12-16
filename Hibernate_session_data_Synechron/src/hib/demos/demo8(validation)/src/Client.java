import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import util.HibernateUtil;
import demo.Emp;

public class Client {
	private static SessionFactory sf = HibernateUtil.getSessionFactory();

	public static boolean validate(Emp e)
	{
		ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
		Validator validator = vf.getValidator();
		Set<ConstraintViolation<Emp>> setcon = validator.validate(e);
		System.out.println(setcon);
		if(setcon.size() >0)
		{
			System.out.println("Messages  : ");
			for (ConstraintViolation<Emp> cv : setcon) {
				System.out.println(cv.getMessage() + "\t" + cv.getInvalidValue());
			}
			return false;
		}
		else
		{
			System.out.println("Valid Object.....");
			return true;
		}
		
	}
	
	
	public static void create(Emp d){
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
			e.printStackTrace();
		}finally{
			session.close();
		}
	}

	public static void main(String[] args) throws Exception {
		Emp e= new Emp();
		e.setEmpno( "A");
		e.setName("VVVVV");
		e.setEmail("aa@ga");
		e.setSalary(1010);
		Date d = new SimpleDateFormat("dd/mm/yyyy" ).parse("12/12/2015");
		e.setBirthday(d);
		if( validate(e))
			create(e);
		HibernateUtil.getSessionFactory().close();
		
	}

}
