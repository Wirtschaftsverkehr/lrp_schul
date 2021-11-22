package parsers;


import DO.Depot;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FutureDepotParser {
    private Scanner scanner;

    public FutureDepotParser(Scanner scanner) {
        this.scanner = scanner;
    }

    public List<Depot> parse() {
        List<Depot> depots = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] lineArray = line.split(";");
            //if line has all the entries we need
            //line contains retailer name
            //values are not empty

            if (lineArray.length < 6) continue;
            if (lineArray[6].isEmpty() ||
                    lineArray[5].isEmpty() || lineArray[4].isEmpty() ||
                    lineArray[3].isEmpty() || lineArray[2].isEmpty() ||
                    lineArray[1].isEmpty() || lineArray[0].isEmpty()) continue;
            try {
                Integer id = Integer.parseInt(lineArray[0]);
                Double lon = Double.parseDouble(lineArray[2]);
                Double lat = Double.parseDouble(lineArray[3]);
                double area = Double.parseDouble(lineArray[1]);
                double rent_min = Double.parseDouble(lineArray[5]);
                double rent_max = Double.parseDouble(lineArray[6]);

                Depot depot = new Depot(id, lon, lat, area, rent_min, rent_max);
                depots.add(depot);
            } catch (NumberFormatException e) {
            }
        }
        return depots;
    }
}
