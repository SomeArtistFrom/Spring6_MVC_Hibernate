package web.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import java.util.List;

@Component
public class UserDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public UserDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<User> showAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select u from User u", User.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public User showOneUser(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, id);
    }

    @Transactional
    public void save(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }

    @Transactional
    public void update(int id, User updatedUser) {
        Session session = sessionFactory.getCurrentSession();
        User userToNeedUpdated = session.get(User.class, id);

        userToNeedUpdated.setName(updatedUser.getName());
        userToNeedUpdated.setAge(updatedUser.getAge());
        userToNeedUpdated.setProfession(updatedUser.getProfession());
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(User.class, id));
    }
}