package demo;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.QueryHint;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="DeptTable")
@Cacheable(value=true)
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
/*@NamedQuery(name="myq", query="select d from Dept d",
	hints={@QueryHint(name="org.hibernate.cacheable",value="true")}) */
@NamedQuery(name="myqwithparam", query="select d from Dept d where d.loc = :loc",
hints={@QueryHint(name="org.hibernate.cacheable",value="true")})
public class Dept {
	@Id
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
