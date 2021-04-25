package gq.nkkx.oldanimations.client.mixin.screen.ingame;

import gq.nkkx.oldanimations.renderer.OldAnimationsInventoryScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InventoryScreen.class)
public class InventoryScreenMixin {

    private final OldAnimationsInventoryScreen renderer = new OldAnimationsInventoryScreen((InventoryScreen) (Object) this);

    @Inject(at = @At(value = "HEAD"), method = "render")
    private void old_animations$render(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo callbackInfo) {
        renderer.render(callbackInfo);
    }

}
