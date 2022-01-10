package edu.greenriver.sdev.myspringproject.services;

import edu.greenriver.sdev.myspringproject.dbs.IAdventureRepository;
import edu.greenriver.sdev.myspringproject.models.Adventure;
import edu.greenriver.sdev.myspringproject.models.Hike;
import edu.greenriver.sdev.myspringproject.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * This class sets up an adventure service layer for the site.
 *
 * @author Dana Clemmer
 * @version 1.0
 * 10/16/21
 */

@Service
public class AdventureService {
    //access the data layer using dependency injection
    //the IAdventureRepository is a Spring bean
    private final IAdventureRepository repo;

    private final HikeService hikeService;
    private final LoginService loginService;

    /**
     * Constructor for AdventureService class that instantiates the repo Spring bean
     * and hike service.
     *
     * @param repo        passed in repo Spring bean
     * @param hikeService passed in hike service
     */
    public AdventureService(IAdventureRepository repo, HikeService hikeService, LoginService loginService) {
        this.repo = repo;
        this.hikeService = hikeService;
        this.loginService = loginService;
    }

    /**
     * Returns a list of all Adventure objects in db.
     *
     * @return list of all Adventure objects.
     */
    public List<Adventure> allAdventures() {
        return repo.findAll();
    }

    /**
     * Returns a list of all Adventure objects associated with a user.
     *
     * @param username passed in username
     * @return list of all Adventure objects associated with user.
     */
    public List<Adventure> allUserAdventures(String username) {
        User user = loginService.loadUser(username);
        return new ArrayList<>(user.getAdventures());
    }


    /**
     * Returns associated Adventure object with passed in id.
     *
     * @param id Adventure object id
     * @return Adventure object
     */
    public Adventure adventureById(int id) {
        //returns adventure object with passed in ID
        return repo.findById(id).orElse(null);
    }

    /**
     * Saves an Adventure object to the db.
     *
     * @param adventure Adventure object passed in to save.
     * @return saved new Adventure object
     */
    public Adventure saveAdventure(Adventure adventure) {
        return repo.save(adventure);
    }

    /**
     * Edits an existing Adventure object.
     *
     * @param adventure Adventure to be edited
     * @return saved edited Adventure
     */
    public Adventure editAdventure(Adventure adventure) {
        if (repo.findById(adventure.getAdventureId()).isEmpty()) {
            throw new NoSuchElementException("Missing adventure");
        }
        return repo.save(adventure);
    }

    /**
     * Deletes an Adventure object in the db.
     *
     * @param id Adventure object id
     */
    public void deleteAdventure(int id) {
        if (repo.findById(id).isEmpty()) {
            throw new NoSuchElementException("Missing adventure");
        }
        hikeService.deleteHikes(id);
        repo.deleteById(id);
    }

    /**
     * Checks if Adventure object exists in db
     *
     * @param id Adventure object id
     * @return true/false
     */
    public boolean adventureExists(int id) {
        return adventureById(id) != null;
    }


    /**
     * Returns a list of all regions
     *
     * @return list of regions
     */
    public List<String> getRegions() {
        return new ArrayList<>(Arrays.asList("Olympic Peninsula", "Puget Sound", "North Cascades", "Central Cascades",
                "South Cascades", "Columbia River Gorge", "Oregon Coast"));
    }

    /**
     * Filters out and returns all Adventure objects that include the beach.
     *
     * @return list of Adventure objects that include the beach.
     */
    public List<Adventure> beachAdventures() {
        List<Adventure> all = repo.findAll();
        return all.stream()
                .filter(Adventure::isBeachIncluded)
                .collect(Collectors.toList());
    }


    /**
     * Returns all Hike objects associated with an Adventure object.
     *
     * @param id Adventure object id
     * @return list of Hike objects.
     */
    public List<Hike> getAdventureHikes(int id) {
        if (repo.findById(id).isEmpty()) {
            throw new NoSuchElementException("Missing adventure");
        }
        return adventureById(id).getHikes();
    }

    /**
     * Returns Hike object associated with adventure id and hike id.
     *
     * @param adventureId id associated with Adventure object.
     * @param hikeId      id associated with Hike object.
     * @return Hike object
     */
    public Hike getHikeByIds(int adventureId, int hikeId) {
        Adventure adventure = adventureById(adventureId);
        List<Hike> hikes = adventure.getHikes();
        for (Hike hike : hikes) {
            if (hike.getHikeId() == hikeId)
                return hike;
        }
        return null;
    }
}
