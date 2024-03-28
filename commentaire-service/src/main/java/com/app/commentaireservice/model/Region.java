package com.app.commentaireservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Builder
@Data
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Region {
    @Id
    @GeneratedValue
    private Long id;

    private String ville;

    private String imageUrl;
    @OneToMany(mappedBy = "region")
    private List<Commentaire> commentaires;
}
