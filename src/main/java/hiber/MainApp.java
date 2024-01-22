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
      userService.add(car);
      user1.setCar(car);
      userService.add(user1);

      User user2 = new User("балбес", "баранович", "bbb)");
      car.setModel("Bently");
      car.setSeries(2);
      userService.add(car);
      user2.setCar(car);
      userService.add(user2);

      User user3 = new User("Стас", "сосало", "ccc)");
      car.setModel("Chevrolet");
      car.setSeries(3);
      userService.add(car);
      user3.setCar(car);
      userService.add(user3);

      User user4 = new User("Даниил", "денис", "ddd)");
      car.setModel("Dodge");
      car.setSeries(4);
      userService.add(car);
      user4.setCar(car);
      userService.add(user4);

      User oldUser = userService.getUserByCar("Chevrolet",3);
      System.out.println(oldUser.getFirstName());

      List<User> users = userService.getListUsers();
      users.forEach(System.out::println);
      context.close();
   }
}