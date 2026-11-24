package lab5.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import lab5.BorrowingService;

public class TestSingleton {

    @Test
    void testSingleton() {
        BorrowingService service1 =
                BorrowingService.getInstance();

        BorrowingService service2 =
                BorrowingService.getInstance();

        assertSame(service1, service2,
                "Two Singleton instances detected");
    }
}