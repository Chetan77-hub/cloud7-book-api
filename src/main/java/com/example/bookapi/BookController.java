package com.example.bookapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import java.nio.file.Files;
import java.nio.file.Paths;



@RestController
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @CrossOrigin(origins = "*") // Enable CORS if frontend is on a different domain
    @GetMapping("/api/data.json")
    public ResponseEntity<String> getBookData() {
        try {
            // Adjust the path to match your mounted disk location
            String json = Files.readString(Paths.get("/mnt/data/data.json"));
            logger.debug("Successfully read data.json content");

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json");
            return ResponseEntity.ok().headers(headers).body(json);
        } catch (Exception e) {
            logger.error("Error reading data.json", e);
            return ResponseEntity.status(500).body("{\"error\": \"Could not read data.json\"}");
        }
    }

    // Simple health check endpoint for load balancer
    @GetMapping("/healthz")
    public ResponseEntity<String> healthCheck() {
        System.out.println("Health check done");
        return ResponseEntity.ok("OK");
    }
}
