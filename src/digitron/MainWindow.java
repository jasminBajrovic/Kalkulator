package digitron;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow {

	private JFrame frame;
	private JTextField txtEkran;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 434, 367);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		txtEkran = new JTextField();
		txtEkran.setText("0");
		txtEkran.addKeyListener(new KeyAdapter() //Slusamo tastaturu
		{
			@Override
			public void keyTyped(KeyEvent unosSaTastature) //Kada korisnik otkuca nesto poziva se ova metoda
			{
				txtEkran.setText(InternaLogika.procesirajUnos(unosSaTastature.getKeyChar())); //Pozivamo procesiranje unosa i povrat te metode postavljamo za displej 
				unosSaTastature.consume(); //"Trosimo" ovaj dogadjaj da ne bi dozvolili standardnu obradu. Probajte da iskomentirate ovu liniju :) 
			}
		});
		txtEkran.setBackground(Color.BLACK);
		txtEkran.setForeground(Color.GREEN);
		txtEkran.setHorizontalAlignment(SwingConstants.RIGHT);
		txtEkran.setFont(new Font("Tahoma", Font.PLAIN, 30));
		txtEkran.setBounds(10, 11, 386, 50);
		frame.getContentPane().add(txtEkran);
		txtEkran.setColumns(10);
		JButton button = new JButton("0");
		button.addActionListener(new ActionListener() //Za svako dugme imamo listener koji ceka klik korisnika
		{
			public void actionPerformed(ActionEvent e) 
			{
				txtEkran.setText(InternaLogika.procesirajUnos('0')); //I svako dugme jednostavno salje svoj simbol na procesiranje i postvalja sta dobije nazad na ekran

			}
		});
		button.setBounds(10, 289, 89, 23);
		frame.getContentPane().add(button);

		JButton button_1 = new JButton("1");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtEkran.setText(InternaLogika.procesirajUnos('1'));

			}
		});
		button_1.setBounds(10, 255, 89, 23);
		frame.getContentPane().add(button_1);

		JButton button_2 = new JButton("2");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtEkran.setText(InternaLogika.procesirajUnos('2'));

			}
		});
		button_2.setBounds(109, 255, 89, 23);
		frame.getContentPane().add(button_2);

		JButton button_3 = new JButton("3");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtEkran.setText(InternaLogika.procesirajUnos('3'));

			}
		});
		button_3.setBounds(208, 255, 89, 23);
		frame.getContentPane().add(button_3);

		JButton button_4 = new JButton("4");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtEkran.setText(InternaLogika.procesirajUnos('4'));

			}
		});
		button_4.setBounds(10, 221, 89, 23);
		frame.getContentPane().add(button_4);

		JButton button_5 = new JButton("5");
		button_5.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{	
				txtEkran.setText(InternaLogika.procesirajUnos('5'));

			}
		});
		button_5.setBounds(109, 221, 89, 23);
		frame.getContentPane().add(button_5);

		JButton button_6 = new JButton("6");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtEkran.setText(InternaLogika.procesirajUnos('6'));

			}
		});
		button_6.setBounds(208, 221, 89, 23);
		frame.getContentPane().add(button_6);

		JButton button_7 = new JButton("7");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtEkran.setText(InternaLogika.procesirajUnos('7'));

			}
		});
		button_7.setBounds(10, 187, 89, 23);
		frame.getContentPane().add(button_7);

		JButton button_8 = new JButton("8");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtEkran.setText(InternaLogika.procesirajUnos('8'));

			}
		});
		button_8.setBounds(109, 187, 89, 23);
		frame.getContentPane().add(button_8);

		JButton button_9 = new JButton("9");
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				txtEkran.setText(InternaLogika.procesirajUnos('9'));

			}
		});
		button_9.setBounds(208, 187, 89, 23);
		frame.getContentPane().add(button_9);

		JButton btnC = new JButton("C");
		btnC.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				txtEkran.setText(InternaLogika.ocisti(true)); //Jedino kod C i CE pozivamo drugu metodu koju za argument ima boolean koji odredjuje brisemo li sve ili samo poslednje
			}
		});
		btnC.setBounds(10, 72, 89, 23);
		frame.getContentPane().add(btnC);

		JButton btnCe = new JButton("CE");
		btnCe.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				txtEkran.setText(InternaLogika.ocisti(false));
			}
		});
		btnCe.setBounds(109, 72, 89, 23);
		frame.getContentPane().add(btnCe);

		JButton btnMc = new JButton("MC");
		btnMc.setBounds(208, 72, 89, 23);
		frame.getContentPane().add(btnMc);

		JButton btnM = new JButton("M+");
		btnM.setBounds(10, 106, 89, 23);
		frame.getContentPane().add(btnM);

		JButton btnMm = new JButton("M-");
		btnMm.setBounds(109, 106, 89, 23);
		frame.getContentPane().add(btnMm);

		JButton btnMr = new JButton("MR");
		btnMr.setBounds(208, 106, 89, 23);
		frame.getContentPane().add(btnMr);

		JButton button_tacka = new JButton(".");
		button_tacka.setBounds(109, 289, 89, 23);
		frame.getContentPane().add(button_tacka);

		JButton button_proc = new JButton("%");
		button_proc.setBounds(208, 289, 89, 23);
		frame.getContentPane().add(button_proc);

		JButton btn_jed = new JButton("=");
		btn_jed.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				txtEkran.setText(InternaLogika.procesirajUnos('='));
			}
		});
		btn_jed.setBounds(307, 289, 89, 23);
		frame.getContentPane().add(btn_jed);

		JButton button_sab = new JButton("+");
		button_sab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				txtEkran.setText(InternaLogika.procesirajUnos('+'));
			}
		});
		button_sab.setBounds(307, 255, 89, 23);
		frame.getContentPane().add(button_sab);

		JButton button_min = new JButton("-");
		button_min.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				txtEkran.setText(InternaLogika.procesirajUnos('-'));
			}
		});
		button_min.setBounds(307, 221, 89, 23);
		frame.getContentPane().add(button_min);

		JButton button_put = new JButton("*");
		button_put.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				txtEkran.setText(InternaLogika.procesirajUnos('*'));
			}
		});
		button_put.setBounds(307, 187, 89, 23);
		frame.getContentPane().add(button_put);

		JButton button_pod = new JButton("/");
		button_pod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				txtEkran.setText(InternaLogika.procesirajUnos('/'));
			}
		});
		button_pod.setBounds(307, 153, 89, 23);
		frame.getContentPane().add(button_pod);

		JButton button_oZagrada = new JButton("(");
		button_oZagrada.setBounds(208, 153, 42, 23);
		frame.getContentPane().add(button_oZagrada);

		JButton button_zZagrada = new JButton(")");
		button_zZagrada.setBounds(260, 153, 42, 23);
		frame.getContentPane().add(button_zZagrada);
		
		JButton btnNegPoz = new JButton("+/-");
		btnNegPoz.setBounds(109, 153, 89, 23);
		frame.getContentPane().add(btnNegPoz);
	}
}
