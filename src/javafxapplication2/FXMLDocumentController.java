/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Asus1
 */
public class FXMLDocumentController implements Initializable {
    
    private int op;
    private boolean reset = false;
    private boolean replace = false;
    private double result = 0;
    private double second = 0;
    
    
    private Label label;
    @FXML
    private Button b1;
    @FXML
    private Button b2;
    @FXML
    private Button b3;
    @FXML
    private Button b4;
    @FXML
    private Button b5;
    @FXML
    private Button b6;
    @FXML
    private Button AC;
    @FXML
    private Button plusmin;
    @FXML
    private Button b7;
    @FXML
    private Button b8;
    @FXML
    private Button percent;
    @FXML
    private Button div;
    @FXML
    private Button b9;
    @FXML
    private Button mul;
    @FXML
    private Button sub;
    @FXML
    private Button add;
    @FXML
    private Button decimal;
    @FXML
    private Button b0;
    @FXML
    private Button equal;
    @FXML
    private TextField textfield;
    @FXML
    private TextField whatOp;
    
    @FXML 
    private void showOnText(ActionEvent event) {
        String s = textfield.getText();
        Button b = (Button) event.getSource();
        
        if(textfield.getText().contains("-")) 
            s = textfield.getText().substring(1, textfield.getText().length());
        
        if((s.substring(0,1).equals("0") && !textfield.getText().contains(".")) || reset) 
            textfield.setText(b.getText());
        else 
            textfield.setText(textfield.getText() + b.getText());
        
        reset = false;
        
        if(!replace) {
            result = 0;
            result = Double.parseDouble(textfield.getText());
        }
        else 
            second = Double.parseDouble(textfield.getText());
        
    }
    
    @FXML 
    private void decimal(ActionEvent event) {
        String s = textfield.getText();
        Button b = (Button) event.getSource();
        boolean neg = false;
        
        if(textfield.getText().contains("-")) 
            neg = true;
        
        if(replace && (Double.parseDouble(textfield.getText()) == result)) {
            textfield.setText("0.");
            reset = false;
        }
        else if(b.getText().equals(".") && !textfield.getText().contains(".")) 
            textfield.setText(neg ? "-" + textfield.getText() + "." : textfield.getText() + ".");  
            
    }
    
    @FXML 
    private void setOp(ActionEvent event) {
        Button b = (Button) event.getSource();
        
        if(b.getText().equals("+")) {
            op = 1;
            whatOp.setText("Addition");
        }
        if(b.getText().equals("-")) {
            op = 2;
            whatOp.setText("Subtraction");
        }
        if(b.getText().equals("x")) {
            op = 3;
            whatOp.setText("Multiplication");
        }
        if(b.getText().equals("/")) {
            op = 4;
            whatOp.setText("Division");
        }
        reset = true;
        replace = true;
    }
    
    @FXML 
    private void calculate(ActionEvent event) {
        if(op == 1)
            result += second;
        if(op == 2)
            result -= second;
        if(op == 3)
            result *= second;
        if(op == 4)
            result /= second;
       
        boolean dec = checkDec(result);
        
        if(dec) 
            textfield.setText(Double.toString(result));
        else 
            textfield.setText(Integer.toString((int)result));
        reset = true;
        replace = false;
        whatOp.setText("");
    }
    
    private boolean checkDec(Double num) {
        if(num < 0) {
            while(num < 0)
                num += 1;
            
            if(num > 0)
                return true;
        }
        else {
            while(num > 0) 
                num -= 1;
            
            if(num < 0)
                return true;
        }
        return false;
    }
    
    @FXML 
    private void AC(ActionEvent event) {
        result = 0; 
        textfield.setText("0");
    }
    
    @FXML 
    private void numSign(ActionEvent event) {
        //if replace that means first num is typed in
        if(textfield.getText().contains("-"))
            textfield.setText(textfield.getText().substring(1,textfield.getText().length()));
        else 
            textfield.setText("-" + textfield.getText());
        
       if(replace)
           second *= -1;
       else 
           result *= -1;
          
    }
    
    @FXML 
    private void percentage(ActionEvent event) {
        String display = "";
    
        if(replace) {
            second /= 100; 
            boolean dec = checkDec(second);
            display = dec ? Double.toString(second) : Integer.toString((int) second);    
        }
        else {
            result /= 100;
            boolean dec = checkDec(result);
            display = dec ? Double.toString(result) : Integer.toString((int) result);    
        }
        
        textfield.setText(display);
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
