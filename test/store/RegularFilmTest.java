package store;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegularFilmTest {

    private RegularFilm regularFilm;

    @BeforeEach
    public void runBeforeEachTest() {
        regularFilm = new RegularFilm("Logan");
    }

    @Test
    void testGetFilmTitle() {
        assertEquals("Logan", regularFilm.getTitle());
    }

    @Test
    void testIfFilmIsCurrentlyRented() {
        assertEquals(false, regularFilm.isCurrentlyRented());
    }

    @Test
    void testPriceCalculationWhenZeroDays() {
        regularFilm.calculatePrice(0);
        assertEquals(0, regularFilm.getPrice());
    }

    @Test
    void testPriceCalculationWhenNegativeDays() {
        regularFilm.calculatePrice(-5);
        assertEquals(0, regularFilm.getPrice());
    }

    @Test
    void testPriceCalculationWhenUnderTheLimit() {
        regularFilm.calculatePrice(2);
        assertEquals(3, regularFilm.getPrice());
    }

    @Test
    void testPriceCalculationWhenOverTheLimit() {
        regularFilm.calculatePrice(5);
        assertEquals(9, regularFilm.getPrice());
    }

    @Test
    void testGettingFilmTitleAndTypeString() {
        assertEquals("Logan (RegularFilm)", regularFilm.getTitleAndType());
    }

    @Test
    void testGettingFilmPrice() {
        assertEquals(0, regularFilm.getPrice());
    }

    @Test
    void testGettingFilmRentalInfo() {
        assertEquals("Logan (RegularFilm) 0 day(s) 0 EUR", regularFilm.getFilmRentalInfo());
    }

    @Test
    void testGettingFilmReturnInfo() {
        assertEquals("Logan (RegularFilm) 0 extra day(s) 0 EUR", regularFilm.getFilmReturnInfo());
    }

    @Test
    void testSettingNegativePriceForFilm() {
        regularFilm.setPrice(-150);
        assertEquals(0, regularFilm.getPrice());
    }

    @Test
    void testSettingCorrectFilmPrice() {
        regularFilm.setPrice(150);
        assertEquals(150, regularFilm.getPrice());
    }

    @Test
    void testSettingFilmRentalStatus() {
        regularFilm.setCurrentlyRented(true);
        assertEquals(true, regularFilm.isCurrentlyRented());
    }

    @Test
    void testGettingTheNumberOfDaysTheFilmIsRented() {
        assertEquals(0, regularFilm.getDays());
    }

    @Test
    void testSettingNegativeRentDaysForFilm() {
        regularFilm.setDays(-5);
        assertEquals(0, regularFilm.getDays());
    }

    @Test
    void testSettingCorrectRentDaysForFilm() {
        regularFilm.setDays(7);
        assertEquals(7, regularFilm.getDays());
    }

    @Test
    void testGettingFilmsStoreInventoryState() {
        assertEquals(false, regularFilm.isInStoreInventory());
    }

    @Test
    void testSettingFilmsStoreInventoryState() {
        regularFilm.setInStoreInventory(true);
        assertEquals(true, regularFilm.isInStoreInventory());
    }

}
