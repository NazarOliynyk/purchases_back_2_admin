package taskforinternship.purchases_back.restTemplates;

import org.junit.*;
import taskforinternship.purchases_back.models.CurrencyType;
import taskforinternship.purchases_back.models.Purchase;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MainRestTemplateTest {
    private MainRestTemplate mainRestTemplate;

    @BeforeClass
    public static void beforeClass() {
        System.out.println("Before Test");
    }

    @AfterClass
    public  static void afterClass() {
        System.out.println("After Test");
    }

    @Before
    public void initTest() {
        mainRestTemplate = new MainRestTemplate();
        System.out.println("Class created");
    }

    @After
    public void afterTest() {
        mainRestTemplate = null;
        System.out.println("Class nullified");
    }

    @Test
    public void read() throws ParseException, IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Purchase purchase = Purchase.builder().
                date(sdf.parse("2019-05-20")).
                currency(CurrencyType.UAH).build();

        double uah = mainRestTemplate.read(purchase, CurrencyType.UAH);
        double eur = mainRestTemplate.read(purchase, CurrencyType.EUR);
        double usd = mainRestTemplate.read(purchase, CurrencyType.USD);
        double pln = mainRestTemplate.read(purchase, CurrencyType.PLN);
        System.out.println("------ The cross-rates UAH to other currencies: -------");
        System.out.println(uah);
        System.out.println(eur);
        System.out.println(usd);
        System.out.println(pln);
        System.out.println("--------------------------------------------------------");
    }
}
