package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import uz.pdp.appwarehouse.entity.*;
import uz.pdp.appwarehouse.payload.InputDto;
import uz.pdp.appwarehouse.payload.OutputDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.*;

import java.util.List;
import java.util.Optional;

@Service
public class OutputService {

    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    CurrencyRepository currencyRepository;
    @Autowired
    OutputRepository outputRepository;

    public Result addOutputService(OutputDto outputDto) {

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent())
            return new Result("This Warehouse doesn't exist", false);

        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        if (!optionalClient.isPresent())
            return new Result("This Client doesn't exist", false);

        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        if (!optionalCurrency.isPresent())
            return new Result("This Currency doesn't exist", false);

        Output output = new Output();
        output.setDate(outputDto.getDate());
        output.setCurrency(optionalCurrency.get());
        output.setWarehouse(optionalWarehouse.get());
        output.setClient(optionalClient.get());
        output.setFactureNumber(outputDto.getFactureNumber());
        outputRepository.save(output);
        return new Result("Output successfully saved", true);
    }

    public List<Output> getOutputListService() {
        List<Output> outputList = outputRepository.findAll();
        return outputList;
    }

    public Output getOutputByIdService(Integer id) {
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (!optionalOutput.isPresent())
            return new Output();
        Output output = optionalOutput.get();
        return output;
    }

    public Result deleteOutputService(Integer id) {
        outputRepository.deleteById(id);
        return new Result("Output successfully deleted", true);
    }

    public Result updateOutputService(OutputDto outputDto, @PathVariable Integer id) {
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (!optionalOutput.isPresent())
            return new Result("This Output doesn't exist", false);

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent())
            return new Result("This Warehouse doesn't exist", false);

        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        if (!optionalClient.isPresent())
            return new Result("This Client doesn't exist", false);

        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        if (!optionalCurrency.isPresent())
            return new Result("This Currency doesn't exist", false);

        Output output = optionalOutput.get();
        output.setDate(outputDto.getDate());
        output.setCurrency(optionalCurrency.get());
        output.setWarehouse(optionalWarehouse.get());
        output.setClient(optionalClient.get());
        output.setFactureNumber(outputDto.getFactureNumber());
        outputRepository.save(output);
        return new Result("Output successfully updated", true);
    }
}
