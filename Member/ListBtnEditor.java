package Member;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ListBtnEditor extends DefaultCellEditor {

	private ListBtn button;
	private ListEvent event;
	//private String lbl = "查看";
	//private Boolean clicked;

	public ListBtnEditor() {
		super(new JTextField());
		button = new ListBtn("查看");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//調用自訂處理方法
				//fireEditingStopped();
				//Member.outputClassmate();
				event.invoke(e);
			}
		});

	}
	
	public ListBtnEditor(ListEvent e) {
		this();
		this.event = e;
	}

	@Override 
    public Object getCellEditorValue() 
    { 
        return this.button.getText(); 
    } 
	
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
			//ListBtn btn = (ListBtn) value;
			button.setRow(row);
			button.setColumn(column);
			//button.setStr(btn.getStr());
			//clicked = true;
			return button;
	}
	
}

abstract class ListEvent {
	public abstract void invoke(ActionEvent e);
}
