package com.app.commentaireservice.controller;

import com.app.commentaireservice.dto.RegionRequest;
import com.app.commentaireservice.dto.RegionResponse;
import com.app.commentaireservice.model.Region;
import com.app.commentaireservice.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/regions")
@CrossOrigin("*")
public class RegionController {
    @Autowired
    private RegionService regionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Region createRegion(@RequestBody RegionRequest regionRequest){
        return regionService.createRegion(regionRequest);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RegionResponse> getAllRegions(){
        return regionService.getAllRegions();
    }

}
