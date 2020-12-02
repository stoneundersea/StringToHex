package com.java.yangnj;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.springframework.stereotype.Component;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;

/**
 * @author ynj
 * @program: StringToHex
 * @description:
 * @date 2020-11-30 20:15:19
 */
@Component
public class MainController {
    @FXML
    private AnchorPane rootLayout;
    @FXML
    private Button stringToHexButton;
    @FXML
    private Button hexToStringButton;
    @FXML
    private ChoiceBox charsetSelect;
    @FXML
    private TextField stringText;
    @FXML
    private TextField hexText;
    @FXML
    private TextField unicodeText;

    private static String[] commonCharset = {
            "UTF-8","GB18030","Big5","GB2312","GBK","x-IBM1388","UTF-16",
            "UTF-16BE","UTF-16LE"
    };

    public void initialize() {
        /**
        * Some charset items first in ChoiceBox.
         */
        List itemList = new ArrayList();
        for (int i = 0; i < commonCharset.length; i++) {
            if (Charset.isSupported(commonCharset[i])) {
                itemList.add(commonCharset[i]);
            }
        }
        SortedMap<String, Charset> charsets = Charset.availableCharsets();
        Set<String> keys = charsets.keySet();
        for (String key : keys) {
            Charset charset = charsets.get(key);
            if (!isInCommonCharset(charset.displayName(), commonCharset)) {
                itemList.add(charset.displayName());
            }
        }
        ObservableList<String> observableList = FXCollections.observableList(itemList);
        charsetSelect.setItems(observableList);
        //charsetSelect.setStyle("-fx-font-family: 'Calibri';-fx-font-size: 25;");
        charsetSelect.getSelectionModel().select(0);
    }

    @FXML
    public void buttonClicked(ActionEvent actionEvent) throws UnsupportedEncodingException {

        if(stringToHexButton == (Button)actionEvent.getSource()){
            hexText.setText(ConvertUtils.stringToHexStringByCharsetName(stringText.getText(),
                    (String) charsetSelect.getSelectionModel().getSelectedItem()));
            unicodeText.setText(ConvertUtils.getUnicodeFormUtf16(stringText.getText()));
        }

        if(hexToStringButton == (Button)actionEvent.getSource()){
            stringText.setText(ConvertUtils.hexStringToStringByCharsetName(hexText.getText(),
                    (String) charsetSelect.getSelectionModel().getSelectedItem()));
            unicodeText.setText("");
        }

    }
    @FXML
    public void choiceBoxClicked(ActionEvent actionEvent){

    }

    /**
     *
     * @param charsetName
     * @param commonCharset
     * @return boolean(true or false)
     */
    private boolean isInCommonCharset(String charsetName,String[] commonCharset){
        for(int i = 0;i < commonCharset.length;i++){
            if(charsetName.equalsIgnoreCase(commonCharset[i])){
                return true;
            }
        }
        return false;
    }
}
