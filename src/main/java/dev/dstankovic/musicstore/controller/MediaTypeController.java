package dev.dstankovic.musicstore.controller;

import dev.dstankovic.musicstore.service.MediaTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mediatypes")
public class MediaTypeController {

    private MediaTypeService mediaTypeService;

    public MediaTypeController(MediaTypeService mediaTypeService) {

        this.mediaTypeService = mediaTypeService;
    }

    @GetMapping("/list")
    public String listMediaTypes(Model model) {

        model.addAttribute("mediatypes", mediaTypeService.findAll());

        return "list-media-types";
    }
}
