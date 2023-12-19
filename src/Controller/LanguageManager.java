package Controller;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageManager {
    private static ResourceBundle messages;

    public static void setLanguage(String languageCode){
        Locale locale = new Locale(languageCode);
        messages = ResourceBundle.getBundle("messages", locale);
    }

    public static String getMessage(String key){
        return messages.getString(key);
    }
}
