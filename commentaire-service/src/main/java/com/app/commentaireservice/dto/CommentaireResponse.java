package com.app.commentaireservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentaireResponse {
    private Long id ;
    private String content;
    private userDto user;
    private Long regionId;
}
