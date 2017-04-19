import customers.Customer;
import inventory.StoreInventory;
import store.Film;
import store.RegularFilm;
import store.NewFilm;
import store.OldFilm;

import java.util.ArrayList;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) {

        /*
         * Initialising hashSet of storeFilms
         * and customerFilms. StoreFilms hold films
         * which go into StoreInventory and
         * customerFilms hold films which customer
         * wants to rent and return.
         */
        HashSet<Film> storeFilms = new HashSet<>();
        HashSet<Film> customerFilms = new HashSet<>();

        /*
         * Initialising films
         */
        Film film1 = new NewFilm("Matrix 11");
        Film film2 = new RegularFilm("Spider man");
        Film film3 = new RegularFilm("Spider man 2");
        Film film4 = new OldFilm("Out of africa");
        Film movie1 = new NewFilm("Logan");
        Film movie2 = new NewFilm("The Boss Baby");
        Film movie3 = new RegularFilm("Hancock");
        Film movie4 = new OldFilm("Titanic");

        /*
         * Adding films to hashSet so
         * there are no duplicates.
         */
        storeFilms.add(film1);
        storeFilms.add(film1); // duplicate not added into set
        storeFilms.add(film2);
        storeFilms.add(film3);
        storeFilms.add(film4);
        customerFilms.add(movie1);
        customerFilms.add(movie2);
        customerFilms.add(movie2); // duplicate not added into set
        customerFilms.add(movie3);
        customerFilms.add(movie4);

        /*
         * Initialising two inventories, one is empty
         * inventory and one takes ArrayList of Films
         * as an argument.
         */
        StoreInventory apolloInventory = new StoreInventory();
        StoreInventory forumCinemasInventory = new StoreInventory(new ArrayList<>(storeFilms));

        /*
         * Initialising two customers
         */
        Customer customer1 = new Customer("Enar", 0);
        Customer customer2 = new Customer("Markus", 150);

        System.out.println("RENTING FILMS: ");
        customer1.rent(film1, 1);
        customer1.rent(film2, 5);
        customer1.rent(film3, 2);
        customer1.rent(film4, 7);
        System.out.println("Total price : " + customer1.getTotalPrice() + " EUR");
        customer1.pay(); // if not using method pay then total price keeps rising like a bill.
        System.out.println("");

        System.out.println("RETURNING FILMS");
        customer1.returnFilm(film1, 2);
        customer1.returnFilm(film2, 1);
        System.out.println("Total late charge : " + customer1.getTotalPrice() + " EUR");
        customer1.pay();
        System.out.println("");

        System.out.println("PAYING WITH BONUS POINTS");
        customer2.rent(film1, 1);
        customer2.payWithBonusPoints(film1);
        System.out.println("Total price : " + customer2.getTotalPrice() + " EUR");
        System.out.println("Remaining Bonus points: " + customer2.getBonusPoints());

        System.out.println("\nSHOWING ALL FILMS IN THE forumCinemasInventory");
        for (int i = 0; i < forumCinemasInventory.listAllFilms().size(); i++) {
            System.out.println(forumCinemasInventory.listAllFilms().get(i).getTitleAndType());
        }

        System.out.println("\nSHOWING ALL FILMS IN THE forumCinemasInventory WHICH ARE AVAILABLE FOR RENT");
        for (int i = 0; i < forumCinemasInventory.listAllFilmsInStore().size(); i++) {
            System.out.println(forumCinemasInventory.listAllFilmsInStore().get(i).getTitleAndType());
        }

        /*
         * Adding films to apolloInventory
         */
        System.out.println("");
        apolloInventory.addFilm(movie1);
        apolloInventory.addFilm(movie2);
        apolloInventory.addFilm(movie3);
        apolloInventory.addFilm(movie4);

        System.out.println("\nSHOWING ALL FILMS IN THE apolloInventory");
        for (int i = 0; i < apolloInventory.listAllFilms().size(); i++) {
            System.out.println(apolloInventory.listAllFilms().get(i).getTitleAndType());
        }

        /*
         * Removing Logan (NewFilm) and
         * changing Hancock (RegularFilm)
         * to Hancock (OldFilm)
         */
        System.out.println("");
        apolloInventory.removeFilm(movie1);
        apolloInventory.changeFilmType(movie3, "Old");

        System.out.println("\nSHOWING ALL FILMS IN THE apolloInventory AFTER THE CHANGE");
        for (int i = 0; i < apolloInventory.listAllFilms().size(); i++) {
            System.out.println(apolloInventory.listAllFilms().get(i).getTitleAndType());
        }

        /*
         * Demonstrating renting list of movies
         * and paying bonus points multiple times
         * on different films.
         */
        System.out.println("");
        apolloInventory.addFilm(movie1);
        System.out.println("\nRENTING LIST OF MOVIES USING ARRAYLIST");
        customer1.rent(new ArrayList<>(customerFilms), 2);
        System.out.println("Total price : " + customer1.getTotalPrice() + " EUR");
        customer1.setBonusPoints(222);
        customer1.payWithBonusPoints(movie1);
        customer1.payWithBonusPoints(movie2);
        System.out.println("Total price : " + customer1.getTotalPrice() + " EUR");
        customer1.pay();

        /*
         * Demonstrating returning list of films.
         */
        System.out.println("\nRETURNING LIST OF FILMS LATE");
        customer1.returnFilm(new ArrayList<>(customerFilms), 5);
        System.out.println("Total late charge : " + customer1.getTotalPrice() + " EUR");
        customer1.pay();

        System.out.println("\nRETURNING LIST OF FILMS ON TIME");
        customer1.rent(new ArrayList<>(customerFilms), 2);
        System.out.println("Total price : " + customer1.getTotalPrice() + " EUR");
        customer1.pay();
        System.out.println("");
        customer1.returnFilm(new ArrayList<>(customerFilms));
        System.out.println("Total late charge : " + customer1.getTotalPrice() + " EUR");
        customer1.pay();

        System.out.println("\nRETURNING FILM ON TIME");
        customer1.returnFilm(film1);
        customer1.returnFilm(film3);
    }
}
