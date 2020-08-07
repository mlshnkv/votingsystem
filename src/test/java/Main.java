import org.moloshnikov.votingsystem.model.DayMenu;
import org.moloshnikov.votingsystem.model.Dish;
import org.moloshnikov.votingsystem.model.Restaurant;
import org.moloshnikov.votingsystem.model.User;
import org.moloshnikov.votingsystem.repository.VoteRepository;
import org.moloshnikov.votingsystem.to.DayMenuTo;
import org.moloshnikov.votingsystem.util.VotingUtil;

import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        VoteRepository voteRepository = new VoteRepository();
        Restaurant restaurant = new Restaurant("Биша-Муда");
        Restaurant restaurant1 = new Restaurant("Ваша-Наша");

        User user0 = new User();
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        User user4 = new User();
        User user5 = new User();
        User user6 = new User();

        Dish dish1 = new Dish("Каша", 2);
        Dish dish2 = new Dish("Картошка", 5);
        Dish dish3 = new Dish("Макароны", 3);
        Dish dish4 = new Dish("Овощи", 6);

        restaurant.addDishToMenu(dish1);
        restaurant.addDishToMenu(dish2);
        restaurant1.addDishToMenu(dish3);
        restaurant1.addDishToMenu(dish4);

        DayMenu dayMenu1 = new DayMenu(new Date(), restaurant);
        DayMenu dayMenu2 = new DayMenu(new Date(), restaurant1);

        dayMenu1.addDish(restaurant.getMenu().get(0));
        dayMenu1.addDish(restaurant.getMenu().get(1));

        dayMenu2.addDish(restaurant1.getMenu().get(0));
        dayMenu2.addDish(restaurant1.getMenu().get(1));

        voteRepository.putVote(VotingUtil.makeVote(dayMenu1, user0));
        voteRepository.putVote(VotingUtil.makeVote(dayMenu1, user1));
        voteRepository.putVote(VotingUtil.makeVote(dayMenu1, user2));
        voteRepository.putVote(VotingUtil.makeVote(dayMenu1, user3));
        voteRepository.putVote(VotingUtil.makeVote(dayMenu1, user4));
        voteRepository.putVote(VotingUtil.makeVote(dayMenu2, user5));
        voteRepository.putVote(VotingUtil.makeVote(dayMenu2, user6));

        List<DayMenuTo> listOfRestaurantTo = VotingUtil.getTos(voteRepository.getVotes());

        for (DayMenuTo dayMenuTo : listOfRestaurantTo) {
            System.out.println(dayMenuTo);
        }
    }
}
