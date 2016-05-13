package com.grim3212.mc.pack;

import com.grim3212.mc.pack.core.GrimCore;
import com.grim3212.mc.pack.core.part.PartRegistry;
import com.grim3212.mc.pack.cuisine.GrimCuisine;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod(modid = GrimPack.modID, name = GrimPack.modName, version = GrimPack.modVersion, dependencies = "required-after:Forge@[12.16.1.1895,)", acceptedMinecraftVersions = "[1.9]", guiFactory = "com.grim3212.mc.pack.core.config.ConfigGuiFactory", updateJSON = "https://raw.githubusercontent.com/grim3212/Grim-Pack/master/update.json")
public class GrimPack {

	@Instance(GrimPack.modID)
	public static GrimPack INSTANCE;

	public static final String modID = "grimpack";
	public static final String modName = "Grim Pack";
	public static final String modVersion = "1.9-1.0.0.0";

	static {
		PartRegistry.registerPart(GrimCore.INSTANCE);
		PartRegistry.registerPart(GrimCuisine.INSTANCE);
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ModMetadata data = event.getModMetadata();
		data.autogenerated = false;
		data.version = modVersion;
		data.name = modName;
		data.authorList.add("Grim3212");
		data.logoFile = "assets/" + modID + "/" + modID + ".png";
		data.url = "http://mods.grim3212.com/mc/my-mods/" + modID;
		data.description = "The cohesion of all of grims mods into one super pack.";
		data.credits = "Thanks to all the mod authors of mods that I have updated. Thanks to the Forge team and everyone who has helped contribute or request mods.";

		// Register GUI handler for the Instruction Manual
		NetworkRegistry.INSTANCE.registerGuiHandler(GrimPack.INSTANCE, new PackGuiHandler());

		PartRegistry.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		PartRegistry.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		PartRegistry.postInit(event);
	}
}
