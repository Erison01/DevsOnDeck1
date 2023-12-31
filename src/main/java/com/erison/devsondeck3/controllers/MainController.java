package com.erison.devsondeck3.controllers;

import com.erison.devsondeck3.models.*;
import com.erison.devsondeck3.services.DeveloperService;
import com.erison.devsondeck3.services.OrganizationService;
import com.erison.devsondeck3.services.PositionService;
import com.erison.devsondeck3.services.SkillService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private DeveloperService developerService;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private SkillService skillService;

    @Autowired
    private PositionService positionService;

    @RequestMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/dev/register")
    public String dRegister (Model model , @ModelAttribute ("newDev")Developer newDev, HttpSession session){

    Long loggedDevId  = (Long) session.getAttribute("loggedDevId");
    if(loggedDevId != null){
        return "redirect:/dev/skills";
    }
        model.addAttribute("newDev",new Developer());
        return "devRegister";
    }

    @PostMapping("/dev/register")
    public String devReg(@Valid @ModelAttribute("newDev")Developer newDev, BindingResult result, Model model ,HttpSession session){

        developerService.register(newDev,result);
        if (result.hasErrors()){
        model.addAttribute("logDev",new devLogin());
        return "devRegister";
        }
        session.setAttribute("loggedDevId",newDev.getId());

        return "redirect:/dev/skills";
    }

    @GetMapping("/dev/login")
    public String dlogin(Model model , @ModelAttribute("logDev")Developer logDev,HttpSession session){
    Long loggedDevId = (Long) session.getAttribute("loggedDevId");
    if (loggedDevId != null){
        return "redirect:/dev/dashboard";
    }
        model.addAttribute("logDev",new devLogin());
        return "devLogin";

    }

    @PostMapping("/dev/login")
    public String devLogin(@Valid @ModelAttribute("logDev") devLogin logDev,BindingResult result,Model model, HttpSession session){

        Developer developer = developerService.login(logDev,result);
        if (result.hasErrors()){
            model.addAttribute("newDev",new Developer());
            return "devLogin";
        }
        session.setAttribute("loggedDevId",developer.getId());
        return "redirect:/dev/dashboard";
    }

    @GetMapping("/dev/skills")
    public String showSkillsAndBio(Model model, HttpSession session) {
        Long loggedDevId = (Long) session.getAttribute("loggedDevId");

        if (loggedDevId == null) {
            return "redirect:/dev/register";
        }

        // Get the logged-in developer from the database based on the ID
        Developer loggedInDeveloper = developerService.findById(loggedDevId);

        // Get all available skills from the database
        List<Skill> allSkills = developerService.getAllSkills();

        // Add the developer's skills and shortBio to the model
        model.addAttribute("loggedInDeveloper", loggedInDeveloper);
        model.addAttribute("allSkills", allSkills);

        return "devSkills"; // Redirect to the skills JSP
    }

    @PostMapping("/dev/skills")
    public String updateSkillsAndBio(@RequestParam("skills") List<Long> skillIds,
                                     @RequestParam("shortBio") String shortBio,
                                     @ModelAttribute("loggedInDeveloper") Developer loggedInDeveloper,
                                     BindingResult result, Model model,HttpSession session) {

        Long loggedDevId = (Long) session.getAttribute("loggedDevId");

        if (result.hasErrors()) {
            // If there are validation errors, return to the skills page with the errors displayed
            List<Skill> allSkills = developerService.getAllSkills();
            model.addAttribute("allSkills", allSkills);
            return "devSkills";
        }

        // Retrieve the logged-in developer from the database based on the ID
        Developer existingDeveloper = developerService.findById(loggedDevId);

        // Retrieve the selected skills from the database based on their IDs
        List<Skill> skills = developerService.getSkillsByIds(skillIds);

        // Update the developer's skills and shortBio
        existingDeveloper.setSkills(skills);
        existingDeveloper.setShortBio(shortBio);

        // Save the changes to the database
        developerService.saveDev(existingDeveloper);


        return "redirect:/dev/dashboard";
    }



    @GetMapping("/dev/dashboard")
    public String devDashboard(Model model, HttpSession session) {
        Long loggedDevId = (Long) session.getAttribute("loggedDevId");

        if (loggedDevId == null) {
            return "redirect:/dev/login";
        }

        // Retrieve the logged-in developer from the database based on the ID
        Developer loggedInDeveloper = developerService.findById(loggedDevId);

        // Get the skills of the logged-in developer
        List<Skill> skills = loggedInDeveloper.getSkills();

        // Get all job positions that require any of the skills of the logged-in developer
        List<Position> matchingPositions = positionService.findPositionsBySkills(skills);

        // Add the matching job positions and the logged-in developer's name to the model
        model.addAttribute("matchingPositions", matchingPositions);
        model.addAttribute("loggedInDeveloper", loggedInDeveloper);

        return "devDashboard";
    }

    //apply



    // Edit developer by clicking his name




    @GetMapping("/org/register")
    public String oRegister (Model model , @ModelAttribute ("newOrg") Organization newOrg, HttpSession session){

        Long loggedOrgId  = (Long) session.getAttribute("loggedOrgId");
        if(loggedOrgId != null){
            return "redirect:/org/dashboard";
        }
        model.addAttribute("newOrg",new Organization());
        return "orgRegister";
    }

    @PostMapping("/org/register")
    public String orgReg(@Valid @ModelAttribute("newOrg")Organization newOrg, BindingResult result, Model model ,HttpSession session){

       organizationService.register(newOrg,result);
        if (result.hasErrors()){
            model.addAttribute("logOrg",new orgLogin());
            return "orgRegister";
        }
        session.setAttribute("loggedOrgId",newOrg.getId());

        return "redirect:/org/dashboard";
    }

    @GetMapping("/org/login")
    public String ologin(Model model , @ModelAttribute("logOrg")Organization logOrg,HttpSession session){
        Long loggedOrgId = (Long) session.getAttribute("loggedOrgId");
        if (loggedOrgId != null){
            return "redirect:/org/dashboard";
        }
       model.addAttribute("logOrg",new orgLogin());
        return "orgLogin";

    }

    @PostMapping("/org/login")
    public String orgLogin(@Valid @ModelAttribute("logOrg") orgLogin logOrg,BindingResult result,Model model, HttpSession session){

        Organization organization = organizationService.login(logOrg,result);
        if (result.hasErrors()){

            model.addAttribute("newOrg",new Organization());
            return "orgLogin";
        }
        session.setAttribute("loggedOrgId",organization.getId());
        return "redirect:/org/dashboard";
    }

    @GetMapping("/org/dashboard")
    public String orgDashboard(Model model, HttpSession session) {
        Long loggedOrgId = (Long) session.getAttribute("loggedOrgId");

        if (loggedOrgId == null) {
            return "redirect:/org/login";
        }

        // Retrieve the organization from the database based on the ID
        Organization organization = organizationService.findById(loggedOrgId);

        // Get all job positions related to the organization
        List<Position> positions = positionService.getPositionsByOrganization(organization);

        List<Developer> developers = developerService.allUsers();

        // Add the organization and positions to the model
        model.addAttribute("organization", organization);
        model.addAttribute("positions", positions);
        model.addAttribute("developers", developers);

        return "orgDashboard";
    }



    @GetMapping("/org/jobs/new")
    public String showAddPositionForm(Model model, HttpSession session) {
        Long loggedOrgId = (Long) session.getAttribute("loggedOrgId");

        if (loggedOrgId == null) {
            return "redirect:/org/login";
        }

        // Get all available skills from the database
        List<Skill> allSkills = developerService.getAllSkills();

        // Add the skills to the model
        model.addAttribute("allSkills", allSkills);

        // Add a new Position object to the model
        model.addAttribute("position", new Position());

        return "addPosition";
    }

    @PostMapping("/org/jobs")
    public String addPosition(@Valid @ModelAttribute("position") Position position, BindingResult result, HttpSession session) {
        Long loggedOrgId = (Long) session.getAttribute("loggedOrgId");

        if (loggedOrgId == null) {
            return "redirect:/org/login";
        }


        Organization organization = organizationService.findById(loggedOrgId);

        // Set the organization for the position
        position.setOrganization(organization);

        // Save the new position to the database
        positionService.savePosition(position);


        return "redirect:/org/dashboard";
    }

    @DeleteMapping("/org/jobs/delete/{id}")
    public String deleteJobPosition(@PathVariable("id") Long positionId, HttpSession session) {
        Long loggedOrgId = (Long) session.getAttribute("loggedOrgId");
        if (loggedOrgId == null) {
            return "redirect:/org/login";
        }

        Organization organization = organizationService.findById(loggedOrgId);

        // Check if the organization owns the position before deleting
        Position position = positionService.findPositionByIdAndOrganization(positionId, organization);
        if (position != null) {
            positionService.deletePosition(position);
        }
        return "redirect:/org/dashboard";
    }




    @RequestMapping("/logout")
    public String logout (HttpServletRequest request, HttpSession session ){
        session.invalidate();

        return "redirect:/";
    }
}
