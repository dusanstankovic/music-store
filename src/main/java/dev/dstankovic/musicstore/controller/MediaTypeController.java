package dev.dstankovic.musicstore.controller;

import dev.dstankovic.musicstore.entity.MediaType;
import dev.dstankovic.musicstore.report.GenerateMediaTypesListReport;
import dev.dstankovic.musicstore.service.MediaTypeService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.util.List;

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
        }

        mediaTypeService.save(mediaType);

        return "redirect:/mediatypes/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("mediatypeId") int id) {

        mediaTypeService.deleteById(id);

        return "redirect:/mediatypes/list";
    }

    @GetMapping(value = "/report", produces = org.springframework.http.MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> mediaTypeReport() {

        List<MediaType> mediaTypes = mediaTypeService.findAll();

        ByteArrayInputStream bis = GenerateMediaTypesListReport.mediaTypesReport(mediaTypes);

        var headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=media_types_report.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(org.springframework.http.MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}
