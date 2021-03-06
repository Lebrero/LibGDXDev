package com.gdx.lebmorda.Scene2D.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gdx.lebmorda.main.BasicGame;

public class MusicalNote extends Image{
    ParticleEffect effect;

    public MusicalNote(){   
        super(new Texture("note.png"));
        effect = new ParticleEffect();
        effect.load(Gdx.files.internal("bubleNote.p"), BasicGame.textureAtlas);
        effect.start();
        effect.setPosition(this.getWidth()/2+this.getX(),this.getHeight()/2+this.getY());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        effect.draw(batch);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        effect.setPosition(this.getWidth()/2+this.getX(),this.getHeight()/2+this.getY());
        effect.update(delta);

    }
}
