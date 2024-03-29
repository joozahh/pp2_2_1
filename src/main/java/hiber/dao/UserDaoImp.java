package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Transient;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   private final SessionFactory sessionFactory;
   @Autowired
   public UserDaoImp(SessionFactory sessionFactory){
      this.sessionFactory = sessionFactory;
   }
   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user.getCar());
      sessionFactory.getCurrentSession().save(user);
   }
   @Transactional
   @Override
   public void add(Car car) {
      sessionFactory.getCurrentSession().save(car);
   }

   @Override
   public List<User> getListUsers() {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User", User.class);
      return query.getResultList();
   }
   @Transactional
   @Override
   public User getUserbyCar(String model, int series) {
      return sessionFactory
              .openSession()
              .createQuery("from User where car.model =  :model AND car.series = :series", User.class)
              .setParameter("model", model)
              .setParameter("series", series)
              .stream().findAny().orElse(null);
   }

}
