package anaso.ItemChange;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;

public class ItemChangeKey
{

	public KeyBinding slotLeft = null;
	public KeyBinding slotRight = null;

	public ItemChangeKey(KeyBinding[] keyBindings)
	{
		this.slotLeft = keyBindings[0];
		this.slotRight = keyBindings[1];
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void inputKey(InputEvent.KeyInputEvent event)
	{
		Minecraft MC = FMLClientHandler.instance().getClient();
		try{
			if(MC.currentScreen == null)
			{
				if(!MC.ingameGUI.getChatGUI().getChatOpen())
				{
					//設定したキーであるかの確認
					
					EntityPlayer entityplayer = MC.thePlayer;
	
					// 動作の実行
					if(slotLeft.isPressed())
					{
						if (entityplayer.inventory.currentItem == 0)
						{
							entityplayer.inventory.currentItem = 8;
						}
						else
						{
							entityplayer.inventory.currentItem--;
						}
					}
					else if(slotRight.isPressed())
					{
						if (entityplayer.inventory.currentItem == 8)
						{
							entityplayer.inventory.currentItem = 0;
						}
						else
						{
							entityplayer.inventory.currentItem++;
						}
					}
				}
			}
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
	}
}