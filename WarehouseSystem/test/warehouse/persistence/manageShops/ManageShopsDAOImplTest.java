package warehouse.persistence.manageShops;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import warehouse.shared.transferObjects.Shop;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ManageShopsDAOImplTest {

    ManageShopsDAOImpl shopsDAO;

    @BeforeEach
    public void arrange() {
        shopsDAO = ManageShopsDAOImpl.getInstance();
        shopsDAO.deleteAllShops();
    }

    @Test
    public void insertShopIntoTable() {
        // act
        shopsDAO.createShop("Horsens", "Kamtjatka", "8700");
        // assert
        assertTrue(shopsDAO.checkIfShopExists("Horsens", "Kamtjatka"));
    }

    @Test
    public void insertShopsIntoTable() {
        // act
        shopsDAO.createShop("Horsens", "Kamtjatka", "8700");
        shopsDAO.createShop("Aarhus", "Cezarygade", "8000");
        shopsDAO.createShop("Aarhus", "Mariavej", "8000");
        // assert
        assertTrue(shopsDAO.checkIfShopExists("Horsens", "Kamtjatka"));
        assertTrue(shopsDAO.checkIfShopExists("Aarhus", "Cezarygade"));
        assertTrue(shopsDAO.checkIfShopExists("Aarhus", "Mariavej"));
    }

    @Test
    public void readShopsFromTable() {
        //arrange
        Shop shop1 = new Shop("Horsens", "Kamtjatka", "8700");
        Shop shop2 = new Shop("Aarhus", "Cezarygade", "8000");
        Shop shop3 = new Shop("Aarhus", "Mariavej", "8000");

        //act
        shopsDAO.createShop(shop1.getCity(), shop1.getStreet(), shop1.getZipCode());
        shopsDAO.createShop(shop2.getCity(), shop2.getStreet(), shop2.getZipCode());
        shopsDAO.createShop(shop3.getCity(), shop3.getStreet(), shop3.getZipCode());
        ArrayList<Shop> readShops = shopsDAO.getAllShops();

        //assert
        assertTrue(readShops.get(0).equalsWithOudId(shop1)
            &&readShops.get(1).equalsWithOudId(shop2)
            &&readShops.get(2).equalsWithOudId(shop3));
    }

//    @Test
//    public void deleteShop() {
//        //arrange
//        Shop shop1 = new Shop("Horsens", "Kamtjatka", "8700");
//        shopsDAO.createShop(shop1.getCity(), shop1.getStreet(), shop1.getZipCode());
//
//        //act
//        shopsDAO.deleteShop(shop1);
//
//        //assert
//        assertTrue();
//    }
}