package Project.validate;

import Project.model.Product;
import Project.repository.IProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

@Component
public class Validate_CheckTrungTen implements Validator {
    @Autowired
    IProductRepo iProductRepo;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Product product = (Product) target;
        List<Product> products = (List<Product>) iProductRepo.findAll();
        for (Product product1 : products){
            if (product.getName().equals(product1.getName())){
                errors.rejectValue("name","", "Trùng tên");
                return;
            }
        }
    }
}
