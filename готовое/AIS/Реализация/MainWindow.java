import tables.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainWindow extends JFrame {
    int x = 10, y = 10, wight = 150, height = 30;

    public static String userRole;
    static boolean admin = false;

    static Statement statement;

    ListPanel listPanel;
    InfoPanel infoPanel;
    ActionPanel actionPanel;
    EditorPanel editorPanel;

    static List<String> idList;


    public MainWindow() {
        setTitle("Моя Аптека (" + userRole + ")");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1110, 700);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);

        listPanel = new ListPanel();
        add(listPanel);
        infoPanel = new InfoPanel();
        add(infoPanel);
        actionPanel = new ActionPanel();
        add(actionPanel);
        if (admin) {
            editorPanel = new EditorPanel();
            add(editorPanel);
        }
        statement = App.statement;
    }

    public static void adminCheck(String role) {
        MainWindow.userRole = role;
        if (Objects.equals(role, "Admin")) admin = true;
    }

    class ListPanel extends JPanel {
        JButton changeBtn;
        JList<String> jList;
        JScrollPane scrollPane;
        boolean orderList = true;

        DefaultListModel<String> listModel;

        public ListPanel() {
            setBackground(Color.WHITE);
            setBounds(10, 10, wight + 20, 640);
            setLayout(null);

            changeBtn = new JButton("Вывод");
            changeBtn.setBackground(Color.white);
            changeBtn.setBounds(10, 10, wight, height);
            add(changeBtn);

            listModel = new DefaultListModel<>();
            jList = new JList<>(listModel);
            scrollPane = new JScrollPane(jList);
            scrollPane.setBounds(10, 20 + height, wight, 500);
            add(scrollPane);

            changeBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    orderList = !orderList;
                    if (orderList) {
                        changeBtn.setText("Ассортимент");
                        loadOrders();
                    } else {
                        changeBtn.setText("Заказы");
                        loadMedicine();
                    }
                }
            });

            jList.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent listSelectionEvent) {
                    int selectedItem = jList.getSelectedIndex();

                    if (selectedItem == -1) {
                        return;
                    }

                    String id = idList.get(selectedItem);
                    if (orderList) {
                        InfoPanel.loadOrderInfoById(id);
                    } else {
                        InfoPanel.loadMedicineInfoById(id);
                    }
                }
            });
        }

        void loadMedicine() {
            idList = new ArrayList<>();
            listModel.removeAllElements();

            String query = "SELECT * FROM Medicine";
            try {
                ResultSet resultSet = App.statement.executeQuery(query);
                while (resultSet.next()) {
                    String id = resultSet.getString("id");
                    String name = resultSet.getString("name");
                    idList.add(id);
                    listModel.addElement(id + ". " + name);
                }
            } catch (SQLException e) {System.out.println(e.getMessage());}
        }

        void loadOrders() {
            idList = new ArrayList<>();
            listModel.removeAllElements();

            String query = "SELECT * FROM \"Order\"";
            try {
                ResultSet resultSet = App.statement.executeQuery(query);
                while (resultSet.next()) {
                    String id = resultSet.getString("id");
                    String date = resultSet.getString("date");
                    idList.add(id);
                    listModel.addElement(id + ". " + date);
                }
            } catch (SQLException e) {System.out.println(e.getMessage());}
        }
    }

    class InfoPanel extends JPanel {
        static JLabel[] labels;
        static JLabel panelName;

        public InfoPanel() {
            setBackground(Color.white);
            setBounds(wight + 40, 10, 530, 300);
            setLayout(null);

            panelName = new JLabel("Информация");
            panelName.setBounds(x, y, wight, height);
            panelName.setForeground(Color.gray);
            add(panelName);

            labels = new JLabel[7];
            for (int i = 0; i < labels.length; i++) {
                labels[i] = new JLabel(String.valueOf(i));
                labels[i].setBounds(x, y + (height * (i + 1)), 510, height);
                add(labels[i]);
            }
        }

        public static void loadMedicineInfoById(String id) {
            String query = "SELECT * FROM Medicine WHERE id = " + id;

            try {
                ResultSet resultSet = App.statement.executeQuery(query);

                if (resultSet.next()) {
                    labels[0].setText("Препарат №" + id);
                    labels[1].setText("Название: " + resultSet.getString("name"));
                    labels[2].setText("Изготовитель: " + resultSet.getString("manufacturer"));
                    labels[3].setText("Описание: " + resultSet.getString("description"));
                    labels[4].setText("Изображение: " + resultSet.getString("image"));
                    labels[5].setText("Цена: " + String.valueOf(resultSet.getInt("price")) + "руб.") ;
                    labels[6].setText("Кол-во на складе: " + String.valueOf(resultSet.getInt("quantity")) + "шт.");
                }

            } catch (SQLException e) {System.out.println("loadMedicineInfoById: " + e.getMessage());}
        }

        public static void loadOrderInfoById(String id) {
            String query = "SELECT * FROM \"Order\" WHERE id = " + id;

            try {
                ResultSet resultSet = App.statement.executeQuery(query);

                if (resultSet.next()) {
                    labels[0].setText("Заказ №" + id);
                    labels[1].setText("Дата: " + resultSet.getString("date"));
                    labels[2].setText("Аптека №: " + resultSet.getString("pharmacy"));
                    labels[3].setText("Количество: " + resultSet.getString("count"));
                    labels[4].setText("Препарат: : " + resultSet.getString("medicines"));
                    labels[5].setText("5");
                    labels[6].setText("6");

                }

            } catch (SQLException e) {System.out.println("loadOrderInfoById: " + e.getMessage());}
        }
    }

    class ActionPanel extends JPanel {
        JLabel[] infoLabels;
        JLabel panelName;
        JList<String> jList;
        JScrollPane scrollPane;


        public ActionPanel() {
            setBackground(Color.WHITE);
            setBounds(730, 10, 350, 640);
            setLayout(null);

            panelName = new JLabel("Ифнормация");
            panelName.setForeground(Color.gray);
            panelName.setBounds(x, y, wight, height);
            add(panelName);

            loadPharmacyInfo();

            scrollPane = new JScrollPane(jList);
            scrollPane.setBounds(x, y + (height * 7), wight * 2, 400);
            add(scrollPane);


            infoLabels = new JLabel[5];
            for (int i = 0; i < infoLabels.length; i++) {
                infoLabels[i] = new JLabel(String.valueOf(i));
                infoLabels[i].setBounds(x, y + ((i + 1) * height ), wight * 2, height);
                add(infoLabels[i]);
            }

        }

        void loadPharmacyInfo() {
            idList = new ArrayList<>();
            DefaultListModel<String> listModel = new DefaultListModel<>();
            jList = new JList<>(listModel);
            String query = "SELECT * FROM Pharmacy";

            try {
                ResultSet resultSet = App.statement.executeQuery(query);
                while (resultSet.next()) {
                    String id = resultSet.getString("id");
                    String name = resultSet.getString("address");
                    idList.add(id);
                    listModel.addElement(id + ". " + name);
                }

                jList.addListSelectionListener(new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent listSelectionEvent) {
                        int selectedItem = jList.getSelectedIndex();
                        if (selectedItem == -1) {
                            return;
                        }
                        String id = idList.get(selectedItem);
                        loadInfo(id);
                    }
                });

            } catch (SQLException e) {System.out.println(e.getMessage());}
        }

        void loadInfo(String id) {
            String query = "SELECT * FROM Pharmacy WHERE id = " + id;

            try {
                ResultSet resultSet = App.statement.executeQuery(query);
                if (resultSet.next()) {
                    infoLabels[0].setText("Аптека №" + id);
                    infoLabels[1].setText("Адрес: " + resultSet.getString("address"));
                    infoLabels[2].setText("Изображение: " + resultSet.getString("image"));
                    infoLabels[3].setText("Время открытия: " + resultSet.getString("openTime"));
                    infoLabels[4].setText("Время закрытия: " + resultSet.getString("closeTime"));
                }

            } catch (SQLException e) {System.out.println("loginWindow: " + e.getMessage());}
        }
    }

    class EditorPanel extends  JPanel {
        JLabel panelName;
        JLabel[] labels;
        JTextField[] textFields;
        JButton addNewOrder;

        static JButton[] deleteButtons;

        String[] labelsTitle = {"id", "Дата", "id аптеки", "Количество", "Препарат"};

        public EditorPanel() {
            setBackground(Color.white);
            setBounds(wight + 40, 320, 530, 330);
            setLayout(null);

            panelName = new JLabel("Создать новый заказ");
            panelName.setForeground(Color.gray);
            panelName.setBounds(x, y, wight, height);
            add(panelName);

            labels = new JLabel[5];
            for (int i = 0; i < labels.length; i++) {
                labels[i] = new JLabel(labelsTitle[i]);
                labels[i].setBounds(x + wight + 5, y + (height * (i +1)), wight, height);
                add(labels[i]);
            }

            textFields = new JTextField[5];
            for (int i = 0; i < textFields.length; i++) {
                textFields[i] = new JTextField();
                textFields[i].setBounds(x, y + (height * (i +1)), wight, height);
                add(textFields[i]);
            }

            addNewOrder = new JButton("Добавить");
            addNewOrder.setBounds(x, y + (height * (6) + 10), wight, height);
            addNewOrder.setBackground(Color.white);
            addNewOrder.addActionListener(e -> newOrder());
            add(addNewOrder);

            deleteButtons = new JButton[2];
            for (int i = 0; i < deleteButtons.length; i++) {
                deleteButtons[i] = new JButton();
                deleteButtons[i].setBackground(Color.white);
                add(deleteButtons[i]);
            }
            deleteButtons[0].setBounds(x + wight * 2 + 30, y + 20, wight, height);
            deleteButtons[0].setText("Удалить препарат");
            deleteButtons[0].addActionListener(e -> delete("Medicine"));

            deleteButtons[1].setBounds(x + wight * 2 + 30, y + height + 30, wight, height);
            deleteButtons[1].setText("Удалить заказ");
            deleteButtons[1].addActionListener(e -> delete("Order"));
        }

        void newOrder() {
            String id = textFields[0].getText(),
                    date = textFields[1].getText(),
                    pharmacy = textFields[2].getText(),
                    count = textFields[3].getText(),
                    medicines = textFields[4].getText();

            if (id.isEmpty() || date.isEmpty() || pharmacy.isEmpty() || count.isEmpty() || medicines.isEmpty()) return;

            String query = "INSERT INTO \"Order\" (id, date, pharmacy, count, medicines) " +
                    "VALUES (?, ?, ?, ?, ?)";

            try (Connection conn = DriverManager.getConnection(App.url);
                 PreparedStatement pstmt = conn.prepareStatement(query)) {

                pstmt.setString(1, id);
                pstmt.setString(2, date);
                pstmt.setString(3, pharmacy);
                pstmt.setInt(4, Integer.parseInt(count));
                pstmt.setString(5, medicines);

                int rowsAffected = pstmt.executeUpdate();
                System.out.println("Вставлено записей: " + rowsAffected);

            } catch (SQLException e) {System.out.println("Editor: " + e.getMessage());}
        }

        void delete(String type) {
            String id;
            if (type == "Medicine") id = JOptionPane.showInputDialog("Введите id препарата");
            else id = JOptionPane.showInputDialog("Введите id заказа");

            String query = "DELETE FROM \"" + type + "\" WHERE id = " + id;

            try {statement.executeUpdate(query);}
            catch (SQLException e) {System.out.println("delete: " + e.getMessage());}
        }
    }
}
