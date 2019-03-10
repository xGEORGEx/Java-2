import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class EscapeButton extends JFrame {
    public EscapeButton() throws HeadlessException {
        setBounds(300, 100, 800, 800);
        setTitle("Push me!");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());
        JPanel Panel = new JPanel();
        //У панели для Layout устанваем значения null, что бы можно было жестко хадать
        //размеры и начальные координаты кнопки
        Panel.setLayout(null);
        //Создаём кнопку
        JButton jPushMe = new JButton("Нажми на меня!");

        add(Panel, BorderLayout.CENTER);
        //Добавляем кнопку на панель
        Panel.add(jPushMe);
        //Устанавливаем для кнопки размеры и начальные координаты так, чтобы она была по-середине экрана
        jPushMe.setBounds((getWidth() / 2) - 75, (getHeight() / 2) - 15, 130, 30);


        //Как только курсор мыши наведен на кнопку генерируем случайные
        //координаты в пределах панели и перемещаем туда кнопку
        jPushMe.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Random random = new Random();
                int x = random.nextInt(Panel.getWidth() - jPushMe.getWidth());
                int y = random.nextInt(Panel.getHeight() - jPushMe.getHeight());
                jPushMe.setBounds(x, y, jPushMe.getWidth(), jPushMe.getHeight());
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new EscapeButton();
    }
}
