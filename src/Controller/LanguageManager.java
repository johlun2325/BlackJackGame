package Controller;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageManager {
    private static ResourceBundle messages;
    private static String currentLanguage = "sv";

    public static void setLanguage(String languageCode){
        System.out.println(currentLanguage);
        currentLanguage = languageCode;
        messages = ResourceBundle.getBundle("messages", Locale.of(languageCode));
    }

    public static String getMessage(String key){
        return messages.getString(key);
    }

    public static void toggleLanguage() {
        setLanguage(currentLanguage.equals("sv") ? "en" : "sv");
    }

}
