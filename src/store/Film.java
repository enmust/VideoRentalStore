package store;

public interface Film {

    /**
     * Calculates price for a film
     * @param days number of days film is rented
     */
    void calculatePrice(int days);

    /**
     * Gets film title and class into one String
     * @return film title and class in one String
     */
    String getTitleAndType();

    /** Combines film title, type, days rented
     *  and price into one string
     *  @return tidy String of film rental info
     */
    String getFilmRentalInfo();

    /** Combines film title, type, days rented
     *  and price into one string.
     *  Only difference from getFilmRentalInfo()
     *  is the word 'extra' before 'day(s)'.
     *  @return tidy String of film return info
     */
    String getFilmReturnInfo();

    /**
     * Getter for film price
     * @return film price
     */
    int getPrice();

    /** Getter for the number of days
     *  the film is rented for
     * @return film's renting period aka number of days rented
     */
    int getDays();

    /**
     * Getter for film title
     * @return film title
     */
    String getTitle();

    /**
     * Checks if film is currently rented or not.
     * @return boolean value for film rental state
     */
    boolean isCurrentlyRented();

    /**
     * Setter for film's rental state.
     * @param currentlyRented boolean which sets film's rental state
     */
    void setCurrentlyRented(boolean currentlyRented);

    /**
     * Checks if film is in the store inventory.
     * e.g. if false then film is not in the store and
     * further actions with that film are unavailable.
     * @return boolean value for film store inventory state
     */
    boolean isInStoreInventory();

    /**
     * Sets film's state in the store.
     * @param inStoreInventory boolean value which changes film's state in the store
     */
    void setInStoreInventory(boolean inStoreInventory);

    /**
     * Setter for a film price
     * @param price to be set for a film
     */
    void setPrice(int price);

    /** Sets the amount of days
     *  the film is rented for
     *  @param days amount of time film is rented
     */
    void setDays(int days);
}
