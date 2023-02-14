package domain;

import java.util.regex.Pattern;

public class Name {

    private static final int LENGTH_LOWER_BOUND_INCLUSIVE = 1;
    private static final int LENGTH_UPPER_BOUND_INCLUSIVE = 5;

    private static final String LENGTH_ERROR_MESSAGE = "이름은 1 ~ 5 글자여야 합니다.";
    private static final String VALUE_ERROR_MESSAGE = "이름은 문자만 숫자로 가능합니다.";
    private static final String VALID_WORD_REGEX = "(\\w)+";
    private static final Pattern NAME_PATTERN = Pattern.compile(VALID_WORD_REGEX);

    private final String name;

    public Name(final String name) {
        validate(name);
        this.name = name;
    }

    public void validate(String name) {
        validateWord(name);
        validateName(name);
    }

    private void validateName(String name) {
        if (isOutOfRange(name)) {
            throw new IllegalArgumentException(LENGTH_ERROR_MESSAGE);
        }
    }

    private void validateWord(String name) {
        if (!NAME_PATTERN.matcher(name).matches()) {
            throw new IllegalArgumentException(VALUE_ERROR_MESSAGE);
        }
    }

    private boolean isOutOfRange(String name) {
        return !(LENGTH_LOWER_BOUND_INCLUSIVE <= name.length()
                && name.length() <= LENGTH_UPPER_BOUND_INCLUSIVE);
    }

}
