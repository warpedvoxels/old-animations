package gq.nkkx.oldanimations.features;

import gq.nkkx.oldanimations.features.context.ItemRenderingFeatureExecutionContext;

/**
 * A item rendering feature is a feature in which alters the way the game renders a item.
 *
 * @see OldAnimationsFeature
 * @since 2.0.0-SNAPSHOT
 */
public interface ItemRenderingFeature<T extends ItemRenderingFeatureExecutionContext> extends OldAnimationsFeature<T> {
}
