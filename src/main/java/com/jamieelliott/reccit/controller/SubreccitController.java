package com.jamieelliott.reccit.controller;

import com.jamieelliott.reccit.dto.SubreccitDto;
import com.jamieelliott.reccit.service.SubreccitService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subreccit")
@AllArgsConstructor
@Slf4j
public class SubreccitController {

    private final SubreccitService subreccitService;

    @PostMapping
    public ResponseEntity<SubreccitDto> createSubreddit(@RequestBody SubreccitDto subredditDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(subreccitService.save(subredditDto));
    }

    @GetMapping
    public ResponseEntity<List<SubreccitDto>> getAllSubreddits() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(subreccitService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubreccitDto> getSubreddit(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(subreccitService.getSubreccit(id));
    }

}
