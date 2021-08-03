package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Measurement;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.MeasurementRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MeasurementService {

    @Autowired
    MeasurementRepository measurementRepository;

    public Result addMeasurementService(Measurement measurement) {
        boolean exists = measurementRepository.existsByName(measurement.getName());
        if (exists) {
            return new Result("This measurement is exist", false);
        }
        measurementRepository.save(measurement);
        return new Result("This measurement is successfully added", true);
    }

    public List<Measurement> getMeasurementService() {
        return measurementRepository.findAll();
    }

    public Measurement getMeasurementByIdService(Integer id) {
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (!optionalMeasurement.isPresent())
            return new Measurement();
        return optionalMeasurement.get();
    }

    public Result deleteMeasurementService(Integer id) {
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (!optionalMeasurement.isPresent())
            return new Result("This measurement not found", false);
        measurementRepository.deleteById(id);
        return new Result("Measurement successfully deleted!", true);
    }

    public Result updateMeasurementService(Measurement measurement, Integer id) {
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (!optionalMeasurement.isPresent()) {
            return new Result("This measurement not found", false);
        }
        measurementRepository.save(measurement);
        return new Result("This measurement is successfully updated", true);
    }
}
