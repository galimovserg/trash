


import javax.swing.table.AbstractTableModel;
/*
 * JMultiTable это класс, предназначенный для создания абстрактных таблиц, которые в свою очередь
 * выводятся на экран, и не изменяют содержимое
 * 
 * */

public class JMultiTable extends AbstractTableModel{
	//данные	 
	String data[][];
	//названия столбцев
	private String title[];
	//конструктор абстрактной таблицы
	public JMultiTable(String data[][],String title[]) {
		this.data=data;
		this.title=title;
	}
	//метод заменяет содержимое
	public void setContent(String data[][],String title[]){
		this.data=data;
		this.title=title;
	}
	//получает имя столбца(вызывается при перерисовке)
	public String getColumnName(int c) {
		 return title[c];
	}

	//возвращает количество колонок(нужно для прорисовки)
	public int getColumnCount() {
			return title.length;
	}
	//возвращает количество строк(нужно для прорисовки)
	public int getRowCount() {
		return data.length;
	}
	//возвращает содержимое ячейки(нужно для прорисовки)
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}
	//возврашает редактируется ячейка или нет
	public boolean isCellEditable(int rowIndex,int columnIndex) {
		return false;	
	}

}
