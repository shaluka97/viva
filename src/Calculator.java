import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Maininterface extends JFrame {
    private JButton btncal;
    private JTextField text1;
    private JTextField text2;

    private int input1;
    private int input2;

    Maininterface() {
        setSize(500, 300);
        setTitle("Calculator");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        Font defaultfont = new Font("", 1, 15);
        setResizable(false);
        setLayout(new GridLayout(2, 1));

        JPanel toppanel = new JPanel(new GridLayout(2,1));
        JPanel bottompanel = new JPanel(new FlowLayout());

        JPanel input1 = new JPanel(new FlowLayout());
        JPanel input2 = new JPanel(new FlowLayout());

        JLabel lbl1 = new JLabel("Number 1 :");
        lbl1.setFont(defaultfont);

        JLabel lbl2 = new JLabel("Number 2 :");
        lbl2.setFont(defaultfont);

        text1 = new JTextField(10);
        text1.setFont(defaultfont);

        text2 = new JTextField(10);
        text2.setFont(defaultfont);

        input1.add(lbl1);
        input1.add(text1);

        input2.add(lbl2);
        input2.add(text2);

        btncal = new JButton("Calculate");
        btncal.setSize(50, 50);

        toppanel.add(input1);
        toppanel.add(input2);
        bottompanel.add(btncal);
        add(toppanel);
        add(bottompanel);

    }

    public int numn1() {
        String input = text1.getText();
        if(input!=null && input!=""){
            input1 = Integer.parseInt(input);
            return input1;
        }
        return 0;
    }

    public int numn2() {
        String input = text2.getText();
        if(input!=null&& input!="") {
            input2 = Integer.parseInt(input);
            return input2;
        }
        return 0;
    }

    public JButton startbtn() {
        return btncal;
    }

}

abstract class popup extends JFrame {
    private JTextArea textArea;

    abstract public void print(int input1, int input2);

    popup() {
        setSize(400, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        Font defaultfont = new Font("", 1, 15);
        setResizable(false);
        setLayout(new FlowLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setBackground(Color.WHITE);
        textArea.setSize(350, 350);
        textArea.setFont(defaultfont);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(350, 350));
        add(scrollPane);

    }

    public JTextArea getTextArea() {
        return textArea;
    }


}

class addwin extends popup {
    addwin() {
        setTitle("Addition");
    }

    @Override
    public void print(int input1, int input2) {
        getTextArea().append(input1 + " + " + input2 + " = " + (input1 + input2) + "\n");
    }


}

class subtwin extends popup {
    subtwin() {
        setTitle("Subtraction");
    }

    @Override
    public void print(int input1, int input2) {
        getTextArea().append(input1 + " - " + input2 + " = " + (input1 - input2) + "\n");
    }


}

class multiwin extends popup {
    multiwin() {
        setTitle("Multiplication");
    }

    @Override
    public void print(int input1, int input2) {
        getTextArea().append(input1 + " x " + input2 + " = " + (input1 * input2) + "\n");
    }


}

class divwin extends popup {
    divwin() {
        setTitle("Division");
    }

    @Override
    public void print(int input1, int input2) {
        getTextArea().append(input1 + " / " + input2 + " = " + (input1 / input2) + "\n");
    }


}

class modulwin extends popup {
    modulwin() {
        setTitle("Modulor");
    }

    @Override
    public void print(int input1, int input2) {
        getTextArea().append(input1 + " % " + input2 + " = " + (input1 % input2) + "\n");
    }

}

class controller implements ActionListener {
    Maininterface main1;
    popup[] calclist = {new addwin(), new subtwin(), new multiwin(), new divwin(), new modulwin()};

    controller() {
        main1 = new Maininterface();
        main1.setVisible(true);
        main1.startbtn().addActionListener(this);
    }

    public void start() {
        for (popup cal : calclist) {
            cal.setVisible(true);
            cal.print(main1.numn1(), main1.numn2());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        main1.setVisible(true);
        if (e.getSource() == main1.startbtn() && main1.numn1()!=0 && main1.numn2()!=0) {
            start();
        }else {
            System.out.println("invalid input");
        }
    }
}

public class Calculator {
    public static void main(String args[]) {
        controller run = new controller();

    }
}

