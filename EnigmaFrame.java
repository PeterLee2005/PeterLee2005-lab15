import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EnigmaFrame extends JFrame {

    private JComboBox<Integer> innerRotor, middleRotor, outerRotor;
    private JTextField rotorStart;
    private JTextArea input, output;
    private JButton encrypt, decrypt;
    
    public EnigmaFrame() {
        super();

        innerRotor = new JComboBox<>(new Integer[]{1,2,3,4,5});
        middleRotor = new JComboBox<>(new Integer[]{1,2,3,4,5});
        outerRotor = new JComboBox<>(new Integer[]{1,2,3,4,5});

        rotorStart = new JTextField(3);

        encrypt = new JButton("Encrypt");
        decrypt = new JButton("Decrypt");

        input = new JTextArea(5, 40);
        output = new JTextArea(5, 40);

        
        JPanel top = new JPanel(new FlowLayout());
        top.add(new JLabel("Inner:"));
        top.add(innerRotor);
        top.add(new JLabel("Middle:"));
        top.add(middleRotor);
        top.add(new JLabel("Out:"));
        top.add(outerRotor);
        
        top.add(new JLabel("Initial Positions:"));
        top.add(rotorStart);

        top.add(encrypt);
        top.add(decrypt);


        JPanel center = new JPanel(new BorderLayout());
        center.add(new JLabel("Input:"), BorderLayout.NORTH);
        center.add(input, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new BorderLayout());
        bottom.add(new JLabel("Output:"), BorderLayout.NORTH);
        bottom.add(output, BorderLayout.CENTER);


        EnigmaActionListener a = new EnigmaActionListener();

        encrypt.addActionListener(a);
        decrypt.addActionListener(a);


        add(top, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
    }

    private class EnigmaActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String init = rotorStart.getText();
            String mode = e.getActionCommand().toLowerCase();
            String msg = input.getText().replace("\n", "");

            String[] args = {
                innerRotor.getSelectedItem().toString(),
                middleRotor.getSelectedItem().toString(),
                outerRotor.getSelectedItem().toString(),
                init,
                mode,
                msg
            };

            try {
                String result = Comms.run(args);
                output.setText(result);
            } catch (Exception ex) {
                System.err.print("Could not run");
            };

        }
    }
}