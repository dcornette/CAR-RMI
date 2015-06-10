package rmi;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test the class MessageImpl
 */
public class MessageImplTest {

    private MessageImpl msg;

    @Before
    public void setUp() throws Exception {
        this.msg = new MessageImpl("Test content", new SiteImpl("Lille 1"));
    }

    @Test
    public void testGoodGetContent() throws Exception {
        assertEquals(this.msg.getContent(), "Test content");
    }

    @Test
    public void testBadGetContent() throws Exception {
        assertFalse(this.msg.getContent().equals("test content"));
    }

    @Test
    public void testGoodSender() throws Exception {
        assertEquals(this.msg.getSender().getName(), "Lille 1");
    }

    @Test
    public void testBadSender() throws Exception {
        assertFalse(this.msg.getSender().getName().equals("lille 1"));
    }
}