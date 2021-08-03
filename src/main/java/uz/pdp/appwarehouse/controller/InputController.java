package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Input;
import uz.pdp.appwarehouse.entity.Product;
import uz.pdp.appwarehouse.payload.InputDto;
import uz.pdp.appwarehouse.payload.ProductDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.InputService;
import uz.pdp.appwarehouse.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/input")
public class InputController {
    @Autowired
    InputService inputService;

    @PostMapping
    public Result addInput(@RequestBody InputDto inputDto) {
        Result result = inputService.addInputService(inputDto);
        return result;
    }

    @GetMapping
    public List<Input> getInputList() {
        List<Input> inputList = inputService.getInputListService();
        return inputList;
    }

    @GetMapping("/{id}")
    public Input getInputById(@PathVariable Integer id) {
        Input inputById = inputService.getInputByIdService(id);
        return inputById;
    }

    @DeleteMapping("/{id}")
    public Result deleteInput(@PathVariable Integer id) {
        Result result = inputService.deleteInputService(id);
        return result;
    }

    @PutMapping("/{id}")
    public Result updateInput(@RequestBody InputDto inputDto, @PathVariable Integer id) {
        Result result = inputService.updateInputService(inputDto, id);
        return result;
    }
}
