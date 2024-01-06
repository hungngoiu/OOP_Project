package utilz;

import main.Game;

import javax.imageio.ImageIO;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.util.Arrays;

import entities.Crabby;

import static utilz.Constant.EnemyConstant.CRABBY;

public class LoadSave {

    public static final String PLAYER_ATLAS = "player_sprites.png";
    public static final String LEVEL_ATLAS = "outside_sprites.png";
    public static final String LEVEL_DATA = "level_one_data_long.png";
    public static final String MENU_BUTTONS = "button_atlas.png";
    public static final String MENU_BACKGROUND = "menu_background.png";
    public static final String BACKGROUND_OF_MENU = "background_of_menu.png";
    public static final String PAUSE_BACKGROUND = "pause_menu.png";
    public static final String URM_BUTTONS = "urm_buttons.png";
    public static final String VOLUME_BUTTONS = "volume_buttons.png";
    public static final String SOUND_BUTTONS  = "sound_button.png";
    public static final String CRABBY_SPRITE = "crabby_sprite.png";
    public static final String STATUS_BAR = "health_power_bar.png";
    public static final String PLAYING_BG_IMG = "playing_bg_img.png";
    public static final String BIG_CLOUDS = "big_clouds.png";
    public static final String SMALL_CLOUDS = "small_clouds.png";
    public static final String COMPLETED_IMG = "completed_sprite.png";

    public static BufferedImage getSpriteAtlas(String fileName) {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);
        try {
            assert is != null;
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert is != null;
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }

    public static BufferedImage[] GetAllLevels() {
        URL url = LoadSave.class.getResource("/lvls");
        File file = null;

        try {
            file = new File(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        File[] files = file.listFiles();
        Arrays.sort(files);

        BufferedImage[] imgs = new BufferedImage[files.length];
        for (int i = 0; i < imgs.length; i++) {
            try {
                imgs[i] = ImageIO.read(files[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return imgs;
    }
    public static ArrayList<Crabby> GetCrabs() {
        BufferedImage img = getSpriteAtlas(LEVEL_DATA);
        ArrayList<Crabby> list = new ArrayList<>();
        for (int j = 0; j < img.getHeight(); j++)
            for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j));
                int value = color.getGreen();
                if (value == CRABBY)
                    list.add(new Crabby(i * Game.TILES_SIZE, j * Game.TILES_SIZE));
            }
        return list;

    }
    public static int[][] GetLevelData() {
        BufferedImage img = getSpriteAtlas(LEVEL_DATA);
        int[][] lvlData = new int[img.getHeight()][img.getWidth()];
        for (int i = 0; i < img.getHeight(); i++) {
            for (int j = 0; j < img.getWidth(); j++) {
                Color color = new Color(img.getRGB(j, i));
                int value = color.getRed();
                if (value >= 48) {
                    value = 11;
                }
                lvlData[i][j] = value;
            }
        }
        return lvlData;
    }
}
