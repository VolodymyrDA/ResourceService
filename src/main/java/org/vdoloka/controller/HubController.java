package org.vdoloka.controller;

import lombok.RequiredArgsConstructor;
import org.vdoloka.entity.Location;
import org.vdoloka.entity.User;
import org.vdoloka.repository.impl.LocationsRepositoryImpl;
import org.vdoloka.service.impl.UsersServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class HubController {
    private final UsersServiceImpl usersService;
    private final LocationsRepositoryImpl locationsRepository;

    @GetMapping(path = "/hub/{hubId}")
    public String viewProfile(@PathVariable int hubId, Model model) {
        List<Location> locations = locationsRepository.getLocations();
        model.addAttribute("locations", locations);
        User user = usersService.findByUserID(hubId);
        model.addAttribute("hub", user);
        return "hub";
    }
}