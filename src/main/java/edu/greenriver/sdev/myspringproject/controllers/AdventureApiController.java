package edu.greenriver.sdev.myspringproject.controllers;

import edu.greenriver.sdev.myspringproject.models.Adventure;
import edu.greenriver.sdev.myspringproject.services.AdventureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Adventure API Restful Controller.
 *
 * @author Dana Clemmer
 * @version 1.0
 * 11/16/21
 */
@RestController
@RequestMapping("pnwAdventures/api/adventures")
public class AdventureApiController {
    private final AdventureService service;

    /**
     * Constructor that instantiates the AdventureService service.
     *
     * @param service passed in service
     */
    public AdventureApiController(AdventureService service) {
        this.service = service;
    }

    /**
     * Returns a list of all Adventure objects.
     *
     * @return List of all Adventure objects
     */
    @GetMapping
    public ResponseEntity<List<Adventure>> allAdventures() {
        return new ResponseEntity<>(service.allAdventures(), HttpStatus.OK);
    }

    /**
     * Returns Adventure object associated with passed in id.
     *
     * @param id Adventure id
     * @return Adventure object associated with passed in id.
     */
    @GetMapping("{id}")
    public ResponseEntity<Adventure> adventureById(@PathVariable int id) {
        //if product not there
        if(!service.adventureExists(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(service.adventureById(id), HttpStatus.OK);
    }

    /**
     * Adds a new Adventure object to db.
     *
     * @param adventure new Adventure object
     * @return No content status code
     */
    @PostMapping
    public ResponseEntity addAdventure(@RequestBody Adventure adventure) {
        adventure = service.saveAdventure(adventure);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    /**
     * Edits and updates a passed in Adventure object.
     *
     * @param adventure Adventure object being updated
     * @return Ok status code if updated
     */
    @PutMapping
    public ResponseEntity<Adventure> editAdventure(@RequestBody Adventure adventure) {
        if(!service.adventureExists(adventure.getAdventureId()))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(service.editAdventure(adventure), HttpStatus.OK);
    }

    /**
     * Deletes Adventure object associated with passed in id.
     *
     * @param id id associated with Adventure object to be deleted.
     * @return No content status code
     */
    @DeleteMapping("{id}")
    public ResponseEntity deleteAdventure(@PathVariable int id) {
        service.deleteAdventure(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
