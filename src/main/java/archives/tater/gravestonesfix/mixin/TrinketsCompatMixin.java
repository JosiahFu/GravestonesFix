package archives.tater.gravestonesfix.mixin;

import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import net.guavy.gravestones.compat.TrinketsCompat;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

@Mixin(TrinketsCompat.class)
public class TrinketsCompatMixin {
    @ModifyVariable(
            method = "setInventory",
            at = @At("HEAD"),
            argsOnly = true
    )
    private List<ItemStack> setStacks(List<ItemStack> value, @Share("realStacks") LocalRef<Queue<ItemStack>> realStacks) {
        realStacks.set(new ArrayDeque<>(value));
        // Return a single element list so that the loop only occurs once
        return List.of(ItemStack.EMPTY);
    }

    @ModifyVariable(
            method = "setInventory",
            at = @At(value = "INVOKE", target = "Ldev/emi/trinkets/api/TrinketInventory;getStack(I)Lnet/minecraft/item/ItemStack;")
    )
    private ItemStack setStackFromStacks(ItemStack value, @Share("realStacks") LocalRef<Queue<ItemStack>> realStacks) {
        var stacks = realStacks.get();
        if (stacks.isEmpty()) return ItemStack.EMPTY;
        return realStacks.get().remove();
    }
}
