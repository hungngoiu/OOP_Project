package utilz;

import main.Game;

public class Constant {
    public static final float GRAVITY = 0.04f * Game.SCALE;
    public static final int ANIMATION_PER_SECOND = 8;
    public static final int TICKS_PER_ANI = Game.UPS / ANIMATION_PER_SECOND;

    public static class Projectiles{
        public static final int CANNON_BALL_DEFAULT_WIDTH = 15;
        public static final int CANNON_BALL_DEFAULT_HEIGHT = 15;

        public static final int CANNON_BALL_WIDTH = (int)(Game.SCALE * CANNON_BALL_DEFAULT_WIDTH);
        public static final int CANNON_BALL_HEIGHT = (int)(Game.SCALE * CANNON_BALL_DEFAULT_HEIGHT);
        public static final float SPEED = 0.5f * Game.SCALE;
        public static final int CANNON_BALL_DAMAGE = 25;
    }
    public static class ObjectConstant {
        public static final int RED_POTION = 0;
        public static final int BLUE_POTION = 1;
        public static final int BARREL = 2;
        public static final int BOX = 3;
        public static final int SPIKE = 4;
        public static final int CANNON_LEFT = 5;
        public static final int CANNON_RIGHT = 6;
        public static final int CANNON_BALL = 7;
        public static final int TREASURE_CHEST = 8;
        public static final int KEY = 9;
        public static final int PADLOCK = 10;
        public static final int FLAG = 11;
        public static final int PLATFORM = 12;

        public static final int RED_POTION_VALUE = 15;//HEALTH
        public static final int BLUE_POTION_VALUE = 30;//POWER

        public static final int CONTAINER_WIDTH_DEFAULT = 40;
        public static final int CONTAINER_HEIGHT_DEFAULT = 30;
        public static final int CONTAINER_WIDTH = (int) ( CONTAINER_WIDTH_DEFAULT* Game.SCALE);
        public static final int CONTAINER_HEIGHT = (int) ( CONTAINER_HEIGHT_DEFAULT* Game.SCALE);

        public static final int POTION_WIDTH_DEFAULT = 12;
        public static final int POTION_HEIGHT_DEFAULT = 16;
        public static final int POTION_WIDTH = (int) ( POTION_WIDTH_DEFAULT* Game.SCALE);
        public static final int POTION_HEIGHT = (int) ( POTION_HEIGHT_DEFAULT* Game.SCALE);

        public static final int SPIKE_WIDTH_DEFAULT = 32;
        public static final int SPIKE_HEIGHT_DEFAULT = 32;
        public static final int SPIKE_WIDTH = (int) (Game.SCALE * SPIKE_WIDTH_DEFAULT);
        public static final int SPIKE_HEIGHT = (int) (Game.SCALE * SPIKE_HEIGHT_DEFAULT);

        public static final int CANNON_WIDTH_DEFAULT = 40;
        public static final int CANNON_HEIGHT_DEFAULT = 26;
        public static final int CANNON_WIDTH = (int) (CANNON_WIDTH_DEFAULT * Game.SCALE);
        public static final int CANNON_HEIGHT = (int) (CANNON_HEIGHT_DEFAULT * Game.SCALE);

        public static final int TREASURE_CHEST_WIDTH_DEFAULT = 32;
        public static final int TREASURE_CHEST_HEIGHT_DEFAULT = 32;
        public static final int TREASURE_CHEST_WIDTH = (int) (TREASURE_CHEST_WIDTH_DEFAULT * Game.SCALE);
        public static final int TREASURE_CHEST_HEIGHT = (int) (TREASURE_CHEST_HEIGHT_DEFAULT * Game.SCALE);

        public static final int KEY_WIDTH_DEFAULT = 24;
        public static final int KEY_HEIGHT_DEFAULT = 24;
        public static final int KEY_WIDTH = (int) (KEY_WIDTH_DEFAULT * Game.SCALE);
        public static final int KEY_HEIGHT = (int) (KEY_HEIGHT_DEFAULT * Game.SCALE);

        public static final int PADLOCK_WIDTH_DEFAULT = 32;
        public static final int PADLOCK_HEIGHT_DEFAULT = 32;
        public static final int PADLOCK_WIDTH = (int) (PADLOCK_WIDTH_DEFAULT * Game.SCALE);
        public static final int PADLOCK_HEIGHT = (int) (PADLOCK_HEIGHT_DEFAULT * Game.SCALE);

        public static final int FLAG_WIDTH_DEFAULT = 34;
        public static final int FLAG_HEIGHT_HEIGHT = 93;
        public static final int FLAG_WIDTH = (int) (FLAG_WIDTH_DEFAULT * Game.SCALE);
        public static final int FLAG_HEIGHT = (int) (FLAG_HEIGHT_HEIGHT * Game.SCALE);

        public static final int PLATFORM_WIDTH_DEFAULT = 30;
        public static final int PLATFORM_HEIGHT_DEFAULT = 16;
        public static final int PLATFORM_WIDTH = (int) (PLATFORM_WIDTH_DEFAULT * Game.SCALE);
        public static final int PLATFORM_HEIGHT = (int) (PLATFORM_HEIGHT_DEFAULT * Game.SCALE);

        public static int GetObjectSpriteAmount(int object_type) {

            switch (object_type) {
                case RED_POTION, BLUE_POTION:
                    return 7;
                case BARREL, BOX:
                    return 8;
                case CANNON_LEFT, CANNON_RIGHT:
                    return 7;
                case TREASURE_CHEST, KEY:
                    return 8;
                case FLAG:
                    return 9;
            }
            return 1;
        }
    }
    public static class Environment {
        public static final int BIG_CLOUDS_WIDTH_DEFAULT = 408;
        public static final int BIG_CLOUDS_HEIGHT_DEFAULT = 101;
        public static final int BIG_CLOUDS_WIDTH = (int) (BIG_CLOUDS_WIDTH_DEFAULT * Game.SCALE);
        public static final int BIG_CLOUDS_HEIGHT = (int) (BIG_CLOUDS_HEIGHT_DEFAULT * Game.SCALE);
        public static final int SMALL_CLOUDS_WIDTH_DEFAULT = 74;
        public static final int SMALL_CLOUDS_HEIGHT_DEFAULT = 24;
        public static final int SMALL_CLOUDS_WIDTH = (int) (SMALL_CLOUDS_WIDTH_DEFAULT * Game.SCALE);
        public static final int SMALL_CLOUDS_HEIGHT = (int) (SMALL_CLOUDS_HEIGHT_DEFAULT * Game.SCALE);
    }

    public static class EnemyConstant {
        public static final int CRABBY = 0;
        public static final int PINKSTAR = 1;
        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int ATTACK = 2;
        public static final int HIT = 3;
        public static final int DEAD = 4;

        public static final int CRABBY_WIDTH_DEFAULT = 72;
        public static final int CRABBY_HEIGHT_DEFAULT = 32;
        public static final int CRABBY_WIDTH = (int) (CRABBY_WIDTH_DEFAULT * Game.SCALE);
        public static final int CRABBY_HEIGHT = (int) (CRABBY_HEIGHT_DEFAULT * Game.SCALE);

        public static final int CRABBY_DRAWOFFSET_X = (int) (25 * Game.SCALE);
        public static final int CRABBY_DRAWOFFSET_Y = (int) (6 * Game.SCALE);

        public static final int PINKSTAR_WIDTH_DEFAULT = 34;
        public static final int PINKSTAR_HEIGHT_DEFAULT = 30;
        public static final int PINKSTAR_WIDTH = (int) (PINKSTAR_WIDTH_DEFAULT * Game.SCALE);
        public static final int PINKSTAR_HEIGHT = (int) (PINKSTAR_HEIGHT_DEFAULT * Game.SCALE);

        public static final int PINKSTAR_DRAWOFFSET_X = (int) (9 * Game.SCALE);
        public static final int PINKSTAR_DRAWOFFSET_Y = (int) (7 * Game.SCALE);

        public static int GetEnemySpriteAmount(int enemy_type, int enemy_state) {

            switch (enemy_type) {
                case CRABBY:
                    switch (enemy_state) {
                        case IDLE:
                            return 9;
                        case RUNNING:
                            return 6;
                        case ATTACK:
                            return 7;
                        case HIT:
                            return 4;
                        case DEAD:
                            return 5;
                    }
                case PINKSTAR:
                    switch (enemy_state) {
                        case IDLE:
                            return 8;
                        case RUNNING:
                            return 6;
                        case HIT:
                            return 4;
                        case DEAD:
                            return 5;
                        case ATTACK:
                            return 7;
                    }
            }

            return 0;
        }

        public static int GetMaxHealth(int enemy_type) {
            switch (enemy_type) {
                case CRABBY:
                    return 10;
                default:
                    return 1;
            }
        }

        public static int GetEnemyDmg(int enemy_type) {
            switch (enemy_type) {
                case CRABBY:
                    return 15;
                case PINKSTAR:
                    return 20;
                default:
                    return 0;
            }

        }

    }
    public static class UI{
         public static class Buttons{
             public static final int B_WIDTH_DEFAULT = 140;
             public static final int B_HEIGHT_DEFAULT = 56;
             public static final int B_WIDTH = (int) (B_WIDTH_DEFAULT * Game.SCALE);
             public static final int B_HEIGHT = (int) (B_HEIGHT_DEFAULT * Game.SCALE);
         }
        public static class URMButtons {
            public static final int URM_DEFAULT_SIZE = 56;
            public static final int URM_SIZE = (int) (URM_DEFAULT_SIZE * Game.SCALE);

        }

        public static class PauseButtons{
             public static final int SOUND_SIZE_DEFAULT = 42;
            public static final int SOUND_SIZE = (int)(SOUND_SIZE_DEFAULT * Game.SCALE);
        }
        public static class VolumeButtons {
            public static final int VOLUME_DEFAULT_WIDTH = 28;
            public static final int VOLUME_DEFAULT_HEIGHT = 44;
            public static final int SLIDER_DEFAULT_WIDTH = 215;

            public static final int VOLUME_WIDTH = (int) (VOLUME_DEFAULT_WIDTH * Game.SCALE);
            public static final int VOLUME_HEIGHT = (int) (VOLUME_DEFAULT_HEIGHT * Game.SCALE);
            public static final int SLIDER_WIDTH = (int) (SLIDER_DEFAULT_WIDTH * Game.SCALE);
        }
    }
    public static class Directions {
        public static final int UP = 0;
        public static final int LEFT = 1;
        public static final int DOWN = 2;
        public static final int RIGHT = 3;
    }
    public static class PlayerConstants {
        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int JUMP = 2;
        public static final int FALLING = 3;
        public static final int ATTACK = 4;
        public static final int HIT = 5;
        public static final int DEAD = 6;
        public static int GetPlayerSpriteAmount(int player_action) {
            switch (player_action) {
                case DEAD:
                    return 8;
                case RUNNING:
                    return 6;
                case IDLE:
                    return 5;
                case HIT:
                    return 4;
                case JUMP:
                case ATTACK:
                    return 3;
                case FALLING:
                default:
                    return 1;
            }
        }
    }

}
