package ru.allergiya.sbertest1.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.allergiya.sbertest1.service.IncrementService;

@RestController
public class IncrementController {
    private IncrementService incrementService;

    public IncrementController(IncrementService incrementService) {
        this.incrementService = incrementService;
    }

    @GetMapping("/increment")
    public ResponseEntity<String> incrementNumbers(@RequestParam String numbers){
        return new ResponseEntity<>(incrementService.increment(numbers), HttpStatus.OK);
    }

    @PostMapping(value = "/setdiff")
    public ResponseEntity<?> setDiffNumber(@RequestBody int diff){
        incrementService.setDiff(diff);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
