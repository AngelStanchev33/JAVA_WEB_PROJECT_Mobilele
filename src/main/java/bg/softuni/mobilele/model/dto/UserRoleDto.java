package bg.softuni.mobilele.model.dto;

import java.util.ArrayList;
import java.util.List;

public class UserRoleDto {

    private List<String> roles;

    public UserRoleDto() {
        this.roles = new ArrayList<>();
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

}
