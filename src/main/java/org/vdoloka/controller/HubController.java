package org.vdoloka.controller;

import org.vdoloka.entity.LocationEntity;
import org.vdoloka.entity.UserEntity;
import org.vdoloka.repository.impl.LocationsRepositoryImpl;
import org.vdoloka.service.impl.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class HubController {

    private final UsersServiceImpl usersService;
    private final LocationsRepositoryImpl locationsRepository;

    @Autowired
    public HubController(UsersServiceImpl usersService, LocationsRepositoryImpl locationsRepository) {
        this.usersService = usersService;
        this.locationsRepository = locationsRepository;
    }

    @GetMapping(path = "/hub/{hubId}")
    public String viewProfile(@PathVariable(value = "hubId") int hubId, Model model) {
        List<LocationEntity> locations = locationsRepository.getLocations();
        model.addAttribute("locations", locations);
        UserEntity userEntity = usersService.findByUserID(hubId);
        model.addAttribute("hub", userEntity);
        return "hub";
    }
}