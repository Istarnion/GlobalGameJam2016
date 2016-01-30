package com.holypoly.ggj.system;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.holypoly.ggj.component.AnimationComponent;
import com.holypoly.ggj.screen.GameScreen;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author istarnion
 */
public class GraphicsSystem extends AbstractSystem implements Disposable {

    public List<AnimationComponent> animations;

    private OrthographicCamera cam;

    private FitViewport viewport;

    private SpriteBatch spriteBatch;

    private ShapeRenderer shapeRenderer;

    private Box2DDebugRenderer debugRenderer;

    private Texture pentagram;

    public GraphicsSystem(GameScreen game) {
        super(game);
        Gdx.gl.glClearColor(0, 0, 0, 1);

        animations = new ArrayList<AnimationComponent>();

        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        debugRenderer = new Box2DDebugRenderer();

        cam = new OrthographicCamera(8, 9);
        viewport = new FitViewport(20, 22.5f, cam);

        pentagram = new Texture("images/pentagram.png");
    }

    @Override
    public void update(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.setProjectionMatrix(cam.combined);
        spriteBatch.begin();
        {
            spriteBatch.draw(pentagram, -10, -10, 20, 20);
        }
        spriteBatch.end();

        shapeRenderer.setProjectionMatrix(cam.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        {
            Vector2 drawPos = null;
            Vector2 drawDimen = null;
            for(AnimationComponent ac : animations) {
                drawPos = game.getPhysicsSystem().getEntityPos(ac.entityID);
                drawDimen = game.getPhysicsSystem().getEntityDimensions(ac.entityID);
                if(drawPos == null) {
                    // Render anim at 0, 0
                    shapeRenderer.ellipse(-0.5f, -0.5f, 1, 1);
                }
                else {
                    // Render anim at drawPos
                    shapeRenderer.ellipse(drawPos.x-drawDimen.x/2, drawPos.y-drawDimen.y/2, drawDimen.x, drawDimen.y);
                }
            }
        }
        shapeRenderer.end();

        debugRenderer.render(game.getPhysicsSystem().getWorld(), cam.combined);
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void dispose() {
        pentagram.dispose();
    }
}
