package net.musecom.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.musecom.backend.entity.MyTimelines;
import net.musecom.backend.service.MyTimelinesService;

@RestController
@CrossOrigin(origins = "/api/mytimelines")
@RequestMapping("/api/mytimelines")
public class MyTimelinesController {

    @Autowired
    private MyTimelinesService myTimelinesService;

    @GetMapping
    public ResponseEntity<List<MyTimelines>> getAllMyTimelines(){
        List<MyTimelines> myTimelines = myTimelinesService.getAllMyTimelines();
        return ResponseEntity.ok(myTimelines);
    }
}
