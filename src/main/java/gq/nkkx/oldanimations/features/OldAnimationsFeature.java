package gq.nkkx.oldanimations.features;

import gq.nkkx.oldanimations.features.context.FeatureExecutionContext;

/**
 * A feature is a class by which you alter the visual mechanics of the game to port an old animation to new versions.
 * Each resource implements this interface or its derivatives.
 *
 * @see ItemRenderingFeature
 * @since 2.0.0-SNAPSHOT
 */
public interface OldAnimationsFeature<T extends FeatureExecutionContext> {

    void transform(T context);

}
