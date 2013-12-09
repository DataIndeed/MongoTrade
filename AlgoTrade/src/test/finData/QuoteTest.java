package test.finData;

import finData.Quote;

import java.util.Date;
import org.junit.Assert;
import org.junit.Test;

/**
 * Author: Elier
 * Date: 12/9/13
 */
public class QuoteTest {
    String name = "value";
    Date date = new Date(0);
    double open = 0;
    double high = 0;
    double low = 0;
    double close = 0;
    double adjClose = 0;
    long volume = 0;
    //new Quote(name, date, open, high, low, close, adjClose, volume);

    @Test
    public void testGetName() throws Exception {
        String nameUpper = name.toUpperCase();
        String t1 = new Quote(name, date, open, high, low, close, adjClose, volume).getName();
        String t2 = new Quote(nameUpper, date, open, high, low, close, adjClose, volume).getName();
        Assert.assertEquals(t1, t2);
    }

    @Test
    public void testSetName() throws Exception {

    }

    @Test
    public void testGetDate() throws Exception {

    }

    @Test
    public void testSetDate() throws Exception {

    }

    @Test
    public void testGetOpen() throws Exception {

    }

    @Test
    public void testSetOpen() throws Exception {

    }

    @Test
    public void testGetHigh() throws Exception {

    }

    @Test
    public void testSetHigh() throws Exception {

    }

    @Test
    public void testGetLow() throws Exception {

    }

    @Test
    public void testSetLow() throws Exception {

    }

    @Test
    public void testGetClose() throws Exception {

    }

    @Test
    public void testSetClose() throws Exception {

    }

    @Test
    public void testGetAdjClose() throws Exception {

    }

    @Test
    public void testSetAdjClose() throws Exception {

    }

    @Test
    public void testGetVolume() throws Exception {

    }

    @Test
    public void testSetVolume() throws Exception {

    }

    @Test
    public void testHashCode() throws Exception {

    }

    @Test
    public void testEquals() throws Exception {
        // as name param is converted to uppercase and the equals method just check the equality of name and date attributes
        String nameUpper = name.toUpperCase();
        String t1 = new Quote(name, date, open, high, low, close, adjClose, volume).getName();
        String t2 = new Quote(nameUpper, date, 1, 1, 1, 1, 1, 1).getName();
        Assert.assertEquals(t1, t2);

        // with different dates there is not equality
        Quote t3 = new Quote(nameUpper, new Date(1), 1, 1, 1, 1, 1, 1);
        Quote t4 = new Quote(name, date, open, high, low, close, adjClose, volume);
        Assert.assertFalse(t3.equals(t4));

    }

    @Test
    public void testCompareTo() throws Exception {
        Quote t1 = new Quote(name, new Date(0), open, high, low, close, adjClose, volume);
        Quote t2 = new Quote(name, new Date(1), open, high, low, close, adjClose, volume);

        Assert.assertTrue(t1.compareTo(t2) < 0);
    }

    @Test
    public void testToString() throws Exception {

    }
}
