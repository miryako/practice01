import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginWindow extends JFrame {
    int x = 10, y = 10, wight = 150, height = 30;

    JLabel[] labels;
    JTextField passwordTF;
    JTextField phoneTF;
    JButton loginBtn;

    String role;

    public LoginWindow() {
        setTitle("Вход в систему");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 220);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);

        labels = new JLabel[3];
        for (int i = 0; i < labels.length; i++) {
            labels[i] = new JLabel();
            if (i == 0) labels[i].setBounds(x, y + ((height + 10) * i), wight + 50, height);
            else labels[i].setBounds(x + wight + 5, y + ((height + 10) * i), wight + 50, height);
            labels[i].setForeground(Color.gray);
            add(labels[i]);
        }
        labels[0].setText("МояАптека   Вход в систему");
        labels[1].setText("Номер телефона");
        labels[2].setText("Пароль");

        passwordTF = new JTextField(); labels[1].getBounds();
        passwordTF.setBounds(x, y + ((height + 10) * 2), wight, height);
        add(passwordTF);

        phoneTF = new JTextField();
        phoneTF.setBounds(x, y + ((height + 10)), wight, height);
        add(phoneTF);

        loginBtn = new JButton("Войти");
        loginBtn.setBackground(Color.white);
        loginBtn.setBounds(x, y + ((height + 10) * 3), wight, height);
        loginBtn.addActionListener(e -> checkFields(phoneTF.getText().toString(), passwordTF.getText().toString()));
        add(loginBtn);

        setVisible(true);
    }

    void checkFields(String phone, String password) {
        phone = Beta.checkInputText(phone, true);
        password = Beta.checkInputText(password, true);


        if (phone.isEmpty() || password.isEmpty()) {
            Beta.sendMessage(this, "Предупреждение", "Заполните все поля");
            return;
        }

        String query = "SELECT * FROM User WHERE phone = '" + phone + "' AND password = '" + password + "'";

        try {
            ResultSet resultSet = App.statement.executeQuery(query);

            if (resultSet.next()) {
                Beta.sendMessage(this, "Сообщение", "Здравствуйте, " + resultSet.getString("name"));
                role = resultSet.getString("role");
                MainWindow.adminCheck(role);
                App.openMainMenu();
            } else {
                Beta.sendMessage(this, "Сообщение", "Неверные данные");
                passwordTF.setText("");
            }

        } catch (SQLException e) {
            System.out.println("loginWindow " + e.getMessage());
        }
    }
}
