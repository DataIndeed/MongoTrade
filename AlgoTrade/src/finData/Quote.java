package finData;

import java.text.DateFormat;
import java.util.Date;

/**
 * @author Elier
 * @since 2013-12-09
 */
public class Quote implements Comparable<Quote> {
    private String name;
    private Date date;
    private double open;
    private double high;
    private double low;
    private double close;
    private double adjClose;
    private long volume;

    /**
     * Constructor
     * @param name
     * @param date
     * @param open
     * @param high
     * @param low
     * @param close
     * @param adjClose
     * @param volume
     */
    public Quote(String name, Date date, double open, double high, double low, double close, double adjClose, long volume) {
        setName(name);
        setDate(date);
        setOpen(open);
        setHigh(high);
        setLow(low);
        setClose(close);
        setAdjClose(adjClose);
        setVolume(volume);
    }

    /**
     * Get the quote name
     * @return quote name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the quote name
     * @param name quote name
     */
    public void setName(String name) {
        this.name = name.toUpperCase();
    }

    /**
     * Get the quote date
     * @return quote date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Set the quote date
     * @param date quote date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Get the quote open price
     * @return quote open price
     */
    public double getOpen() {
        return open;
    }

    /**
     * Set the quote open price
     * @param open quote open price
     */
    public void setOpen(double open) {
        this.open = open;
    }

    /**
     * Get the quote highest price
     * @return quote highest price
     */
    public double getHigh() {
        return high;
    }

    /**
     * Set the quote highest price
     * @param high quote highest price
     */
    public void setHigh(double high) {
        this.high = high;
    }

    /**
     * Get the quote lowest price
     * @return quote lowest price
     */
    public double getLow() {
        return low;
    }

    /**
     * Set the quote lowest price
     * @param low quote lowest price
     */
    public void setLow(double low) {
        this.low = low;
    }

    /**
     * Get the quote close price
     * @return quote close price
     */
    public double getClose() {
        return close;
    }

    /**
     * Set the quote close price
     * @param close quote close price
     */
    public void setClose(double close) {
        this.close = close;
    }

    /**
     * Get the adjusted closing price
     * @return adjusted closing price
     */
    public double getAdjClose() {
        return adjClose;
    }

    /**
     * Set the adjusted closing price
     * @param adjClose adjusted closing price
     */
    public void setAdjClose(double adjClose) {
        this.adjClose = adjClose;
    }

    /**
     * Get the quote volume
     * @return quote volume
     */
    public long getVolume() {
        return volume;
    }

    /**
     * Set the quote volume
     * @param volume quote volume
     */
    public void setVolume(long volume) {
        this.volume = volume;
    }

    /**
     * Overriding hashCode() method. Return the date field's hashCode
     * @return date field's hashCode
     */
    @Override
    public int hashCode() {
        return new Date().hashCode();
    }

    /**
     * Equals
     * @param obj other quote objecto to comapre to
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (!(obj instanceof Quote))
            return false;

        Quote t = (Quote) obj;

        return new String(name).equals(t.getName()) &&
                new Date(date.getTime()).equals(t.getDate());
               /* (new Double(open)).equals(t.getOpen()) &&
                (new Double(high)).equals(t.getOpen()) &&
                (new Double(low)).equals(t.getOpen()) &&
                (new Double(close)).equals(t.getClose()) &&
                (new Double(adjClose)).equals(t.getAdjClose()) &&
                (new Double(volume)).equals(t.getVolume());*/
    }

    /**
     *
     * @param t the other quote to compare to
     * @return
     */
    @Override
    public int compareTo(Quote t) {
        return this.date.compareTo(t.date);
    }

    /**
     * String format of the Quote object. Mainly for debugging proposal
     * @return
     */
    @Override
    public String toString() {
        return "{\n\tName: " + DateFormat.getDateInstance(DateFormat.SHORT).format(getDate()) +
                "\n\tDate: " + getDate() +
                "\n\tHigh: " + getHigh() +
                "\n\tLow: " + getLow() +
                "\n\tClose: " + getClose() +
                "\n\tAdjClose: " + getAdjClose() +
                "\n\tVolume: " + getVolume() +
                "\n}";
    }
}