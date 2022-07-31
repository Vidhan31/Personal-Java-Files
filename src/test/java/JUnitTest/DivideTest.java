package JUnitTest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DivideTest {

    @Test
    void add() {

        assertEquals(1, Divide.add(9, 5));
    }

    @Test
    void addTest() {

        assertEquals(2, Divide.add(10 ,5));
    }

    @Test
    void addAnotherTest() {

        assertEquals(0, Divide.add(0 ,9));
    }

    @Test
    void addGivesAnException() {

        assertThrows(ArithmeticException.class,
                () -> Divide.add(5, 0));
    }
}