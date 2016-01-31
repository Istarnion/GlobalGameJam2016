package com.holypoly.ggj;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *
 * @author istarnion
 */
public class AnimationFactory {

    public AssetManager assets;

    public AnimationFactory(AssetManager assets) {
        this.assets = assets;
    }

    public Animation getNeutralTorch() {
        return new Animation(
                0.25f,
                getAnimStrip(assets.get(
                        "images/spritesheet.png",
                        Texture.class),
                        0, 2,
                        1, 1,
                        24, 24)
        );
    }

    public Animation getPurpleTorch() {
        return new Animation(
                0.25f,
                getAnimStrip(assets.get(
                        "images/spritesheet.png",
                        Texture.class),
                        0, 0,
                        1, 5,
                        24, 24)
        );
    }

    public Animation getYellowTorch() {
        return new Animation(
                0.25f,
                getAnimStrip(assets.get(
                        "images/spritesheet.png",
                        Texture.class),
                        0, 1,
                        1, 5,
                        24, 24)
        );
    }

    public Animation getPurplePlayer() {
        return new Animation(
                0.25f,
                getAnimStrip(assets.get(
                        "images/purplewitch.png",
                        Texture.class),
                        0, 0,
                        1, 4,
                        32, 32)
        );
    }

    public Animation getYellowPlayer() {
        return new Animation(
                0.25f,
                getAnimStrip(assets.get(
                        "images/yellowwitch.png",
                        Texture.class),
                        0, 0,
                        1, 4,
                        32, 32)
        );
    }

    public Animation getMagicMissile() {
        return new Animation(
                0.05f,
                getAnimStrip(assets.get(
                        "images/magicmissile.png",
                        Texture.class),
                        0, 0,
                        1, 6,
                        32, 32)
        );
    }

    private TextureRegion[] getAnimStrip(Texture tex, int x, int y, int rows, int cols, int width, int height) {
        TextureRegion[][] temp = TextureRegion.split(tex, width, height);

        System.out.println("------------");
        System.out.println("First: "+temp.length);
        System.out.println("Second: "+temp[0].length);
        System.out.println("------------");

        TextureRegion[] strip = new TextureRegion[rows * cols];

        int n = 0;
        for(int i=y; i<y+rows; i++) {
            for(int j=x; j<x+cols; j++) {
                strip[n++] = temp[i][j];
            }
        }

        if(n != strip.length) System.out.println("U ARE STOOPIG HALLGIIIIR n: "+n+", rows: "+rows+", cols: "+cols);

        return strip;
    }
}
