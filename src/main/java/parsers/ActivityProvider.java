package parsers;

import DO.ActivityFeatures;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


/**
 * This class parses all Shops and LEHs with provided scanner. For this purpose ScannerAdapter is used
 */
public class ActivityProvider implements ActivityParser {

    private final Retailer retailer;

    public ActivityProvider(Retailer retailer) {
        this.retailer = retailer;
    }

    public List<ActivityFeatures> getRawShops(Scanner scanner) {
        List<ActivityFeatures> listShops;

        ScannerAdapter parser = new ScannerAdapter(scanner, this.retailer);
        listShops = parser.parseShops();
        Set<Integer> idAlreadySeen = new HashSet<>();
        listShops.removeIf(p -> !idAlreadySeen.add(p.getId()));
        assert listShops != null;
        return listShops;
    }

    public List<ActivityFeatures> getLEH(Scanner scanner) {
        ScannerAdapter parser = new ScannerAdapter(scanner, this.retailer);
        List<ActivityFeatures> settLEH = parser.parseLEH();
        Set<Integer> idAlreadySeen = new HashSet<>();
        settLEH.removeIf(p -> !idAlreadySeen.add(p.getId()));
        assert idAlreadySeen.size() == settLEH.size();
        return settLEH;
    }

}
