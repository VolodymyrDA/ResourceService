package org.vdoloka.controller;

import lombok.RequiredArgsConstructor;
import org.vdoloka.dto.UserDTO;
import org.vdoloka.dto.mapper.UserMapper;
import org.vdoloka.entity.Location;
import org.vdoloka.service.NomenclatureService;
import org.vdoloka.service.UsersService;
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
    private final UsersService usersService;
    private final NomenclatureService nomenclatureService;

    @GetMapping("/profile")
    public String viewProfile(Model model) {
        List<Location> locations = nomenclatureService.getLocations();
        model.addAttribute("locations", locations);
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@Valid UserDTO userDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getFieldErrors());
            List<Location> locations = nomenclatureService.getLocations();
            model.addAttribute("locations", locations);
            return "profile";
        }
        usersService.updateUser(UserMapper.INSTANCE.toEntity(userDTO));
        model.addAttribute("message", "Update Success");
        return "login";
    }
}