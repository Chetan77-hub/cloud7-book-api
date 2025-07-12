package com.example.bookapi;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
public class BookController {

    @CrossOrigin(origins = "*") // Enable CORS if frontend is on a different domain
    @GetMapping("/data.json")
    public ResponseEntity<String> getBookData() {
        try {
            // Adjust the path to match your mounted disk location
            String json = Files.readString(Paths.get("/mnt/data/data.json"));
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json");
            return ResponseEntity.ok().headers(headers).body(json);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("{\"error\": \"Could not read data.json\"}");
        }
    }

    // Simple health check endpoint for load balancer
    @GetMapping("/healthz")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("OK");
    }
}

