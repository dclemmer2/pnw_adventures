package edu.greenriver.sdev.myspringproject.controllers;

import edu.greenriver.sdev.myspringproject.models.Adventure;
import edu.greenriver.sdev.myspringproject.models.User;
import edu.greenriver.sdev.myspringproject.services.AdventureService;
import edu.greenriver.sdev.myspringproject.services.LoginService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * Controller class for site.
 *
 * @author Dana Clemmer
 * @version 1.0
 * 10/6/21
 */
@Controller
@RequestMapping("/pnwAdventures") //all routes in controller start w "adventures"
public class AdventureController {

    //access the service layer using dependency injection
    private final AdventureService service;
    private final LoginService loginService;

    /**
     * Constructor that instantiates the services.
     *
     * @param service      passed in adventure service
     * @param loginService passed in login service
     */
    public AdventureController(AdventureService service, LoginService loginService) {
        this.service = service;
        this.loginService = loginService;
    }

    /**
     * Returns the index page.
     *
     * @return index view
     */
    @RequestMapping({"", "/", "/index", "/index.html"}) //the http routes
    public String index() {
        //gather up data, perform logic, save variables for the UI

        return "pages/index"; //return the name of the page (template, view) to load
    }

    /**
     * Returns a page with all information on a single Adventure object.
     *
     * @param model - model
     * @param id    - passed in id of an Adventure object
     * @return individual-adventure view
     */
    @RequestMapping("/individual/{id}")
    public String individualElement(Model model, @PathVariable int id) {
        model.addAttribute("adventure", service.adventureById(id));
        return "pages/individual-adventure";
    }

    /**
     * Returns a page with data of all Adventure objects associated with User currently in session (logged in).
     *
     * @return summary view
     */
    @RequestMapping("/summary")
    public String summary(Model model, Principal principal) {
        String username = principal.getName();
        model.addAttribute("adventures", service.allUserAdventures(username));
        return "pages/summary";
    }

    /**
     * Returns a page with a form to create a new Adventure object.
     *
     * @param model - model
     * @return form view
     */
    @GetMapping("/createAdventure")
    public String loadForm(Model model) {
        //pass an empty object to a form
        Adventure adventure = new Adventure(0, "", "", "", false,
                false, false, false, "", "~/images/mistyroad.jpg", null, null);
        model.addAttribute("adventure", adventure);
        model.addAttribute("regions", service.getRegions());
        model.addAttribute("button", "Create Adventure");
        return "pages/form";
    }

    /**
     * Returns a page with a form filled out with an existing Adventure object to edit.
     *
     * @param model - model
     * @param id    - passed in id of an Adventure object
     * @return form view
     */
    @GetMapping("/edit/{id}")
    public String loadFormWithRecord(Model model, @PathVariable int id) {
        model.addAttribute("adventure", service.adventureById(id));
        model.addAttribute("regions", service.getRegions());
        model.addAttribute("button", "Update Adventure");

        return "pages/form";
    }

    /**
     * Saves the newly created or updated Adventure object and reroutes to the summary page.
     *
     * @param adventure - passed in newly created or updated Adventure object
     * @return summary view that includes newly created or updated Adventure object
     */
    @PostMapping("/form")
    public String handleForm(@ModelAttribute Adventure adventure, Principal principal) {
        String username = principal.getName();
        User user = loginService.loadUser(username);
        adventure.setUser(user);
        service.saveAdventure(adventure);
        return "redirect:summary";
    }

    /**
     * Deletes an Adventure object.
     *
     * @param id - passed in id of an Adventure object
     * @return summary view
     */
    @RequestMapping("/delete/{id}")
    public String deleteRecord(@PathVariable int id) {
        service.deleteAdventure(id);
        return "redirect:../summary";
    }

    /**
     * Returns the adventure ideas page.
     *
     * @return ideas view
     */
    @RequestMapping("/ideas")
    public String ideas() {
        return "pages/ideas";
    }

    /**
     * Returns the consumer page.
     *
     * @return consumer view
     */
    @RequestMapping("/consumer")
    public String consumer() {
        return "pages/consumer";
    }

    /**
     * Returns the admin dashboard page.
     *
     * @return admin view
     */
    @RequestMapping("/admin/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("userCount", loginService.numUsers());
        return "pages/dashboard";
    }

    /**
     * Returns the login page.
     *
     * @return login view
     */
    @RequestMapping("/login")
    public String login(Model model, @RequestParam(required = false) String error) {
        if (error != null) {
            model.addAttribute("error", "Username/password not recognized");
        }
        return "pages/login";
    }

    /**
     * Returns a page with a register form to create a new account.
     *
     * @param model - model
     * @return register view
     */
    @GetMapping("/register")
    public String loadRegister(Model model) {
        //pass an empty object to a form
        User user = new User(0, "", "", "", "", "", "", null, null);
        model.addAttribute("user", user);
        return "pages/register";
    }

    /**
     * Saves new User object to db and redirects to summary page.
     *
     * @param user - passed in newly created User object
     * @return summary view that includes newly created or updated User object
     */
    @PostMapping("/registerAccount")
    public String handleRegister(@ModelAttribute User user) {
        loginService.saveUser(user);
        return "redirect:summary";
    }

    /**
     * Redirects user after login based on user roles.
     *
     * @param request http request
     * @return page based on user role
     */
    @RequestMapping("/default")
    public String defaultAfterLogin(HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN")) {
            return "redirect:admin/dashboard";
        }
        return "redirect:summary";
    }
}
