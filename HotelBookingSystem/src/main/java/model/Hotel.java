package main.java.model;

public class Hotel {

    private final String name;               // Hotel name
    private final int rating;                // Hotel rating
    private final int weekdayRegularRate;    // Weekday rate for Regular customers
    private final int weekendRegularRate;    // Weekend rate for Regular customers
    private final int weekdayRewardRate;     // Weekday rate for Rewards customers
    private final int weekendRewardRate;     // Weekend rate for Rewards customers

    // Constructor
    public Hotel(String name, int rating,
                 int weekdayRegularRate, int weekendRegularRate,
                 int weekdayRewardRate, int weekendRewardRate) {
        this.name = name;
        this.rating = rating;
        this.weekdayRegularRate = weekdayRegularRate;
        this.weekendRegularRate = weekendRegularRate;
        this.weekdayRewardRate = weekdayRewardRate;
        this.weekendRewardRate = weekendRewardRate;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    public int getWeekdayRate(boolean isRewardCustomer) {
        return isRewardCustomer ? weekdayRewardRate : weekdayRegularRate;
    }

    public int getWeekendRate(boolean isRewardCustomer) {
        return isRewardCustomer ? weekendRewardRate : weekendRegularRate;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "name='" + name + '\'' +
                ", rating=" + rating +
                ", weekdayRegularRate=" + weekdayRegularRate +
                ", weekendRegularRate=" + weekendRegularRate +
                ", weekdayRewardRate=" + weekdayRewardRate +
                ", weekendRewardRate=" + weekendRewardRate +
                '}';
    }
}

