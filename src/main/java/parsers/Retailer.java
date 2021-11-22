package parsers;

public enum Retailer {
    LIDL("LIDL");

    private final String value;

    Retailer(String value){
        this.value = value;
    }

    public String get(){
        return value;
    }

    }



