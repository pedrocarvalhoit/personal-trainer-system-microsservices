package com.carvalho.pts_api_perimetry_assessment.service.impl;

import com.carvalho.pts_api_perimetry_assessment.adapter.Adapter;
import com.carvalho.pts_api_perimetry_assessment.dto.PerimetryAssessmentDto;
import com.carvalho.pts_api_perimetry_assessment.entity.PerimetryAssessmentEntity;
import com.carvalho.pts_api_perimetry_assessment.repository.PerimetryAssessmentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PerimetryAssessmentServiceImplTest {

    @Mock
    private PerimetryAssessmentRepository repository;

    @Mock
    private Adapter adapter;

    @InjectMocks
    private PerimetryAssessmentServiceImpl service;

    private PerimetryAssessmentEntity perimetryAssessmentEntity;
    private PerimetryAssessmentDto perimetryAssessmentDto;

    @BeforeEach
    public void setup(){
        perimetryAssessmentEntity = new PerimetryAssessmentEntity();
        perimetryAssessmentEntity.setId(1L);
        perimetryAssessmentEntity.setCreatedAt(LocalDateTime.now());
        perimetryAssessmentEntity.setShoulder(120.0);
        perimetryAssessmentEntity.setTorax(90.0);
        perimetryAssessmentEntity.setWaist(79.5);
        perimetryAssessmentEntity.setAbdomen(75.0);
        perimetryAssessmentEntity.setHip(95.0);
        perimetryAssessmentEntity.setRightArm(35.0);
        perimetryAssessmentEntity.setLeftArm(35.0);
        perimetryAssessmentEntity.setRightThigh(50.0);
        perimetryAssessmentEntity.setLeftThigh(50.0);
        perimetryAssessmentEntity.setRightLeg(40.0);
        perimetryAssessmentEntity.setLeftLeg(40.0);

        perimetryAssessmentDto = PerimetryAssessmentDto.builder()
                .id(1l)
                .createdAt(LocalDateTime.now())
                .shoulder(120.0)
                .torax(90.0)
                .waist(79.5)
                .abdomen(75.0)
                .hip(95.0)
                .rightArm(35.0)
                .leftArm(35.0)
                .rightThigh(50.0)
                .leftThigh(50.0)
                .rightLeg(40.0)
                .leftLeg(40.0)
                .build();
    }

    PerimetryAssessmentServiceImplTest() {
    }


    @Test
    void delete() {
        when(repository.findById(1L)).thenReturn(Optional.of(perimetryAssessmentEntity));

        service.delete(1L);

        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).delete(perimetryAssessmentEntity);
    }

    @Test
    void deleteNotFound(){
        when(repository.findById(1l)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.delete(1L));
        verify(repository, times(1)).findById(1l);
    }

}