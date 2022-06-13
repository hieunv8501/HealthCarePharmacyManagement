package Components;

import com.github.lgooddatepicker.components.DatePicker;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class DateButton extends JButton {
    
    public DateButton(DatePicker dp) {
        ImageIcon dPickerIcon = new ImageIcon(getClass().getResource("/Images/icons8_calendar_31_30px.png"));
        JButton datePickerButton = dp.getComponentToggleCalendarButton();
        datePickerButton.setText("");
        datePickerButton.setIcon(dPickerIcon); 
    }   
}
