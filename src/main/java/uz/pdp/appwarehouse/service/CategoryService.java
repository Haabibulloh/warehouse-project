package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Category;
import uz.pdp.appwarehouse.entity.Measurement;
import uz.pdp.appwarehouse.payload.CategoryDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.CategoryRepository;
import uz.pdp.appwarehouse.repository.MeasurementRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public Result addCategoryService(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        if (categoryDto.getParentCategoryId() != null) {
            Optional<Category> optionalCategory = categoryRepository.findById(categoryDto.getParentCategoryId());
            if (!optionalCategory.isPresent())
                return new Result("This Parent Category not found", false);
            category.setParentCategory(optionalCategory.get());
        }
        categoryRepository.save(category);
        return new Result("Category successfully added", true);
    }

    public List<Category> getCategoryListService() {
        List<Category> categories = categoryRepository.findAll();
        return categories;
    }

    public Category getCategoryByIdService(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent())
            return new Category();
        Category category = optionalCategory.get();
        return category;
    }

    public Result deleteCategoryService(Integer id) {
        categoryRepository.deleteById(id);
        return new Result("Category successfully deleted", true);
    }

    public Result updateCategoryService(Integer id, CategoryDto categoryDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent())
            return new Result("This category doesn't exist", false);
        Category editCategory = optionalCategory.get();
        editCategory.setName(categoryDto.getName());
        if (categoryDto.getParentCategoryId() != null) {
            Optional<Category> optionalCategoryParent = categoryRepository.findById(categoryDto.getParentCategoryId());
            if (!optionalCategoryParent.isPresent())
                return new Result("Parent Category doesn't exist", false);
            editCategory.setParentCategory(optionalCategoryParent.get());
        }
        categoryRepository.save(editCategory);
        return new Result("Category successfully updated", true);
    }
}
