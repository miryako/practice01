import tables.Staff;

import javax.naming.Context;
import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Beta {
    public static String checkInputText(String text, boolean noSpace) {
        if (noSpace) text = text.trim().replaceAll("\\s+", "");
        else {
            while (text.endsWith(" ") || text.endsWith("\n")) text = text.substring(0, text.length() - 1);
            while (text.startsWith(" ") || text.startsWith("\n")) text = text.substring(1, text.length());
        }

        return text;
    }

    public static void sendMessage(Component component, String title, String text) {
        JOptionPane.showMessageDialog(component, text, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static String getOneElementFromDB(Statement statement, String query, String element) {
        try {
            ResultSet resultSet = statement.executeQuery(query);

            return resultSet.getString(element);


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

}
