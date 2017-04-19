package store;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NewFilmTest {

    private NewFilm newFilm;

    @BeforeEach
    public void runBeforeEachTest() {
        newFilm = new NewFilm("Logan");
    }

    @Test
    void testPriceCalculationWhenZeroDays() {
        newFilm.calculatePrice(0);
        assertEquals(0, newFilm.getPrice());
    }

    @Test
    void testPriceCalculationWhenNegativeDays() {
        newFilm.calculatePrice(-150);
        assertEquals(0, newFilm.getPrice());
    }

    @Test
    void testPriceCalculationWhenPositiveDays() {
        newFilm.calculatePrice(20);
        assertEquals(80, newFilm.getPrice());
    }

}
