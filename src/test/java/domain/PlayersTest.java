package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    public static final String PLAYER_NUMBER_UPPER_BOUND_INCLUSIVE = "참여자 수는 2 ~ 20명만 가능합니다.";

    @Test
    @DisplayName("모든 게임 참여자들의 이름 생성")
    void createPlayerNamesSuccess() {
        List<String> playerNamesInput = List.of("pobi", "honux", "crong", "jk");

        assertDoesNotThrow(() -> Players.from(playerNamesInput));
    }

    @Test
    @DisplayName("모든 게임 참여자들의 이름은 중복될 수 없다")
    void createPlayerNamesFail() {
        List<String> playerNamesInput = List.of("pobi", "pooh", "pooh", "jk");

        assertThatThrownBy(() -> Players.from(playerNamesInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이름은 중복될 수 없습니다.");
    }

    @DisplayName("한명만 참여 할 수 없다")
    @Test
    void createPlayerNumberUnderNumberFail() {
        List<String> input = List.of("pobi");
        assertThatThrownBy(() -> Players.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(PLAYER_NUMBER_UPPER_BOUND_INCLUSIVE);
    }

    @DisplayName("20명 이상은 참여 할 수 없다")
    @Test
    void createPlayerNumberOverNumberFail() {
        List<String> input = new ArrayList<>();
        char toCharacter = 'a';
        for (int i = 0; i < 21; i++) {
            input.add(String.valueOf(i + toCharacter));
        }

        assertThatThrownBy(() -> Players.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(PLAYER_NUMBER_UPPER_BOUND_INCLUSIVE);
    }

}
