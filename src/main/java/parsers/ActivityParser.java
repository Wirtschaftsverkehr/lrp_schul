package parsers;


import DO.ActivityFeatures;

import java.util.List;
import java.util.Scanner;

public interface ActivityParser {
    List<ActivityFeatures> getRawShops(Scanner scanner);
    List<ActivityFeatures> getLEH(Scanner scanner);

}
