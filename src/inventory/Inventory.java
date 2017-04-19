package inventory;

import store.Film;

import java.util.ArrayList;

public interface Inventory {

    /**
     * Adds film to inventory
     * @param film to be added into store inventory
     */
    void addFilm(Film film);

    /**
     * Removes film from inventory
     * @param film to be removed from store inventory
     */
    void removeFilm(Film film);

    /**
     * Changes film type
     * @param film which type will be changed
     * @param type which film will be changed to
     */
    void changeFilmType(Film film, String type);

    /**
     * Lists all the films which
     * are in the store inventory.
     * @return ArrayList of all the films
     * in the store inventory.
     */
    ArrayList<Film> listAllFilms();

    /**
     * Lists all films which
     * are not rented at the moment
     * in other words shows films which
     * are available for rent.
     * @return ArrayList of all the films
     * currently available for rent.
     */
    ArrayList<Film> listAllFilmsInStore();

}
