package gr.codehub.mockito.p05_exception_handling;

import java.time.LocalDate;

public class PhoneRequest {
    private String model;
    private int price;
    private LocalDate releaseDate;

    public PhoneRequest(String model, int price, LocalDate releaseDate) {
        this.model = model;
        this.price = price;
        this.releaseDate = releaseDate;
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
