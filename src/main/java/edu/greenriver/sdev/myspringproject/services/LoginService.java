package edu.greenriver.sdev.myspringproject.services;

import edu.greenriver.sdev.myspringproject.dbs.IUserRepository;
import edu.greenriver.sdev.myspringproject.models.Permission;
import edu.greenriver.sdev.myspringproject.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * This class sets up a login service layer for the site.
 *
 * @author Dana Clemmer
 * @version 1.0
 * 12/3/21
 */

@Service
public class LoginService implements UserDetailsService {
    private final IUserRepository userRepository;

    /**
     * Constructor for LoginService class that instantiates the user repo Spring bean.
     *
     * @param userRepository passed in userRepository Spring bean
     */
    public LoginService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Finds user in repo associated with passed in username.
     *
     * @param username passed in username
     * @return user if found, otherwise throws an exception.
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);

        if (user != null) {
            return user;
        }
        throw new UsernameNotFoundException("Username is not recognized");
    }

    /**
     * Finds user in repo associated with passed in username.
     *
     * @param username passed in username
     * @return user if found, otherwise throws an exception.
     */
    public User loadUser(String username) {
        User user = userRepository.findByUsername(username);

        if (user != null) {
            return user;
        }
        throw new UsernameNotFoundException("Username is not recognized");
    }

    /**
     * Saves user to db
     *
     * @param user passed in user
     */
    public void saveUser(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        Permission permissions = new Permission(0, "ROLE_USER", user);
        user.setPermissions(new ArrayList<>());
        user.getPermissions().add(permissions);
        userRepository.save(user);
    }

    /**
     * Returns total number of users in db.
     *
     * @return number of users
     */
    public int numUsers() {
        int count = 0;
        for (User user : userRepository.findAll()) {
            count++;
        }
        return count;
    }
}
