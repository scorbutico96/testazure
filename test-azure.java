import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class VulnerableApp {

    // Metodo con vulnerabilità SQL Injection
    public static void vulnerableSQLInjection(String username) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "password");
            Statement stmt = conn.createStatement();
            
            // Vulnerabile a SQL Injection
            String query = "SELECT * FROM users WHERE username = '" + username + "'";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                System.out.println("User: " + rs.getString("username"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Metodo vulnerabile a XSS
    public static void vulnerableXSS(String input) {
        System.out.println("User input: " + input);  // Potenziale riflessione di input non sanitizzato
    }

    // Metodo con hardcoded credentials (vulnerabilità)
    public static void hardCodedCredentials() {
        String password = "supersecret123";  // Credenziali hardcoded
        System.out.println("Password: " + password);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a username for SQL query:");
        String username = scanner.nextLine();
        vulnerableSQLInjection(username);

        System.out.println("Enter some text for output (XSS test):");
        String input = scanner.nextLine();
        vulnerableXSS(input);
    }
}
