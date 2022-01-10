package edu.greenriver.sdev.myspringproject.services;

import edu.greenriver.sdev.myspringproject.dbs.IAdventureRepository;
import edu.greenriver.sdev.myspringproject.dbs.IHikeRepository;
import edu.greenriver.sdev.myspringproject.models.Hike;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * This class sets up a hike service layer for the site.
 *
 * @author Dana Clemmer
 * @version 1.0
 * 11/16/21
 */

@Service
public class HikeService {
    private final IHikeRepository repo;
    private final IAdventureRepository adventureRepository;

    /**
     * Constructor for HikeService class that instantiates the repos Spring beans.
     *
     * @param adventureRepository passed in adventureRepository Spring bean
     * @param hikeRepository passed in hikeRepository Spring bean
     */
    public HikeService(IAdventureRepository adventureRepository, IHikeRepository hikeRepository) {
        this.adventureRepository = adventureRepository;
        this.repo = hikeRepository;
    }

    /**
     * Returns a list of all Hike objects in db.
     *
     * @return list of all Hike objects
     */
    public List<Hike> allHikes() {
        return repo.findAll();
    }
    /**
     * Returns associated Hike object with passed in id.
     *
     * @param id Hike object id
     * @return Hike object
     */
    public Hike hikeById(int id) {
        return repo.findById(id).orElse(null);
    }

    /**
     * Saves a Hike object to the db.
     *
     * @param hike Hike object passed in to save.
     * @return saved new Hike
     */
    public Hike saveHike(Hike hike) {
        //validate that the hike object has valid data

        return repo.save(hike);
    }

    /**
     * Edits an existing Hike object
     * @param hike Hike to be edited
     *
     * @return saved edited Hike
     */
    public Hike editHike(Hike hike)
    {
        if (repo.findById(hike.getHikeId()).isEmpty())
        {
            throw new NoSuchElementException("Missing hike");
        }
        return repo.save(hike);
    }

    /**
     * Deletes a Hike object in the db.
     *
     * @param id Hike object id
     */
    public void deleteHike(int id) {
        if (repo.findById(id).isEmpty()) {
            throw new NoSuchElementException("Missing hike");
        }
        repo.deleteById(id);
    }

    /**
     * Deletes Hikes associated with Adventure in the db.
     *
     * @param id Adventure object id
     */
    public void deleteHikes(int id) {
        if (adventureRepository.findById(id).isEmpty()) {
            throw new NoSuchElementException("Missing adventure");
        }

        List<Hike> hikes = adventureRepository.findById(id).get().getHikes();
        for (Hike hike : hikes) {
            deleteHike(hike.getHikeId());
        }
    }

    /**
     * Checks if hike exists in db.
     *
     * @param id Hike object id
     * @return true/false
     */
    public boolean hikeExists(int id)
    {
        return hikeById(id) != null;
    }
}
