package dev.dstankovic.musicstore.controller;

import dev.dstankovic.musicstore.entity.MediaType;
import dev.dstankovic.musicstore.service.MediaTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

        return "mediatypes/list-media-types";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {

        MediaType mediaType = new MediaType();
        model.addAttribute("mediatype", mediaType);

        return "mediatypes/media-type-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("mediatypeId") int id, Model model) {

        MediaType mediaType = mediaTypeService.findById(id);
        model.addAttribute("mediatype", mediaType);

        return "mediatypes/media-type-form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("mediatype") MediaType mediaType, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "mediatypes/media-type-form";
        } else {
            mediaTypeService.save(mediaType);
        }

        return "redirect:/mediatypes/list";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("mediatypeId") int id) {

        mediaTypeService.deleteById(id);

        return "redirect:/mediatypes/list";
    }
}
