package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Input;
import uz.pdp.appwarehouse.entity.Output;
import uz.pdp.appwarehouse.payload.InputDto;
import uz.pdp.appwarehouse.payload.OutputDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.InputService;
import uz.pdp.appwarehouse.service.OutputService;

import java.util.List;

@RestController
@RequestMapping("/output")
public class OutputController {
    @Autowired
    OutputService outputService;

    @PostMapping
    public Result addOutput(@RequestBody OutputDto outputDto) {
        Result result = outputService.addOutputService(outputDto);
        return result;
    }

    @GetMapping
    public List<Output> getOutputList() {
        List<Output> outputList = outputService.getOutputListService();
        return outputList;
    }

    @GetMapping("/{id}")
    public Output getOutputById(@PathVariable Integer id) {
        Output outputById = outputService.getOutputByIdService(id);
        return outputById;
    }

    @DeleteMapping("/{id}")
    public Result deleteOutput(@PathVariable Integer id) {
        Result result = outputService.deleteOutputService(id);
        return result;
    }

    @PutMapping("/{id}")
    public Result updateOutput(@RequestBody OutputDto outputDto, @PathVariable Integer id) {
        Result result = outputService.updateOutputService(outputDto, id);
        return result;
    }
}
