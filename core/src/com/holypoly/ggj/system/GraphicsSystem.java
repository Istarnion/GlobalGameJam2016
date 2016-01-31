package com.holypoly.ggj.system;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.holypoly.ggj.component.AnimationComponent;
import com.holypoly.ggj.screen.GameScreen;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author istarnion
 */
public class GraphicsSystem extends AbstractSystem {

    private BitmapFont font;

    public List<AnimationComponent> animations;

    public OrthographicCamera[] cameras;

    private OrthographicCamera hudCam;

    private FitViewport hudViewport;

    private FitViewport viewportLeft, viewportRight;

    private SpriteBatch spriteBatch;

    private SpriteBatch hudBatch;

    private Texture pentagram;

    private Texture background;

    private Texture overlay;

    public GraphicsSystem(GameScreen game) {
        super(game);
        Gdx.gl.glClearColor(0, 0, 0, 1);

        font = new BitmapFont();
        font.setColor(Color.RED);

        animations = new ArrayList<AnimationComponent>();

        spriteBatch = new SpriteBatch();
        hudBatch = new SpriteBatch();
        hudCam = new OrthographicCamera(8, 9);

        cameras = new OrthographicCamera[2];

        cameras[0] = new OrthographicCamera(8, 9);
        cameras[1] = new OrthographicCamera(8, 9);
        viewportLeft = new FitViewport(20, 22.5f, cameras[0]);
        viewportRight = new FitViewport(20, 22.5f, cameras[1]);

        hudViewport = new FitViewport(200, 225, hudCam);


        background = game.assets.get("images/bg.png");
        pentagram = game.assets.get("images/pentagram.png");
        overlay = game.assets.get("images/overlay.png");
    }

    @Override
    public void update(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        for(OrthographicCamera cam : cameras) {
            if(cam != null) cam.update();
        }

        Gdx.gl.glViewport(Gdx.graphics.getWidth()/2, 0, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight());

        spriteBatch.setProjectionMatrix(cameras[0].combined);
        spriteBatch.begin();
        {
            // Draw BG
            spriteBatch.draw(background, -150, -100, 300, 200);
            spriteBatch.draw(pentagram, -50, -50, 100, 100);

            // Draw components
            Vector2 drawPos;
            Vector2 drawDimen;
            for(AnimationComponent ac : animations) {
                ac.animTime += delta;

                drawPos = game.getPhysicsSystem().getEntityPos(ac.entityID);
                drawDimen = game.getPhysicsSystem().getEntityDimensions(ac.entityID);
                float angle = game.getPhysicsSystem().getBody(ac.entityID).getAngle();

                spriteBatch.draw(
                        ac.animation.getKeyFrame(ac.animTime, true),
                        drawPos.x-drawDimen.x/2, drawPos.y-drawDimen.y/2,
                        drawDimen.x/2, drawDimen.y/2,
                        drawDimen.x, drawDimen.y, 1.2f, 1.2f,
                        angle * MathUtils.radiansToDegrees
                );
            }
        }
        spriteBatch.end();
        hudBatch.setProjectionMatrix(hudCam.combined);
        hudBatch.begin();
        {
            hudBatch.draw(
                    overlay,
                    100, -112.5f, -200, 225
            );

            font.draw(hudBatch, ""+(String.format("%03d", (int)(game.getPurpleScore()))), 71, -100);
        }
        hudBatch.end();

        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight());

        spriteBatch.setProjectionMatrix(cameras[1].combined);
        spriteBatch.begin();
        {
            // Draw BG
            spriteBatch.draw(background, -150, -100, 300, 200);
            spriteBatch.draw(pentagram, -50, -50, 100, 100);

            // Draw components
            Vector2 drawPos;
            Vector2 drawDimen;
            for(AnimationComponent ac : animations) {
                ac.animTime += delta;

                drawPos = game.getPhysicsSystem().getEntityPos(ac.entityID);
                drawDimen = game.getPhysicsSystem().getEntityDimensions(ac.entityID);
                float angle = game.getPhysicsSystem().getBody(ac.entityID).getAngle();

                spriteBatch.draw(
                        ac.animation.getKeyFrame(ac.animTime, true),
                        drawPos.x-drawDimen.x/2, drawPos.y-drawDimen.y/2,
                        drawDimen.x/2, drawDimen.y/2,
                        drawDimen.x, drawDimen.y, 1.2f, 1.2f,
                        angle * MathUtils.radiansToDegrees
                );
            }
        }
        spriteBatch.end();
        hudBatch.setProjectionMatrix(hudCam.combined);
        hudBatch.begin();
        {
            hudBatch.draw(
                    overlay,
                    -100, -112.5f, 200, 225
            );

            font.draw(hudBatch, ""+(String.format("%03d", (int)(game.getYellowScore()))), -95, -100);
        }
        hudBatch.end();

    }

    public void resize(int width, int height) {
        viewportLeft.update(width, height);
        viewportRight.update(width, height);
        hudViewport.update(width, height);
    }

    public AnimationComponent getComponent(int entityId) {
        for(AnimationComponent ac : animations) {
            if(ac.entityID == entityId) return ac;
        }
        return null;
    }

    public void removeAnimation(int entityId) {
        Iterator<AnimationComponent> it = animations.iterator();
        AnimationComponent ac = null;
        while(it.hasNext()) {
            ac = it.next();
            if(ac.entityID == entityId) {
                it.remove();
                return;
            }
        }
    }
}
