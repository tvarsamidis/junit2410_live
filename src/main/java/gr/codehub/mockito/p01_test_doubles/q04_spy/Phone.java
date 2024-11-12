package gr.codehub.mockito.p01_test_doubles.q04_spy;

import java.time.LocalDate;

public class Phone {
    private String phoneId;
    private String model;
    private int price;
    private LocalDate releaseDate;

    public Phone(String phoneId, String model, int price, LocalDate releaseDate) {
        this.phoneId = phoneId;
        this.model = model;
        this.price = price;
        this.releaseDate = releaseDate;
    }

    public String getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(String phoneId) {
        this.phoneId = phoneId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
