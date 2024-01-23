package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("Алеша", "Алкаш", "aaa)");
      Car car = new Car("Audi", 1);
      user1.setCar(car);
      userService.add(user1);

      User user2 = new User("балбес", "баранович", "bbb)");
      Car car2 = new Car("Bentley", 2);
      user2.setCar(car2);
      userService.add(user2);

      User user3 = new User("Стас", "сосало", "ccc)");
      Car car3 = new Car("Chevrolet", 3);
      user3.setCar(car3);
      userService.add(user3);

      User user4 = new User("Даниил", "денис", "ddd)");
      Car car4 = new Car("Dodge", 4);
      user4.setCar(car4);
      userService.add(user4);

      User oldUser = userService.getUserByCar("Chevrolet",3);
      System.out.println(oldUser.getFirstName());

      List<User> users = userService.getListUsers();
      for (User user : users) {
         System.out.println(user);
         if (user.getCar() != null){
            System.out.println(user.getCar());
         }
      }
      context.close();
   }
}