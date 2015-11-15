import java.awt.EventQueue;


public class Music {

	private JFrame frame;
	private JFrame frame1;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnStart;
	private JButton btnStop;
	private JTextPane txtpnBeatsPerMinute;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Music window = new Music();
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
	public Music() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Dialog", Font.PLAIN, 24));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		textField = new JTextField();
		textField.setBounds(27, 79, 181, 52);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(236, 79, 181, 52);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		btnStart = new JButton("Start");
		btnStart.addMouseListener(new MouseAdapter() {
			@Override
			
			public void mouseClicked(MouseEvent e) {
				
				try
				{
					AudioPlayer song = new AudioPlayer(textField.getText(), Double.parseDouble(textField_1.getText()));
					song.play();
					
				}
				catch(Exception error){
					JOptionPane.showMessageDialog(null, "Either bpm or filename is not valid.");
				}
			}
		});
		btnStart.setBounds(164, 194, 116, 25);
		frame.getContentPane().add(btnStart);
		
		JTextPane txtpnFileName = new JTextPane();
		txtpnFileName.setBackground(UIManager.getColor("Button.background"));
		txtpnFileName.setText("File name");
		txtpnFileName.setBounds(78, 52, 89, 21);
		frame.getContentPane().add(txtpnFileName);
		
		txtpnBeatsPerMinute = new JTextPane();
		txtpnBeatsPerMinute.setBackground(UIManager.getColor("Button.background"));
		txtpnBeatsPerMinute.setText("Beats per minute");
		txtpnBeatsPerMinute.setBounds(268, 52, 126, 21);
		frame.getContentPane().add(txtpnBeatsPerMinute);

	}
}
