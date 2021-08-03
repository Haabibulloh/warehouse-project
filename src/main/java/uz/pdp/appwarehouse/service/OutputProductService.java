package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import uz.pdp.appwarehouse.entity.*;
import uz.pdp.appwarehouse.payload.InputProductDto;
import uz.pdp.appwarehouse.payload.OutputProductDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.*;

import java.util.List;
import java.util.Optional;

@Service
public class OutputProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    OutputRepository outputRepository;
    @Autowired
    OutputProductRepository outputProductRepository;

    public Result addOutputProductService(OutputProductDto outputProductDto) {

        Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
        if (!optionalProduct.isPresent())
            return new Result("This Product doesn't exist", false);

        Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());
        if (!optionalOutput.isPresent())
            return new Result("This Output doesn't exist", false);

        OutputProduct outputProduct = new OutputProduct();
        outputProduct.setProduct(optionalProduct.get());
        outputProduct.setAmount(outputProductDto.getAmount());
        outputProduct.setPrice(outputProductDto.getPrice());
        outputProduct.setOutput(optionalOutput.get());
        outputProductRepository.save(outputProduct);
        return new Result("OutputProduct successfully saved", true);
    }

    public List<OutputProduct> getOutputProductListService() {
        List<OutputProduct> outputProductList = outputProductRepository.findAll();
        return outputProductList;
    }

    public OutputProduct getOutputProductListByIdService(Integer id) {
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if (!optionalOutputProduct.isPresent())
            return new OutputProduct();
        OutputProduct outputProduct = optionalOutputProduct.get();
        return outputProduct;
    }

    public Result deleteOutputProductService(Integer id) {
        outputProductRepository.deleteById(id);
        return new Result("InputProduct successfully deleted", true);
    }

    public Result updateOutputProductService(OutputProductDto outputProductDto, @PathVariable Integer id) {
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if (!optionalOutputProduct.isPresent())
            return new Result("This OutputProduct doesn't exist", false);

        Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
        if (!optionalProduct.isPresent())
            return new Result("This Product doesn't exist", false);

        Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());
        if (!optionalOutput.isPresent())
            return new Result("This Output doesn't exist", false);

        OutputProduct outputProduct = optionalOutputProduct.get();
        outputProduct.setProduct(optionalProduct.get());
        outputProduct.setAmount(outputProductDto.getAmount());
        outputProduct.setPrice(outputProductDto.getPrice());
        outputProduct.setOutput(optionalOutput.get());
        outputProductRepository.save(outputProduct);
        return new Result("InputProduct successfully updated", true);
    }
}
