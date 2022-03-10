package oracle.model;

import java.io.Serializable;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({ @NamedQuery(name = "Departments.findAll", query = "select o from Departments o") })
public class Departments implements Serializable {
    private static final long serialVersionUID = 3669718361619574314L;
    @Id
    @Column(name = "DEPARTMENT_ID", nullable = false)
    private Integer departmentId;
    @Column(name = "DEPARTMENT_NAME", nullable = false, length = 30)
    private String departmentName;
    @Column(name = "LOCATION_ID")
    private Integer locationId;
    @ManyToOne
    @JoinColumn(name = "MANAGER_ID")
    private Employees employees;
    @OneToMany(mappedBy = "departments", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Employees> employeesList;

    public Departments() {
    }

    public Departments(Integer departmentId, String departmentName, Integer locationId, Employees employees) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.locationId = locationId;
        this.employees = employees;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }


    public Employees getEmployees() {
        return employees;
    }

    public void setEmployees(Employees employees) {
        this.employees = employees;
    }

    public List<Employees> getEmployeesList() {
        return employeesList;
    }

    public void setEmployeesList(List<Employees> employeesList) {
        this.employeesList = employeesList;
    }

    public Employees addEmployees(Employees employees) {
        getEmployeesList().add(employees);
        employees.setDepartments(this);
        return employees;
    }

    public Employees removeEmployees(Employees employees) {
        getEmployeesList().remove(employees);
        employees.setDepartments(null);
        return employees;
    }
}
