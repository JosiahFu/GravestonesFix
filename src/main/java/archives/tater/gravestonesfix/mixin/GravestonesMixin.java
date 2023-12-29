package archives.tater.gravestonesfix.mixin;

import net.guavy.gravestones.Gravestones;
import net.minecraft.block.AbstractBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Gravestones.class)
public class GravestonesMixin {
    @ModifyArg(
            method = "<clinit>",
            at = @At(value = "INVOKE", target = "Lnet/guavy/gravestones/block/GravestoneBlock;<init>(Lnet/minecraft/block/AbstractBlock$Settings;)V" ),
            index = 0
    )
    private static AbstractBlock.Settings modifyResistance(AbstractBlock.Settings settings) {
		return settings.resistance(6000f);
    }
}
