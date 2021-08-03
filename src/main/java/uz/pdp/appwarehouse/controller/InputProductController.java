package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Input;
import uz.pdp.appwarehouse.entity.InputProduct;
import uz.pdp.appwarehouse.payload.InputDto;
import uz.pdp.appwarehouse.payload.InputProductDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.InputProductService;
import uz.pdp.appwarehouse.service.InputService;

import java.util.List;

@RestController
@RequestMapping("/inputProduct")
public class InputProductController {
    @Autowired
    InputProductService inputProductService;

    @PostMapping
    public Result addInputProduct(@RequestBody InputProductDto inputProductDto) {
        Result result = inputProductService.addInputProductService(inputProductDto);
        return result;
    }

    @GetMapping
    public List<InputProduct> getInputProductList() {
        List<InputProduct> inputProductList = inputProductService.getInputProductListService();
        return inputProductList;
    }

    @GetMapping("/{id}")
    public InputProduct getInputProductById(@PathVariable Integer id) {
        InputProduct inputProductById = inputProductService.getInputProductListByIdService(id);
        return inputProductById;
    }

    @DeleteMapping("/{id}")
    public Result deleteInputProduct(@PathVariable Integer id) {
        Result result = inputProductService.deleteInputProductService(id);
        return result;
    }

    @PutMapping("/{id}")
    public Result updateInputProduct(@RequestBody InputProductDto inputProductDto, @PathVariable Integer id) {
        Result result = inputProductService.updateInputProductService(inputProductDto, id);
        return result;
    }
}
