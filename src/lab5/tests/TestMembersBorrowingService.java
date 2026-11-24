package lab5.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import lab5.BorrowingService;
import lab5.Member;

public class TestMembersBorrowingService {

    @Test
    void testMemberServices() {
        BorrowingService service =
                BorrowingService.getInstance();

        Member member1 = new Member("Member 1", service);
        Member member2 = new Member("Member 2", service);

        assertSame(
            member1.getBorrowingService(),
            member2.getBorrowingService(),
            "Members have two different borrowing services"
        );
    }
}