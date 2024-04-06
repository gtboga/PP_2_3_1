package application.dao;

import application.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
        entityManager.flush();
    }



    @Override
    public void updateUser(int id, User user) {
        User newUser = readUser(id);
        newUser.setName(user.getName());
        newUser.setSurname(user.getSurname());
        newUser.setCity(user.getCity());
        newUser.setAge(user.getAge());
    }

    @Override
    public void editUser(User user) {
        entityManager.merge(user);
        entityManager.flush();
    }

    @Override
    public User readUser(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User deleteUser(int id) {
        User user = readUser(id);
        if (user != null) {
            entityManager.remove(user);
            entityManager.flush();
        }
        return user;
    }
}
