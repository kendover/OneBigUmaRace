public class GameStarter {
    public static void main(String[] args) {
        Player player = new Player();
        player.connectToServer();
        player.assignSprite();
        player.addKeyBindings();
        player.setUpGUI();
    }
    
}