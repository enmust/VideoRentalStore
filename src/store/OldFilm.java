package store;

public class OldFilm extends RegularFilm {

    /**
     * Constructor for an old film which uses super
     * method and refers to its superclass RegularFilm.
     * Also OldFilm has a Limit which is 5 days instead of
     * 3 days the RegularFilm has.
     * @param title film's name which will let you
     *              identify different films.
     */
    public OldFilm(String title) {
        super(title);
        BASIC_PRICE = 3;
        LIMIT = 5;
    }

}
