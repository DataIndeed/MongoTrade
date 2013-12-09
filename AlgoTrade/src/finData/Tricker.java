package finData;

import java.text.DateFormat;
import java.util.Date;

/**
 * @author Elier
 * @since 2013-12-09
 */
public class Tricker implements Comparable<Tricker> {
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
    public Tricker(String name, Date date, double open, double high, double low, double close, double adjClose, long volume) {
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
     * Get the tricker name
     * @return tricker name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the tricker name
     * @param name tricker name
     */
    public void setName(String name) {
        this.name = name.toUpperCase();
    }

    /**
     * Get the tricker date
     * @return tricker date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Set the tricker date
     * @param date tricker date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Get the tricker open price
     * @return tricker open price
     */
    public double getOpen() {
        return open;
    }

    /**
     * Set the tricker open price
     * @param open tricker open price
     */
    public void setOpen(double open) {
        this.open = open;
    }

    /**
     * Get the tricker highest price
     * @return tricker highest price
     */
    public double getHigh() {
        return high;
    }

    /**
     * Set the tricker highest price
     * @param high tricker highest price
     */
    public void setHigh(double high) {
        this.high = high;
    }

    /**
     * Get the tricker lowest price
     * @return tricker lowest price
     */
    public double getLow() {
        return low;
    }

    /**
     * Set the tricker lowest price
     * @param low tricker lowest price
     */
    public void setLow(double low) {
        this.low = low;
    }

    /**
     * Get the tricker close price
     * @return tricker close price
     */
    public double getClose() {
        return close;
    }

    /**
     * Set the tricker close price
     * @param close tricker close price
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
     * Get the tricker volume
     * @return tricker volume
     */
    public long getVolume() {
        return volume;
    }

    /**
     * Set the tricker volume
     * @param volume tricker volume
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
     * @param obj other tricker objecto to comapre to
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (!(obj instanceof Tricker))
            return false;

        Tricker t = (Tricker) obj;

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
     * @param t the other tricker to compare to
     * @return
     */
    @Override
    public int compareTo(Tricker t) {
        return this.date.compareTo(t.date);
    }

    /**
     * String format of the Tricker object. Mainly for debugging proposal
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