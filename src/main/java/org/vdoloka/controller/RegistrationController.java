package org.vdoloka.controller;

import lombok.RequiredArgsConstructor;
import org.vdoloka.dto.UserDTO;
import org.vdoloka.dto.mapper.UserMapper;
import org.vdoloka.entity.Location;
import org.vdoloka.repository.impl.LocationsRepositoryImpl;
import org.vdoloka.service.impl.UsersServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class RegistrationController {
    private final UsersServiceImpl usersService;
    private final LocationsRepositoryImpl locationsRepository;

    @GetMapping("/registration")
    public String registration(Model model) {
        List<Location> locations = locationsRepository.getLocations();
        model.addAttribute("locations", locations);
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(UserDTO userDTO, Model model) {
        usersService.addUser(UserMapper.INSTANCE.toEntity(userDTO));
        model.addAttribute("message", "Registration Success");
        return "login";
    }
}