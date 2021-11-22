package parsers;

import DO.ActivityFeatures;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * this class is little bit tricky. I use  an Adapter Class for Scanner.class to parse match and sort data from the main file
 * so, first read line from main file and serialize values like usually for  @List<ActivityFeatures>
 */
class ScannerAdapter {

    private Scanner scanner;
    private Retailer retailer;

    ScannerAdapter(Scanner scanner, Retailer retailer) {
        this.retailer = retailer;
        this.scanner = scanner;
    }


    List<ActivityFeatures> parseShops() {  // todo change all if statements to String.format or scanner.nextDouble or scanner.matcher and so on..
        List<ActivityFeatures> shops = new ArrayList<>();
        Pattern pattern = Pattern.compile("[0-9]{5,7}.[0-9]+");
        while (scanner.hasNextLine()) { //line is present
            String line = scanner.nextLine();
            String[] lineArray = line.split(";");
            //if line has all the entries we need
            //line contains retailer name
            //values are not empty
            if (lineArray.length < 11) continue;
            if (lineArray[10].isEmpty() || lineArray[9].isEmpty() ||
                    lineArray[8].isEmpty() || lineArray[7].isEmpty() ||
                    lineArray[6].isEmpty() || lineArray[4].isEmpty() ||
                    lineArray[3].isEmpty() || lineArray[2].isEmpty() ||
                    lineArray[1].isEmpty() || lineArray[0].isEmpty()) continue;

            if (containsWord(lineArray[7], retailer)) {
                if (pattern.matcher(lineArray[9]).matches() && pattern.matcher(lineArray[10]).matches()) { //proves whether Lon and Lat both are correct
                    ActivityFeatures activity = new ActivityFeatures();
                    activity.setX(Double.parseDouble(lineArray[9]));
                    activity.setY(Double.parseDouble(lineArray[10]));
                    activity.setShpName(lineArray[7]); //set the retailer name
                    activity.setId(Integer.parseInt(lineArray[6]));
                    activity.setArea(lineArray[8]);
                    activity.setDepotIDReal(Integer.parseInt(lineArray[0])); //depot id
                    shops.add(activity);
                }
            }
        }
        return shops;
    }

    List<ActivityFeatures> parseLEH() {
        List<ActivityFeatures> centers = new ArrayList<>();
        Pattern pattern = Pattern.compile("[0-9]+.[0-9]+"); //matcher for Lon. and Lat coordinates
        while (scanner.hasNextLine()) { //for each line
            String line = scanner.nextLine();
            String[] lineArray = line.split(";"); //split string
            // line is not empty and has at least 16 elements (shops and lehs)
            if (lineArray.length < 11) continue;
            if (lineArray[10].isEmpty() || lineArray[9].isEmpty() ||
                    lineArray[8].isEmpty() || lineArray[7].isEmpty() ||
                    lineArray[6].isEmpty() || lineArray[4].isEmpty() ||
                    lineArray[3].isEmpty() || lineArray[2].isEmpty() ||
                    lineArray[1].isEmpty() || lineArray[0].isEmpty())
                continue;  //values are not empty. proofs if the string line contains both depots and shops data

            if (containsWord(lineArray[7], retailer)) { //if string contains retailer name
                if (pattern.matcher(lineArray[3]).matches() && pattern.matcher(lineArray[4]).matches()) { //proves whether coordinates are digits

                    ActivityFeatures centerActivity = new ActivityFeatures();
                    centerActivity.setId(Integer.parseInt(lineArray[0]));
                    centerActivity.setX(Double.parseDouble(lineArray[3]));
                    centerActivity.setY(Double.parseDouble(lineArray[4]));
                    centers.add(centerActivity);
                }
            }
        }
        return centers;
    }

    //all possible shops in MainFile:  AEZ, Aktiv Discount, Akzenta, Aldi Markt, Aldi Sued, Bonus, C+C Shaper, Cap, Centra, City-Supermarkt, Diska, Dornseifer
    // E-aktiv Markt, E-C+C, E-Center, Edeka, E-neukauf, E-Reichelt, Fleggaard, Fruchtboerse, Handelshof, Hieber, Hit,
    // Ihre Kette Extra, Inkoop, Irma, Kaufland, Kein Banner, Konsum Leipzig, Konsum SB Frisch, Konsum-Kauf, Kupsch. Lidl, Marktkauf,
    // Metro C&C (D), Mini Markt, Minipreis, Nah & Frisch Cam, Nah & Gut, Nahkauf, Netto, Netto(Nord), Netto City, Norma, NP, Penny
    // Perfetto, PuG, Real, Rewe, Rewe Center, Rewe City, Rewe XL, Schuwe, Simmel, Spar, Spar, Express, Toom, Topkauf, Treff, WEZ
    private static boolean containsWord(String shopName, Retailer retailer) {
        boolean isThisLineRetailer = false;
        if (retailer == Retailer.LIDL) {
            if (shopName.toLowerCase().contains("lidl")) //3550 shops
                isThisLineRetailer = true;
        }
//        this script finds desired word in line
//        String[] split = word.split(" ");
//        String REGEX_FIND_WORD = "(?i).*?\\b%s.+\\b.*?"; //(?i) makes this case insensitive, \\b \\b means word boundaries, %s-> insert retailer word in string.format
//        String regex = String.format(REGEX_FIND_WORD, word);
//        text.matches(regex);

        return isThisLineRetailer;
    }
}
