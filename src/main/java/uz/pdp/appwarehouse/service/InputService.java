package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import uz.pdp.appwarehouse.entity.*;
import uz.pdp.appwarehouse.payload.InputDto;
import uz.pdp.appwarehouse.payload.ProductDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.*;

import java.util.List;
import java.util.Optional;

@Service
public class InputService {

    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    CurrencyRepository currencyRepository;
    @Autowired
    InputRepository inputRepository;

    public Result addInputService(InputDto inputDto) {

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent())
            return new Result("This Warehouse doesn't exist", false);

        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        if (!optionalSupplier.isPresent())
            return new Result("This Supplier doesn't exist", false);

        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if (!optionalCurrency.isPresent())
            return new Result("This Currency doesn't exist", false);

        Input input = new Input();
        input.setDate(inputDto.getDate());
        input.setCurrency(optionalCurrency.get());
        input.setWarehouse(optionalWarehouse.get());
        input.setSupplier(optionalSupplier.get());
        input.setFactureNumber(inputDto.getFactureNumber());
        inputRepository.save(input);
        return new Result("Input successfully saved", true);
    }

    public List<Input> getInputListService() {
        List<Input> inputList = inputRepository.findAll();
        return inputList;
    }

    public Input getInputByIdService(Integer id) {
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (!optionalInput.isPresent())
            return new Input();
        Input input = optionalInput.get();
        return input;
    }

    public Result deleteInputService(Integer id) {
        inputRepository.deleteById(id);
        return new Result("Input successfully deleted", true);
    }

    public Result updateInputService(InputDto inputDto, @PathVariable Integer id) {
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (!optionalInput.isPresent())
            return new Result("This Input doesn't exist", false);

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent())
            return new Result("This Warehouse doesn't exist", false);

        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        if (!optionalSupplier.isPresent())
            return new Result("This Supplier doesn't exist", false);

        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if (!optionalCurrency.isPresent())
            return new Result("This Currency doesn't exist", false);

        Input input = optionalInput.get();
        input.setDate(inputDto.getDate());
        input.setCurrency(optionalCurrency.get());
        input.setWarehouse(optionalWarehouse.get());
        input.setSupplier(optionalSupplier.get());
        input.setFactureNumber(inputDto.getFactureNumber());
        inputRepository.save(input);
        return new Result("Input successfully updated", true);
    }
}
