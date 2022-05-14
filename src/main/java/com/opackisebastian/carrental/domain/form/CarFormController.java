package com.opackisebastian.carrental.domain.form;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("form/")
@RequiredArgsConstructor
public class CarFormController {

    private CarFormService carFormService;

    private final ModelMapper modelMapper = new ModelMapper();

    @GetMapping
    public ResponseEntity<List<FormResponseDTO>> retrieveAllForms() {
        List<Form> allForms = carFormService.getAllForms();

        return ResponseEntity.ok(convertFromEntity(allForms));
    }

    private List<FormResponseDTO> convertFromEntity(List<Form> form) {
        List<FormResponseDTO> result = new ArrayList<>();

        form.forEach(f -> result.add(modelMapper.map(f, FormResponseDTO.class)));

        return result;
    }
}
