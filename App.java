import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class App extends JFrame {
    public App() throws HeadlessException {
        setBounds(500, 200, 400, 400);
        setTitle("My application");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        //Создаём верхнюю панель и добавляем на неё текстовое поле для отображения переписки в центре окна
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        JTextArea jChatArea = new JTextArea();
        jChatArea.setLineWrap(true);

        topPanel.add(jChatArea, BorderLayout.CENTER);
        topPanel.add(new JScrollPane(jChatArea));

        //Создаём нижнюю панель и добавляем на неё однострочное текстовое поле для ввода сообщений и
        //кнопку для отсылки сообщений
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        JTextField jTextField = new JTextField();
        JButton jSend = new JButton("Отправить");

        bottomPanel.add(jTextField, BorderLayout.CENTER);
        bottomPanel.add(jSend, BorderLayout.EAST);

        //Создаём "слушателя" для щелчка по кнопке
        jSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Перекидываем текст из нижнего поля в верхнее
                jChatArea.append(jTextField.getText() + "\n");
                jTextField.setText("");
            }
        });

        //Создаём "слушателя" для нажатия на Enter
        jTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Перекидываем текст из нижнего поля в верхнее
                jChatArea.append(jTextField.getText() + "\n");
                jTextField.setText("");
            }
        });

        //Добавляем верхнюю и нижнюю панель в окно
        add(topPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.PAGE_END);

        setVisible(true);

    }


    public static void main(String[] args) {
       new App();
    }
}
