package dev.freaking.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import dev.freaking.main.Handler;
import dev.freaking.model.Assets;

public class GameOverState extends State {

    private Label noiDung;
    private LabelStyle style;
    private Skin skin;
    private int SCREEN_WIDTH = Gdx.graphics.getWidth();
    private int SCREEN_HEIGHT = Gdx.graphics.getHeight();
    private int score;
    private BitmapFont gameOverFont, scoreFont, bestScoreFont;
    private TextureRegion background;

    public GameOverState(Handler handler, GameStateManager gsm, int score) {
        super(handler, gsm);
        this.background = Assets.bgGameOver;
        PlayState.HIGH_SCORE = Math.max(PlayState.HIGH_SCORE, score);
        this.score = score;
        System.out.println(score);
        System.out.println(PlayState.HIGH_SCORE);
        generateFont();

    }

    private void generateFont() {
        FreeTypeFontGenerator generator =
                new FreeTypeFontGenerator(Gdx.files.internal("gloriahallelujah.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter =
                new FreeTypeFontGenerator.FreeTypeFontParameter();

        // Game Over Font
        parameter.size = SCREEN_WIDTH / 10;
        parameter.characters = "GameOver";
        gameOverFont = generator.generateFont(parameter);
        // ScoreFont

        parameter.size = (int) SCREEN_WIDTH / 14;
        parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()>?:";
        scoreFont = generator.generateFont(parameter);
        bestScoreFont = generator.generateFont(parameter);

        scoreFont.setColor(com.badlogic.gdx.graphics.Color.WHITE);

        bestScoreFont.setColor(com.badlogic.gdx.graphics.Color.WHITE);

        generator.dispose();
    }

    private void handleInput() {
//        if (Gdx.input.justTouched()) {
//            gsm.set(new PlayState(handler, gsm));
//        }
        if (Gdx.input.justTouched()) {
            if (Gdx.input.getX() >= SCREEN_WIDTH * 0.29f
                    && Gdx.input.getX() <= SCREEN_WIDTH * 0.68f
                    && Gdx.input.getY() >= SCREEN_HEIGHT * 0.815f
                    && Gdx.input.getY() <= SCREEN_HEIGHT * 0.9f) {
                gsm.set(new MenuState(handler, gsm));
            }
            if (Gdx.input.getX() >= SCREEN_WIDTH * 0.29f
                    && Gdx.input.getX() <= SCREEN_WIDTH * 0.68f
                    && Gdx.input.getY() >= SCREEN_HEIGHT * 0.67f
                    && Gdx.input.getY() <= SCREEN_HEIGHT * 0.75f) {
                gsm.set(new PlayState(handler, gsm));
            }
        }
    }

    @Override
    public void update(float dt) {
        Gdx.gl.glClearColor(64 / 255f, 64 / 255f, 64 / 255f, 64 / 255f);
        handleInput();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.begin();
        batch.draw(background, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
//        gameOverFont.draw(batch, "Game Over", SCREEN_WIDTH * 0.26f, SCREEN_HEIGHT * 0.6f);
        scoreFont.draw(batch, "Score : " + score, SCREEN_WIDTH * 0.2f, SCREEN_HEIGHT * 0.54f);
        bestScoreFont.draw(batch, "High Score : " + PlayState.HIGH_SCORE, SCREEN_WIDTH * 0.2f, SCREEN_HEIGHT * 0.46f);
        batch.end();
    }

    @Override
    public void dispose() {
        gameOverFont.dispose();
        scoreFont.dispose();
        bestScoreFont.dispose();
    }

}
