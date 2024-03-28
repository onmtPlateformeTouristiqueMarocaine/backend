package com.app.commentaireservice.service;

import com.app.commentaireservice.dto.CommentaireRequest;
import com.app.commentaireservice.dto.CommentaireResponse;
import com.app.commentaireservice.dto.userDto;
import com.app.commentaireservice.model.Commentaire;
import com.app.commentaireservice.model.Region;
import com.app.commentaireservice.repository.CommentaireRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;
@Service
@Slf4j
public class CommentaireService {

    @Autowired
    private CommentaireRepository commentaireRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Commentaire createCommentaire(CommentaireRequest commentaireRequest, Region region) {
        Commentaire commentaire = Commentaire.builder()
                .content(commentaireRequest.getContent())
                .userId(commentaireRequest.getUser())
                .region(region) 
                .build();
        log.info("Commentaire is saved");
        return commentaireRepository.saveAndFlush(commentaire);
    }

    public List<CommentaireResponse> getAllCommentaire() {
        List<Commentaire> commentaires = commentaireRepository.findAll();
        return commentaires.stream()
                .map(this::mapToCommentaireResponse)
                .collect(Collectors.toList());
    }

    private CommentaireResponse mapToCommentaireResponse(Commentaire commentaire) {
        String userServiceUrl = "http://localhost:3000/api/user";
        ResponseEntity<userDto> responseEntity = restTemplate.getForEntity(userServiceUrl + "/{username}", userDto.class, commentaire.getUserId());
        userDto username = responseEntity.getBody();

        return CommentaireResponse.builder()
                .id(commentaire.getId())
                .content(commentaire.getContent())
                .user(username)
                .build();
    }
}
