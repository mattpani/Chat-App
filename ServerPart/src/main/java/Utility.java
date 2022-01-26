import ciphers.Ciphers;
import ciphers.RSA;
import ciphers.RightRightShift;
import ciphers.RightShift;

import java.util.HashMap;
import java.util.Vector;

public class Utility {
    public final static String jdbcURL = "jdbc:postgresql://localhost:5432/postgres";
    public final static String dbUser = "postgres";
    public final static String dbPassword = "123456";
    public static HashMap<String, Ciphers> cipherMap;

    public void initHashMap() {
        this.cipherMap = new HashMap<>();
        this.cipherMap.put("RightRightShift", new RightRightShift());
        this.cipherMap.put("RightShift", new RightShift());
        this.cipherMap.put("RSA", new RSA());
    }

}
