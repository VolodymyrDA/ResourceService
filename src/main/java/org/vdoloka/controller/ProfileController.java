package org.vdoloka.controller;

import lombok.RequiredArgsConstructor;
import org.vdoloka.entity.Location;
import org.vdoloka.entity.User;
import org.vdoloka.repository.impl.LocationsRepositoryImpl;
import org.vdoloka.service.impl.UsersServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class ProfileController {
    private final UsersServiceImpl usersService;
    private final LocationsRepositoryImpl locationsRepository;

    @GetMapping("/profile")
    public String viewProfile(Model model) {
        List<Location> locations = locationsRepository.getLocations();
        model.addAttribute("locations", locations);
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getFieldErrors());
            List<Location> locations = locationsRepository.getLocations();
            model.addAttribute("locations", locations);
            return "profile";
        }
        usersService.updateUser(user);
        model.addAttribute("message", "Update Success");
        return "login";
    }
}