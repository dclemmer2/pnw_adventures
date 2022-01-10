package edu.greenriver.sdev.myspringproject.dbs;

import edu.greenriver.sdev.myspringproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface sets up a User repository to access the database.
 *
 * @author Dana Clemmer
 * @version 1.0
 * 12/3/21
 */
public interface IUserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

}
