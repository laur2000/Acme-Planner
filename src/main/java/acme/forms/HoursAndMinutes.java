package acme.forms;

import java.math.BigDecimal;

public class HoursAndMinutes {
	static final Double DECIMAL_CONVERTER = 1 / 0.6;
	static final Double FORMATTED_CONVERTER = 0.6 / 1;

    private final Integer hours;
    private final Integer minutes;

    public static HoursAndMinutes fromDecimalTime(final Double decimalTime) {
        Integer hours = (int) Math.floor(decimalTime);
        Integer minutes = (int) Math.round((decimalTime - Math.floor(decimalTime)) * HoursAndMinutes.FORMATTED_CONVERTER * 100);
        if (minutes.equals(60)) {
        	hours++;
        	minutes = 0;
        }
        return new HoursAndMinutes(hours, minutes);
    }

    public static HoursAndMinutes fromFormattedTime(final Double formattedTime) throws Exception {
        Integer hours = (int) Math.floor(formattedTime);
        Integer minutes = (int) Math.round((formattedTime - Math.floor(formattedTime)) * 100);
        if (minutes >= 60 || hours > 9999 || BigDecimal.valueOf(formattedTime).scale() > 2) {
            throw new Exception("Input range is invalid");
        }
        return new HoursAndMinutes(hours, minutes);
    }

    public static HoursAndMinutes fromHoursAndMinutes(final Integer hours, final Integer minutes) throws Exception {
        if (minutes >= 60) {
            throw new Exception("Input range is invalid");
        }
        return new HoursAndMinutes(hours, minutes);
    }

    public static HoursAndMinutes empty() {
        return new HoursAndMinutes(0, 0);
    }

    private HoursAndMinutes(final Integer hours, final Integer minutes) {
        super();
        this.hours = hours;
        this.minutes = minutes;
    }

    public Integer getHours() {
        return this.hours;
    }

    public Integer getMinutes() {
        return this.minutes;
    }

    public Double getDecimalTime() {
        return this.hours + (double) this.minutes / 100 * HoursAndMinutes.DECIMAL_CONVERTER;
    }

    public Double getFormattedTime() {
        return this.hours + (double) this.minutes / 100;
    }

    @Override
    public String toString() {
        if (this.hours >= 24) {
            return String.format("%dd %dh %dmin", this.hours / 24, this.hours % 24, this.minutes);
        } else {
            return String.format("%dh %dmin", this.hours, this.minutes);
        }
    }
}
