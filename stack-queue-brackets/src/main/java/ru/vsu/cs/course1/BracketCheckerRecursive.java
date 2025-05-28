package ru.vsu.cs.course1;

public class BracketCheckerRecursive {
    private int index = 0;

    public boolean check(String input) {
        index = 0;
        try {
            return checkRecursive(input) && index == input.length();
        } catch (Exception e) {
            return false;
        }
    }

    private boolean checkRecursive(String input) {
        while (index < input.length()) {
            char currentChar = input.charAt(index);
            if (currentChar == '(' || currentChar == '[' || currentChar == '{') {
                index++;
                if (!checkRecursive(input)) {
                    return false;
                }
                if (index >= input.length() || !isMatchingPair(currentChar, input.charAt(index))) {
                    return false;
                }
                index++;
            } else if (currentChar == ')' || currentChar == ']' || currentChar == '}') {
                return true;
            } else {
                index++;
            }
        }
        return true;
    }

    private boolean isMatchingPair(char opening, char closing) {
        return (opening == '(' && closing == ')') ||
                (opening == '[' && closing == ']') ||
                (opening == '{' && closing == '}');
    }
}