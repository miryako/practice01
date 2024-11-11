import java.sql.*;

public class App {
    public static String url = "jdbc:sqlite:C:\\Users\\mironiko\\OneDrive\\Рабочий стол\\myPharmacy.db";
    public static Statement statement;

    static LoginWindow loginWindow;

    public static void main(String[] args) {
        conectToSQLite();

        loginWindow = new LoginWindow();
        loginWindow.setVisible(true);
    }

    static void conectToSQLite() {
        try {
            Connection connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void openMainMenu() {
        loginWindow.setVisible(false);
        MainWindow mainWindow = new MainWindow();
        mainWindow.setVisible(true);
    }
}
