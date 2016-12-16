package demo.differntclass;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class EmpPK  implements Serializable{
	private String fname;
	private String lname;
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	@Override
	public String toString() {
		return "EmpPK [fname=" + fname + ", lname=" + lname + "]";
	}
	
}
