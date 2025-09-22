package com.carvalho.pts_api_inicial_assessment.service.impl;

import com.carvalho.pts_api_inicial_assessment.adapter.Adapter;
import com.carvalho.pts_api_inicial_assessment.dto.InicialAssessmentDto;
import com.carvalho.pts_api_inicial_assessment.entity.InicialAssessmentEntity;
import com.carvalho.pts_api_inicial_assessment.repository.InicialAssessmentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InicialAssessmentServiceImplTest {
/**
    @Mock
    private InicialAssessmentRepository repository;

    @Mock
    private Adapter adapter;

    @InjectMocks
    private InicialAssessmentServiceImpl service;

    private InicialAssessmentEntity inicialAssessmentEntity;
    private InicialAssessmentDto inicialAssessmentDto;

    @BeforeEach
    public void setup(){
        inicialAssessmentEntity = new InicialAssessmentEntity();
        inicialAssessmentEntity.setId(1L);
        inicialAssessmentEntity.setCreatedAt(LocalDateTime.now());
        inicialAssessmentEntity.setSedentary(true);
        inicialAssessmentEntity.setSmoker(true);
        inicialAssessmentEntity.setRestingHeartRate(65);
        inicialAssessmentEntity.setMaxHeartRate(185);
        inicialAssessmentEntity.setSystolicBloodPressure(130);
        inicialAssessmentEntity.setDiastolicBloodPressure(75);

        inicialAssessmentDto = InicialAssessmentDto.builder()
                .id(1l)
                .createdAt(LocalDateTime.now())
                .sedentary(true)
                .smoker(true)
                .restingHeartRate(65)
                .systolicBloodPressure(130)
                .diastolicBloodPressure(75)
                .build();
    }

    InicialAssessmentServiceImplTest() {
    }


    @Test
    void saveInicialAssessment() {
        when(adapter.inicialAssessmentModelToEntity(inicialAssessmentDto)).thenReturn(inicialAssessmentEntity);
        when(repository.save(inicialAssessmentEntity)).thenReturn(inicialAssessmentEntity);
        when(adapter.inicialAssessmentEntityToModel(inicialAssessmentEntity)).thenReturn(inicialAssessmentDto);

        InicialAssessmentDto savedInicialAssessment = service.saveInicialAssessment("10L", inicialAssessmentDto);

        assertEquals(inicialAssessmentDto, savedInicialAssessment);
        verify(repository, times(1)).save(inicialAssessmentEntity);
    }

    @Test
    void searchById() {
        when(repository.findById(1L)).thenReturn(Optional.of(inicialAssessmentEntity));
        when(adapter.inicialAssessmentEntityToModel(inicialAssessmentEntity)).thenReturn(inicialAssessmentDto);

        InicialAssessmentDto findedInicialAssessment = service.searchById(1L);

        assertEquals(findedInicialAssessment, inicialAssessmentDto);
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void searchByIdNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.searchById(1L));
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void findAllByAthleteId() {
        InicialAssessmentEntity inicialAssessmentEntity2 = new InicialAssessmentEntity();
        inicialAssessmentEntity2.setId(2L);
        inicialAssessmentEntity2.setCreatedAt(LocalDateTime.now());
        inicialAssessmentEntity2.setSedentary(true);
        inicialAssessmentEntity2.setSmoker(true);
        inicialAssessmentEntity2.setRestingHeartRate(65);
        inicialAssessmentEntity2.setMaxHeartRate(185);
        inicialAssessmentEntity2.setSystolicBloodPressure(130);
        inicialAssessmentEntity2.setDiastolicBloodPressure(75);

        InicialAssessmentDto inicialAssessmentDto2 = InicialAssessmentDto.builder()
                .id(1l)
                .createdAt(LocalDateTime.now())
                .sedentary(true)
                .smoker(true)
                .restingHeartRate(65)
                .systolicBloodPressure(130)
                .diastolicBloodPressure(75)
                .build();

        when(repository.findAll()).thenReturn(List.of(inicialAssessmentEntity, inicialAssessmentEntity2));
        when(adapter.inicialAssessmentEntityToModel(inicialAssessmentEntity)).thenReturn(inicialAssessmentDto);
        when(adapter.inicialAssessmentEntityToModel(inicialAssessmentEntity2)).thenReturn(inicialAssessmentDto2);

        List<InicialAssessmentDto> list = service.findAllByAthleteId(a);

        System.out.println(list);

        assertTrue(list.contains(inicialAssessmentDto));
        assertTrue(list.contains(inicialAssessmentDto2));
        assertEquals(2, list.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void update() {
        InicialAssessmentDto updatedDto = InicialAssessmentDto.builder()
                .id(1l)
                .createdAt(inicialAssessmentEntity.getCreatedAt())
                .sedentary(false)
                .smoker(false)
                .restingHeartRate(50)
                .systolicBloodPressure(50)
                .diastolicBloodPressure(50)
                .build();

        when(repository.findById(1L)).thenReturn(Optional.of(inicialAssessmentEntity));
        when(repository.save(inicialAssessmentEntity)).thenReturn(inicialAssessmentEntity);
        when(adapter.inicialAssessmentEntityToModel(inicialAssessmentEntity)).thenReturn(updatedDto);

        InicialAssessmentDto result = service.update(1L, inicialAssessmentDto);

        System.out.println(result);

        assertNotNull(result);
        assertEquals(updatedDto.isSedentary(), result.isSedentary());

        verify(repository, times(1)).save(inicialAssessmentEntity);

    }

    @Test
    void delete() {
        when(repository.findById(1L)).thenReturn(Optional.of(inicialAssessmentEntity));

        service.delete(1L);

        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).delete(inicialAssessmentEntity);
    }

    @Test
    void deleteNotFound(){
        when(repository.findById(1l)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.delete(1L));
        verify(repository, times(1)).findById(1l);
    }
    */
}