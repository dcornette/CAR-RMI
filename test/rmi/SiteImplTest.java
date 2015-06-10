package rmi;

import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;

import static org.junit.Assert.*;

/**
 * Test the class SiteImpl
 */
public class SiteImplTest {

    private SiteImpl site1;
    private SiteImpl site2;

    @Before
    public void SetUp() throws RemoteException {
        this.site1 = new SiteImpl("1");
        this.site2 = new SiteImpl("2");
    }

    @Test
    public void testSendMessage() throws Exception {
        /* To verify that, you need to check if the message has really been received by the site 2 */
    }

    @Test
    public void testGoodAddChild() throws Exception {
        this.site1.addChild(site2);
        assertEquals(this.site1.getChild("2"), this.site2);
    }

    @Test
    public void testBadAddChild() throws Exception {
        this.site1.addChild(site2);
        assertFalse(this.site1.getChild("3").equals(this.site2));
    }

    @Test
    public void testGoodName() throws Exception {
        assertEquals(this.site1.getName(), "1");
    }

    @Test
    public void testBadName() throws Exception {
        assertEquals(this.site1.getName(), "2");
    }
}