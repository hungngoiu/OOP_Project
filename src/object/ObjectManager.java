package object;

import gameStates.Playing;
import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static utilz.Constant.ObjectConstant.*;

public class ObjectManager {
    private Playing playing;
    private BufferedImage[][] potionImgs, containerImgs;
    private ArrayList<Potion> potions;
    private ArrayList<GameContainer> containers;

    public ObjectManager(Playing playing) {
        this.playing = playing;
        loadImgs();
        potions = new ArrayList<>();
        potions.add (new Potion (300,300,RED_POTION));
        potions.add (new Potion (400,300,BLUE_POTION));
        containers = new ArrayList<>();
        containers.add (new GameContainer(500,300, BARREL));
        containers.add (new GameContainer(600,300, BOX));
    }

    private void loadImgs() {
        BufferedImage potionSprite = LoadSave.getSpriteAtlas(LoadSave.POTION_ATLAS);
        potionImgs = new BufferedImage[2][7];

        for (int j = 0; j < potionImgs.length; j++)
            for (int i = 0; i < potionImgs[j].length; i++)
                potionImgs[j][i] = potionSprite.getSubimage(12 * i, 16 * j, 12, 16);

        BufferedImage containerSprite = LoadSave.getSpriteAtlas(LoadSave.CONTAINER_ATLAS);
        containerImgs = new BufferedImage[2][8];

        for (int j = 0; j < containerImgs.length; j++)
            for (int i = 0; i < containerImgs[j].length; i++)
                containerImgs[j][i] = containerSprite.getSubimage(40 * i, 30 * j, 40, 30);

       /* BufferedImage potionSprite = LoadSave.getSpriteAtlas(LoadSave.POTION_ATLAS);
        potionImgs = new BufferedImage[2][7];
        for (int j = 0; j < potionImgs.length; j++) {
            for (int i = 0; i < potionImgs[j].length; j++) {
                potionImgs[j][i] = potionSprite.getSubimage(40 * i, 30 * j, 40, 30);
            }
        }*/
    }

    public void update() {
        for (Potion p : potions) {
            if (p.isActive())
                p.update();
        }
        for (GameContainer gc : containers) {
            if (gc.isActive())
                gc.update();
        }
    }

    public void draw(Graphics g, int xLvlOffset) {
        drawPotions(g, xLvlOffset);
        drawContainers(g, xLvlOffset);
    }

    private void drawContainers(Graphics g, int xLvlOffset) {
        for (GameContainer gc : containers) {
            if (gc.isActive()) {
                int type = 0;
                if (gc.getObjectType() == BARREL) {
                    type = 1;
                }
                g.drawImage(containerImgs[type][gc.getAniIndex()], (int) (gc.getHitbox().x - gc.getxDrawOffSet() - xLvlOffset), (int) (gc.getHitbox().y - gc.getxDrawOffSet()), CONTAINER_WIDTH, CONTAINER_HEIGHT, null);

            }
        }
    }

    private void drawPotions(Graphics g, int xLvlOffset) {
        for (Potion p : potions) {
            if (p.isActive()) {
                int type = 0;// blue
                if (p.getObjectType() == RED_POTION)
                    type = 1; //red
                g.drawImage(potionImgs[type][p.getAniIndex()], (int) (p.getHitbox().x - p.getxDrawOffSet() - xLvlOffset), (int) (p.getHitbox().y - p.getxDrawOffSet()), POTION_WIDTH, POTION_HEIGHT, null);

            }
        }
    }
}