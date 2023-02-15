package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LadderTest {

    @Test
    @DisplayName("게임 참여자 수와 높이에 따라 사다리 생성")
    void createLadderSuccess() {
        int playerCount = 3;
        int heightSize = 5;
        PlayerNumber playerNumber = new PlayerNumber(playerCount);
        Height height = new Height(heightSize);

        Ladder ladder = Ladder.of(playerNumber, height);

        assertThat(ladder.getLines())
                .hasSize(playerCount);
        assertThat(ladder.getLines().get(0).getPoints())
                .hasSize(heightSize);
    }

    @Test
    @DisplayName("사다리의 다리 생성")
    void createHorizontalLineAtSuccess() {
        int playerCount = 3;
        int heightSize = 4;
        PlayerNumber playerNumber = new PlayerNumber(playerCount);
        Height height = new Height(heightSize);

        Ladder ladder = Ladder.of(playerNumber, height);
        ladder.buildBridge(1, 2);

        ladder.getPoint(1, 2).matchDirection(Direction.RIGHT_DOWN);
        ladder.getPoint(1, 3).matchDirection(Direction.LEFT_DOWN);
    }


}
