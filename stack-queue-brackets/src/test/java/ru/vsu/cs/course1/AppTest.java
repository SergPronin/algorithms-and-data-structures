package ru.vsu.cs.course1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    @Test
    public void testBracketCheckerCustomStack() {
        assertTrue(BracketChecker.check("()"), "Simple parentheses should be valid");
        assertTrue(BracketChecker.check("([]){}"), "Mixed brackets should be valid");
        assertFalse(BracketChecker.check(")("), "Unmatched closing first should be invalid");
        assertFalse(BracketChecker.check("((]"), "Mismatched brackets should be invalid");
        assertTrue(BracketChecker.check("((()){})"), "Nested brackets should be valid");
    }

    @Test
    public void testBracketCheckerStandardStack() {
        assertTrue(BracketCheckerStandard.check("()"), "Simple parentheses should be valid");
        assertTrue(BracketCheckerStandard.check("([]){}"), "Mixed brackets should be valid");
        assertFalse(BracketCheckerStandard.check(")("), "Unmatched closing first should be invalid");
        assertFalse(BracketCheckerStandard.check("((]"), "Mismatched brackets should be invalid");
        assertTrue(BracketCheckerStandard.check("((()){})"), "Nested brackets should be valid");
    }

    @Test
    public void testBracketCheckerRecursive() {
        BracketCheckerRecursive checker = new BracketCheckerRecursive();
        assertTrue(checker.check("()"), "Simple parentheses should be valid");
        assertTrue(checker.check("([]){}"), "Mixed brackets should be valid");
        assertFalse(checker.check(")("), "Unmatched closing first should be invalid");
        assertFalse(checker.check("((]"), "Mismatched brackets should be invalid");
        assertTrue(checker.check("((()){})"), "Nested brackets should be valid");
    }
}