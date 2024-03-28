package com.app.commentaireservice.controller;

import com.app.commentaireservice.dto.CommentaireRequest;
import com.app.commentaireservice.dto.CommentaireResponse;
import com.app.commentaireservice.model.Commentaire;
import com.app.commentaireservice.model.Region;
import com.app.commentaireservice.service.CommentaireService;
import com.app.commentaireservice.service.RegionService;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/commentaire")
public class CommentaireController {

    @Autowired
    private CommentaireService commentaireService;

    @Autowired
    private RegionService regionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createCommentaire(@RequestBody CommentaireRequest commentaireRequest) {
         Region region = regionService.getRegionById(commentaireRequest.getRegionId());

        if (region != null) {
            Commentaire commentaire = commentaireService.createCommentaire(commentaireRequest, region);
            if (commentaire != null) {
                return ResponseEntity.ok(commentaire);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create commentaire");
            }
        } else {
            return ResponseEntity.badRequest().body("Region with ID " + commentaireRequest.getRegionId() + " does not exist");
        }
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CommentaireResponse> getAllCommentaire(){
        return commentaireService.getAllCommentaire();
    }
}
