package com.example.challenge.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {
    private final Class entityClass;
    private final Long entityId;

    public EntityNotFoundException(Class entityClass, Long entityId) {
        super("Entity of type " + entityClass.getName() + " and id " + entityId + " could not be found.");
        this.entityClass = entityClass;
        this.entityId = entityId;
    }

}