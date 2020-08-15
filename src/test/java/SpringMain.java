import org.moloshnikov.votingsystem.model.Restaurant;
import org.moloshnikov.votingsystem.web.AdminRestaurantController;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml", "db/spring-db.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestaurantController adminRestaurantController = appCtx.getBean(AdminRestaurantController.class);
            adminRestaurantController.create(new Restaurant(null, "Золотая рыбка"));
            System.out.println(adminRestaurantController.getAll());
            adminRestaurantController.delete(100019);
            System.out.println(adminRestaurantController.getAll());
//            System.out.println(adminRestaurantController.get());

//            MealRestController mealController = appCtx.getBean(MealRestController.class);
//            List<MealTo> filteredMealsWithExcess =
//                    mealController.getBetween(
//                            LocalDate.of(2020, Month.JANUARY, 30), LocalTime.of(7, 0),
//                            LocalDate.of(2020, Month.JANUARY, 31), LocalTime.of(11, 0));
//            filteredMealsWithExcess.forEach(System.out::println);
//            System.out.println();
//            System.out.println(mealController.getBetween(null, null, null, null));
        }
    }
}
