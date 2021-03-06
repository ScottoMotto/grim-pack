package com.grim3212.mc.pack.industry.client;

import com.grim3212.mc.pack.core.common.CommonItems;
import com.grim3212.mc.pack.core.config.CoreConfig;
import com.grim3212.mc.pack.core.manual.IManualPart;
import com.grim3212.mc.pack.core.manual.ManualPart;
import com.grim3212.mc.pack.core.manual.ManualRegistry;
import com.grim3212.mc.pack.core.manual.pages.Page;
import com.grim3212.mc.pack.core.manual.pages.PageCrafting;
import com.grim3212.mc.pack.core.manual.pages.PageFurnace;
import com.grim3212.mc.pack.core.manual.pages.PageInfo;
import com.grim3212.mc.pack.core.util.RecipeHelper;
import com.grim3212.mc.pack.industry.block.IndustryBlocks;
import com.grim3212.mc.pack.industry.client.pages.PageMachine;
import com.grim3212.mc.pack.industry.config.IndustryConfig;
import com.grim3212.mc.pack.industry.init.IndustryRecipes;
import com.grim3212.mc.pack.industry.item.IndustryItems;
import com.grim3212.mc.pack.industry.util.MachineRecipes;
import com.grim3212.mc.pack.industry.util.MachineRecipes.MachineType;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ManualIndustry implements IManualPart {

	public static ManualIndustry INSTANCE = new ManualIndustry();

	public static Page workbench_page;
	public static Page iceMaker_page;
	public static Page togglerack_page;
	public static Page fireBlock_page;
	public static Page waterBlock_page;
	public static Page lavaBlock_page;
	public static Page spike_page;
	public static Page sensor_page;
	public static Page gravityBoots_page;
	public static Page controller_page;
	public static Page attract_page;
	public static Page repulse_page;
	public static Page mob_repulsor_page;
	public static Page gravitor_page;
	public static Page uranium_page;
	public static Page radiationSuit_page;
	public static Page uraniumSmelt_page;
	public static Page refinedUranium_page;
	public static Page plutonium_page;
	public static Page refinedPlutonium_page;
	public static Page reactorCore_page;
	public static Page reactorCase_page;
	public static Page ironParts_page;
	public static Page reactor_page;
	public static Page bombShell_page;
	public static Page c4_page;
	public static Page nuclearBomb_page;
	public static Page gate_page;
	public static Page gateTrumpet_page;
	public static Page garage_page;
	public static Page garageRemote_page;
	public static Page hLight_page;
	public static Page hTorch_page;
	public static Page sidewalk_page;
	public static Page tarball_page;
	public static Page asphalt_page;
	public static Page rways_page;
	public static Page paint_page;
	public static Page doors_page;
	public static Page others_page;
	public static Page decoration_page;
	public static Page paintTech_page;
	public static Page buckLadd_page;
	public static Page coalIron_page;
	public static Page steelIngot_page;
	public static Page steelStuff_page;
	public static Page steelTools_page;
	public static Page fuel_page;
	public static Page machineInfo_page;
	public static Page refinery_page;
	public static Page refineryRecipes_page;
	public static Page derrick_page;
	public static Page derrickRecipes_page;
	public static Page modernFurnace_page;
	public static Page modernFurnaceRecipes_page;
	public static Page extruder_page;
	public static Page extruderInfo_page;
	public static Page fan_page;
	public static Page portableUpgrade_page;
	public static Page specificSensor_page;
	public static Page upgradedSpecificSensor_page;
	public static Page positionFinder_page;
	public static Page specificSensorInfo_page;
	public static Page specificSensorInfo2_page;
	public static Page arrowSensor_page;
	public static Page fireSensor_page;
	public static Page metalMesh_page;
	public static Page conveyorBelt_page;
	public static Page drill_page;
	public static Page combination_page;
	public static Page locksmithWorkbench_page;
	public static Page warehouseCrate_page;
	public static Page safes_page;
	public static Page locker_page;
	public static Page cabinets_page;
	public static Page itemTower_page;
	public static Page tank_page;
	public static Page shapedChargeBase_page;
	public static Page shapedCharge_page;
	public static Page flipFlopTorch_page;
	public static Page glowstoneTorch_page;
	public static Page bridgeLaser_page;
	public static Page bridgeAccel_page;
	public static Page bridgeTrick_page;
	public static Page bridgeDeath_page;
	public static Page gravLift_page;
    public static Page chunkLoader_page;

	@Override
	public void initPages() {
		if (IndustryConfig.subpartStorage) {
			tank_page = new PageCrafting("tank", new ItemStack(IndustryBlocks.tank));
			combination_page = new PageCrafting("combination", 25, new ItemStack(IndustryItems.locksmith_key), new ItemStack(IndustryItems.locksmith_lock));
			locksmithWorkbench_page = new PageCrafting("locksmith_workbench", new ItemStack(IndustryBlocks.locksmith_workbench));
			warehouseCrate_page = new PageCrafting("warehouse_crate", new ItemStack(IndustryBlocks.warehouse_crate));
			cabinets_page = new PageCrafting("cabinets", 25, new ItemStack(IndustryBlocks.wood_cabinet), new ItemStack(IndustryBlocks.glass_cabinet));
			safes_page = new PageCrafting("safes", 25, new ItemStack(IndustryBlocks.obsidian_safe), new ItemStack(IndustryBlocks.gold_safe));
			locker_page = new PageCrafting("locker", new ItemStack(IndustryBlocks.locker));
			itemTower_page = new PageCrafting("item_tower", new ItemStack(IndustryBlocks.item_tower));
		}

		if (IndustryConfig.subpartShapedCharges) {
			shapedChargeBase_page = new PageCrafting("shaped_charge", RecipeHelper.createPath("shaped_charge"));
			shapedCharge_page = new PageCrafting("shaped_charge_radius", RecipeHelper.getAllPaths("shaped_charge_"), 20);
		}

		if (IndustryConfig.subpartSensors) {
			specificSensorInfo_page = new PageInfo("specific_sensor_info");
			specificSensorInfo2_page = new PageInfo("specific_sensor_info2");
			positionFinder_page = new PageCrafting("position_finder", new ItemStack(IndustryItems.position_finder)).appendImageUrl("gps.png");
			specificSensor_page = new PageCrafting("specific_sensor", new ItemStack(IndustryBlocks.specific_sensor)).appendImageUrl("specific_sensor.png");
			upgradedSpecificSensor_page = new PageCrafting("upgraded_specific_sensor", new ItemStack(IndustryBlocks.upgraded_specific_sensor));
			sensor_page = new PageCrafting("recipes", IndustryRecipes.sensors, 20);
			arrowSensor_page = new PageCrafting("arrow_sensor", new ItemStack(IndustryBlocks.arrow_sensor));
			fireSensor_page = new PageCrafting("fire_sensor", new ItemStack(IndustryBlocks.fire_sensor));
		}

		if (IndustryConfig.subpartFans)
			fan_page = new PageCrafting("fan", new ItemStack(IndustryBlocks.fan));

		if (IndustryConfig.subpartExtruder) {
			extruder_page = new PageCrafting("extruder", new ItemStack(IndustryItems.extruder));
			extruderInfo_page = new PageInfo("info");
		}

		if (IndustryConfig.subpartWorkbenchUpgrades) {
			workbench_page = new PageCrafting("recipes", IndustryRecipes.workbenches, 25);
			portableUpgrade_page = new PageCrafting("portable_upgrade", IndustryRecipes.portableUpgrades, 25);
		}

		if (IndustryConfig.subpartIceMaker)
			iceMaker_page = new PageCrafting("recipes", new ItemStack(IndustryBlocks.ice_maker));

		if (IndustryConfig.subpartElementalBlocks) {
			togglerack_page = new PageCrafting("toggle", new ItemStack(IndustryBlocks.togglerack));
			fireBlock_page = new PageCrafting("fire", new ItemStack(IndustryBlocks.fire_block));
			waterBlock_page = new PageCrafting("water", new ItemStack(IndustryBlocks.water_block));
			lavaBlock_page = new PageCrafting("lava", new ItemStack(IndustryBlocks.lava_block));
		}

		if (IndustryConfig.subpartSpikes)
			spike_page = new PageCrafting("recipe", RecipeHelper.createPath("spike"));

		if (IndustryConfig.subpartGravity) {
			gravityBoots_page = new PageCrafting("boots", new ItemStack(IndustryItems.gravity_boots));
			controller_page = new PageCrafting("control", IndustryRecipes.control, 25);
			attract_page = new PageCrafting("attract", IndustryRecipes.attracting, 25);
			repulse_page = new PageCrafting("repulse", IndustryRecipes.repulsing, 25);
			mob_repulsor_page = new PageCrafting("mob_repulsor", new ItemStack(IndustryItems.mob_repulsor));
			gravitor_page = new PageCrafting("gravitor", IndustryRecipes.gravitoring, 25);
		}

		if (IndustryConfig.subpartNuclear) {
			uranium_page = new PageCrafting("uranium", new ItemStack(IndustryBlocks.uranium_ore));
			radiationSuit_page = new PageCrafting("armor", IndustryRecipes.armor, 20);
			uraniumSmelt_page = new PageFurnace("uranium_smelt", new ItemStack(IndustryBlocks.uranium_ore));
			refinedUranium_page = new PageCrafting("refined_uranium", new ItemStack(IndustryItems.refined_uranium));
			plutonium_page = new PageCrafting("plutonium", new ItemStack(IndustryItems.plutonium_ingot));
			refinedPlutonium_page = new PageCrafting("refined_plutonium", new ItemStack(IndustryItems.refined_plutonium));
			reactorCore_page = new PageCrafting("reactor_core", new ItemStack(IndustryItems.reactor_core));

			reactorCase_page = new PageCrafting("reactor_case", new ItemStack(IndustryItems.reactor_core_case));
			ironParts_page = new PageCrafting("iron_parts", new ItemStack(IndustryItems.iron_parts));
			reactor_page = new PageCrafting("reactor", new ItemStack(IndustryBlocks.reactor));
			bombShell_page = new PageCrafting("bomb_shell", new ItemStack(IndustryBlocks.bomb_shell));
			c4_page = new PageCrafting("c4", new ItemStack(IndustryBlocks.c4));
			nuclearBomb_page = new PageCrafting("nuclear_bomb", new ItemStack(IndustryBlocks.nuclear_bomb));
		}

		if (IndustryConfig.subpartGates) {
			gate_page = new PageCrafting("gate", IndustryRecipes.gates, 25);
			gateTrumpet_page = new PageCrafting("trumpet", new ItemStack(IndustryItems.gate_trumpet));
			garage_page = new PageCrafting("garage", IndustryRecipes.garages, 25);
			garageRemote_page = new PageCrafting("remote", new ItemStack(IndustryItems.garage_remote));
		}

		if (IndustryConfig.subpartHLights) {
			hLight_page = new PageCrafting("hlight", new ItemStack(IndustryBlocks.halogen_light));
			hTorch_page = new PageCrafting("htorch", IndustryRecipes.htorches, 25);
		}

		if (IndustryConfig.subpartRWays) {
			sidewalk_page = new PageCrafting("swalk", new ItemStack(IndustryBlocks.sidewalk));
			tarball_page = new PageCrafting("tarball", new ItemStack(IndustryItems.tarball));
			asphalt_page = new PageFurnace("asphalt", new ItemStack(IndustryItems.tarball));
			rways_page = new PageCrafting("rways", IndustryRecipes.rways, 20).appendImageUrl("rway.png");
		}

		if (IndustryConfig.subpartDoors)
			doors_page = new PageCrafting("doors", IndustryRecipes.doors, 25);

		if (IndustryConfig.subpartDecoration) {
			others_page = new PageCrafting("others", IndustryRecipes.others, 25);
			decoration_page = new PageCrafting("decoration", IndustryRecipes.decoration, 25);
			paint_page = new PageCrafting("paint", new ItemStack(IndustryItems.paint_roller));
			paintTech_page = new PageCrafting("paint", IndustryRecipes.paint, 25);
		}

		if (IndustryConfig.subpartMetalWorks) {
			metalMesh_page = new PageCrafting("metal_mesh", new ItemStack(IndustryBlocks.metal_mesh));
			buckLadd_page = new PageCrafting("buckladd", IndustryRecipes.buckladd, 25);
		}

		if (IndustryConfig.subpartSteel) {
			coalIron_page = new PageCrafting("coaliron", IndustryRecipes.coaliron, 25);
			steelIngot_page = new PageFurnace("steelingot", new ItemStack(IndustryItems.coal_iron_ingot));
			steelStuff_page = new PageCrafting("steelstuff", IndustryRecipes.steelstuff, 20);
			steelTools_page = new PageCrafting("steeltools", IndustryRecipes.steeltools, 20);
		}

		if (IndustryConfig.subpartConveyor) {
			conveyorBelt_page = new PageCrafting("conveyor_belt", new ItemStack(IndustryBlocks.conveyor_belt));
		}

		if (IndustryConfig.subpartMachines) {
			drill_page = new PageCrafting("drill", 25, new ItemStack(IndustryBlocks.drill), new ItemStack(IndustryItems.drill_head_item));
			fuel_page = new PageCrafting("fuel", IndustryRecipes.fuelstuff, 25);
			machineInfo_page = new PageInfo("info");
			refinery_page = new PageCrafting("refinery", new ItemStack(IndustryBlocks.refinery));
			refineryRecipes_page = new PageMachine("refinery_recipes", MachineRecipes.INSTANCE.getInputs(MachineType.REFINERY), 35, MachineType.REFINERY);

			if (CoreConfig.subpartAluminum)
				derrick_page = new PageCrafting("derrick", 25, new ItemStack(IndustryBlocks.derrick), new ItemStack(CommonItems.aluminum_can));

			if (OreDictionary.doesOreNameExist("can"))
				derrickRecipes_page = new PageMachine("derrick_recipes", "can", MachineType.DERRICK);

			modernFurnace_page = new PageCrafting("mfurnace", new ItemStack(IndustryBlocks.modern_furnace));
			modernFurnaceRecipes_page = new PageMachine("mfurnace_recipes", MachineRecipes.INSTANCE.getInputs(MachineType.MODERN_FURNACE), 35, MachineType.MODERN_FURNACE);
		}

		if (IndustryConfig.subpartTorches) {
			flipFlopTorch_page = new PageCrafting("flipflop", new ItemStack(IndustryBlocks.flip_flop_torch));
			glowstoneTorch_page = new PageCrafting("glowstone", new ItemStack(IndustryBlocks.glowstone_torch));
		}

		if (IndustryConfig.subpartBridges) {
			bridgeLaser_page = new PageCrafting("laser", RecipeHelper.createPath("bridge_laser"));
			bridgeAccel_page = new PageCrafting("accel", RecipeHelper.createPath("bridge_accel"));
			bridgeTrick_page = new PageCrafting("trick", RecipeHelper.createPath("bridge_trick"));
			bridgeDeath_page = new PageCrafting("death", RecipeHelper.createPath("bridge_death"));
			gravLift_page = new PageCrafting("gravity", RecipeHelper.createPath("bridge_gravity"));
		}

		if(IndustryConfig.subpartChunkLoader){
		    chunkLoader_page = new PageCrafting("chunk_loader", new ItemStack(IndustryBlocks.chunk_loader));
        }
	}

	@Override
	public void registerChapters(ManualPart part) {
		if (IndustryConfig.subpartWorkbenchUpgrades)
			ManualRegistry.addChapter("benches", part).addPages(workbench_page, portableUpgrade_page);

		if (IndustryConfig.subpartStorage)
			ManualRegistry.addChapter("storage", part).addPages(warehouseCrate_page, cabinets_page, safes_page, locker_page, itemTower_page, combination_page, locksmithWorkbench_page, tank_page);

		if (IndustryConfig.subpartElementalBlocks)
			ManualRegistry.addChapter("elemental", part).addPages(togglerack_page, fireBlock_page, waterBlock_page, lavaBlock_page);

		if (IndustryConfig.subpartSpikes)
			ManualRegistry.addChapter("spikes", part).addPages(spike_page);

		if (IndustryConfig.subpartFans)
			ManualRegistry.addChapter("fan", part).addPages(fan_page).appendImageUrl("fans.png");

		if (IndustryConfig.subpartExtruder)
			ManualRegistry.addChapter("extruders", part).addPages(extruder_page, extruderInfo_page).appendImageUrl("extruder.png");

		if (IndustryConfig.subpartSensors)
			ManualRegistry.addChapter("sensors", part).addPages(sensor_page, positionFinder_page, specificSensorInfo_page, specificSensorInfo2_page, specificSensor_page, upgradedSpecificSensor_page, arrowSensor_page, fireSensor_page);

		if (IndustryConfig.subpartGravity)
			ManualRegistry.addChapter("gravity", part).addPages(gravityBoots_page, controller_page, attract_page, gravitor_page, repulse_page, mob_repulsor_page);

		if (IndustryConfig.subpartNuclear) {
			ManualRegistry.addChapter("refining", part).addPages(uranium_page, radiationSuit_page, uraniumSmelt_page, refinedUranium_page, plutonium_page, refinedPlutonium_page, reactorCore_page);
			ManualRegistry.addChapter("reactor", part).addPages(reactorCase_page, ironParts_page, reactor_page);
		}

		if (IndustryConfig.subpartNuclear || IndustryConfig.subpartShapedCharges) {
			ManualRegistry.addChapter("explosives", part).addPages(bombShell_page, c4_page, nuclearBomb_page, shapedChargeBase_page, shapedCharge_page);
		}

		if (IndustryConfig.subpartGates)
			ManualRegistry.addChapter("gates", part).addPages(gate_page, gateTrumpet_page, garage_page, garageRemote_page);

		if (IndustryConfig.subpartHLights)
			ManualRegistry.addChapter("hlights", part).addPages(hLight_page, hTorch_page);

		if (IndustryConfig.subpartRWays)
			ManualRegistry.addChapter("rways", part).addPages(sidewalk_page, tarball_page, asphalt_page, rways_page);

		if (IndustryConfig.subpartDoors || IndustryConfig.subpartDecoration)
			ManualRegistry.addChapter("moderntech", part).addPages(doors_page, others_page, decoration_page, paint_page, paintTech_page);

		if (IndustryConfig.subpartMetalWorks || IndustryConfig.subpartSteel)
			ManualRegistry.addChapter("metalworks", part).addPages(buckLadd_page, coalIron_page, steelIngot_page, steelStuff_page, steelTools_page, metalMesh_page);

		if (IndustryConfig.subpartMachines || IndustryConfig.subpartConveyor)
			ManualRegistry.addChapter("machines", part).addPages(machineInfo_page, refinery_page, refineryRecipes_page, derrick_page, derrickRecipes_page, fuel_page, modernFurnace_page, modernFurnaceRecipes_page, drill_page, conveyorBelt_page);

		if (IndustryConfig.subpartIceMaker)
			ManualRegistry.addChapter("ice", part).addPages(iceMaker_page).appendImageUrl("icemaker.png");

		if (IndustryConfig.subpartTorches)
			ManualRegistry.addChapter("torches", part).addPages(flipFlopTorch_page, glowstoneTorch_page);

		if (IndustryConfig.subpartBridges)
			ManualRegistry.addChapter("bridges", part).addPages(bridgeLaser_page, bridgeAccel_page, bridgeTrick_page, bridgeDeath_page, gravLift_page);

		if(IndustryConfig.subpartChunkLoader)
		    ManualRegistry.addChapter("chunk_loader", part).addPages(chunkLoader_page);
	}

}
