package com.app.commentaireservice.model;

import jakarta.persistence.*;
import lombok.*;
@Builder
@Data
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Commentaire {
    @Id
    @GeneratedValue
    private Long id ;
    private String content;
    private Long userId;
    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;


}
