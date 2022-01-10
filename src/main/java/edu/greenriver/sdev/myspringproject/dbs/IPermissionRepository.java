package edu.greenriver.sdev.myspringproject.dbs;

import edu.greenriver.sdev.myspringproject.models.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface sets up a Permission repository to access the database.
 *
 * @author Dana Clemmer
 * @version 1.0
 * 12/3/21
 */
public interface IPermissionRepository extends JpaRepository<Permission, Integer> {
}
