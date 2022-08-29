import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;

import org.graalvm.compiler.hotspot.nodes.aot.InitializeKlassNode;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import java.awt.Button;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import javax.swing.SpinnerNumberModel;

public class MainFrame extends JFrame implements ActionListener {


	private JFrame frame;
	private JTextField textField;
	private JTextField textField_2;
	JLabel lblNewLabel_1;
	JLabel lblNewLabel_2;
	private JTextField textField_3;
	JButton button;
	JButton compress;
	JSpinner spinner;
	public String IN_FILE_PATH1;
	JLabel donelabel;
	JLabel donelabel1;
	JLabel done1;


	//EventQueue.invokeLater(new Runnable() {

	public MainFrame(String done) {
		InitializeDone(done);
	}


	private void InitializeDone(String done) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setLayout(new FlowLayout(FlowLayout.CENTER,50,30));

		done1 = new JLabel(done);

		this.add(done1);

		this.setVisible(true);
		this.pack();

	}

	public MainFrame() {
		Initialize();

	}


	private void Initialize() {



		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setLayout(new FlowLayout(FlowLayout.CENTER,50,30));

		this.setTitle("LZW COMPERSSOR BY RON AMAR & PELEG SWISA");

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(500,500));
		panel.setBackground(Color.LIGHT_GRAY);


		//Label
		JLabel lblNewLabel = new JLabel("Enter the file you want to compress");
		//		Panel.add(lblNewLabel);
		panel.add(lblNewLabel);

		//text fILE		
		textField = new JTextField();
		textField.setColumns(10);

		panel.add(textField);

		//select File
		button = new JButton("Select File");
		button.addActionListener((ActionListener) this);

		panel.add(button);



		lblNewLabel_1 = new JLabel("Choose a name for the compressed file, and choose a depth");

		panel.add(lblNewLabel_1);




		textField_2 = new JTextField("");
		textField_2.setColumns(10);
		panel.add(textField_2);

		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		panel.add(spinner);

		lblNewLabel_2 = new JLabel("Please choose a name for the decoded file");
		panel.add(lblNewLabel_2);

		textField_3 = new JTextField("");
		textField_3.setColumns(10);
		panel.add(textField_3);
		
		donelabel1 = new JLabel("");
		panel.add(donelabel1);

		donelabel = new JLabel("");
		panel.add(donelabel);
		compress = new JButton("Compress");
		compress.addActionListener((ActionListener) this);
		panel.add(compress);





		this.add(panel);


		this.setVisible(true);
		this.pack();




	}

	@Override
	public void actionPerformed(ActionEvent e) {


		if(e.getSource() == button) {

			JFileChooser fileChooser = new JFileChooser();

			fileChooser.setCurrentDirectory(new File("C:\\Users\\peleg\\java\\Lzw")); //sets current directory

			int response = fileChooser.showOpenDialog(null); //select file to open
			//			int response = fileChooser.showSaveDialog(null); //select file to save

			if(response == JFileChooser.APPROVE_OPTION) {
				File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
				//				System.out.println(file);
				//				lblNewLabel_1.setText("peleg");
				IN_FILE_PATH1  = fileChooser.getSelectedFile().getAbsolutePath();



				textField.setText(IN_FILE_PATH1);



			}


		}
		if(e.getSource() == compress) {


			Main encode = new Main (IN_FILE_PATH1 , textField_2.getText() , spinner.getComponentCount() , textField_3.getText() );



			donelabel1.setText("-----------------Compression at work----------------\n" );
            donelabel.setText("------------------PLEASE WAIT--------------------"); 



		}


	}



}
