package com.foroclone.foroclone.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.foroclone.foroclone.domain.dto.CommunityDto;
import com.foroclone.foroclone.domain.entities.CommunityEntity;
import com.foroclone.foroclone.mappers.Mapper;
import com.foroclone.foroclone.services.CommunityService;

@RestController
public class CommunityController {
    
    private CommunityService communityService;

    private Mapper<CommunityEntity, CommunityDto> communityMapper;

    public CommunityController(CommunityService communityService, Mapper<CommunityEntity, CommunityDto> communityMapper) {
        this.communityService = communityService;
        this.communityMapper = communityMapper;

    }

    @PostMapping(path = "/communities")
    public ResponseEntity<CommunityDto> createCommunity(@RequestBody CommunityDto community) {
        CommunityEntity communityEntity = communityMapper.mapFrom(community);
        CommunityEntity savedCommunityEntity = communityService.save(communityEntity);
        return new ResponseEntity<>(communityMapper.mapTo(savedCommunityEntity), HttpStatus.CREATED);
    }

    @GetMapping(path = "/communities")
    public List<CommunityDto> listCommunitys() {
        List<CommunityEntity> communitys = communityService.findAll();
        return communitys.stream()
                .map(communityMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/communities/{id}")
    public ResponseEntity<CommunityDto> getCommunity(@PathVariable() Long id) {
        Optional<CommunityEntity> foundCommunity = communityService.findOne(id);
        return foundCommunity.map(communityEntity -> {
            CommunityDto communityDto = communityMapper.mapTo(communityEntity);
            return new ResponseEntity<>(communityDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @GetMapping("/communities/by-user/{userId}")
    public ResponseEntity<List<CommunityDto>> getCommunitiesByUser(@PathVariable Long userId) {
        List<CommunityEntity> communities = communityService.findByCreatorId(userId);

        if (communities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<CommunityDto> response = communities.stream()
                .map(communityMapper::mapTo)
                .toList();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PutMapping(path = "/communities/{id}")
    public ResponseEntity<CommunityDto> fullUpdateCommunity(
            @PathVariable() Long id,
            @RequestBody CommunityDto communityDto) {

        if(!communityService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        communityDto.setId(id);
        CommunityEntity communityEntity = communityMapper.mapFrom(communityDto);
        CommunityEntity savedCommunityEntity = communityService.save(communityEntity);
        return new ResponseEntity<>(
                communityMapper.mapTo(savedCommunityEntity),
                HttpStatus.OK);
    }

    @PatchMapping(path = "/communities/{id}")
    public ResponseEntity<CommunityDto> partialUpdate(
            @PathVariable() Long id,
            @RequestBody CommunityDto communityDto
    ) {
        if(!communityService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        CommunityEntity communityEntity = communityMapper.mapFrom(communityDto);
        CommunityEntity updatedCommunity = communityService.partialUpdate(id, communityEntity);
        return new ResponseEntity<>(
                communityMapper.mapTo(updatedCommunity),
                HttpStatus.OK);
    }

    @DeleteMapping(path = "/communities/{id}")
    public ResponseEntity<CommunityEntity> deleteCommunity(@PathVariable() Long id) {
        communityService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
