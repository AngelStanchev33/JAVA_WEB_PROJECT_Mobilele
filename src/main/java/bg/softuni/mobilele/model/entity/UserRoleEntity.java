package bg.softuni.mobilele.model.entity;

import bg.softuni.mobilele.model.entity.enums.UserRoleEnum;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "roles")
public class UserRoleEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRoleEnum userRole;

    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<UserEntity> users;

    // Getters & Setters
    public UserRoleEnum getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRoleEnum userRole) {
        this.userRole = userRole;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "UserRoleEntity{" +
                "userRole=" + userRole +
                ", users=" + users +
                '}';
    }
}
