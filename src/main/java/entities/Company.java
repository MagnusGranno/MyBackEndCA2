package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "company")
public class Company implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = true)
    @NotNull
    @Column(name = "company_name", length = 50)
    private String companyName;

    @ManyToMany(mappedBy = "companyList")
    private List<User> userList;

    public Company() {}

    public Company(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public List<User> getUserList() {
        return userList;
    }
    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
