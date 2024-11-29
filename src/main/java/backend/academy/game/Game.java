package backend.academy.game;

public class Game {

    public Game() {
    }

    public void start() {
        GameLogic game = GameLogic.gameLogicCreator();
        game.renderMazeWithoutPass();
        game.renderMazeWithPath();
    }
}
