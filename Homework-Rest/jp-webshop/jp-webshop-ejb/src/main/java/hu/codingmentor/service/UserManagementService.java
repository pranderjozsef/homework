package hu.codingmentor.service;

import hu.codingmentor.dto.UserDTO;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class UserManagementService {

    private final Map<String, UserDTO> users = new HashMap<>();
    
    @PostConstruct
    public void init() {
        UserDTO admin = new UserDTO("miki", "Password0", "Miklos", "Berenyi", LocalDate.of(1965, 10, 14), LocalDate.of(2012, 10, 20), true);
        UserDTO user = new UserDTO("andras", "Password1", "Andras", "Berenyi", LocalDate.of(1962, 10, 17), LocalDate.of(2012, 10, 20), false);
        
        users.put(admin.getUsername(), admin);
        users.put(user.getUsername(), user);
    }

    public Collection<UserDTO> getUsers() {
        return users.values();
    }

    public UserDTO getUser(String username) {
        return users.get(username);
    }

    public UserDTO addUser(UserDTO user) {
        users.put(user.getUsername(), user);
        return user;
    }

    public UserDTO editUser(UserDTO user) {
        return addUser(user);
    }

    public UserDTO deleteUser(String username) {
        return users.remove(username);
    }
}
