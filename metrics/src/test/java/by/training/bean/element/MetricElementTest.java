package by.training.bean.element;

import static org.junit.Assert.*;
import static org.easymock.EasyMock.*;

import org.junit.Test;

public class MetricElementTest {

    @Test
    public void testRefreshInterval() {
        WidgetElement easymock = createNiceMock(WidgetElement.class);

        expect(easymock.getActive()).andReturn(8).times(2);
        expect(easymock.getIconMetrics()).andReturn(null);
        expect(easymock.getActive()).andReturn(1);
        replay(easymock);

        ConfigElement config = new ConfigElement();
        config.setWidget(easymock);

        easymock.setActive(8);
        assertEquals(8, config.getWidget().getActive());
        assertNull("testRefreshInterval has failed", config.getWidget().getIconMetrics());
        assertEquals(8, config.getWidget().getActive());

        easymock.setActive(1);
        assertEquals(1, config.getWidget().getActive());

        verify(easymock);
    }

}
