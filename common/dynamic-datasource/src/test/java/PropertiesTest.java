import com.springboot.cloud.properties.PropertiesManager;

public class PropertiesTest {
    public static void main(String[] args) {
       String val = PropertiesManager.getString("clean.sc_product.driverClassName");
       System.out.println(val);
       String val2 = PropertiesManager.getString("clean.db.connections");
       System.out.println(val2);
    }
}
