package edu.greenriver.sdev.myspringproject.dbs;

import edu.greenriver.sdev.myspringproject.models.Hike;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface sets up a Hike repository to access the database.
 *
 * @author Dana Clemmer
 * @version 1.0
 * 11/16/21
 */
public interface IHikeRepository extends JpaRepository<Hike, Integer> {
}
