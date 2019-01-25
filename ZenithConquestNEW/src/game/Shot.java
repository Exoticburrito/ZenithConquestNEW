package game;


public class Shot extends Sprites implements SharedData {

    private final int H_SPACE = 6;
    private final int V_SPACE = 1;

    public Shot() {
    }

    public Shot(int x, int y) {

        initShot(x, y);
    }

    private void initShot(int x, int y) {
        setImage(playerShot.getImage());
        
        setX(x + H_SPACE);
        setY(y - V_SPACE);
    }
}