package com.example.challenge.service;

import com.example.challenge.domain.ParentEntity;
import com.example.challenge.dto.ParentDTO;
import com.example.challenge.exception.handling.ErrorResponse;
import com.example.challenge.exception.handling.GlobalExceptionHandler;
import com.example.challenge.mapper.ParentMapper;
import com.example.challenge.repository.ParentRepository;
import com.example.challenge.service.impl.ParentServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.ActiveProfiles;

import java.util.Objects;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@ActiveProfiles("test")
public class ParentServiceImplTest {

    @Mock
    private ParentRepository parentRepository;

    @Mock
    private ParentMapper parentMapper;

    @InjectMocks
    private ParentServiceImpl parentServiceImpl;

    @InjectMocks
    private GlobalExceptionHandler exceptionHandler;

    @Test
    public void testCreateParent_success() {
        ParentDTO parentDTO = new ParentDTO();
        parentDTO.setName("Test Parent");

        ParentEntity parentEntity = new ParentEntity();
        parentEntity.setName("Test Parent");

        when(parentMapper.parentDTOToParent(parentDTO)).thenReturn(parentEntity);
        when(parentRepository.save(parentEntity)).thenReturn(parentEntity);
        when(parentMapper.parentToParentDTO(parentEntity)).thenReturn(parentDTO);

        ParentDTO result = parentServiceImpl.createParent(parentDTO);

        assertNotNull(result);
        assertEquals(parentDTO.getName(), result.getName());

        verify(parentMapper, times(1)).parentDTOToParent(parentDTO);
        verify(parentRepository, times(1)).save(parentEntity);
        verify(parentMapper, times(1)).parentToParentDTO(parentEntity);
    }

    @Test
    public void testUpdateParent_success() {
        Long parentId = 1L;
        ParentDTO parentDTO = new ParentDTO();
        parentDTO.setId(parentId);
        parentDTO.setName("Updated Parent");
        parentDTO.setVersion(1L);

        ParentEntity existingParent = new ParentEntity();
        existingParent.setId(parentId);
        existingParent.setName("Existing Parent");
        existingParent.setVersion(1L);

        when(parentRepository.findById(parentId)).thenReturn(Optional.of(existingParent));

        ParentEntity updatedParent = new ParentEntity();
        updatedParent.setId(parentId);
        updatedParent.setName("Updated Parent");

        when(parentMapper.parentDTOToParent(parentDTO)).thenReturn(updatedParent);
        when(parentRepository.saveAndFlush(updatedParent)).thenReturn(updatedParent);
        when(parentMapper.parentToParentDTO(updatedParent)).thenReturn(parentDTO);

        ParentDTO result = parentServiceImpl.updateParent(parentDTO);

        assertNotNull(result);
        assertEquals(parentDTO.getName(), result.getName());

        verify(parentRepository, times(1)).findById(parentId);
        verify(parentMapper, times(1)).parentDTOToParent(parentDTO);
        verify(parentRepository, times(1)).saveAndFlush(updatedParent);
        verify(parentMapper, times(1)).parentToParentDTO(updatedParent);
    }


    @Test
    public void testUpdateParent_OptimisticLockingException() {
        Long parentId = 1L;
        ParentDTO parentDTO = new ParentDTO();
        parentDTO.setId(parentId);
        parentDTO.setName("Updated Parent");
        parentDTO.setVersion(1L);

        ParentEntity existingParent = new ParentEntity();
        existingParent.setId(parentId);
        existingParent.setName("Existing Parent");
        existingParent.setVersion(2L);

        when(parentRepository.findById(parentDTO.getId())).thenReturn(Optional.of(existingParent));
        when(parentMapper.parentDTOToParent(parentDTO)).thenReturn(existingParent);

        parentServiceImpl.updateParent(parentDTO);

        ObjectOptimisticLockingFailureException ex = new ObjectOptimisticLockingFailureException("Optimistic locking failure", new Exception());

        // Call the handler method
        ResponseEntity<ErrorResponse> responseEntity = exceptionHandler.handleOptimisticLockingFailureException(ex);

        // Verify the response status code
        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());

        // Verify the error message
        assertEquals("Entity has been updated by another user. Please refresh and try again.",
                Objects.requireNonNull(responseEntity.getBody()).getErrorMessage());
    }
}
