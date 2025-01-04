import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main2 {
    public static void main(String[] args) {
        RegistrationForm registrationForm = new RegistrationForm();
        registrationForm.setVisible(true);
    }
}

class RegistrationForm extends JFrame{

    private  final Container c;

    private  final String[] dates = {"1","2","3","4","5","6","7","8","9"};
    private  final String[] months = {"1","2","3","4"};
    private  final String[] years = {"1","2","3","4"};

    Connection connection;
    RegistrationForm(){

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3308/dbc","root","");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        setTitle("Registration Form");
        setBounds(300,90,500,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        JLabel title = new JLabel("Sign-Up Form");
        title.setBounds(100,20,200,25);
        title.setFont(new Font("Arial",Font.BOLD,20));
        title.setForeground(Color.BLUE);
        c.add(title);

        JLabel name = new JLabel("Name");
        name.setBounds(30,80,100,25);
        name.setFont(new Font("Arial",Font.BOLD,20));
        c.add(name);

        JTextField tname = new JTextField();
        tname.setBounds(180,80,200,25);
        tname.setFont(new Font("Arial",Font.PLAIN,15));
        c.add(tname);

        JLabel email = new JLabel("Email");
        email.setBounds(30,140,100,25);
        email.setFont(new Font("Arial",Font.BOLD,20));
        c.add(email);

        JTextField temail = new JTextField();
        temail.setBounds(180,140,200,25);
        temail.setFont(new Font("Arial",Font.PLAIN,15));
        c.add(temail);

        JLabel gender = new JLabel("Gender");
        gender.setBounds(30,200,100,25);
        gender.setFont(new Font("Arial",Font.BOLD,20));
        c.add(gender);

        JRadioButton male = new JRadioButton("Male");
        male.setBounds(180,200,80,25);
        male.setFont(new Font("Arial",Font.PLAIN,15));
        male.setSelected(true);
        c.add(male);

        JRadioButton female = new JRadioButton("Female");
        female.setBounds(280,200,90,25);
        female.setFont(new Font("Arial",Font.PLAIN,15));
        female.setSelected(false);
        c.add(female);

        ButtonGroup gengrp = new ButtonGroup();
        gengrp.add(male);
        gengrp.add(female);

        JLabel password = new JLabel("Password");
        password.setBounds(30,260,100,25);
        password.setFont(new Font("Arial",Font.BOLD,20));
        c.add(password);

        JTextField tpassword = new JTextField();
        tpassword.setBounds(180,260,200,25);
        tpassword.setFont(new Font("Arial",Font.PLAIN,15));
        c.add(tpassword);

        JLabel dob = new JLabel("DOB");
        dob.setBounds(30,320,100,25);
        dob.setFont(new Font("Arial",Font.BOLD,20));
        c.add(dob);

        JComboBox date = new JComboBox<>(dates);
        date.setBounds(180,320,50,25);
        date.setFont(new Font("Arial",Font.PLAIN,15));
        c.add(date);

        JComboBox month = new JComboBox<>(months);
        month.setBounds(230,320,50,25);
        month.setFont(new Font("Arial",Font.PLAIN,15));
        c.add(month);

        JComboBox year = new JComboBox<>(years);
        year.setBounds(280,320,50,25);
        year.setFont(new Font("Arial",Font.PLAIN,15));
        c.add(year);



        JCheckBox robot = new JCheckBox("I am not a robot");
        robot.setBounds(30,380,200,25);
        robot.setFont(new Font("Arial",Font.PLAIN,15));
        c.add(robot);

        JButton signup = new JButton("Sign-Up");
        signup.setBounds(100,440,200,25);
        signup.setFont(new Font("Arial",Font.BOLD,20));
        signup.setBackground(Color.BLUE);
        signup.setForeground(Color.WHITE);
        c.add(signup);

        signup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String d  = (String) date.getSelectedItem()+ month.getSelectedItem()+ year.getSelectedItem();

                try {
                    PreparedStatement p = connection.prepareStatement("INSERT INTO db(name,email,gender,password,dob) VALUES(?,?,?,?,?)");

                    p.setObject(1,tname.getText());
                    p.setObject(2,temail.getText());
                    p.setObject(3,male.isSelected() ? "Male":"Female");
                    p.setObject(4,tpassword.getText());
                    p.setObject(5,d);
                    p.executeUpdate();

                    JOptionPane.showMessageDialog(null,"Sign_Up Successfull","Success",JOptionPane.INFORMATION_MESSAGE);

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

    }
}