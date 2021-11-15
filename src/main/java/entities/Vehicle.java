package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "vehicle")
public class Vehicle implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = true)
    @NotNull
    @Column(name = "vehicle_name", length = 50)
    private String vehicleName;

    @OneToMany
    private List<User> userList;

    public Vehicle() {}


    public Vehicle(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getVehicleName() {
        return vehicleName;
    }
    public void setVehicleName(String vehicleName){
        this.vehicleName = vehicleName;
    }
    public List<User> getUserList() {
        return userList;
    }
    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

}
