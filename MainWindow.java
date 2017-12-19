package Main;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.json.simple.parser.ParseException;

import javax.swing.JComboBox;
import javax.swing.JEditorPane;

import java.awt.BorderLayout;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class MainWindow {

	private JFrame frame;
	private JTextField textField;
	private JTextPane cloudPane;
	private JTextPane windPane;
	private JTextPane txtpnEnterCityName;
	private JTextPane cityNamePane;
	private JTextPane errorMessage;

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
	 *
	 * @throws ParseException
	 * @throws IOException
	 */
	public MainWindow() throws ParseException, IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 *
	 * @throws ParseException
	 * @throws IOException
	 */
	private void initialize() throws ParseException, IOException {
		frame = new JFrame();
		frame.setBounds(100, 100, 493, 510);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		CityList cities = new CityList();
		String[] cityMenu = new String[cities.getCities().size()];
		cityMenu = cities.getCities().toArray(cityMenu);
		frame.getContentPane().setLayout(null);


		JTextPane temperaturePane = new JTextPane();
		temperaturePane.setBackground(SystemColor.menu);
		temperaturePane.setEditable(false);
		temperaturePane.setText("Temperature: ");
		temperaturePane.setBounds(163, 179, 274, 22);
		frame.getContentPane().add(temperaturePane);

		cloudPane = new JTextPane();
		cloudPane.setEditable(false);
		cloudPane.setBackground(SystemColor.menu);
		cloudPane.setText("Cloud Percentage: ");
		cloudPane.setBounds(163, 203, 274, 22);
		frame.getContentPane().add(cloudPane);

		windPane = new JTextPane();
		windPane.setEditable(false);
		windPane.setBackground(SystemColor.menu);
		windPane.setText("Wind Speed: ");
		windPane.setBounds(163, 227, 274, 22);
		frame.getContentPane().add(windPane);


		cityNamePane = new JTextPane();
		cityNamePane.setText("Selected City: ");
		cityNamePane.setEditable(false);
		cityNamePane.setBackground(SystemColor.menu);
		cityNamePane.setBounds(163, 155, 274, 22);
		frame.getContentPane().add(cityNamePane);

		errorMessage = new JTextPane();
		errorMessage.setBackground(SystemColor.menu);
		errorMessage.setBounds(163, 69, 160, 22);
		frame.getContentPane().add(errorMessage);


		textField = new JTextField();
		textField.setBounds(163, 45, 160, 22);
		textField.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if(cities.getCities().contains(textField.getText())){
				errorMessage.setText("");
				String selectedCity = cities.searchCities(textField.getText()).get(0);
				try {
					Weather city = new Weather(selectedCity);
					cityNamePane.setText("Seleted City: " + city.getName());
					temperaturePane.setText("Temperature: " + city.getCurrentTemp());
					cloudPane.setText("Cloud Percentage: " + city.getCloudPercentage());
					windPane.setText("Wind Speed: " + city.getWindSpeed());
				} catch (ParseException | IOException e) {
					System.out.println(e);
				}
				textField.setText("");
				}else{
					errorMessage.setText("City not found, try again!");
					textField.setText("");
				}

			}});
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		txtpnEnterCityName = new JTextPane();
		txtpnEnterCityName.setEditable(false);
		txtpnEnterCityName.setBackground(SystemColor.menu);
		txtpnEnterCityName.setText("Enter city name");
		txtpnEnterCityName.setBounds(163, 13, 160, 22);
		frame.getContentPane().add(txtpnEnterCityName);




	}
}
