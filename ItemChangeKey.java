package anaso.ItemChange;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.EnumSet;
import org.lwjgl.input.Keyboard;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.*;

public class ItemChangeKey extends KeyHandler
{

	public static int bindKeyL = Keyboard.KEY_LEFT;
	public static int bindKeyR = Keyboard.KEY_RIGHT;

	public ItemChangeKey(KeyBinding[] keyBindings, boolean[] repeatings)
	{
		super(keyBindings, repeatings);

		this.bindKeyL = keyBindings[0].keyCode;
		this.bindKeyR = keyBindings[1].keyCode;
	}

	@Override
	public String getLabel()
	{
		return "ItemChange Keybindings";
	}

	@Override
	public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat)
	{
		Minecraft MC = ModLoader.getMinecraftInstance();
		try{
			if(tickEnd && MC.currentScreen == null)
			{
				if(!MC.ingameGUI.getChatGUI().getChatOpen())
				{
					//設定したキーであるかの確認
					
					EntityPlayer entityplayer = ModLoader.getMinecraftInstance().thePlayer;
					
					for(int i = 0; MC.gameSettings.keyBindings.length > i; i++)
					{
						if(MC.gameSettings.keyBindings[i].keyDescription.equals("ItemLeft"))
						{
							this.bindKeyL = MC.gameSettings.keyBindings[i].keyCode;
						}
						else if(MC.gameSettings.keyBindings[i].keyDescription.equals("ItemRight"))
						{
							this.bindKeyR = MC.gameSettings.keyBindings[i].keyCode;
						}
					}
	
					// 動作の実行
					if(kb.keyCode == this.bindKeyL)
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
					else if(kb.keyCode == this.bindKeyR)
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
			System.out.println(e);
		}
	}

	@Override
	public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd)
	{
		//do whatever
	}

	@Override
	public EnumSet<TickType> ticks()
	{
		return EnumSet.of(TickType.CLIENT);
		//I am unsure if any different TickTypes have any different effects.
	}
}