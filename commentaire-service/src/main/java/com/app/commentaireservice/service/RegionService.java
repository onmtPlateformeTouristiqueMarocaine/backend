package com.app.commentaireservice.service;

import com.app.commentaireservice.dto.RegionRequest;
import com.app.commentaireservice.dto.RegionResponse;
import com.app.commentaireservice.model.Region;
import com.app.commentaireservice.repository.RegionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;

    public Region createRegion(RegionRequest regionRequest) {
        Region region = Region.builder()
                .ville(regionRequest.getVille())
                .imageUrl(regionRequest.getImageUrl())
                .build();
        log.info("Region is saved");
        return regionRepository.saveAndFlush(region);
    }
    public List<RegionResponse> getAllRegions() {
        List<Region> regions = regionRepository.findAll();
        return regions.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    private RegionResponse convertToResponse(Region region) {
        return RegionResponse.builder()
                .id(region.getId())
                .ville(region.getVille())
                .imageUrl(region.getImageUrl())
                .build();
    }
    public boolean regionExists(Long regionId) {
        return regionRepository.existsById(regionId);
    }


    public Region getRegionById(Long regionId) {
        Optional<Region> regionOptional = regionRepository.findById(regionId);
        return regionOptional.orElse(null);
    }
}
