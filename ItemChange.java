package anaso.ItemChange;

import net.minecraft.src.*;
import net.minecraft.world.*;
import net.minecraft.block.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import org.lwjgl.input.Keyboard;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod
(
	modid = "ItemChange",
	name = "Item Change",
	version = "1.6"
)

@NetworkMod
(
	clientSideRequired = true
)

public class ItemChange
{
	public static int bindKeyL = Keyboard.KEY_LEFT;
	public static int bindKeyR = Keyboard.KEY_RIGHT;

	@Mod.PostInit
	public void PostInit(FMLPostInitializationEvent event)
	{
		// キーバインドの設定
		KeyBinding[] myBinding = {new KeyBinding("ItemLeft", bindKeyL), new KeyBinding("ItemRight", bindKeyR)};

		boolean[] myBindingRepeat = {false, false};

		ItemChangeKey myKeyHandler = new ItemChangeKey(myBinding, myBindingRepeat);

		KeyBindingRegistry.registerKeyBinding(myKeyHandler);
	}
}