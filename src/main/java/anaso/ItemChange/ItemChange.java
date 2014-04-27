package anaso.ItemChange;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

@Mod
(
	modid = "anaso.ItemChange",
	version = "1.7"
)

public class ItemChange
{
	@SideOnly(Side.CLIENT)
	public static final KeyBinding slotLeft = new KeyBinding("key.slotLeft.name", Keyboard.KEY_LEFT, "itemChange.inputEvent.name");
	public static final KeyBinding slotRight = new KeyBinding("key.slotRight.name", Keyboard.KEY_RIGHT, "itemChange.inputEvent.name");

	KeyBinding[] keyBindings = {slotLeft,slotRight};

	@Mod.EventHandler
	public void Init(FMLInitializationEvent event)
	{
		if(event.getSide() == Side.CLIENT){
			ClientRegistry.registerKeyBinding(slotLeft);
			ClientRegistry.registerKeyBinding(slotRight);
		}

		FMLCommonHandler.instance().bus().register(new ItemChangeKey(keyBindings));
	}
}