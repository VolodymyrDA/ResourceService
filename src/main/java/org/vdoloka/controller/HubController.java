package org.vdoloka.controller;

import lombok.RequiredArgsConstructor;
import org.vdoloka.entity.Location;
import org.vdoloka.entity.User;
import org.vdoloka.service.NomenclatureService;
import org.vdoloka.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class HubController {
    private final UsersService usersService;
    private final NomenclatureService nomenclatureService;

    @GetMapping(path = "/hub/{hubId}")
    public String viewProfile(@PathVariable int hubId, Model model) {
        List<Location> locations = nomenclatureService.getLocations();
        model.addAttribute("locations", locations);
        User user = usersService.findByUserID(hubId);
        model.addAttribute("hub", user);
        return "hub";
    }
}