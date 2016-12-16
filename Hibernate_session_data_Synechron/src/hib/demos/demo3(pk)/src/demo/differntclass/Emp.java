package demo.differntclass;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="emptable")
public class Emp {
	@EmbeddedId
	private EmpPK pk ;
	private double salary;
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public EmpPK getPk() {
		return pk;
	}
	public void setPk(EmpPK pk) {
		this.pk = pk;
	}
	@Override
	public String toString() {
		return "Emp [pk=" + pk + ", salary=" + salary + "]";
	}
	
	
}
