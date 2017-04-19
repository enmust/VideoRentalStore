package inventory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.Film;
import store.NewFilm;
import store.OldFilm;
import store.RegularFilm;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StoreInventoryTest {

    private StoreInventory storeInventory;
    private StoreInventory emptyStoreInventory;
    private RegularFilm regularFilm = new RegularFilm("Spider man");
    private NewFilm newFilm = new NewFilm("Matrix 11");
    private OldFilm oldFilm = new OldFilm("Out of africa");
    private ArrayList<Film> listOfFilms = new ArrayList<>(Arrays.asList(regularFilm, newFilm));

    @BeforeEach
    public void runBeforeEachTest() {
        storeInventory = new StoreInventory(listOfFilms);
        emptyStoreInventory = new StoreInventory();
    }

    @Test
    void testAddingFilmToEmptyStoreInventory() {
        emptyStoreInventory.addFilm(regularFilm);
        assertEquals(true, regularFilm.isInStoreInventory());
    }

    @Test
    void testAddingSameFilmMultipleTimesToEmptyStoreInventory() {
        assertThrows(FilmAlreadyInTheStoreException.class, () -> {
            emptyStoreInventory.addFilm(regularFilm);
            emptyStoreInventory.addFilm(regularFilm);
        });
    }

    @Test
    void testAddingFilmToExistingStoreInventory() {
        storeInventory.addFilm(oldFilm);
        assertEquals(true, oldFilm.isInStoreInventory());
    }

    @Test
    void testAddingSameFilmToStoreInventory() {
        assertThrows(FilmAlreadyInTheStoreException.class, () -> {
            storeInventory.addFilm(regularFilm);
        });
    }

    @Test
    void testRemovingFilmFromStoreInventory() {
        emptyStoreInventory.addFilm(regularFilm);
        emptyStoreInventory.removeFilm(regularFilm);
        assertEquals(false, regularFilm.isInStoreInventory());
    }

    @Test
    void testRemovingFilmWhenItDoesNotExistInTheStoreInventory() {
        assertThrows(FilmNotFoundInTheStoreException.class, () -> {
            emptyStoreInventory.removeFilm(regularFilm);
            assertEquals(false, regularFilm.isInStoreInventory());
        });
    }

    @Test
    void testChangingTypeWhenFilmIsNotInTheStore() {
        emptyStoreInventory.changeFilmType(newFilm, "Old");
        assertEquals("Matrix 11 (NewFilm)", newFilm.getTitleAndType());
    }

    @Test
    void testChangingFilmTypeFromNewToRegular() {
        emptyStoreInventory.addFilm(new NewFilm("Matrix 11"));
        emptyStoreInventory.changeFilmType(emptyStoreInventory.listAllFilms().get(0), "Regular");
        assertEquals("Matrix 11 (RegularFilm)", emptyStoreInventory.listAllFilms().get(0).getTitleAndType());
    }

    @Test
    void testChangingFilmTypeFromNewToOld() {
        emptyStoreInventory.addFilm(new NewFilm("Matrix 11"));
        emptyStoreInventory.changeFilmType(emptyStoreInventory.listAllFilms().get(0), "Old");
        assertEquals("Matrix 11 (OldFilm)", emptyStoreInventory.listAllFilms().get(0).getTitleAndType());
    }

    @Test
    void testChangingFilmTypeFromRegularToNew() {
        emptyStoreInventory.addFilm(new RegularFilm("Matrix 11"));
        emptyStoreInventory.changeFilmType(emptyStoreInventory.listAllFilms().get(0), "New");
        assertEquals("Matrix 11 (NewFilm)", emptyStoreInventory.listAllFilms().get(0).getTitleAndType());
    }

    @Test
    void testExpectUnknownTypeExceptionWhenEnteringInvalidType() {
        assertThrows(UnknownFilmTypeException.class, () -> {
            emptyStoreInventory.addFilm(new OldFilm("Matrix 11"));
            emptyStoreInventory.changeFilmType(emptyStoreInventory.listAllFilms().get(0), "WrongType");
        });
    }

    @Test
    void testReturnArrayOfAllTheFilmsInTheStore() {
        assertEquals(listOfFilms, storeInventory.listAllFilms());
    }

    @Test
    void testReturnEmptyArrayOfFilmsInTheStore() {
        assertEquals(0, emptyStoreInventory.listAllFilms().size());
    }

    @Test
    void testReturnEmptyArrayWhenRemovingAllTheFilms() {
        emptyStoreInventory.addFilm(regularFilm);
        emptyStoreInventory.addFilm(newFilm);
        emptyStoreInventory.removeFilm(regularFilm);
        emptyStoreInventory.removeFilm(newFilm);
        assertEquals(0, emptyStoreInventory.listAllFilms().size());
    }

    @Test
    void testReturnCorrectArrayOfFilmsWhenRemovingSome() {
        storeInventory.removeFilm(regularFilm);
        assertEquals(newFilm, storeInventory.listAllFilms().get(0));
    }

    @Test
    void testReturnArrayOfCurrentlyAvailableFilms() {
        assertEquals(listOfFilms, storeInventory.listAllFilmsInStore());
    }

    @Test
    void testReturnEmptyArrayWhenAllTheFilmsAreRented() {
        NewFilm rentedFilm = new NewFilm("Logan");
        rentedFilm.setCurrentlyRented(true);
        emptyStoreInventory.addFilm(rentedFilm);
        assertEquals(0, emptyStoreInventory.listAllFilmsInStore().size());
    }

    @Test
    void testReturnCorrectArrayWhenSomeOfFilmsAreRented() {
        NewFilm rentedFilm = new NewFilm("Logan");
        rentedFilm.setCurrentlyRented(true);
        emptyStoreInventory.addFilm(rentedFilm);
        emptyStoreInventory.addFilm(regularFilm);
        assertEquals(1, emptyStoreInventory.listAllFilmsInStore().size());
    }
}
