package store;

public class RegularFilm implements Film {

    /**
     * Basic price for a Regular Film
     */
    int BASIC_PRICE = 3;
    /**
     * Time(days) how long applies
     * the Basic price for a film
     */
    int LIMIT = 3;

    /**
     * Film title which is unique
     */
    private String title;
    /**
     * Film price
     */
    private int price;
    /**
     * Shows amount of days the film is rented
     */
    private int days;
    /**
     * Boolean value which shows if
     * film is currently rented or not
     */
    private boolean isCurrentlyRented;
    /**
     * Boolean value which shows if film
     * is in the store inventory or not
     */
    private boolean isInStoreInventory;

    /**
     * Constructor for a regular film which creates a new regular film object
     * and sets both isCurrentlyRented and isInStoreInventroy statuses to false
     * @param title film's name which will let you identify different films.
     */
    public RegularFilm(String title) {
        this.title = title;
        this.isCurrentlyRented = false;
        this.isInStoreInventory = false;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public boolean isCurrentlyRented() {
        return isCurrentlyRented;
    }

    /**
     * Calculates film's price. Until hitting the limit which is
     * 3 days the price stays at 3 EUR after that every single
     * day adds 3 more euros to price.
     * @param days number of days film is rented
     */
    @Override
    public void calculatePrice(int days) {
        if (days <= 0) {
            this.price = 0;
            this.days = 0;
        } else if (days <= LIMIT) {
            this.price = BASIC_PRICE;
            this.days = days;
        } else if (days > LIMIT) {
            this.price = BASIC_PRICE + (days - LIMIT) * BASIC_PRICE;
            this.days = days;
        }
    }

    @Override
    public String getTitleAndType() {
        return getTitle() + " (" + getClass().getSimpleName() + ")";
    }

    @Override
    public String getFilmRentalInfo() {
        return getTitleAndType() + " " + getDays() + " day(s) " + getPrice() + " EUR";
    }

    @Override
    public String getFilmReturnInfo() {
        return getTitleAndType() + " " + getDays() + " extra day(s) " + getPrice() + " EUR";
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public void setCurrentlyRented(boolean currentlyRented) {
        isCurrentlyRented = currentlyRented;
    }

    @Override
    public int getDays() {
        return days;
    }

    @Override
    public boolean isInStoreInventory() {
        return isInStoreInventory;
    }

    @Override
    public void setInStoreInventory(boolean inStoreInventory) {
        isInStoreInventory = inStoreInventory;
    }

    @Override
    public void setPrice(int price) {
        if (price <= 0) {
            this.price = 0;
        } else {
            this.price = price;
        }
    }

    @Override
    public void setDays(int days) {
        if (days <= 0) {
            this.days = 0;
        } else {
            this.days = days;
        }
    }



}
