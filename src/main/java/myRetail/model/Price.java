package myRetail.model;

public class Price {
    private double value;
    private String currency_code;
    public Price(){};
    public Price(double value, String currency_code){
        this.value = value;
        this.currency_code = currency_code;
    }

    public String getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }

    public double getValue() {

        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format(
                "Price[value=%f, currency_code=%s ]",
                value, currency_code);
    }
}
