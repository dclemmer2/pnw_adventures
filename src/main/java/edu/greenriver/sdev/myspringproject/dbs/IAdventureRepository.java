package edu.greenriver.sdev.myspringproject.dbs;

import edu.greenriver.sdev.myspringproject.models.Adventure;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface sets up an Adventure repository to access the database.
 *
 * @author Dana Clemmer
 * @version 1.0
 * 10/16/21
 */
public interface IAdventureRepository extends JpaRepository<Adventure, Integer> {
}
