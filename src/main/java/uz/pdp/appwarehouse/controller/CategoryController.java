package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Category;
import uz.pdp.appwarehouse.entity.Measurement;
import uz.pdp.appwarehouse.payload.CategoryDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.CategoryService;
import uz.pdp.appwarehouse.service.MeasurementService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping
    public Result addCategoryController(@RequestBody CategoryDto categoryDto) {
        Result result = categoryService.addCategoryService(categoryDto);
        return result;
    }

    @GetMapping
    public List<Category> getCategoryList() {
        List<Category> categoryList = categoryService.getCategoryListService();
        return categoryList;
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Integer id) {
        Category categoryById = categoryService.getCategoryByIdService(id);
        return categoryById;
    }

    @DeleteMapping("/{id}")
    public Result deleteCategory(@PathVariable Integer id) {
        Result result = categoryService.deleteCategoryService(id);
        return result;
    }

    @PutMapping("/{id}")
    public Result updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable Integer id) {
        Result result = categoryService.updateCategoryService(id, categoryDto);
        return result;
    }
}
