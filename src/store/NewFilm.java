package store;

public class NewFilm extends RegularFilm {

    /**
     * Basic price for a New Film which is
     * called premium price and it is 4 EUR.
     */
    private final int PREMIUM_PRICE = 4;

    /**
     * Constructor for a new film which uses super
     * method and refers to its superclass RegularFilm
     * @param title film's name which will let you
     *              identify different films.
     */
    public NewFilm(String title) {
        super(title);
    }

    /**
     * Calculates film's price. Film's price
     * is always 4 EUR times number of days.
     * @param days number of days film is rented
     */
    @Override
    public void calculatePrice(int days) {
        if (days <= 0) {
            setPrice(0);
            setDays(0);
        } else {
            setPrice(PREMIUM_PRICE * days);
            setDays(days);
        }
    }

}
