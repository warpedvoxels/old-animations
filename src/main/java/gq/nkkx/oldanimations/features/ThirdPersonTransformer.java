package gq.nkkx.oldanimations.features;

import gq.nkkx.oldanimations.features.context.ItemRenderingMatrices;
import gq.nkkx.oldanimations.utils.PlayerEntityModelAccess;
import net.minecraft.entity.LivingEntity;

public interface ThirdPersonTransformer {

    void transformThirdPersonItem(LivingEntity entity, ItemRenderingMatrices matrices);

    void transformThirdPersonEntity(PlayerEntityModelAccess model, LivingEntity entity, float ticks);

}
