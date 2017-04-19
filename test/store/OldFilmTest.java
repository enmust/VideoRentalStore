package store;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OldFilmTest {

    private OldFilm oldFilm;

    @BeforeEach
    public void runBeforeEachTest() {
        oldFilm = new OldFilm("Logan");
    }

    @Test
    void testPriceCalculationWhenZeroDays() {
        oldFilm.calculatePrice(0);
        assertEquals(0, oldFilm.getPrice());
    }

    @Test
    void testPriceCalculationWhenNegativeDays() {
        oldFilm.calculatePrice(-1);
        assertEquals(0, oldFilm.getPrice());
    }

    @Test
    void testPriceCalculationWhenUnderTheLimit() {
        oldFilm.calculatePrice(4);
        assertEquals(3, oldFilm.getPrice());
    }

    @Test
    void testPriceCalculationWhenOverTheLimit() {
        oldFilm.calculatePrice(7);
        assertEquals(9, oldFilm.getPrice());
    }

}
