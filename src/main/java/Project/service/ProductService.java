package Project.service;

import Project.dao.ProductDAO;
import Project.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService {
    @Autowired
    ProductDAO productDAO;

    public List<Product> getAll(){
        return productDAO.getList();
    }

    public void add(Product product){
        productDAO.save(product);
    }

    public void edit(int id, Product product){
        productDAO.update(id, product);
    }

    public void delete(int id){
        productDAO.delete(id);
    }

    public Product findById(int id){
        return productDAO.findById(id);
    }

}
