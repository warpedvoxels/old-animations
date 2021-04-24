package gq.nkkx.oldanimations.features.context;

public class ItemRenderingProgress {

    private final float swingProgress;
    private final float equipProgress;
    private final float tickDelta;

    public ItemRenderingProgress(float swingProgress, float equipProgress, float tickDelta) {
        this.swingProgress = swingProgress;
        this.equipProgress = equipProgress;
        this.tickDelta = tickDelta;
    }

    public float swing() {
        return swingProgress;
    }

    public float equip() {
        return equipProgress;
    }

    private float tickDelta() {
        return tickDelta;
    }

}
