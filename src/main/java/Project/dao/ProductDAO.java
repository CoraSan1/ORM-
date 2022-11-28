package Project.dao;

import Project.model.Product;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class ProductDAO {
    @Autowired
    EntityManager entityManager;

    public List<Product> getList() {
        String query = "SELECT p FROM Product p";
        TypedQuery<Product> query1 = entityManager.createQuery(query, Product.class);
        return query1.getResultList();
    }

    public void save(Product product) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(product);
        entityTransaction.commit();

    }

    public void update(int id, Product product){
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.merge(product);
        entityTransaction.commit();
    }

    public void delete(int id){
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.remove(findById(id));
        entityTransaction.commit();
    }

    public Product findById(int id){
        String query = "SELECT p FROM Product p WHERE p.id = :id";
       Product product = entityManager.createQuery(query, Product.class).setParameter("id", id).getSingleResult();
        return product;
    }
}
