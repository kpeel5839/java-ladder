package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final int SPLIT_LIMIT_REMOVER = -1;
    private static final String PLAYER_NAME_INPUT_MESSAGE = "참여할 사람 이름을 입력하세요. (이름은 쉼표(,)로 구분하세요)";
    private static final String REWARDS_INPUT_MESSAGE = "실행 결과를 입력하세요. (결과는 쉼표(,)로 구분하세요)";
    private static final String LADDER_HEIGHT_INPUT_MESSAGE = "최대 사다리 높이는 몇 개인가요?";
    private static final String COMMAND_INPUT_MESSAGE = "결과를 보고 싶은 사람은?";
    private static final String DELIMITER = ",";
    private static final String NON_INTEGER_ERROR_MESSAGE = "높이는 정수만 입력 가능합니다.";
    private static final Scanner SCANNER = new Scanner(System.in);

    public List<String> requestPlayerNames() {
        String playerNamesInput = requestUserInput(PLAYER_NAME_INPUT_MESSAGE);

        return getSplitInput(playerNamesInput);
    }


    public int requestLadderHeight() {
        String heightInput = requestUserInput(LADDER_HEIGHT_INPUT_MESSAGE);

        try {
            return Integer.parseInt(heightInput);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(NON_INTEGER_ERROR_MESSAGE);
        }
    }

    public List<String> requestRewards() {
        String rewardsInput = requestUserInput(REWARDS_INPUT_MESSAGE);

        return getSplitInput(rewardsInput);
    }

    public String requestCommand() {
        return requestUserInput(COMMAND_INPUT_MESSAGE).trim();
    }

    private List<String> getSplitInput(String inputToSplit) {
        return Arrays.stream(inputToSplit.split(DELIMITER, SPLIT_LIMIT_REMOVER))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    private String requestUserInput(String requestMessage) {
        System.out.println(requestMessage);
        String playerNames = SCANNER.nextLine();
        System.out.print(System.lineSeparator());
        return playerNames;
    }

}
