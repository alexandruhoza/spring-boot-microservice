package com.example.challenge.mapper;

import com.example.challenge.domain.ChildEntity;
import com.example.challenge.dto.ChildDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChildMapper {

    ChildDTO childToChildDTO(ChildEntity childEntity);

    ChildEntity childDTOToChild(ChildDTO childDTO);
}
