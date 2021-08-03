package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Measurement;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.MeasurementService;

import java.util.List;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {
    @Autowired
    MeasurementService measurementService;

    @PostMapping
    public Result addMeasurementController(@RequestBody Measurement measurement) {
        return measurementService.addMeasurementService(measurement);
    }

    @GetMapping
    public List<Measurement> getMeasurementController() {
        return measurementService.getMeasurementService();
    }

    @GetMapping("/{id}")
    public Measurement getMeasurementByIdController(@PathVariable Integer id) {
        return measurementService.getMeasurementByIdService(id);
    }

    @DeleteMapping("/{id}")
    public Result deleteMeasurementController(@PathVariable Integer id) {
        Result result = measurementService.deleteMeasurementService(id);
        return result;
    }

    @PutMapping("/{id}")
    public Result updateMeasurementController(@RequestBody Measurement measurement, @PathVariable Integer id) {
        Result result = measurementService.updateMeasurementService(measurement, id);
        return result;
    }
}
