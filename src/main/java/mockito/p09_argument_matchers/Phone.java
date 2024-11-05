package mockito.p09_argument_matchers;

import java.time.LocalDate;
import java.util.Objects;

public class Phone {
    private String phoneId;
    private String model;
    private int price;
    private LocalDate releaseDate;
    private boolean isFastCharge;

    public Phone() {
    }

    public Phone(String phoneId, String model, int price, LocalDate releaseDate) {
        this.phoneId = phoneId;
        this.model = model;
        this.price = price;
        this.releaseDate = releaseDate;
    }

    public Phone(String phoneId, String model, int price, LocalDate releaseDate, boolean isFastCharge) {
        this.phoneId = phoneId;
        this.model = model;
        this.price = price;
        this.releaseDate = releaseDate;
        this.isFastCharge = isFastCharge;
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

    public boolean isFastCharge() {
        return isFastCharge;
    }

    public void setFastCharge(boolean fastCharge) {
        isFastCharge = fastCharge;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        return price == phone.price &&
               Objects.equals(model, phone.model) &&
               Objects.equals(releaseDate, phone.releaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, price, releaseDate);
    }
}
