package org.vdoloka.controller;

import org.vdoloka.entity.LocationEntity;
import org.vdoloka.entity.UserEntity;
import org.vdoloka.repository.impl.LocationsRepositoryImpl;
import org.vdoloka.service.impl.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;


@Controller
public class ProfileController {

    private final UsersServiceImpl usersService;
    private final LocationsRepositoryImpl locationsRepository;

    @Autowired
    public ProfileController(UsersServiceImpl usersService, LocationsRepositoryImpl locationsRepository) {
        this.usersService = usersService;
        this.locationsRepository = locationsRepository;
    }

    @GetMapping("/profile")
    public String viewProfile(Model model) {
        List<LocationEntity> locations = locationsRepository.getLocations();
        model.addAttribute("locations", locations);
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@Valid UserEntity userEntity, BindingResult bindingResult, Model model) {
        model.addAttribute("errors", bindingResult.getFieldErrors());
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getFieldErrors());
            List<LocationEntity> locations = locationsRepository.getLocations();
            model.addAttribute("locations", locations);
            return "profile";
        } else {
            usersService.updateUser(userEntity);
            model.addAttribute("message", "Update Success");
            return "login";
        }

    }
}