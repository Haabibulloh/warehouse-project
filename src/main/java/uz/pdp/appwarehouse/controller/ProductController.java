package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appwarehouse.entity.Product;
import uz.pdp.appwarehouse.payload.ProductDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.AttachmentService;
import uz.pdp.appwarehouse.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping
    public Result addProduct(@RequestBody ProductDto productDto) {
        Result result = productService.addProductService(productDto);
        return result;
    }

    @GetMapping
    public List<Product> getProductList() {
        List<Product> productList = productService.getProductListService();
        return productList;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Integer id) {
        Product productById = productService.getProductByIdService(id);
        return productById;
    }

    @DeleteMapping("/{id}")
    public Result deleteProduct(@PathVariable Integer id) {
        Result result = productService.deleteProductService(id);
        return result;
    }

    @PutMapping("/{id}")
    public Result updateProduct(@RequestBody ProductDto productDto, @PathVariable Integer id) {
        Result result = productService.updateProductService(productDto, id);
        return result;
    }
}
