package entities;

import main.Game;
import utilz.LoadSave;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import gameStates.Playing;

import static main.Game.SCALE;
import static main.Game.UPS;
import static utilz.Constant.ANIMATION_PER_SECOND;
import static utilz.Constant.GRAVITY;
import static utilz.Constant.PlayerConstants.*;
import static utilz.HelpMethods.*;

public class Player extends Entity {
    private BufferedImage img;
    private BufferedImage[][] animations;
    private int[][] lvlData;
    private boolean left, right, jump;
    private boolean isMoving = false;
    private boolean attacking = false;

    private float xDrawOffset = 21 * SCALE;
    private float yDrawOffset = 4 * SCALE;


    // Applying Gravity
    private float jumpSpeed = -2.25f * SCALE;

    // Health Bar UI
    private BufferedImage statusBarImg;

    private int statusBarWidth = (int) (192 * Game.SCALE);
    private int statusBarHeight = (int) (58 * Game.SCALE);
    private int statusBarX = (int) (10 * Game.SCALE);
    private int statusBarY = (int) (10 * Game.SCALE);

    private int healthBarWidth = (int) (150 * Game.SCALE);
    private int healthBarHeight = (int) (4 * Game.SCALE);
    private int healthBarXStart = (int) (34 * Game.SCALE);
    private int healthBarYStart = (int) (14 * Game.SCALE);

    private int healthWidth = healthBarWidth;

    private int flipX = 0;
    private int flipW = 1;

    private boolean attackChecked;
    protected Playing playing;


    public Player(int x, int y, int width, int height, Playing playing) {
        super(x, y, width, height);
        this.state = IDLE;
        this.playing = playing;
        this.maxHealth = 100;
        this.currentHealth = maxHealth;
        this.walkSpeed = 1.0f * SCALE;
        initHitBox(20, 28);
        loadAnimation();
        initAttackBox();
    }
    public void setSpawn(Point spawn) {
        this.x = spawn.x;
        this.y = spawn.y;
        hitBox.x = x;
        hitBox.y = y;
        initAttackBox();
    }

    private void initAttackBox() {
        attackBox = new Rectangle2D.Float(x + hitBox.width + (int) (Game.SCALE * 10), y, (int) (20 * Game.SCALE), (int) (20 * Game.SCALE));
    }

    //set direction
    public void setLeft(boolean left) {
        this.left = left;
    }
    public void setRight(boolean right) {
        this.right = right;
    }
    public void setJump(boolean jump) {
        this.jump = jump;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    public void resetDirBooleans() {
        left = false;
        right = false;
    }

    public void update() {
        updateHealthBar();
        updateAttackBox();

        if (currentHealth <= 0) {
            playing.setGameOver(true);
            return;
        }
        updatePos();
        if (attacking)
            checkAttack();
        updateAnimationTick();
        updateAnimation();
    }

    private void checkAttack() {
        if (attackChecked || aniIndex != 1)
            return;
        attackChecked = true;
        playing.checkEnemyHit(attackBox);

    }
    private void updateAttackBox() {
        if (right)
            attackBox.x = hitBox.x + hitBox.width + (int) (Game.SCALE * 10);
        else if (left)
            attackBox.x = hitBox.x - hitBox.width - (int) (Game.SCALE * 10);
        attackBox.y = hitBox.y + (Game.SCALE * 10);
    }

    private void updateHealthBar() {
        healthWidth = (int) ((currentHealth / (float) maxHealth) * healthBarWidth);
    }


    public void render(Graphics g, int lvlOffset) {
        g.drawImage(animations[state][aniIndex],
                (int) (hitBox.x - xDrawOffset) - lvlOffset + flipX,
                (int) (hitBox.y - yDrawOffset), width * flipW, height, null);
        drawUI(g);
        drawHitbox(g, lvlOffset);
        drawAttackBox(g, lvlOffset);
    }
    private void drawUI(Graphics g) {
        g.drawImage(statusBarImg, statusBarX, statusBarY, statusBarWidth, statusBarHeight, null);
        g.setColor(Color.red);
        g.fillRect(healthBarXStart + statusBarX, healthBarYStart + statusBarY, healthWidth, healthBarHeight);
    }

    private void updatePos() {
        isMoving = false;

        if (jump)
            jump();

        if (!inAir)
            if ((!left && !right) || (right && left))
                return;

        float xSpeed = 0;

        if (left) {
            xSpeed -= walkSpeed;
            flipX = width;
            flipW = -1;
        }
        if (right) {
            xSpeed += walkSpeed;
            flipX = 0;
            flipW = 1;
        }

        if (!inAir)
            if (!IsEntityOnFloor(hitBox, lvlData))
                inAir = true;

        if (inAir) {
            if (CanMoveHere(hitBox.x, hitBox.y + airSpeed, hitBox.width, hitBox.height, lvlData)) {
                hitBox.y += airSpeed;
                airSpeed += GRAVITY;
                updateXPos(xSpeed);
            } else {
                hitBox.y = GetCollisionRoof(hitBox, airSpeed);
                if (airSpeed > 0)
                    resetInAir();
                else
                    airSpeed = GRAVITY;
                updateXPos(xSpeed);
            }

        } else
            updateXPos(xSpeed);
        isMoving = true;
    }

    private void updateXPos(float xSpeed) {
        if(CanMoveHere(hitBox.x + xSpeed, hitBox.y, hitBox.width, hitBox.height, lvlData)) {
            hitBox.x += xSpeed;
        } else {
            hitBox.x = GetCollisionGround(hitBox, xSpeed);
        }
    }

    private void jump() {
        if (inAir)
            return;
        inAir = true;
        airSpeed = jumpSpeed;
    }

    private void resetInAir() {
        inAir = false;
        airSpeed = 0;
    }

    public void updateAnimation() {
        int last_action = state;
        if (isMoving) {
            state = RUNNING;
        } else {
            state = IDLE;
        }

        if (inAir) {
            if (airSpeed < 0) {
                state = JUMP;
            } else {
                state = FALLING;
            }
        }

        if (attacking) {
            state = ATTACK;
            if (last_action != ATTACK) {
                aniIndex = 1;
                aniTick = 0;
                return;
            }

        }
        if (last_action != state) {
            aniIndex = 0;
            aniTick = 0;
        }
    }

    public void updateAnimationTick() {
        aniTick++;
        if (aniTick >= UPS / ANIMATION_PER_SECOND) {
            aniIndex++;
            aniTick = 0;
            if (aniIndex >= GetSpriteAmount(state)) {
                aniIndex = 0;
                attacking = false;
                attackChecked = false;
            }
        }
    }

    public void loadAnimation () {
        img = LoadSave.getSpriteAtlas(LoadSave.PLAYER_ATLAS);
        animations = new BufferedImage[7][8];
        for (int j = 0; j < animations.length; j++) {
            for (int i = 0; i < animations[j].length; i++) {
                animations[j][i] = img.getSubimage(i * 64, j * 40, 64, 40);
            }
        }
        statusBarImg = LoadSave.getSpriteAtlas(LoadSave.STATUS_BAR);
    }
    public void loadLvlData (int[][] lvlData){
        this.lvlData = lvlData;
        if (!IsEntityOnFloor(hitBox, lvlData))
            inAir = true;
    }

    public void changeHealth (int value){
        currentHealth += value;

        if (currentHealth <= 0)
            currentHealth = 0;
        else if (currentHealth >= maxHealth)
            currentHealth = maxHealth;
    }



    public void resetAll() {
        resetDirBooleans();
        inAir = false;
        attacking = false;
        isMoving = false;
        state = IDLE;
        currentHealth = maxHealth;

        hitBox.x = x;
        hitBox.y = y;

        //this is for reset player direction
        flipX = 0;
        flipW = 1;

        if (!IsEntityOnFloor(hitBox, lvlData))
            inAir = true;
    }

}
