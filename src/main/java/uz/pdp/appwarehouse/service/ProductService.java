package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import uz.pdp.appwarehouse.entity.Attachment;
import uz.pdp.appwarehouse.entity.Category;
import uz.pdp.appwarehouse.entity.Measurement;
import uz.pdp.appwarehouse.entity.Product;
import uz.pdp.appwarehouse.payload.CategoryDto;
import uz.pdp.appwarehouse.payload.ProductDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.AttachmentRepository;
import uz.pdp.appwarehouse.repository.CategoryRepository;
import uz.pdp.appwarehouse.repository.MeasurementRepository;
import uz.pdp.appwarehouse.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    MeasurementRepository measurementRepository;

    public Result addProductService(ProductDto productDto) {
        boolean exists = productRepository.existsByNameAndCategoryId(productDto.getName(), productDto.getCategoryId());
        if (exists)
            return new Result("This product is already exist", false);

        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent())
            return new Result("This category doesn't exist", false);

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if (!optionalAttachment.isPresent())
            return new Result("This photo doesn't exist", false);

        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if (!optionalMeasurement.isPresent())
            return new Result("This measurement doesn't exist", false);


        Product product = new Product();
        product.setName(productDto.getName());
        product.setCode("1");// TODO
        product.setCategory(optionalCategory.get());
        product.setPhoto(optionalAttachment.get());
        product.setMeasurement(optionalMeasurement.get());
        productRepository.save(product);
        return new Result("Product successfully saved", true);
    }

    public List<Product> getProductListService() {
        List<Product> productList = productRepository.findAll();
        return productList;
    }

    public Product getProductByIdService(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent())
            return new Product();
        Product product = optionalProduct.get();
        return product;
    }

    public Result deleteProductService(Integer id) {
        productRepository.deleteById(id);
        return new Result("Product successfully deleted", true);
    }

    public Result updateProductService(ProductDto productDto, @PathVariable Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent())
            return new Result("This Product doesn't exist", false);

        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent())
            return new Result("This category doesn't exist", false);

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if (!optionalAttachment.isPresent())
            return new Result("This photo doesn't exist", false);

        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if (!optionalMeasurement.isPresent())
            return new Result("This measurement doesn't exist", false);

        Product product = optionalProduct.get();
        product.setName(productDto.getName());
        product.setCode("1");// TODO
        product.setCategory(optionalCategory.get());
        product.setPhoto(optionalAttachment.get());
        product.setMeasurement(optionalMeasurement.get());
        productRepository.save(product);
        return new Result("Product successfully updated", true);
    }
}
