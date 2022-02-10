
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;




public class WorkAdminFrame extends JFrame{
	
	private AdminApp app;
	
	private JButton addNewStationButton;
	private JButton editStationButton;
	private JButton fixConnectionButton;
	private JButton choosePath;
	private JButton savePath;
	
	private JPanel newStationPanel;
	private JPanel editStationPanel;
	private JPanel StationListPanel;
	private JPanel infoStationPanel;
	private JPanel DataBasePanel;
	
	
	private JTextField newStationName;
	private JTextField newStationIP;
	private JTextField newStationID;
	private JTextField newStationLatitude;
	private JTextField newStationLongitude;
	private JTextField newStationAltitude;
	
	private JTextField editStationName;
	private JTextField editStationIP;
	private JTextField editStationID;
	private JTextField editStationLatitude;
	private JTextField editStationLongitude;
	private JTextField editStationAltitude;
	
	private JTextField teleStationName;
	private JTextField teleStationIP;
	private JTextField teleStationID;
	private JTextField teleStationTemperaure;
	private JTextField teleStationAtmosphericPressure;
	private JTextField teleStationHumidity;
	private JTextField teleStationWindSpeed;
	private JTextField teleStationWindDirection;
	private JTextField teleStationPrecipitationAmount;
	private JTextField teleStationTime;
	
	private JTextField databasePathField;
	private JTextField databaseSizeField;
	private JTextField partitionFreeField;
	private JTextField partitionTotalField;
	private JTextField coinsTotalField;
	
	private JMultiTableTwo StationTable;
	private String[] columnNames = {
	           "ID",
	           "Наименование",
	           "Статус",
	           "IP",
	           "Обновлено"
	};
	private JPanel navpanelTop;
	private JScrollPane scrollPaneTop;
	private int selectedRow=-1;
	WorkAdminFrame th;
	private boolean isChoosingPath=false;

	
	WorkAdminFrame(AdminApp aapp){
		super("Admin App");
		th=this;
		app=aapp;

		StationListPanel=new JPanel();
		StationListPanel.setLayout(null);
		
		editStationPanel=new JPanel();
		editStationPanel.setLayout(null);
		
		newStationPanel=new JPanel();
		newStationPanel.setLayout(null);
		
		infoStationPanel=new JPanel();
		infoStationPanel.setLayout(null);
		
		DataBasePanel=new JPanel();
		DataBasePanel.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Список станций", null, StationListPanel,
                "Does nothing");
		tabbedPane.addTab("Создать новую", null, newStationPanel,
                "Does nothing");
		tabbedPane.addTab("Редактировать", null, editStationPanel,
                "Does nothing");
		tabbedPane.addTab("Телеметрия", null, infoStationPanel,
                "Does nothing");
		tabbedPane.addTab("База данных", null, DataBasePanel,
                "Does nothing");
		
		newStationName=new JTextField();	
		newStationIP=new JTextField();
		newStationID=new JTextField();
		newStationLatitude=new JTextField();
		newStationLongitude=new JTextField();
		newStationAltitude=new JTextField();
		
		newStationName.setBounds(160, 20, 200, 32);
		newStationIP.setBounds(160, 60, 200, 35);
		newStationID.setBounds(160, 100, 200, 35);
		newStationLatitude.setBounds(160, 140, 200, 35);
		newStationLongitude.setBounds(160, 180, 200, 35);
		newStationAltitude.setBounds(160, 220, 200, 35);
		
		JLabel newStationNameLabel=new JLabel("Наименование:");
		JLabel newStationIPLabel=new JLabel("IP - Адресс:");
		JLabel newStationIDLabel=new JLabel("Идентификатор:");
		JLabel newStationLatitudeLabel=new JLabel("Широта:");
		JLabel newStationLongitudeLabel=new JLabel("Долгота:");
		JLabel newStationAltitudeLabel=new JLabel("Высота:");
		
		newStationNameLabel.setBounds(20, 20, 200, 32);
		newStationIPLabel.setBounds(20, 60, 200, 35);
		newStationIDLabel.setBounds(20, 100, 200, 35);
		newStationLatitudeLabel.setBounds(20, 140, 200, 35);
		newStationLongitudeLabel.setBounds(20, 180, 200, 35);
		newStationAltitudeLabel.setBounds(20, 220, 200, 35);
		
		addNewStationButton=new JButton("Добавить");
		addNewStationButton.setBounds(100,270,120,35);
		
		newStationPanel.add(newStationName);
		newStationPanel.add(newStationIP);
		newStationPanel.add(newStationID);
		newStationPanel.add(newStationLatitude);
		newStationPanel.add(newStationLongitude);
		newStationPanel.add(newStationAltitude);
		
		newStationPanel.add(newStationNameLabel);
		newStationPanel.add(newStationIPLabel);
		newStationPanel.add(newStationIDLabel);
		newStationPanel.add(newStationLatitudeLabel);
		newStationPanel.add(newStationLongitudeLabel);
		newStationPanel.add(newStationAltitudeLabel);
		
		newStationPanel.add(addNewStationButton);
		
		addNewStationButton.addMouseListener( new MouseAdapter()
	 	{
	 		public void mousePressed(MouseEvent event) {
	 			//нажали кнопку Добавить
	 			app.AddNewStationButtonClicked();
	 		}
	 	});
		
		
		editStationName=new JTextField();	
		editStationIP=new JTextField();
		editStationID=new JTextField();
		editStationLatitude=new JTextField();
		editStationLongitude=new JTextField();
		editStationAltitude=new JTextField();
		
		editStationName.setBounds(160, 20, 200, 32);
		editStationIP.setBounds(160, 60, 200, 35);
		editStationID.setBounds(160, 100, 200, 35);
		editStationLatitude.setBounds(160, 140, 200, 35);
		editStationLongitude.setBounds(160, 180, 200, 35);
		editStationAltitude.setBounds(160, 220, 200, 35);
		
		JLabel editStationNameLabel=new JLabel("Наименование:");
		JLabel editStationIPLabel=new JLabel("IP - Адресс:");
		JLabel editStationIDLabel=new JLabel("Идентификатор:");
		JLabel editStationLatitudeLabel=new JLabel("Широта:");
		JLabel editStationLongitudeLabel=new JLabel("Долгота:");
		JLabel editStationAltitudeLabel=new JLabel("Высота:");
		
		editStationNameLabel.setBounds(20, 20, 200, 32);
		editStationIPLabel.setBounds(20, 60, 200, 35);
		editStationIDLabel.setBounds(20, 100, 200, 35);
		editStationLatitudeLabel.setBounds(20, 140, 200, 35);
		editStationLongitudeLabel.setBounds(20, 180, 200, 35);
		editStationAltitudeLabel.setBounds(20, 220, 200, 35);
		
		editStationButton=new JButton("Сохранить");
		editStationButton.setBounds(50,270,120,35);
		
		fixConnectionButton=new JButton("Починить");
		fixConnectionButton.setBounds(180,270,120,35);
		
		editStationPanel.add(editStationName);
		editStationPanel.add(editStationIP);
		editStationPanel.add(editStationID);
		editStationPanel.add(editStationLatitude);
		editStationPanel.add(editStationLongitude);
		editStationPanel.add(editStationAltitude);
		
		editStationPanel.add(editStationNameLabel);
		editStationPanel.add(editStationIPLabel);
		editStationPanel.add(editStationIDLabel);
		editStationPanel.add(editStationLatitudeLabel);
		editStationPanel.add(editStationLongitudeLabel);
		editStationPanel.add(editStationAltitudeLabel);
		
		editStationPanel.add(editStationButton);
		editStationPanel.add(fixConnectionButton);
		
		editStationButton.addMouseListener( new MouseAdapter()
	 	{
	 		public void mousePressed(MouseEvent event) {
	 			//нажали кнопку Сохранить
	 			app.editStationButtonClicked();
	 		}
	 	});
		fixConnectionButton.addMouseListener( new MouseAdapter()
	 	{
	 		public void mousePressed(MouseEvent event) {
	 			//нажали кнопку Сохранить
	 			app.fixConnectionButton();
	 		}
	 	});
		
		teleStationID=new JTextField();
		teleStationName=new JTextField();
		teleStationIP=new JTextField();
		teleStationTime=new JTextField();
		teleStationTemperaure=new JTextField();
		teleStationAtmosphericPressure=new JTextField();
		teleStationHumidity=new JTextField();
		teleStationPrecipitationAmount=new JTextField();
		teleStationWindDirection=new JTextField();
		teleStationWindSpeed=new JTextField();
		
		teleStationID.setBounds(220, 20, 200, 32);
		teleStationName.setBounds(220, 60, 200, 32);
		teleStationIP.setBounds(220, 100, 200, 32);
		teleStationTime.setBounds(220, 140, 200, 32);
		teleStationTemperaure.setBounds(220, 180, 200, 32);
		teleStationAtmosphericPressure.setBounds(220, 220, 200, 32);
		teleStationHumidity.setBounds(220, 260, 200, 32);
		teleStationPrecipitationAmount.setBounds(220, 300, 200, 32);
		teleStationWindDirection.setBounds(220, 340, 200, 32);
		teleStationWindSpeed.setBounds(220, 380, 200, 32);
		
		infoStationPanel.add(teleStationID);
		infoStationPanel.add(teleStationName);
		infoStationPanel.add(teleStationIP);
		infoStationPanel.add(teleStationTime);
		infoStationPanel.add(teleStationTemperaure);
		infoStationPanel.add(teleStationAtmosphericPressure);
		infoStationPanel.add(teleStationHumidity);
		infoStationPanel.add(teleStationPrecipitationAmount);
		infoStationPanel.add(teleStationWindDirection);
		infoStationPanel.add(teleStationWindSpeed);
		
		JLabel teleStationNameLabel=new JLabel("Наименование:");
		JLabel teleStationIPLabel=new JLabel("IP - Адресс:");
		JLabel teleStationIDLabel=new JLabel("Идентификатор:");
		JLabel teleStationTimeLabel=new JLabel("Время замера:");
		JLabel teleStationTemperaureLabel=new JLabel("Температура (⁰C):");
		JLabel teleStationAtmosphericPressureLabel=new JLabel("Атмосферное давление (Па):");
		JLabel teleStationHumidityLabel=new JLabel("Влажность (%):");
		JLabel teleStationPrecipitationAmountLabel=new JLabel("Осадки (мм):");
		JLabel teleStationWindDirectionLabel=new JLabel("Направление ветра:");
		JLabel teleStationWindSpeedLabel=new JLabel("Скорость ветра (м/c):");
		teleStationNameLabel.setBounds(20, 20, 200, 32);
		teleStationIDLabel.setBounds(20, 60, 200, 35);
		teleStationIPLabel.setBounds(20, 100, 200, 35);
		teleStationTimeLabel.setBounds(20, 140, 200, 35);
		teleStationTemperaureLabel.setBounds(20, 180, 200, 35);
		teleStationAtmosphericPressureLabel.setBounds(20, 220, 200, 35);
		teleStationHumidityLabel.setBounds(20, 260, 200, 35);
		teleStationPrecipitationAmountLabel.setBounds(20, 300, 200, 35);
		teleStationWindDirectionLabel.setBounds(20, 340, 200, 35);
		teleStationWindSpeedLabel.setBounds(20, 380, 200, 35);
		
		infoStationPanel.add(teleStationNameLabel);
		infoStationPanel.add(teleStationIDLabel);
		infoStationPanel.add(teleStationIPLabel);
		infoStationPanel.add(teleStationTimeLabel);
		infoStationPanel.add(teleStationTemperaureLabel);
		infoStationPanel.add(teleStationAtmosphericPressureLabel);
		infoStationPanel.add(teleStationHumidityLabel);
		infoStationPanel.add(teleStationPrecipitationAmountLabel);
		infoStationPanel.add(teleStationWindDirectionLabel);
		infoStationPanel.add(teleStationWindSpeedLabel);
		
		databasePathField=new JTextField();
		databaseSizeField=new JTextField();
		partitionFreeField=new JTextField();
		partitionTotalField=new JTextField();
		coinsTotalField=new JTextField();
		
		databasePathField.setBounds(220, 20, 200, 32);
		databaseSizeField.setBounds(220, 60, 200, 32);
		partitionFreeField.setBounds(220, 100, 200, 32);
		partitionTotalField.setBounds(220, 140, 200, 32);
		coinsTotalField.setBounds(220, 180, 200, 32);
		
		DataBasePanel.add(databasePathField);
		DataBasePanel.add(databaseSizeField);
		DataBasePanel.add(partitionFreeField);
		DataBasePanel.add(partitionTotalField);
		DataBasePanel.add(coinsTotalField);
				
		JLabel databasePathLabel=new JLabel("Расположение БД:");
		JLabel databaseSizeLabel=new JLabel("Размер (Кб):");
		JLabel partitionFreeLabel=new JLabel("Свободно на диске (Мб):");
		JLabel partitionTotalLabel=new JLabel("Всего на диске (Мб):");
		JLabel coinsTotalLabel=new JLabel("Доход от продаж (руб):");
		
		databasePathLabel.setBounds(20, 20, 200, 32);
		databaseSizeLabel.setBounds(20, 60, 200, 35);
		partitionFreeLabel.setBounds(20, 100, 200, 35);
		partitionTotalLabel.setBounds(20, 140, 200, 35);
		coinsTotalLabel.setBounds(20, 180, 200, 35);
		
		DataBasePanel.add(databasePathLabel);
		DataBasePanel.add(databaseSizeLabel);
		DataBasePanel.add(partitionFreeLabel);
		DataBasePanel.add(partitionTotalLabel);
		DataBasePanel.add(coinsTotalLabel);
		
		choosePath=new JButton("Изменить");
		savePath=new JButton("Применить");
		
		choosePath.setBounds(430, 20, 100, 32);
		savePath.setBounds(550, 20, 100, 32);
		
		DataBasePanel.add(choosePath);
		DataBasePanel.add(savePath);
		
		choosePath.addMouseListener( new MouseAdapter()
	 	{
	 		public void mousePressed(MouseEvent event) {
	 			//нажали кнопку изменить
	 			isChoosingPath=true;
	 			JFileChooser fc = new JFileChooser();
	 			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	 			//In response to a button click:
	 			int returnVal = fc.showOpenDialog(th);

	 	        if (returnVal == JFileChooser.APPROVE_OPTION) {
	 	           File file = fc.getSelectedFile();
	 	           databasePathField.setText(file.getPath()+"/MeteoData.db");
	 	           databaseSizeField.setText(String.valueOf(0));
	 	           partitionFreeField.setText(String.valueOf(file.getFreeSpace()/1024/1024));
	 	           partitionTotalField.setText(String.valueOf(file.getTotalSpace()/1024/1024));
	 	          coinsTotalField.setText("0");
	 	        }
	 		}
	 	});
		
		savePath.addMouseListener( new MouseAdapter()
	 	{
	 		public void mousePressed(MouseEvent event) {
	 			//нажали кнопку сохранить
	 			app.savePathButtonClicked();
	 			isChoosingPath=false;
	 		}
	 	});
		StationTable = new JMultiTableTwo(new String[][]{},columnNames);
		//строим таблицу на основе абстрактной 
		final JTable ltable2=new JTable(StationTable); 
		//колонки менять местами нельзя
	    ltable2.getTableHeader().setReorderingAllowed(false);
	    //слушатель
	    ltable2.addMouseListener( new MouseAdapter()
	 	{
	 		public void mousePressed(MouseEvent event) {
	 			//выделяем параметр
	 			int r=ltable2.getSelectedRow();
	 			selectStation(r);
	 			
	 		}
	 	});
      
	    
	    //создаем скролл и передаем ей содержимое(таблицу)
		scrollPaneTop=new JScrollPane(ltable2);
		scrollPaneTop.setViewportView(ltable2);
		
		addComponentListener(new ComponentListener() {
			public void componentHidden(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				reswin();
			}

			public void componentMoved(ComponentEvent arg0) {
				reswin();
			}

			public void componentResized(ComponentEvent arg0) {
				reswin();
			}

			public void componentShown(ComponentEvent arg0) {
				reswin();
			}
		      });
		navpanelTop=new JPanel();
		StationListPanel.add(scrollPaneTop);
		StationListPanel.add(navpanelTop);
		
		add(tabbedPane);
		setMinimumSize(new Dimension(720,520));
	 	setResizable(true);
	 	setVisible(true);
	 	reswin();
	}
	private int selectedStation=0;
	protected void selectStation(int index) {
		if(index>=0){
			selectedRow=index;
			selectedStation=index;
			long id=Long.valueOf((String)StationTable.getValueAt(index, 0));
			app.selectStation(id);
		}
	}
	void reswin(){
		scrollPaneTop.setBounds(0, 0, getWidth(), getHeight()-30);
		scrollPaneTop.setVisible(true);
		scrollPaneTop.revalidate();
		scrollPaneTop.repaint();
		StationListPanel.repaint();
		
		revalidate();
		repaint();
	}
	public String getNewStationName() {
		
		return newStationName.getText();
	}

	public String getNewStationIP() {
		
		return newStationIP.getText();
	}

	public long getNewStationID() {
		
		return Long.valueOf(newStationID.getText());
	}

	public double getNewStationLatitude() {
		
		return Double.valueOf(newStationLatitude.getText());
	}

	public double getNewStationLongitude() {
		
		return Double.valueOf(newStationLongitude.getText());
	}

	public double getNewStationAltitude() {
		
		return Double.valueOf(newStationAltitude.getText());
	}
	
	public String getEditedStationName() {
		
		return editStationName.getText();
	}

	public String getEditedStationIP() {
		
		return editStationIP.getText();
	}

	public long getEditedStationID() {
		
		return Long.valueOf(editStationID.getText());
	}

	public double getEditedStationLatitude() {
		
		return Double.valueOf(editStationLatitude.getText());
	}

	public double getEditedStationLongitude() {
		
		return Double.valueOf(editStationLongitude.getText());
	}

	public double getEditedStationAltitude() {
		
		return Double.valueOf(editStationAltitude.getText());
	}
	public void setStationData(String[][] data) {
		StationTable.setContent(data, columnNames);
		
		final JTable ltable2=new JTable(StationTable); 
		//колонки менять местами нельзя
	    ltable2.getTableHeader().setReorderingAllowed(false);
	    //слушатель
	    ltable2.addMouseListener( new MouseAdapter()
	 	{
	 		public void mousePressed(MouseEvent event) {
	 			//выделяем параметр
	 			int r=ltable2.getSelectedRow();
	 			selectStation(r);
	 			
	 		}
	 	});
		scrollPaneTop.setViewportView(ltable2);
		scrollPaneTop.revalidate();
		scrollPaneTop.repaint();
		if(selectedRow>=0) {
			ltable2.setRowSelectionInterval(selectedRow, selectedRow);
		}
		revalidate();
		repaint();
	}
	public void setDataOfSelectedStation(String datarow[]) {
		editStationID.setText(datarow[0]);
		editStationName.setText(datarow[1]);
		editStationIP.setText(datarow[2]);
		editStationLatitude.setText(datarow[3]);
		editStationLongitude.setText(datarow[4]);
		editStationAltitude.setText(datarow[5]);
	}
	public void setTeleDataOfSelectedStation(String stData[], Object[] teleData) {
		teleStationID.setText(stData[0]);
		teleStationName.setText(stData[1]);
		teleStationIP.setText(stData[2]);
		if(teleData==null) {
			teleStationTime.setText("нет данных");
			teleStationTemperaure.setText("нет данных");
			teleStationAtmosphericPressure.setText("нет данных");
			teleStationHumidity.setText("нет данных");
			teleStationPrecipitationAmount.setText("нет данных");
			teleStationWindDirection.setText("нет данных");
			teleStationWindSpeed.setText("нет данных");
		
		}else {
			DateFormat dateFormat =new SimpleDateFormat("dd.MM.yy HH:mm:ss", new Locale("ru"));
			String date = dateFormat.format((Long)teleData[1]);
			
			teleStationTime.setText(date);
			teleStationAtmosphericPressure.setText(String.format("%.2f",(double) teleData[2]));
			teleStationHumidity.setText(String.format("%.2f",(double) teleData[3]));
			teleStationPrecipitationAmount.setText(String.format("%.2f",(double) teleData[4]));
			teleStationTemperaure.setText(String.format("%.2f",(double) teleData[5]));
			teleStationWindDirection.setText(String.valueOf((int) teleData[6]));
			teleStationWindSpeed.setText(String.format("%.2f",(Double) teleData[7]));
			
		
		}
	}
	public void setDataBaseState(String databasePath, double databaseSize,double partitionFree, double partitionTotal) {
		if(!isChoosingPath) {
			databasePathField.setText(databasePath);
			databaseSizeField.setText(String.valueOf(databaseSize));
			partitionFreeField.setText(String.valueOf(partitionFree));
			partitionTotalField.setText(String.valueOf(partitionTotal));
			 coinsTotalField.setText(String.format("%.2f",databaseSize*123));
		}
	}
	public String getNewPath() {
		// TODO Auto-generated method stub
		return databasePathField.getText();
	}
}
