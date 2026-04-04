package org.example.qmaservice.controller;

import org.example.qmaservice.dto.QuantityDTO;
import org.example.qmaservice.model.QuantityMeasurementEntity;
import org.example.qmaservice.service.IQuantityMeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/quantities")
public class QuantityMeasurementController {

    @Autowired
    private IQuantityMeasurementService service;

    @PostMapping("/compare")
    public Map<String, Object> compare(@RequestBody List<QuantityDTO> list) {
        boolean equal = service.compare(list.get(0), list.get(1));
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("result", equal ? "Equal ✓" : "Not Equal ✗");
        response.put("equal", equal);
        return response;
    }

    // FIX: reads ?operation=Add/Subtract/Divide and routes to correct method
    @PostMapping("/add")
    public QuantityDTO add(
            @RequestBody List<QuantityDTO> list,
            @RequestParam(defaultValue = "Add") String operation,
            Principal principal) {

        String username = resolveUsername(principal);
        String op = operation.toUpperCase();

        switch (op) {
            case "SUBTRACT": return service.subtract(list.get(0), list.get(1), username);
            case "DIVIDE":   return service.divide(list.get(0), list.get(1), username);
            default:         return service.add(list.get(0), list.get(1), username);
        }
    }

    @PostMapping("/convert")
    public QuantityDTO convert(@RequestBody QuantityDTO dto,
                               @RequestParam String target,
                               Principal principal) {
        return service.convert(dto, target, resolveUsername(principal));
    }

    @GetMapping("/history")
    public List<QuantityMeasurementEntity> getUserHistory(Principal principal) {
        if (principal == null) return Collections.emptyList();
        return service.getUserHistory(principal.getName());
    }

    @GetMapping
    public List<QuantityMeasurementEntity> getAll() { return service.getAll(); }

    @GetMapping("/{id}")
    public QuantityMeasurementEntity getById(@PathVariable Long id) { return service.getById(id); }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) { service.delete(id); return "Deleted successfully"; }

    @PutMapping("/{id}")
    public QuantityMeasurementEntity update(@PathVariable Long id, @RequestBody QuantityMeasurementEntity entity) {
        return service.update(id, entity);
    }

    private String resolveUsername(Principal principal) {
        return (principal != null && principal.getName() != null) ? principal.getName() : "guest";
    }
}