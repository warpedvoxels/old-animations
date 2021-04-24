package gq.nkkx.oldanimations.utils;

import net.minecraft.client.util.math.Vector3f;

public class ItemRescaling {

    private final Vector3f position;
    private final Vector3f scale;

    public ItemRescaling(Vector3f position, Vector3f scale) {
        this.position = position;
        this.scale = scale;
    }

    public Vector3f position() {
        return position;
    }

    public Vector3f scale() {
        return scale;
    }

}
