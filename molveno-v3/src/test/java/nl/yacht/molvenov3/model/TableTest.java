package nl.yacht.molvenov3.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class TableTest {

    private Table table;

    @Before
    public void setUp() {
        this.table = new Table();
    }

    @Test
    public void testUnreservedEmptyTable() {
        boolean reservable = table.canTableBeUsedNow();

        Assert.assertTrue(reservable);
    }

    @Test
    public void testReservedTable() {

        List<LocalDateTime> tijden = Arrays.asList(LocalDateTime.now().minusHours(1), LocalDateTime.now().plusHours(1));

        this.table.setReservationTimes(tijden);
        boolean reservable = this.table.canTableBeUsedNow();

        Assert.assertFalse(reservable);
    }
}
