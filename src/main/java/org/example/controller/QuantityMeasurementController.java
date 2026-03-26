package org.example.controller;

import org.example.dto.QuantityDTO;
import org.example.model.QuantityMeasurementEntity;
import org.example.service.IQuantityMeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quantities")
public class QuantityMeasurementController {

    @Autowired
    private IQuantityMeasurementService service;

    // CREATE
    @PostMapping("/add")
    public QuantityDTO add(@RequestBody List<QuantityDTO> list) {
        return service.add(list.get(0), list.get(1));
    }

    // READ ALL
    @GetMapping
    public List<QuantityMeasurementEntity> getAll() {
        return service.getAll();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public QuantityMeasurementEntity getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Deleted successfully";
    }

    @PostMapping("/compare")
    public boolean compare(@RequestBody List<QuantityDTO> list) {
        return service.compare(list.get(0), list.get(1));
    }

    @PostMapping("/convert")
    public QuantityDTO convert(@RequestBody QuantityDTO dto,
                               @RequestParam String target) {
        return service.convert(dto, target);
    }

    @PutMapping("/{id}")
    public QuantityMeasurementEntity update(@PathVariable Long id,
                                            @RequestBody QuantityMeasurementEntity entity) {
        return service.update(id, entity);
    }

}