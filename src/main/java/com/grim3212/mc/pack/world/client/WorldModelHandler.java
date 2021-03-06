package com.grim3212.mc.pack.world.client;

import com.grim3212.mc.pack.core.client.RenderHelper;
import com.grim3212.mc.pack.world.blocks.BlockFungusBuilding;
import com.grim3212.mc.pack.world.blocks.BlockFungusGrowing;
import com.grim3212.mc.pack.world.blocks.BlockFungusKilling;
import com.grim3212.mc.pack.world.blocks.BlockFungusLayer;
import com.grim3212.mc.pack.world.blocks.BlockGlowstoneSeed;
import com.grim3212.mc.pack.world.blocks.BlockGunpowderReed;
import com.grim3212.mc.pack.world.blocks.BlockRune.EnumRuneType;
import com.grim3212.mc.pack.world.blocks.WorldBlocks;
import com.grim3212.mc.pack.world.config.WorldConfig;
import com.grim3212.mc.pack.world.items.WorldItems;

import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class WorldModelHandler {

	@SubscribeEvent
	public void registerModels(ModelRegistryEvent evt) {
		if (WorldConfig.subpartGunpowderReeds) {
			ModelLoader.setCustomStateMapper(WorldBlocks.gunpowder_reed_block, new StateMap.Builder().ignore(BlockGunpowderReed.AGE).build());
			RenderHelper.renderBlockNormal(WorldBlocks.gunpowder_reed_block);
			RenderHelper.renderItem(WorldItems.gunpowder_reed_item);

		}

		if (WorldConfig.subpartGlowstoneSeeds) {
			ModelLoader.setCustomStateMapper(WorldBlocks.glowstone_seeds, new StateMap.Builder().ignore(BlockGlowstoneSeed.STEP).build());
			RenderHelper.renderBlock(WorldBlocks.glowstone_seeds);
		}

		if (WorldConfig.subpartFungus) {
			ModelLoader.setCustomStateMapper(WorldBlocks.fungus_building, new StateMap.Builder().ignore(BlockFungusBuilding.TYPE).build());
			ModelLoader.setCustomStateMapper(WorldBlocks.fungus_layer_building, new StateMap.Builder().ignore(BlockFungusLayer.TYPE).build());
			ModelLoader.setCustomStateMapper(WorldBlocks.fungus_growing, new StateMap.Builder().ignore(BlockFungusGrowing.TYPE).build());
			ModelLoader.setCustomStateMapper(WorldBlocks.fungus_killing, new StateMap.Builder().ignore(BlockFungusKilling.TYPE).build());

			RenderHelper.renderBlock(WorldBlocks.fungus_maze);
			RenderHelper.renderBlock(WorldBlocks.fungus_ore_building);
			RenderHelper.renderBlockWithMetaInInventory(WorldBlocks.fungus_building, 16);
			RenderHelper.renderBlockWithMetaInInventory(WorldBlocks.fungus_layer_building, 16);
			RenderHelper.renderBlockWithMetaInInventory(WorldBlocks.fungus_growing, 16);
			RenderHelper.renderBlockWithMetaInInventory(WorldBlocks.fungus_killing, 16);
			RenderHelper.renderItem(WorldItems.fungicide);
		}

		if (WorldConfig.subpartRandomite)
			RenderHelper.renderBlock(WorldBlocks.randomite);

		if (WorldConfig.subpartCorruption)
			RenderHelper.renderBlock(WorldBlocks.corruption_block);

		if (WorldConfig.subpart8BitMobs) {
			RenderHelper.renderItem(WorldItems.parabuzzy_shell);
			RenderHelper.renderItem(WorldItems.bobomb);
		}

		if (WorldConfig.subpartRuins) {
			RenderHelper.renderVariantForge(WorldBlocks.rune, EnumRuneType.names());
		}
	}

}
