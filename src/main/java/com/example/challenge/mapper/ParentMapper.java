package com.example.challenge.mapper;

import com.example.challenge.domain.ParentEntity;
import com.example.challenge.dto.ParentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ChildMapper.class)
public interface ParentMapper {

    ParentDTO parentToParentDTO(ParentEntity parentEntity);

    @Mapping(source = "children", target = "children")
    ParentEntity parentDTOToParent(ParentDTO parentDTO);
}
