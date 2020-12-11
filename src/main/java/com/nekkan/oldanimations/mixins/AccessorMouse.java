package com.nekkan.oldanimations.mixins;

import net.minecraft.client.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Mouse.class)
public interface AccessorMouse {

    @Accessor("leftButtonClicked")
    boolean isLeftButtonClicked();

    @Accessor("rightButtonClicked")
    boolean isRightButtonClicked();

    @Accessor("middleButtonClicked")
    boolean isMiddleButtonClicked();

}
