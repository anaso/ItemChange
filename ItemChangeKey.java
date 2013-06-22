package anaso.ItemChange;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.EnumSet;
import org.lwjgl.input.Keyboard;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.*;

public class ItemChangeKey extends KeyHandler
{
	//boolean Check = false;

	public static int bindKeyL = Keyboard.KEY_LEFT;
	public static int bindKeyR = Keyboard.KEY_RIGHT;

	public ItemChangeKey(KeyBinding[] keyBindings, boolean[] repeatings)
	{
		//the first value is an array of KeyBindings, the second is whether or not the call
		//keyDown should repeat as long as the key is down
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
		try{
			if(tickEnd && !ModLoader.getMinecraftInstance().isGamePaused)
			{
				loadOptions();
				EntityPlayer entityplayer = ModLoader.getMinecraftInstance().thePlayer;

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
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	public void loadOptions()
	{
		Minecraft MC = ModLoader.getMinecraftInstance();
		File optionsFile = new File(MC.getMinecraftDir(), "options.txt");

		try
		{
			if (!optionsFile.exists())
			{
				return;
			}

			BufferedReader var1 = new BufferedReader(new FileReader(optionsFile));
			String var2 = "";

			while ((var2 = var1.readLine()) != null)
			{
				try
				{
					String[] var3 = var2.split(":");

					for (int var4 = 0; var4 < this.keyBindings.length; ++var4)
					{
						if (var3[0].equals("key_ItemLeft"))
						{
							bindKeyL = Integer.parseInt(var3[1]);
						}
						else if(var3[0].equals("key_ItemRight"))
						{
							bindKeyR = Integer.parseInt(var3[1]);
						}
					}
				}
				catch (Exception e)
				{
					System.out.println(e);
				}
			}

			KeyBinding.resetKeyBindingArrayAndHash();
			var1.close();
		}
		catch (Exception e)
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