package taskforinternship.purchases_back.services;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import taskforinternship.purchases_back.dao.PurchaseDAO;
import taskforinternship.purchases_back.models.CurrencyType;
import taskforinternship.purchases_back.models.Purchase;
import taskforinternship.purchases_back.models.ResponseTransfer;
import taskforinternship.purchases_back.models.User;
import taskforinternship.purchases_back.services.impl.UserServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PurchaseServiceTest {

    @Autowired
    private PurchaseService purchaseService;
    @MockBean
    private UserServiceImpl userServiceImpl;
    @MockBean
    private PurchaseDAO purchaseDAO;

    @BeforeClass
    public static void beforeClass() {
        System.out.println("Before Test");
    }

    @AfterClass
    public  static void afterClass() {
        System.out.println("After Test");
    }


    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Before
    public void initTest() throws ParseException {
        User user = new User();
        user.setId(1);
        user.setUsername("Nazar");

        Purchase p1 = Purchase.builder().
                name("p1").
                price(100.00).
                date(sdf.parse("2019-05-20")).
                currency(CurrencyType.UAH).build();
        Purchase p2 = Purchase.builder().
                name("p2").
                price(100.00).
                date(sdf.parse("2019-04-04")).
                currency(CurrencyType.EUR).build();
        Purchase p3 = Purchase.builder().
                name("p3").
                price(100.00).
                date(sdf.parse("2019-04-04")).
                currency(CurrencyType.USD).build();
        Purchase p4 = Purchase.builder().
                name("p4").
                price(100.00).
                date(sdf.parse("2019-02-15")).
                currency(CurrencyType.PLN).build();
        List<Purchase> purchaseList = Arrays.asList(p1, p2, p3, p4);
        Mockito.ignoreStubs();
        when(userServiceImpl.findOneById(1)).thenReturn(user);
        when(purchaseDAO.findAllByUserId(1)).thenReturn(purchaseList);

    }

    @After
    public void afterTest() {
        purchaseService = null;
        System.out.println("Class nullified");
    }

    @Test
    public void savePurchase() {
        User user = userServiceImpl.findOneById(1);
        System.out.println("user.getUsername(): "+user.getUsername());
    }

    @Test
    public void findAllByUserId() {
        List<Purchase> purchaseList = purchaseService.findAllByUserId(1);
        System.out.println("-----------Purchases: --------------------");
        purchaseList.forEach(p-> System.out.println(p.toString()));
        System.out.println("-------------------------------------------");
    }

    @Test
    public void deleteAllByUserIdAndDate() throws ParseException {
        Date date1 = sdf.parse("2019-04-04");
        ResponseTransfer responseTransfer1 =
                purchaseService.deleteAllByUserIdAndDate(1, date1);
        Date date2 = sdf.parse("2018-04-04");
        ResponseTransfer responseTransfer2 =
                purchaseService.deleteAllByUserIdAndDate(1, date2);

        System.out.println("------ ResponseOnDelete when given date exists: -------------");
        System.out.println(responseTransfer1.getText());
        System.out.println("------ ResponseOnDelete when given date does not exist: -----");
        System.out.println(responseTransfer2.getText());
        System.out.println("---------------------------------------------------------------");
    }
}
