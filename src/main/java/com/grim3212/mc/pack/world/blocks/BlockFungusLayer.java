package com.grim3212.mc.pack.world.blocks;

import java.util.Random;

import com.grim3212.mc.pack.core.manual.IManualEntry.IManualBlock;
import com.grim3212.mc.pack.core.manual.pages.Page;
import com.grim3212.mc.pack.world.client.ManualWorld;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockFungusLayer extends BlockFungusBase implements IManualBlock {

	protected BlockFungusLayer() {
		super("fungus_layer_building", true);
	}

	public static final Block[] buildId = { Blocks.MOSSY_COBBLESTONE, // 0
																		// change
																		// this
																		// to
																		// clay
			Blocks.DIRT, // 1
			Blocks.STONE, // 2
			Blocks.SAND, // 3
			Blocks.NETHERRACK, // 4
			Blocks.COBBLESTONE, // 5
			Blocks.STONEBRICK, // 6
			Blocks.SANDSTONE, // 7
			Blocks.GRAVEL, // 8
			Blocks.OBSIDIAN, // 9
			Blocks.SNOW, // 10
			Blocks.SOUL_SAND, // 11
			Blocks.PRISMARINE, // 12
			Blocks.MAGMA, // 13
			Blocks.PLANKS, // 14
			Blocks.PACKED_ICE // 15
	};

	@Override
	public boolean canReplace(IBlockState side, IBlockState state) {
		Block block = side.getBlock();
		return (block == Blocks.FLOWING_WATER || block == Blocks.WATER) || (block == Blocks.FLOWING_LAVA || block == Blocks.LAVA) || block == Blocks.AIR || block instanceof BlockBush || block == Blocks.FIRE || block == Blocks.SNOW_LAYER || block == Blocks.REEDS || block == Blocks.VINE || ((block == WorldBlocks.fungus_growing || block == WorldBlocks.fungus_building || block == WorldBlocks.fungus_killing) && (side != state || block != this));
	}

	public int getBrick(Random random) {
		int number = random.nextInt(30);
		if (number <= 8) {
			return 0;
		}
		if (number <= 16) {
			return 1;
		}
		if (number <= 24) {
			return 2;
		}
		return 3;
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		int meta = (Integer) state.getValue(TYPE);

		if (!spreadToSide(worldIn, pos, state, false, false)) {
			Block i2b = buildId[meta];
			int m2b = 0;
			if (i2b == Blocks.STONEBRICK) {
				m2b = getBrick(rand);
				IBlockState stonebrick = Blocks.STONEBRICK.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.byMetadata(m2b));
				worldIn.setBlockState(pos, stonebrick, 2);
				return;
			}

			worldIn.setBlockState(pos, i2b.getDefaultState(), 2);
		}
		return;
	}

	public static final int[] color = { 0x5A9CFF, // 0 mossy cobblestone
			0x82510c, // 1 dirt
			0x999999, // 2 stone
			0xffb500, // 3 sand
			0x851d0c, // 4 nether
			0x777777, // 5 cobble
			0x888888, // 6 stonebri
			0xFFDB87, // 7 sandst
			0xC6C6C6, // 7 gravel
			0x210B52, // 9 obsidian
			0xF7FFFF, // 10 snow - icon can't be very light
			0x5C402D, // 11 soul sand
			0x1C947A, // 12 prismarine
			0xBD580D, // 13 magma
			0xAD8445, // 14 planks
			0x8AA9DB, // 15 packed ice
	};

	@Override
	public Page getPage(IBlockState state) {
		return ManualWorld.buildFungus_page;
	}
}
