package com.example.challenge.service.impl;

import com.example.challenge.domain.ParentEntity;
import com.example.challenge.dto.ParentDTO;
import com.example.challenge.exception.EntityNotFoundException;
import com.example.challenge.mapper.ParentMapper;
import com.example.challenge.repository.ParentRepository;
import com.example.challenge.service.ParentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ParentServiceImpl implements ParentService {

    private final ParentRepository parentRepository;
    private final ParentMapper parentMapper;

    @Transactional
    public ParentDTO createParent(ParentDTO parentDTO) {
        log.info("Calling ParentServiceImpl.createParent for: {}", parentDTO);
        ParentEntity parentEntity = parentMapper.parentDTOToParent(parentDTO);
        parentEntity.getChildren().forEach(childEntity -> childEntity.setParentEntity(parentEntity));
        ParentEntity savedParent = parentRepository.save(parentEntity);
        return parentMapper.parentToParentDTO(savedParent);
    }

    @CachePut(value = "parents", key = "#result.id")
    @Transactional
    public ParentDTO updateParent(ParentDTO parentDTO) {
        log.info("Calling ParentServiceImpl.updateParent for: {}", parentDTO);
        ParentEntity existingParent = parentRepository.findById(parentDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException(ParentEntity.class, parentDTO.getId()));

        existingParent = parentMapper.parentDTOToParent(parentDTO);
        ParentEntity finalExistingParent1 = existingParent;
        existingParent.getChildren().forEach(childEntity -> childEntity.setParentEntity(finalExistingParent1));

        ParentEntity updatedParent = parentRepository.saveAndFlush(existingParent);
        return parentMapper.parentToParentDTO(updatedParent);

    }

    @Cacheable(value = "parents", key = "#parentId")
    public ParentDTO getParentById(Long parentId) {
        log.info("Calling ParentServiceImpl.getParentById for parentId: {}", parentId);
        ParentEntity existingParent = parentRepository.findById(parentId)
                .orElseThrow(() -> new EntityNotFoundException(ParentEntity.class, parentId));

        return parentMapper.parentToParentDTO(existingParent);
    }
}
