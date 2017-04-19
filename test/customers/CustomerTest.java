package customers;

import inventory.FilmNotFoundInTheStoreException;
import inventory.StoreInventory;
import inventory.UnknownFilmTypeException;
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

public class CustomerTest {

    private Customer customer1;
    private StoreInventory storeInventory;
    private NewFilm newFilm;
    private RegularFilm regularFilm;
    private OldFilm oldFilm;

    @BeforeEach
    public void runBeoreEachTest() {
        customer1 = new Customer("Enar", 0);
        storeInventory = new StoreInventory();
        newFilm = new NewFilm("Matrix 11");
        regularFilm = new RegularFilm("Spider man");
        oldFilm = new OldFilm("Out of africa");
    }

    @Test
    void testAddingBonusPointsWhenNewFilm() {
        customer1.addBonusPoints(newFilm);
        assertEquals(2, customer1.getBonusPoints());
    }

    @Test
    void testAddingBonusPointsWhenRegularFilm() {
        customer1.addBonusPoints(regularFilm);
        assertEquals(1, customer1.getBonusPoints());
    }

    @Test
    void testAddingBonusPointsWhenOldFilm() {
        customer1.addBonusPoints(oldFilm);
        assertEquals(1, customer1.getBonusPoints());
    }

    @Test
    void testAddingBonusPointsForMultipleFilms() {
        customer1.addBonusPoints(newFilm);
        customer1.addBonusPoints(newFilm);
        customer1.addBonusPoints(regularFilm);
        customer1.addBonusPoints(regularFilm);
        customer1.addBonusPoints(oldFilm);
        assertEquals(7, customer1.getBonusPoints());
    }

    @Test
    void testRentingArrayOfFilmsForNegativeDays() {
        ArrayList<Film> films = new ArrayList<>(Arrays.asList(regularFilm, newFilm, oldFilm));
        storeInventory.addFilm(regularFilm);
        storeInventory.addFilm(oldFilm);
        storeInventory.addFilm(newFilm);
        assertThrows(IllegalArgumentException.class, () -> customer1.rent(films, -10));
        assertEquals(false, regularFilm.isCurrentlyRented());
        assertEquals(false, oldFilm.isCurrentlyRented());
        assertEquals(false, newFilm.isCurrentlyRented());
    }

    @Test
    void testRentingArrayOfFilms() {
        ArrayList<Film> films = new ArrayList<>(Arrays.asList(regularFilm, newFilm, oldFilm));
        storeInventory.addFilm(regularFilm);
        storeInventory.addFilm(oldFilm);
        storeInventory.addFilm(newFilm);
        customer1.rent(films, 5);
        assertEquals(32, customer1.getTotalPrice());
        assertEquals(true, regularFilm.isCurrentlyRented());
        assertEquals(true, oldFilm.isCurrentlyRented());
        assertEquals(true, newFilm.isCurrentlyRented());
    }

    @Test
    void testRentingEmptyArrayOfFilms() {
        ArrayList<Film> films = null;
        customer1.rent(films, 5);
        assertEquals(0, customer1.getTotalPrice());
    }

    @Test
    void testRentingFilmForNegativeDays() {
        storeInventory.addFilm(newFilm);
        assertThrows(IllegalArgumentException.class, () -> customer1.rent(newFilm, -10));
        assertEquals(false, newFilm.isCurrentlyRented());
    }

    @Test
    void testRentingFilmForPositiveDays() {
        storeInventory.addFilm(newFilm);
        customer1.rent(newFilm, 5);
        assertEquals(20, customer1.getTotalPrice());
        assertEquals(true, newFilm.isCurrentlyRented());
    }

    @Test
    void testRentingWhenFilmIsNull() {
        NewFilm film = null;
        customer1.rent(film, 5);
        assertEquals(0, customer1.getTotalPrice());
    }

    @Test
    void testRentingWhenFilmIsNotInStore() {
        assertThrows(FilmNotFoundInTheStoreException.class, () -> customer1.rent(newFilm, 3));
    }

    @Test
    void testRentingWhenFilmIsCurrentlyRented() {
        storeInventory.addFilm(newFilm);
        newFilm.setCurrentlyRented(true);
        assertThrows(FilmRentalException.class, () -> customer1.rent(newFilm, 3));
    }

    @Test
    void testPayingWhenTotalPriceIsZero() {
        customer1.pay();
        assertEquals(0, customer1.getTotalPrice());
    }

    @Test
    void testPayingForRentingFilms() {
        storeInventory.addFilm(newFilm);
        customer1.rent(newFilm, 5);
        customer1.pay();
        assertEquals(0, customer1.getTotalPrice());
    }

    @Test
    void testPayingForReturningFilms() {
        storeInventory.addFilm(newFilm);
        customer1.rent(newFilm, 2);
        customer1.returnFilm(newFilm, 9);
        customer1.pay();
        assertEquals(0, customer1.getTotalPrice());
    }

    @Test
    void testPayingWithLessThan25BonusPoints() {
        storeInventory.addFilm(newFilm);
        customer1.rent(newFilm, 1);
        customer1.payWithBonusPoints(newFilm);
        assertEquals(4, newFilm.getPrice());
    }

    @Test
    void testPayingWithMoreThan25BonusPoints() {
        storeInventory.addFilm(newFilm);
        customer1.setBonusPoints(30);
        customer1.rent(newFilm, 1);
        customer1.payWithBonusPoints(newFilm);
        assertEquals(0, customer1.getTotalPrice());
    }

    @Test
    void testPayingWithBonusPointsWhenFilmTypeIsNotNewFilm() {
        storeInventory.addFilm(oldFilm);
        oldFilm.setInStoreInventory(true);
        customer1.setBonusPoints(30);
        customer1.rent(oldFilm, 1);
        assertThrows(UnknownFilmTypeException.class, () -> customer1.payWithBonusPoints(oldFilm));
    }

    @Test
    void testPayingWithBonusPointsWhenFilmIsNotInStore() {
        customer1.setBonusPoints(30);
        assertThrows(FilmNotFoundInTheStoreException.class, () -> customer1.payWithBonusPoints(oldFilm));
    }

    @Test
    void testPayingWithBonusPointsWhenFilmIsNotRented() {
        storeInventory.addFilm(oldFilm);
        customer1.setBonusPoints(30);
        assertThrows(FilmRentalException.class, () -> customer1.payWithBonusPoints(oldFilm));
    }

    @Test
    void testPayingWithBonusPointsUntilOutOfBonusPoints() {
        storeInventory.addFilm(newFilm);
        customer1.setBonusPoints(250);
        customer1.rent(newFilm, 25);
        customer1.payWithBonusPoints(newFilm);
        assertEquals(60, customer1.getTotalPrice());
        assertEquals(2, customer1.getBonusPoints());
    }

    @Test
    void testPayingWithBonusPointsUntilPriceIsZero() {
        storeInventory.addFilm(newFilm);
        customer1.setBonusPoints(1000);
        customer1.rent(newFilm, 10);
        customer1.payWithBonusPoints(newFilm);
        assertEquals(0, customer1.getTotalPrice());
        assertEquals(752, customer1.getBonusPoints());
    }

    @Test
    void testReturningArrayOfFilmForNegativeDays() {
        ArrayList<Film> films = new ArrayList<>(Arrays.asList(regularFilm, newFilm, oldFilm));
        storeInventory.addFilm(regularFilm);
        storeInventory.addFilm(newFilm);
        storeInventory.addFilm(oldFilm);
        assertThrows(IllegalArgumentException.class, () -> {
            customer1.rent(films, 1);
            customer1.returnFilm(films, -5);
        });
        assertEquals(true, regularFilm.isCurrentlyRented());
        assertEquals(true, oldFilm.isCurrentlyRented());
        assertEquals(true, newFilm.isCurrentlyRented());
    }

    @Test
    void testReturningArrayOfFilmsDaysLate() {
        ArrayList<Film> films = new ArrayList<>(Arrays.asList(regularFilm, newFilm, oldFilm));
        storeInventory.addFilm(regularFilm);
        storeInventory.addFilm(oldFilm);
        storeInventory.addFilm(newFilm);
        regularFilm.setCurrentlyRented(true);
        newFilm.setCurrentlyRented(true);
        oldFilm.setCurrentlyRented(true);
        customer1.returnFilm(films, 5);
        assertEquals(32, customer1.getTotalPrice());
        assertEquals(false, regularFilm.isCurrentlyRented());
        assertEquals(false, oldFilm.isCurrentlyRented());
        assertEquals(false, newFilm.isCurrentlyRented());
    }

    @Test
    void testReturningArrayOfFilmsOnTime() {
        ArrayList<Film> films = new ArrayList<>(Arrays.asList(regularFilm, newFilm, oldFilm));
        storeInventory.addFilm(regularFilm);
        storeInventory.addFilm(oldFilm);
        storeInventory.addFilm(newFilm);
        regularFilm.setCurrentlyRented(true);
        newFilm.setCurrentlyRented(true);
        oldFilm.setCurrentlyRented(true);
        customer1.returnFilm(films);
        assertEquals(0, customer1.getTotalPrice());
        assertEquals(false, regularFilm.isCurrentlyRented());
        assertEquals(false, oldFilm.isCurrentlyRented());
        assertEquals(false, newFilm.isCurrentlyRented());
    }

    @Test
    void testReturningFilmWhenFilmIsNotInStoreInventory() {
        assertThrows(FilmNotFoundInTheStoreException.class, () -> customer1.returnFilm(regularFilm));
    }

    @Test
    void testReturningFilmWhenFilmIsNotInStoreInventoryDaysLate() {
        assertThrows(FilmNotFoundInTheStoreException.class, () -> customer1.returnFilm(regularFilm, 5));
    }

    @Test
    void testReturningFilmsWhenFilmIsNotCurrentlyRented() {
        newFilm.setInStoreInventory(true);
        assertThrows(FilmRentalException.class, () -> customer1.returnFilm(newFilm));
    }

    @Test
    void testReturningFilmsWhenFilmIsNotCurrentlyRentedDaysLate() {
        newFilm.setInStoreInventory(true);
        assertThrows(FilmRentalException.class, () -> customer1.returnFilm(newFilm, 5));
    }

    @Test
    void testReturningNullFilm() {
        regularFilm = null;
        customer1.returnFilm(regularFilm, 2);
        assertEquals(0, customer1.getTotalPrice());
    }

    @Test
    void testReturningArrayOfNullFilms() {
        ArrayList<Film> films = null;
        customer1.returnFilm(films, 5);
        assertEquals(0, customer1.getTotalPrice());

    }

    @Test
    void testSettingNegativeBonusPoints() {
        customer1.setBonusPoints(-550);
        assertEquals(0, customer1.getBonusPoints());
    }

    @Test
    void testSettingPositiveAmountOfBonusPoints() {
        customer1.setBonusPoints(55);
        assertEquals(55, customer1.getBonusPoints());
    }

    @Test
    void testCreatingNewCustomerWithPositiveAmountOfBonusPoints() {
        Customer customer = new Customer("Enar", 10);
        assertEquals(10, customer.getBonusPoints());
    }

    @Test
    void testToStringMethod() {
        assertEquals("Customer Enar has 0 remaining bonus point(s)", customer1.toString());
    }

}
