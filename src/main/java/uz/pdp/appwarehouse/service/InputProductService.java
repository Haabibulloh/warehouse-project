package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import uz.pdp.appwarehouse.entity.*;
import uz.pdp.appwarehouse.payload.InputDto;
import uz.pdp.appwarehouse.payload.InputProductDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.*;

import java.util.List;
import java.util.Optional;

@Service
public class InputProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    InputRepository inputRepository;
    @Autowired
    InputProductRepository inputProductRepository;

    public Result addInputProductService(InputProductDto inputProductDto) {

        Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        if (!optionalProduct.isPresent())
            return new Result("This Product doesn't exist", false);

        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        if (!optionalInput.isPresent())
            return new Result("This Input doesn't exist", false);

        InputProduct inputProduct = new InputProduct();
        inputProduct.setProduct(optionalProduct.get());
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setExpireDate(inputProductDto.getExpireDate());
        inputProduct.setInput(optionalInput.get());
        inputProductRepository.save(inputProduct);
        return new Result("InputProduct successfully saved", true);
    }

    public List<InputProduct> getInputProductListService() {
        List<InputProduct> inputProductList = inputProductRepository.findAll();
        return inputProductList;
    }

    public InputProduct getInputProductListByIdService(Integer id) {
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (!optionalInputProduct.isPresent())
            return new InputProduct();
        InputProduct inputProduct = optionalInputProduct.get();
        return inputProduct;
    }

    public Result deleteInputProductService(Integer id) {
        inputProductRepository.deleteById(id);
        return new Result("InputProduct successfully deleted", true);
    }

    public Result updateInputProductService(InputProductDto inputProductDto, @PathVariable Integer id) {
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (!optionalInputProduct.isPresent())
            return new Result("This InputProduct doesn't exist", false);

        Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        if (!optionalProduct.isPresent())
            return new Result("This Product doesn't exist", false);

        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        if (!optionalInput.isPresent())
            return new Result("This Input doesn't exist", false);

        InputProduct inputProduct = optionalInputProduct.get();
        inputProduct.setProduct(optionalProduct.get());
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setExpireDate(inputProductDto.getExpireDate());
        inputProduct.setInput(optionalInput.get());
        inputProductRepository.save(inputProduct);
        return new Result("InputProduct successfully updated", true);
    }
}
