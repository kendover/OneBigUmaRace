public class GameStarter {
    public static void main(String[] args) {
        Player player = new Player();
        player.connectToServer();
        player.setUpGUI();
        player.addKeyBindings();
    }
}
