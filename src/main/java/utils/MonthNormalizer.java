package utils;
import java.util.HashMap;
import java.util.Map;

public class MonthNormalizer {

    public static String normalizeMonth(String dateText) {
        Map<String, String> monthNormalization = new HashMap<>();
        monthNormalization.put("იანვარი", "იან");
        monthNormalization.put("თებერვალი", "თებ");
        monthNormalization.put("მარტი", "მარ");
        monthNormalization.put("აპრილი", "აპრ");
        monthNormalization.put("მაისი", "მაი");
        monthNormalization.put("ივნისი", "ივნ");
        monthNormalization.put("ივლისი", "ივლ");
        monthNormalization.put("აგვისტო", "აგვ");
        monthNormalization.put("სექტემბერი", "სექ");
        monthNormalization.put("ოქტომბერი", "ოქტ");
        monthNormalization.put("ნოემბერი", "ნოე");
        monthNormalization.put("დეკემბერი", "დეკ");

        for (Map.Entry<String, String> entry : monthNormalization.entrySet()) {
            dateText = dateText.replace(entry.getKey(), entry.getValue());
        }

        return dateText;
    }
}
