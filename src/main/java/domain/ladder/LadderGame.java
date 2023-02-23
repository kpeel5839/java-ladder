package domain.ladder;

import domain.Command;
import domain.Direction;
import domain.Player;
import domain.Players;
import domain.Point;
import domain.ResultCommand;
import domain.Results;
import domain.Rewards;

import java.util.List;

public class LadderGame {

    private final Ladder ladder;
    private final Players players;
    private final Rewards rewards;

    public LadderGame(Ladder ladder, Players players, Rewards rewards) {
        this.ladder = ladder;
        this.players = players;
        this.rewards = rewards;
        gameStart();
    }

    public void gameStart() {
        players.getPlayers()
                .forEach(this::moveEachPlayer);
    }

    private void moveEachPlayer(Player player) {
        int playerHeight = 0;

        while (playerHeight != ladder.getHeightSize()) {
            move(player, playerHeight);
            playerHeight++;
        }
    }

    private void move(Player player, int playerHeight) {
        int standingLine = player.getStandingLine();
        Point point = ladder.getPoint(playerHeight, standingLine);

        if (point.matchDirection(Direction.LEFT_DOWN)) {
            player.moveLeft();
        }

        if (point.matchDirection(Direction.RIGHT_DOWN)) {
            player.moveRight();
        }
    }

    public Results getResults(Command command) {
        ResultCommand resultCommand = new ResultCommand(players);
        List<Player> playerFromCommand = resultCommand.getCommandResult(command);
        return Results.from(playerFromCommand, rewards.getRewards());
    }

}
