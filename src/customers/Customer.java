package customers;

import inventory.FilmNotFoundInTheStoreException;
import inventory.UnknownFilmTypeException;
import store.Film;
import store.NewFilm;
import store.OldFilm;
import store.RegularFilm;

import java.util.ArrayList;

public class Customer {

    /**
     * Customer name, helps to identify different customers.
     */
    private String name;
    /**
     * Points that customers get for renting films.
     */
    private int bonusPoints;
    /**
     * Total price of films when
     * customer proceeds to pay.
     */
    private int totalPrice;

    /**
     * Constructor for initialising customers.
     * @param name which is like an id.
     * @param bonusPoints are used to pay for
     *                    New Films.
     */
    public Customer(String name, int bonusPoints) {
        if (bonusPoints <= 0) {
            this.bonusPoints = 0;
        } else {
            this.bonusPoints = bonusPoints;
        }
        this.name = name;
    }

    /**
     * Adds Bonus points to customer. NewFilm gives 2 points,
     * both RegularFilm and OldFilm give 1 point.
     * @param film which type will give certain amount of points.
     */
    void addBonusPoints(Film film) {
        if (film instanceof NewFilm) {
            this.bonusPoints += 2;
        } else if (film instanceof OldFilm) {
            this.bonusPoints += 1;
        } else if (film instanceof RegularFilm) {
            this.bonusPoints += 1;
        }
    }

    /**
     * Method for renting multiple films.
     * @param films ArrayList of films customer wants
     *              to rent.
     * @param days for how long customer wants to rent
     *             those films
     */
    public void rent(ArrayList<Film> films, int days) {
        if (films != null) {
            for (Film film : films) {
                rent(film, days);
            }
        }
    }

    /**
     * Method for renting a single film.
     * This method calculate price for customer
     * and sets film currently rented.
     * @param film which will be rent.
     * @param days for how long the film will be rent
     */
    public void rent(Film film, int days) {
        if (film != null) {
            if (days >= 0) {
                if (film.isInStoreInventory()) {
                    if (!film.isCurrentlyRented()) {
                        film.calculatePrice(days);
                        this.totalPrice += film.getPrice();
                        film.setCurrentlyRented(true);
                        addBonusPoints(film);
                        System.out.println(film.getFilmRentalInfo());
                    } else {
                        throw new FilmRentalException("Film " + film.getTitleAndType() + " is currently rented!");
                    }
                } else {
                    throw new FilmNotFoundInTheStoreException("Film " + film.getTitleAndType() + " is not in this store!");
                }
            } else {
                throw new IllegalArgumentException("Number of days to rent a film can't be negative!");
            }
        }
    }

    /**
     * Method which must be used after renting
     * and returning films. It just resets total
     * price to 0 EUR aka pays.
     */
    public void pay() {
        if (this.totalPrice != 0) {
            System.out.println("Paid " + this.totalPrice + " EUR");
            this.totalPrice = 0;
        }
    }

    /**
     * Method for paying with bonus points. When film is
     * a NewFilm you can use 25 points to pay for one day.
     * 50 points for 2 days and so on. Bonus points can
     * only be used when renting films not returning films.
     * @param film which price will be paid with bonus points.
     */
    public void payWithBonusPoints(Film film) {
        if (film != null) {
            int counter = 0;
            if (getBonusPoints() >= 25) {
                if (film.isCurrentlyRented() && film.isInStoreInventory()) {
                    if (film instanceof NewFilm) {
                        int currentFilmPrice = film.getPrice();
                        counter = 0;
                        while (getBonusPoints() >= 25 && film.getDays() > 0) {
                            film.calculatePrice(film.getDays() - 1);
                            setBonusPoints(getBonusPoints() - 25);
                            counter += 25;
                        }
                        this.totalPrice -= (currentFilmPrice - film.getPrice());
                    } else {
                        throw new UnknownFilmTypeException("Film " + film.getTitleAndType() + " can't be paid using Bonus points!");
                    }
                } else {
                    if (!film.isInStoreInventory()) {
                        throw new FilmNotFoundInTheStoreException("Film " + film.getTitleAndType() + " is not in the store inventory!");
                    } else if (!film.isCurrentlyRented()) {
                        throw new FilmRentalException("Can't pay for " + film.getTitleAndType() + " because it is not rented!");
                    }
                }
            } else {
                System.out.println("Not enough Bonus points!");
            }
            System.out.println("(Used " + counter + " Bonus points!)");
        }
    }

    /**
     * Method for returning multiple film.
     * @param films ArrayList of films to be returned.
     * @param daysLate number of days the films are late.
     */
    public void returnFilm(ArrayList<Film> films, int daysLate) {
        if (films != null) {
            for (Film film : films) {
                returnFilm(film, daysLate);
            }
        }
    }

    /**
     * Method for returning multiple films on time.
     * @param films ArrayList of films to be returned.
     */
    public void returnFilm(ArrayList<Film> films) {
        if (films != null) {
            for (Film film : films) {
                returnFilm(film);
            }
        }
    }

    /**
     * Method for returning a single film
     * @param film to be returned back to store.
     * @param daysLate number of days the film is late.
     */
    public void returnFilm(Film film, int daysLate) {
        if (film != null) {
            if (daysLate >= 0) {
                if (film.isInStoreInventory()) {
                    if (film.isCurrentlyRented()) {
                        film.calculatePrice(daysLate);
                        this.totalPrice += film.getPrice();
                        film.setCurrentlyRented(false);
                        System.out.println(film.getFilmReturnInfo());
                    } else {
                        throw new FilmRentalException("Can't return " + film.getTitleAndType() + " because it is not rented!");
                    }
                } else {
                    throw new FilmNotFoundInTheStoreException("Film " + film.getTitleAndType() + " is not in the store inventory!");
                }
            } else {
                throw new IllegalArgumentException("Number of days can't be negative when returning films!");
            }
        }
    }

    /**
     * Method for returning a single film on time.
     * @param film to be returned back to store.
     */
    public void returnFilm(Film film) {
        if (film != null) {
            if (film.isInStoreInventory()) {
                if (film.isCurrentlyRented()) {
                    film.setPrice(0);
                    film.setDays(0);
                    this.totalPrice += film.getPrice();
                    film.setCurrentlyRented(false);
                    System.out.println(film.getFilmReturnInfo());
                } else {
                    throw new FilmRentalException("Can't return " + film.getTitleAndType() + " because it is not rented!");
                }
            } else {
                throw new FilmNotFoundInTheStoreException("Film " + film.getTitleAndType() + " is not in the store inventory!");
            }
        }
    }

    /**
     * Getter for customer's bonus points.
     * @return integer of bonus points.
     */
    public int getBonusPoints() {
        return bonusPoints;
    }

    /**
     * Sets bonus points for customer.
     * @param bonusPoints number of points to be
     *                    added to customer.
     */
    public void setBonusPoints(int bonusPoints) {
        if (bonusPoints <= 0) {
            this.bonusPoints = 0;
        } else {
            this.bonusPoints = bonusPoints;
        }
    }

    /**
     * Gets total price when renting films.
     * @return integer of total price.
     */
    public int getTotalPrice() {
        return totalPrice;
    }

    /**
     * Takes customer's name and bonus points
     * which will be made into tidy String.
     * @return tidy String of info about customer's
     * name and bonus points.
     */
    @Override
    public String toString() {
        return "Customer " + name + " has " + bonusPoints + " remaining bonus point(s)";
    }
}
