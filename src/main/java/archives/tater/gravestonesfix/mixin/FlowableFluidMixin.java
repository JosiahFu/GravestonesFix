package archives.tater.gravestonesfix.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.guavy.gravestones.Gravestones;
import net.guavy.gravestones.block.GravestoneBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.registry.tag.TagKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FlowableFluid.class)
public class FlowableFluidMixin {
    @Redirect(
            method = "canFill",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z",
                    ordinal = 0
            )
    )
    private boolean preventGravestone(BlockState state, Block block) {
        return state.isOf(block) || state.isOf(Gravestones.GRAVESTONE);
    }
}
