package view;

import domain.Direction;
import domain.Point;
import domain.Result;
import domain.Results;
import domain.ladder.Ladder;

import java.util.List;

public class OutputView {

    private static final int PLAYER_NAME_BOX_SIZE_MAX = 5;
    private static final int BRIDGE_LENGTH = 5;
    private static final int VALUE_FOR_CALCULATING_LEADING_OF_NAME = 2;
    private static final String BLANK = " ";
    private static final String LADDER_RESULT_PREFIX_MESSAGE = "사다리 결과";
    private static final String RESULT_PREFIX_MESSAGE = "실행 결과";
    private static final String RESULT_FORMAT= "%s : %s%n";
    private static final String NAME_FORMAT = "%-5s" + BLANK;
    private static final String EMPTY_BRIDGE = BLANK.repeat(BRIDGE_LENGTH);
    private static final String BRIDGE = "-".repeat(BRIDGE_LENGTH);
    private static final String LINE_COMPONENT = "|";
    private static final String LADDER_PREFIX = BLANK.repeat(2);

    public void printLadderResultPrefix() {
        System.out.println(LADDER_RESULT_PREFIX_MESSAGE);
    }

    public void printNames(List<String> names) {
        for (String name : names) {
            System.out.printf(NAME_FORMAT, formatName(name));
        }

        breakLine();
    }

    private String formatName(String name) {
        int leadingOfName = (PLAYER_NAME_BOX_SIZE_MAX - name.length()) / VALUE_FOR_CALCULATING_LEADING_OF_NAME;
        return BLANK.repeat(leadingOfName) + name;
    }

    public void printResult(Results results) {
        System.out.println(RESULT_PREFIX_MESSAGE);
        results.getResults()
                .forEach(this::getFormatResult);
    }

    private void getFormatResult(Result result) {
        String playerName = result.getPlayerName();
        String rewardName = result.getRewardName();
        System.out.printf(RESULT_FORMAT, playerName, rewardName);
    }

    private void breakLine() {
        System.out.print(System.lineSeparator());
    }

    public void printLadder(Ladder ladder) {
        int lineAmount = ladder.getLines().size();
        for (int pointAt = 0; pointAt < ladder.getHeightSize(); pointAt++) {
            printLadderPrefix();
            printSingleLadder(ladder, lineAmount, pointAt);
            breakLine();
        }
    }

    private void printSingleLadder(Ladder ladder, int lineAmount, int pointAt) {
        for (int lineAt = 0; lineAt < lineAmount; lineAt++) {
            Point point = ladder.getPoint(pointAt, lineAt);
            printPointDirection(point);
        }
    }

    private void printPointDirection(Point point) {
        System.out.print(LINE_COMPONENT);
        if (point.matchDirection(Direction.RIGHT_DOWN)) {
            System.out.print(BRIDGE);
            return;
        }
        System.out.print(EMPTY_BRIDGE);
    }

    private void printLadderPrefix() {
        System.out.print(LADDER_PREFIX);
    }

    public void printError(Exception exception) {
        printErrorMessage(exception.getMessage());
    }

    private void printErrorMessage(String message) {
        System.out.println(message);
    }

}
