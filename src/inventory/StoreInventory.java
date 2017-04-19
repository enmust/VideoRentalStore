package inventory;

import store.Film;
import store.NewFilm;
import store.OldFilm;
import store.RegularFilm;

import java.util.ArrayList;

public class StoreInventory implements Inventory {

    /** ArrayList of all the films
     *  in the store inventory
     */
    private ArrayList<Film> allFilms = new ArrayList<>();

    /**
     * Constructor for creating store inventory which
     * takes ArrayList<Films> for its argument. You can
     * add films to invnetory using 'add()' method.
     * This constructor is just more comfortable because
     * you can initialise an inventory with existing
     * list of films.
     * @param allFilms list of films which
     *                 go into store inventory
     */
    public StoreInventory(ArrayList<Film> allFilms) {
        for (Film film : allFilms) {
            film.setInStoreInventory(true);
            this.allFilms.add(film);
        }
    }

    /**
     * Empty constructor for creating empty store
     * inventory. You can add films to inventory
     * using 'add()' method.
     */
    public StoreInventory() {

    }

    @Override
    public void addFilm(Film film) {
        if (film != null) {
            if (!allFilms.contains(film)) {
                film.setInStoreInventory(true);
                allFilms.add(film);
                System.out.println("Added " + film.getTitleAndType() + " to store inventory");
            } else {
                throw new FilmAlreadyInTheStoreException("Film " + film.getTitleAndType() + " is already in the store inventory!");
            }
        }
    }

    @Override
    public void removeFilm(Film film) {
        if (film != null) {
            if (allFilms.contains(film)) {
                film.setInStoreInventory(false);
                allFilms.remove(film);
                System.out.println("Removed " + film.getTitleAndType() + " from store inventory");
            } else {
                throw new FilmNotFoundInTheStoreException("Film " + film.getTitleAndType() + " is not in the store inventory!");
            }
        }
    }

    @Override
    public void changeFilmType(Film film, String type) {
        if (film != null) {
            if (allFilms.contains(film)) {
                if (type.equals("New")) {
                    allFilms.remove(film);
                    allFilms.add(new NewFilm(film.getTitle()));
                } else if (type.equals("Old")) {
                    allFilms.remove(film);
                    allFilms.add(new OldFilm(film.getTitle()));
                } else if (type.equals("Regular")) {
                    allFilms.remove(film);
                    allFilms.add(new RegularFilm(film.getTitle()));
                } else {
                    throw new UnknownFilmTypeException(
                            "Unknown type: "
                                    + type
                                    + " Please use one of the following types: 'Regular', 'New', 'Old'");
                }
            }
        }
    }

    @Override
    public ArrayList<Film> listAllFilms() {
        ArrayList<Film> listOfAllTheFilms = new ArrayList<>();
        for (Film film : allFilms) {
            listOfAllTheFilms.add(film);
        }
        return listOfAllTheFilms;
    }

    @Override
    public ArrayList<Film> listAllFilmsInStore() {
        ArrayList<Film> filmsCurrentlyAvailableForRent = new ArrayList<>();
        for (Film film : allFilms) {
            if (!film.isCurrentlyRented()) {
                filmsCurrentlyAvailableForRent.add(film);
            }
        }
        return filmsCurrentlyAvailableForRent;
    }
}
