package demo;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="EmpTable")
public class Emp  {
	@Id
	@Pattern(regexp = "^[A-Z]$")
	@Column(length=6)
	private String empno;
	
	@Column(length=8)
	@Length(min=5, max=8, message="Invalid length for Name")
	private String name;

	@Column(name="bday")
	@Past
	private Date birthday;
	
	@Min(value=1000, message=" min validation ...")
	@Max(value=2000, message=" max validation" )
	@Column(name="salary")
	private double salary;
	
	@Column(name="emailid")
	@Email(message="email validation")
	private String email;

	public String getEmpno() {
		return empno;
	}

	public void setEmpno(String empno) {
		this.empno = empno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
		
	
}


