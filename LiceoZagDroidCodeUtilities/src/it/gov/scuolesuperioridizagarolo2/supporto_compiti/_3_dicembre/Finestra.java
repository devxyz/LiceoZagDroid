package it.gov.scuolesuperioridizagarolo2.supporto_compiti._3_dicembre;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Finestra extends JFrame {
    private JPanel sfondo;
    private JButton bottone;
    private JLabel etichetta;
    private JTextField op1, op2;
    private JLabel somma;

    public Finestra() {

        this.sfondo = new JPanel();
        this.sfondo.setLayout(null);
        super.setContentPane(this.sfondo);

        this.etichetta = new JLabel();
        this.sfondo.add(this.etichetta);

        this.etichetta.setText("ADDIZIONE");
        this.etichetta.setLocation(10, 10);

        this.op1 = new JTextField();

        this.sfondo.add(this.op1);
        this.op1.setLocation(50, 50);
        this.op1.setText("operando 1");
        this.op2 = new JTextField();

        this.sfondo.add(this.op2);
        this.op2.setLocation(50, 70);
        this.op2.setText("operando 2");

        this.somma = new JLabel();
        this.sfondo.add(this.somma);
        this.somma.setLocation(100, 100);
        this.somma.setText("la somma è...");

        this.bottone = new JButton();
        this.sfondo.add(this.bottone);

        Evento e = new Evento();
        this.bottone.addActionListener(e);

        super.setVisible(true);


        super.setSize(500, 500);
        super.setLocation(10, 10);


        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.invalidate();
        this.repaint();
    }

    class Evento implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int n1 = Integer.parseInt(Finestra.this.op1.getText());
            int n2 = Integer.parseInt(Finestra.this.op2.getText());
            int somma = n1 + n2;
            Finestra.this.somma.setText("la somma è..." + somma);
        }
    }
}
