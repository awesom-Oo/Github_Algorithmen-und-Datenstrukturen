/*
package ueb07;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class HeapStackTest {

    @org.junit.jupiter.api.Test
    void popTest() {
        HeapStack<String> test = new HeapStack<>(12);
        test.push("12");
        test.push("22");
        test.push("32");
        test.push("424");
        test.push("552");
        test.push("6662");
        assertEquals("6662", test.pop());
        assertEquals("552", test.pop());
        assertEquals("424", test.pop());
    }

    @org.junit.jupiter.api.Test
    void pushTest() {
        HeapStack<String> test = new HeapStack<>(12);
        test.push("12");
        test.push("22");
        test.push("32");
        test.push("424");
        test.push("552");
        test.push("6662");
    }

    @org.junit.jupiter.api.Test
    void peekTest() {
        HeapStack<String> test = new HeapStack<>(12);
        test.push("12");
        test.push("22");
        test.push("32");
        test.push("424");
        test.push("552");
        test.push("6662");
        assertEquals("6662", test.peek());
        assertEquals("6662", test.peek());
    }

    @org.junit.jupiter.api.Test
    void clearTest() {
        HeapStack<String> test = new HeapStack<>(12);
        test.push("12");
        test.push("22");
        test.push("32");
        test.push("424");
        test.push("552");
        test.push("6662");
        test.clear();
        assertThrows(NoSuchElementException.class, () -> test.pop());
    }

    @org.junit.jupiter.api.Test
    void isEmptyTest() {
        HeapStack<String> test = new HeapStack<>(12);
        assertTrue(test.isEmpty());
        test.push("12");
        assertFalse(test.isEmpty());
        test.push("22");
        assertFalse(test.isEmpty());
    }

    @org.junit.jupiter.api.Test
    void capacityTest() {
        HeapStack<String> test = new HeapStack<>(12);
        assertThrows(IllegalStateException.class, () -> {
            test.push("da");
            test.push("da2");
            test.push("da3");
            test.push("da4");
            test.push("da5");
            test.push("da6");
            test.push("da7");
            test.push("da8");
            test.push("da9");
            test.push("da10");
            test.push("da11");
            test.push("da12");
            test.push("da13");
            test.push("da14");
            test.push("da14");
            test.push("da14");
            test.push("da14");
        });
    }
}
*/
