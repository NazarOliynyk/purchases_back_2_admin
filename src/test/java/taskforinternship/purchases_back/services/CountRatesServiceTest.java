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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CountRatesServiceTest {

    @Autowired
    private CountRatesService countRatesService;
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

    @Before
    public void initTest() throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Purchase p1 = Purchase.builder().
                price(100.00).
                date(sdf.parse("2019-05-20")).
                currency(CurrencyType.UAH).build();
        Purchase p2 = Purchase.builder().
                price(100.00).
                date(sdf.parse("2019-04-04")).
                currency(CurrencyType.EUR).build();
        Purchase p3 = Purchase.builder().
                price(100.00).
                date(sdf.parse("2019-03-23")).
                currency(CurrencyType.USD).build();
        Purchase p4 = Purchase.builder().
                price(100.00).
                date(sdf.parse("2019-02-15")).
                currency(CurrencyType.PLN).build();
        List<Purchase> purchaseList = Arrays.asList(p1, p2, p3, p4);
        Mockito.ignoreStubs();
        when(purchaseDAO.findAllByUserId(1)).thenReturn(purchaseList);

    }

    @After
    public void afterTest() {
        countRatesService = null;
        System.out.println("Class nullified");
    }


    @Test
    public void count()  {

        double sumUAH = countRatesService.count("2019", 1, CurrencyType.UAH);
        double sumEUR = countRatesService.count("2019", 1, CurrencyType.EUR);
        double sumUSD = countRatesService.count("2019", 1, CurrencyType.USD);
        double sumPLN = countRatesService.count("2019", 1, CurrencyType.PLN);
        double sum2 = countRatesService.count("2009", 1, CurrencyType.EUR);

        System.out.println("----When there are purchases of the given year------------ ");
        System.out.println("sumUAH: "+sumUAH);
        System.out.println("sumEUR: "+sumEUR);
        System.out.println("sumUSD: "+sumUSD);
        System.out.println("sumPLN: "+sumPLN);
        System.out.println("----When there are NO purchases of the given year-------- ");
        System.out.println(sum2);
        System.out.println("--------------------------------------------------------");
    }
}
