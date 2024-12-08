package archives.tater.gravestonesfix.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.guavy.gravestones.Gravestones;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.FlowableFluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FlowableFluid.class)
public class FlowableFluidMixin {
    @WrapOperation(
            method = "canFill",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z",
                    ordinal = 0
            )
    )
    private boolean preventGravestone(BlockState instance, Block block, Operation<Boolean> original) {
        return original.call(instance, block) || instance.isOf(Gravestones.GRAVESTONE);
    }
}
