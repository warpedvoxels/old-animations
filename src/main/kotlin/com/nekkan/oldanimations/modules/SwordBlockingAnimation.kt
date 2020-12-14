package com.nekkan.oldanimations.modules

import net.fabricmc.fabric.api.`object`.builder.v1.client.model.FabricModelPredicateProviderRegistry
import net.minecraft.item.Item
import net.minecraft.item.Items
import net.minecraft.util.Identifier

object SwordBlockingAnimation: LegacyAnimation {

    companion object {
        fun registerFor(item: Item, identifier: Identifier) {
            FabricModelPredicateProviderRegistry.register(item, identifier) { _, _, entity ->
                if(entity != null && entity.offHandStack.item == Items.SHIELD && entity.isUsingItem) 1f else 0f
            }
        }
    }

}
