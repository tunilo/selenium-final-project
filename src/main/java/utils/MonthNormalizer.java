package utils;

import java.util.HashMap;
import java.util.Map;

public class MonthNormalizer {
    public static void main(String[] args) {
        Map<String, String> monthNormalization = new HashMap<>();

        // Full month name to shortened form mappings
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

        // Example usage
        String dateTextBefore = "3 დეკემბერი"; // Example input
        for (Map.Entry<String, String> entry : monthNormalization.entrySet()) {
            dateTextBefore = dateTextBefore.replace(entry.getKey(), entry.getValue());
        }

        System.out.println("Normalized Date: " + dateTextBefore); // Output: "3 დეკ"
    }
}

