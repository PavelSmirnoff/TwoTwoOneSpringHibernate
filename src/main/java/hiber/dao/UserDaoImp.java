package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public List<User> getUserCar(String carModel, int carSeries) {
      //String sql = "select User from User";
      //TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery(sql,User.class);
      String hql = "from User where car.model = :model and car.series = :series";
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hql,User.class);
      query.setParameter("model", carModel);
      query.setParameter("series", carSeries);
      List<User> users = query.getResultList();

      return users;
   }

}
