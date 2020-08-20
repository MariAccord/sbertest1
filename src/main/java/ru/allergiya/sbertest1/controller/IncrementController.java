package ru.allergiya.sbertest1.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.allergiya.sbertest1.service.IncrementService;

@RestController
public class IncrementController {
    private static Logger LOG = LoggerFactory.getLogger(IncrementController.class);
    private IncrementService incrementService;

    public IncrementController(IncrementService incrementService) {
        this.incrementService = incrementService;
    }

    @GetMapping("/increment")
    public ResponseEntity<String> incrementNumbers(@RequestParam String numbers){
        LOG.info("increment request got, numbers : {}", numbers);
        try {
            return new ResponseEntity<>(incrementService.increment(numbers), HttpStatus.OK);
        } catch (RuntimeException e) {
            LOG.error("Error invoking incrementNumbers {}", e.getCause());
            return new ResponseEntity<>(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        }
    }

    @PostMapping(value = "/setdiff")
    public ResponseEntity<?> setDiffNumber(@RequestBody int diff){
        LOG.info("Got setdiff request, new diff value : {}", diff);
        incrementService.setDiff(diff);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
