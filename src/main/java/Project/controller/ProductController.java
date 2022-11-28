package Project.controller;

import Project.model.Category;
import Project.model.Product;
import Project.repository.ICategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import Project.repository.IProductRepo;
import Project.service.ProductService;
import Project.validate.Validate_CheckTrungTen;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@Controller
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    IProductRepo iProductRepo;
    @Autowired
    ICategoryRepo iCategoryRepo;
    @Autowired
    Validate_CheckTrungTen validateCheckTrungTen;

    @GetMapping("products")
    public ModelAndView show(@RequestParam(defaultValue = "0") int page) {
        ModelAndView modelAndView = new ModelAndView("show");
        Page<Product> products = iProductRepo.findAll(PageRequest.of(page, 3, Sort.by("price")));
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id) {
        iProductRepo.deleteById(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/products");
        return modelAndView;
    }

    @ModelAttribute(name = "categories")
    public List<Category> categories() {
        return (List<Category>) iCategoryRepo.findAll();
    }

    @GetMapping("/create")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult,
                         @RequestParam Long id_Category, @RequestParam MultipartFile imgFile, Model model) throws IOException {

        validateCheckTrungTen.validate(product, bindingResult);

        if (bindingResult.hasFieldErrors()) {
            return "create";
        }
        if (product.getImg().equals("") && imgFile.getSize() == 0) {
            model.addAttribute("mess", "Chưa có ảnh");
            return "create";
        }

        if (product.getImg().equals("")) {
            String name = imgFile.getOriginalFilename();
            FileCopyUtils.copy(imgFile.getBytes(), new File("F:\\JavaWeb\\demo_ORM_JDBC\\src\\main\\webapp\\img\\" + name));
            product.setImg(name);
        }


        Category category = iCategoryRepo.findById(id_Category).get();
        product.setCategory(category);

        iProductRepo.save(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        Product product = iProductRepo.findById(id).get();
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @PostMapping("/edit")
    public String edit(@Valid @ModelAttribute Product product, BindingResult bindingResult,@RequestParam String name, @RequestParam Long id_Category, @RequestParam MultipartFile imgFile, Model model) throws IOException {

        String ten = iProductRepo.findById(product.getId()).get().getName();
        if (!ten.equals(name)) {
            validateCheckTrungTen.validate(product, bindingResult);
        }
        Category category = iCategoryRepo.findById(id_Category).get();
        product.setCategory((category));


        Product product1 = product;
        product1.setImg(iProductRepo.findById(product.getId()).get().getImg());

        if (bindingResult.hasFieldErrors()) {
            model.addAttribute("toan", product1);
            return "edit";
        }

        String name1 = imgFile.getOriginalFilename();
        if (!name1.equals("")) {
            FileCopyUtils.copy(imgFile.getBytes(), new File("F:\\JavaWeb\\demo_ORM_JDBC\\src\\main\\webapp\\img\\" + name1));
            product.setImg(name1);
        }



        iProductRepo.save(product1);
        return "redirect:/products";
    }

}
