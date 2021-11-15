package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.mindrot.jbcrypt.BCrypt;

@Entity
@Table(name = "users")
public class User implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @NotNull
  @Column(name = "user_name", length = 25)
  private String userName;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 255)
  @Column(name = "user_pass")
  private String userPass;
  @JoinTable(name = "user_roles", joinColumns = {
    @JoinColumn(name = "user_name", referencedColumnName = "user_name")}, inverseJoinColumns = {
    @JoinColumn(name = "role_name", referencedColumnName = "role_name")})
  @ManyToMany
  private List<Role> roleList = new ArrayList<>();

  @ManyToOne
  private Vehicle vehicle;

  public List<String> getRolesAsStrings() {
    if (roleList.isEmpty()) {
      return null;
    }
    List<String> rolesAsStrings = new ArrayList<>();
    roleList.forEach((role) -> {
        rolesAsStrings.add(role.getRoleName());
      });
    return rolesAsStrings;
  }
  @ManyToMany
  private List<Company> companyList = new ArrayList<>();

  public List<String> getCompaniesAsStrings() {
    if (companyList.isEmpty()) {
      return null;
    }
    List<String> companiesAsStrings = new ArrayList<>();
    companyList.forEach((company) -> {
      companiesAsStrings.add(company.getCompanyName());
    });
    return companiesAsStrings;
  }

  public User() {}

  //TODO Change when password is hashed
   public boolean verifyPassword(String pw, String hashedPw){
        return BCrypt.checkpw(pw, hashedPw);
    }

  public User(String userName, String userPass) {
    this.userName = userName;
    String salt =BCrypt.gensalt();
    this.userPass = BCrypt.hashpw(userPass, salt);
  }


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserPass() {
    return this.userPass;
  }

  public void setUserPass(String userPass) {
    this.userPass = userPass;
  }

  public List<Role> getRoleList() {
    return roleList;
  }

  public void setRoleList(List<Role> roleList) {
    this.roleList = roleList;
  }

  public void addRole(Role userRole) {
    roleList.add(userRole);
  }
  public List<Company> getCompanyList() {
    return companyList;
  }
  public void setCompanyList(List<Company> companyList) {
    this.companyList = companyList;
  }
  public void addCompany(Company company) {
    companyList.add(company);
  }

  public void setVehicle(Vehicle vehicle) {
    this.vehicle = vehicle;
  }

  public Vehicle getVehicle() {
    return vehicle;
  }

}
