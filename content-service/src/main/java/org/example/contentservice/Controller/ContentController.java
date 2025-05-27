package org.example.contentservice.Controller;


import org.example.content.Service.ContentService;

import org.example.contentservice.ContentDTO.ContentResponseDTO;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/content")
public class ContentController {
    private final ContentService contentService;

    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @GetMapping("/{id}")
    public ContentResponseDTO getContent(@PathVariable Long id) {
        return contentService.getFullContent(id);
    }
}
