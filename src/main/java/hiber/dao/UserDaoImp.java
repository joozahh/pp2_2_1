package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user.getCar());
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   public void add(Car car) {
      sessionFactory.getCurrentSession().save(car);
   }

   @Override
   public List<User> getListUsers() {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User", User.class);
      return query.getResultList();
   }

   @Override
   public User getUserbyCar(String model, int series) {
      return (User) sessionFactory
              .openSession()
              .createQuery("from User where car.model =  :model AND car.series = :series", User.class)
              .setParameter("model", model)
              .setParameter("series", series)
              .getSingleResult();
   }

}
