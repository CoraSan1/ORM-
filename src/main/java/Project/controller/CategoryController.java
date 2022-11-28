package Project.controller;

import Project.model.Category;
import Project.repository.ICategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import Project.repository.IProductRepo;

import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    ICategoryRepo iCategoryRepo;
    @Autowired
    IProductRepo iProductRepo;

    @GetMapping("/showCategory")
    public ModelAndView show (){
        ModelAndView modelAndView = new ModelAndView("showCategory");
        List<Category> categories = (List<Category>) iCategoryRepo.findAll();
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }

    @GetMapping("/showCategory/delete/{id}")
    public ModelAndView delete (@PathVariable Long id) {
        iCategoryRepo.deleteById(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/showCategory");
        return modelAndView;
    }

    @GetMapping("/createCategory")
    public ModelAndView create (){
        ModelAndView modelAndView = new ModelAndView("createCategory");
        modelAndView.addObject("category", new Category());
        return modelAndView;
    }

    @PostMapping("/createCategory")
    public String create (@ModelAttribute("category") Category category){
        iCategoryRepo.save(category);
        return "redirect:/showCategory";
    }
}
