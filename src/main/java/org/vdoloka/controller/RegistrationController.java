package org.vdoloka.controller;

import lombok.RequiredArgsConstructor;
import org.vdoloka.dto.UserDTO;
import org.vdoloka.dto.mapper.UserMapper;
import org.vdoloka.entity.Location;
import org.vdoloka.service.NomenclatureService;
import org.vdoloka.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class RegistrationController {
    private final UsersService usersService;
    private final NomenclatureService nomenclatureService;

    @GetMapping("/registration")
    public String registration(Model model) {
        List<Location> locations = nomenclatureService.getLocations();
        model.addAttribute("locations", locations);
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(UserDTO userDTO, Model model) {
        usersService.createUser(UserMapper.INSTANCE.toEntity(userDTO));
        model.addAttribute("message", "Registration Success");
        return "login";
    }
}