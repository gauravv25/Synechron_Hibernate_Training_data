package demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name="DeptTable")
public class Dept {
	@Id
/*	@SequenceGenerator(name="myseq", initialValue=10, allocationSize=50, sequenceName="mydbseq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator= "myseq")  */
	@TableGenerator(name="mytbl", table="pktable", pkColumnName="deptpk",initialValue=10, valueColumnName="pkval", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.TABLE, generator="mytbl")
	@Column(name="deptnoCol")
	private int deptno;
	@Column(name="dnameCol", length=20)
	private String dname;
	@Column(name="locationCol", length=20)
	private String loc;
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	@Override
	public String toString() {
		return "Dept [deptno=" + deptno + ", dname=" + dname + ", loc=" + loc
				+ "]";
	}

	
}
