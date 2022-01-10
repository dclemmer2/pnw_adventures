package edu.greenriver.sdev.myspringproject.controllers;

import edu.greenriver.sdev.myspringproject.models.Hike;
import edu.greenriver.sdev.myspringproject.services.AdventureService;
import edu.greenriver.sdev.myspringproject.services.HikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Hike API Restful Controller.
 *
 * @author Dana Clemmer
 * @version 1.0
 * 11/16/21
 */

@RestController
@RequestMapping("pnwAdventures/api/hikes")
public class HikeApiController {
    private final HikeService service;
    private final AdventureService adventureService;

    /**
     * Constructor that instantiates the services.
     *
     * @param service passed in service
     */
    public HikeApiController(HikeService service, AdventureService adventureService) {
        this.service = service;
        this.adventureService = adventureService;
    }

    /**
     * Returns a list of all Hike objects.
     *
     * @return List of all Hikre objects
     */
    @GetMapping
    public ResponseEntity<List<Hike>> allHikes() {
        return new ResponseEntity<>(service.allHikes(), HttpStatus.OK);
    }

    /**
     * Returns Hike object associated with passed in id.
     *
     * @param id Hike id
     * @return Hike object associated with passed in id.
     */
    @GetMapping("{id}")
    public ResponseEntity<Hike> hikeById(@PathVariable int id) {
        if (!service.hikeExists(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(service.hikeById(id), HttpStatus.OK);
    }

    /**
     * Adds a new Hike object to db.
     *
     * @param hike new Hike object
     * @return No content status code
     */
    @PostMapping
    public ResponseEntity<Hike> addHike(@RequestBody Hike hike) {
        hike = service.saveHike(hike);
        return new ResponseEntity<>(hike, HttpStatus.NO_CONTENT);
    }

    /**
     * Edits and updates a passed in Hike object.
     *
     * @param hike Hike object being updated
     * @return Ok status code if updated
     */
    @PutMapping
    public ResponseEntity<Hike> editHike(@RequestBody Hike hike) {
        if (!service.hikeExists(hike.getHikeId()))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(service.editHike(hike), HttpStatus.OK);
    }

    /**
     * Deletes Hike object associated with passed in id.
     *
     * @param id id associated with Hike object to be deleted.
     * @return No content status code
     */
    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteHike(@PathVariable int id) {
        service.deleteHike(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    /**
     * Returns all hikes associated with an Adventure object.
     *
     * @param id id associated with Adventure object.
     * @return list of Hike objects.
     */
    @GetMapping("{id}/hikes")
    public ResponseEntity<List<Hike>> hikesByAdventureId(@PathVariable int id) {
        if(!adventureService.adventureExists(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(adventureService.getAdventureHikes(id), HttpStatus.OK);
    }

    /**
     * Returns hike associated with adventure id and hike id.
     *
     * @param adventureId id associated with Adventure object.
     * @param hikeId id associated with Hike object.
     * @return Hike object
     */
    @GetMapping("{adventureId}/hike/{hikeId}")
    public Hike hikeByIds(@PathVariable int adventureId, @PathVariable int hikeId) {
        return adventureService.getHikeByIds(adventureId, hikeId);
    }
}
