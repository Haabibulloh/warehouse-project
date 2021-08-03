package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.InputProduct;
import uz.pdp.appwarehouse.entity.OutputProduct;
import uz.pdp.appwarehouse.payload.InputProductDto;
import uz.pdp.appwarehouse.payload.OutputProductDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.InputProductService;
import uz.pdp.appwarehouse.service.OutputProductService;

import java.util.List;

@RestController
@RequestMapping("/outputProduct")
public class OutputProductController {
    @Autowired
    OutputProductService outputProductService;

    @PostMapping
    public Result addOutputProduct(@RequestBody OutputProductDto outputProductDto) {
        Result result = outputProductService.addOutputProductService(outputProductDto);
        return result;
    }

    @GetMapping
    public List<OutputProduct> getOutputProductList() {
        List<OutputProduct> outputProductList = outputProductService.getOutputProductListService();
        return outputProductList;
    }

    @GetMapping("/{id}")
    public OutputProduct getOutputProductById(@PathVariable Integer id) {
        OutputProduct outputProductById = outputProductService.getOutputProductListByIdService(id);
        return outputProductById;
    }

    @DeleteMapping("/{id}")
    public Result deleteOutputProduct(@PathVariable Integer id) {
        Result result = outputProductService.deleteOutputProductService(id);
        return result;
    }

    @PutMapping("/{id}")
    public Result updateOutputProduct(@RequestBody OutputProductDto outputProductDto, @PathVariable Integer id) {
        Result result = outputProductService.updateOutputProductService(outputProductDto, id);
        return result;
    }
}
