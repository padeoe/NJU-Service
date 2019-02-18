import com.padeoe.nicservice.njuwlan.utils.ConversionUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by padeoe on 2015/12/17.
 */
public class ConversionUtilsTest {

    @Test
    public void testGetShownDataSize() throws Exception {
        Assert.assertEquals(ConversionUtils.getShownDataSize("1024"),"1KB");
        Assert.assertEquals(ConversionUtils.getShownDataSize("2048"),"2KB");
        Assert.assertEquals(ConversionUtils.getShownDataSize("2049"),"2.00KB");
        Assert.assertEquals(ConversionUtils.getShownDataSize("1024001"),"1000.00KB");
        Assert.assertEquals(ConversionUtils.getShownDataSize("1099511627777"),"1.00TB");
    }

    @Test
    public void testGetIPv4() throws Exception {
        Assert.assertEquals(ConversionUtils.getIPv4("1926545573"),"114.212.192.165");

    }
}