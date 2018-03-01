import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoordinatesTest {
    Coordinates c;

    @BeforeEach
    void setUp() {
        c = new Coordinates(3, 5);
    }

    @Test
    @DisplayName("richtiger Koordinaten-String")

    void corToString() {
        assertEquals("3:5", c.toString());
    }

    @Test
    @DisplayName(" not instance of Coordinates")

    void corIsNotCor() {
        assertFalse(c.equals(new String("Test")));
    }

    @Test
    @DisplayName("Coordinates is null")

    void corIsNotNull() {
        assertFalse(c.equals​(null));
    }

    @Test
    @DisplayName("Coordinates is a Coordinates")

    void corIscor() {
        assertTrue(c.equals(c));
    }


}