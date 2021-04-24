package gq.nkkx.oldanimations.features.context;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;

public class ItemRenderingMatrices {

    private final VertexConsumerProvider vertexConsumerProvider;
    private final MatrixStack matrixStack;

    public ItemRenderingMatrices(VertexConsumerProvider vertexConsumerProvider, MatrixStack matrixStack) {
        this.vertexConsumerProvider = vertexConsumerProvider;
        this.matrixStack = matrixStack;
    }

    public MatrixStack stack() {
        return matrixStack;
    }

    public VertexConsumerProvider vertex() {
        return vertexConsumerProvider;
    }

}
