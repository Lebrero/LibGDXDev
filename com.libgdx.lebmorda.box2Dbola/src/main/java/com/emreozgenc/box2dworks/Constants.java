package com.emreozgenc.box2dworks;

import com.badlogic.gdx.math.Vector2;

public final class Constants {
    public static final int WIDTH = 1600;
    public static final int HEIGHT = 900;

    /*
    In this case we must use real world data. Because Box2D is using meter not pixels.
    If we use pixels we will got scaling problems and our objects will move slowly. So we
    are using V_WIDTH and V_HEIGHT and with these we are setting up our screen 16 meter width and
    9 meter height.
     */
    public static final int V_WIDTH = 16;
    public static final int V_HEIGHT = 9;

    public static final Vector2 WORLD_GRAVITY = new Vector2(0, -9.18f);
    public static final float BALL_RADIUS = 0.25f;
}
