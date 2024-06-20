package it.unipv.ingsfw.gasCorpCinema.utils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javafx.scene.control.TextField;

public class StringUtils {

	public static boolean isFieldEmpty(TextField textField) {
        return textField == null || textField.getText().trim().isEmpty();
    }

	public static boolean isNumeric(String str) {
        return str.matches("\\d+(\\.\\d+)?");
    }

}
