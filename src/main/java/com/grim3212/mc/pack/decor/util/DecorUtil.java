package com.grim3212.mc.pack.decor.util;

import java.util.List;

import com.grim3212.mc.pack.core.util.NBTHelper;
import com.grim3212.mc.pack.decor.block.DecorBlocks;
import com.grim3212.mc.pack.decor.block.colorizer.BlockColorizerFacing;
import com.grim3212.mc.pack.decor.block.colorizer.BlockColorizerHedge;
import com.grim3212.mc.pack.decor.block.colorizer.BlockColorizerSlopedRotate;
import com.grim3212.mc.pack.decor.block.colorizer.BlockColorizerSlopedRotate.EnumHalf;
import com.grim3212.mc.pack.decor.config.DecorConfig;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class DecorUtil {

	public static String[] getDyeNames() {
		EnumDyeColor[] states = EnumDyeColor.values();
		String[] names = new String[states.length];

		for (int i = 0; i < states.length; i++) {
			names[i] = states[i].name();
		}

		return names;
	}

	public static ItemStack createFurnitureWithState(Block block, IBlockState toPlace) {
		ItemStack furniture = new ItemStack(block);
		NBTHelper.setString(furniture, "registryName", Block.REGISTRY.getNameForObject(toPlace.getBlock()).toString());
		NBTHelper.setInteger(furniture, "meta", toPlace.getBlock().getMetaFromState(toPlace));
		return furniture;
	}

	public static ItemStack createStackFromState(IBlockState state) {
		return new ItemStack(state.getBlock(), 1, state.getBlock().getMetaFromState(state));
	}

	/**
	 * Adds the AxisAlginedBB that it generates using getCollision to the blocks
	 * colliding boxes list
	 * 
	 * @param pos
	 *            The pos we are checking for collision at
	 * @param entityBox
	 *            The entitybox to make sure it is intersecting
	 * @param collidingBoxes
	 *            The blocks colliding blocks list
	 * @param state
	 *            The blockstate we are going to be evaluating
	 * @param numPieces
	 *            The number of pieces that this Slope has
	 */
	public static void addAxisAlignedBoxes(BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, IBlockState state, int numPieces) {
		for (int piece = 0; piece < DecorConfig.smoothness; piece++) {
			for (int smoothness = 0; smoothness < DecorConfig.smoothness; smoothness++) {
				AxisAlignedBB collision = getCollision(state, piece, smoothness, numPieces);

				if (collision != Block.NULL_AABB) {
					AxisAlignedBB axisalignedbb = collision.offset(pos);

					if (entityBox.intersects(axisalignedbb)) {
						collidingBoxes.add(axisalignedbb);
					}
				}
			}
		}
	}

	/**
	 * Returns a new AxisAlignedBB for the specified piece at the smoothness
	 * level
	 * 
	 * Based of of CarpentryBlocks
	 * https://github.com/Mineshopper/carpentersblocks/blob/master/src/main/java
	 * /com/carpentersblocks/util/slope/SlopeUtil.java#L56
	 * 
	 * @param state
	 *            The BlockState that we are grabbing the collision for
	 * @param piece
	 *            The current piece that we are on
	 * @param smoothness
	 *            The current smoothness we are evaluating
	 * @param numPieces
	 *            The number of pieces that this Slope has
	 * 
	 * @return A new AxisAlignedBB at this smoothness level
	 */
	public static AxisAlignedBB getCollision(IBlockState state, int piece, int smoothness, int numPieces) {
		piece++;

		float zeroPieceOffset = (float) (piece - 1) / numPieces;
		float onePieceOffset = (float) piece / numPieces;

		float zeroOffset = (float) smoothness / (float) DecorConfig.smoothness;
		float oneOffset = (float) (smoothness + 1) / (float) DecorConfig.smoothness;

		if (state.getBlock() instanceof BlockColorizerSlopedRotate) {
			if (state.getBlock() == DecorBlocks.slanted_corner) {
				if (state.getValue(BlockColorizerSlopedRotate.HALF) == EnumHalf.BOTTOM) {
					switch (state.getValue(BlockColorizerSlopedRotate.FACING)) {
					case EAST:
						return new AxisAlignedBB(zeroPieceOffset + zeroOffset * (1.0F - zeroPieceOffset), 0.0F, 0.0F, 1.0F, onePieceOffset, oneOffset * (1.0F - zeroPieceOffset));
					case NORTH:
						return new AxisAlignedBB(0.0F, 0.0F, 0.0F, oneOffset * (1.0F - zeroPieceOffset), onePieceOffset, 1.0F - zeroPieceOffset - zeroOffset * (1.0F - zeroPieceOffset));
					case SOUTH:
						return new AxisAlignedBB(zeroPieceOffset + zeroOffset * (1.0F - zeroPieceOffset), 0.0F, 1.0F - oneOffset * (1.0F - zeroPieceOffset), 1.0F, onePieceOffset, 1.0F);
					case WEST:
						return new AxisAlignedBB(0.0F, 0.0F, zeroPieceOffset + zeroOffset * (1.0F - zeroPieceOffset), oneOffset * (1.0F - zeroPieceOffset), onePieceOffset, 1.0F);
					default:
						return Block.NULL_AABB;
					}
				} else {
					switch (state.getValue(BlockColorizerSlopedRotate.FACING)) {
					case EAST:
						return new AxisAlignedBB(zeroPieceOffset + zeroOffset * (1.0F - zeroPieceOffset), 1.0F - onePieceOffset, 0.0F, 1.0F, 1.0F, oneOffset * (1.0F - zeroPieceOffset));
					case NORTH:
						return new AxisAlignedBB(0.0F, 1.0F - onePieceOffset, 0.0F, oneOffset * (1.0F - zeroPieceOffset), 1.0F, 1.0F - zeroPieceOffset - zeroOffset * (1.0F - zeroPieceOffset));
					case SOUTH:
						return new AxisAlignedBB(zeroPieceOffset + zeroOffset * (1.0F - zeroPieceOffset), 1.0F - onePieceOffset, 1.0F - oneOffset * (1.0F - zeroPieceOffset), 1.0F, 1.0F, 1.0F);
					case WEST:
						return new AxisAlignedBB(0.0F, 1.0F - onePieceOffset, zeroPieceOffset + zeroOffset * (1.0F - zeroPieceOffset), oneOffset * (1.0F - zeroPieceOffset), 1.0F, 1.0F);
					default:
						return Block.NULL_AABB;
					}
				}
			}

			if (state.getBlock() == DecorBlocks.sloped_angle) {
				if (state.getValue(BlockColorizerSlopedRotate.HALF) == EnumHalf.BOTTOM) {
					switch (state.getValue(BlockColorizerSlopedRotate.FACING)) {
					case EAST:
						return new AxisAlignedBB(zeroOffset, 0.0F, 0.0F, 1.0F, oneOffset, 1.0F - zeroOffset);
					case NORTH:
						return new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F - zeroOffset, oneOffset, 1.0F - zeroOffset);
					case SOUTH:
						return new AxisAlignedBB(zeroOffset, 0.0F, zeroOffset, 1.0F, oneOffset, 1.0F);
					case WEST:
						return new AxisAlignedBB(0.0F, 0.0F, zeroOffset, 1.0F - zeroOffset, oneOffset, 1.0F);
					default:
						return Block.NULL_AABB;
					}
				} else {
					switch (state.getValue(BlockColorizerSlopedRotate.FACING)) {
					case EAST:
						return new AxisAlignedBB(zeroOffset, 1.0F - oneOffset, 0.0F, 1.0F, 1.0F, 1.0F - zeroOffset);
					case NORTH:
						return new AxisAlignedBB(0.0F, 1.0F - oneOffset, 0.0F, 1.0F - zeroOffset, 1.0F, 1.0F - zeroOffset);
					case SOUTH:
						return new AxisAlignedBB(zeroOffset, 1.0F - oneOffset, zeroOffset, 1.0F, 1.0F, 1.0F);
					case WEST:
						return new AxisAlignedBB(0.0F, 1.0F - oneOffset, zeroOffset, 1.0F - zeroOffset, 1.0F, 1.0F);
					default:
						return Block.NULL_AABB;
					}
				}
			}

			if (state.getBlock() == DecorBlocks.slope) {
				if (state.getValue(BlockColorizerSlopedRotate.HALF) == EnumHalf.BOTTOM) {
					switch (state.getValue(BlockColorizerSlopedRotate.FACING)) {
					case EAST:
						return new AxisAlignedBB(zeroOffset, 0.0F, 0.0F, 1.0F, oneOffset, 1.0F);
					case NORTH:
						return new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 1.0F - zeroOffset, oneOffset);
					case SOUTH:
						return new AxisAlignedBB(0.0F, 0.0F, zeroOffset, 1.0F, oneOffset, 1.0F);
					case WEST:
						return new AxisAlignedBB(0.0F, 0.0F, 0.0F, oneOffset, 1.0F - zeroOffset, 1.0F);
					default:
						return Block.NULL_AABB;
					}
				} else {
					switch (state.getValue(BlockColorizerSlopedRotate.FACING)) {
					case EAST:
						return new AxisAlignedBB(zeroOffset, 1.0F - oneOffset, 0.0F, 1.0F, 1.0F, 1.0F);
					case NORTH:
						return new AxisAlignedBB(0.0F, zeroOffset, 0.0F, 1.0F, 1.0F, oneOffset);
					case SOUTH:
						return new AxisAlignedBB(0.0F, 1.0F - oneOffset, zeroOffset, 1.0F, 1.0F, 1.0F);
					case WEST:
						return new AxisAlignedBB(0.0F, zeroOffset, 0.0F, oneOffset, 1.0F, 1.0F);
					default:
						return Block.NULL_AABB;
					}
				}
			}

			if (state.getBlock() == DecorBlocks.oblique_slope) {
				if (state.getValue(BlockColorizerSlopedRotate.HALF) == EnumHalf.BOTTOM) {
					switch (state.getValue(BlockColorizerSlopedRotate.FACING)) {
					case EAST:
						switch (piece) {
						case 1:
							return new AxisAlignedBB(zeroOffset, 0.0F, 0.0F, 1.0F, 1.0F, oneOffset);
						case 2:
							return new AxisAlignedBB(zeroOffset, 0.0F, 0.0F, 1.0F, oneOffset, 1.0F);
						case 3:
							return new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 1.0F - zeroOffset, oneOffset);
						}
					case NORTH:
						switch (piece) {
						case 1:
							return new AxisAlignedBB(0.0F, 0.0F, 0.0F, oneOffset, 1.0F, 1.0F - zeroOffset);
						case 2:
							return new AxisAlignedBB(0.0F, 0.0F, 0.0F, oneOffset, 1.0F - zeroOffset, 1.0F);
						case 3:
							return new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 1.0F - zeroOffset, oneOffset);
						}
					case SOUTH:
						switch (piece) {
						case 1:
							return new AxisAlignedBB(zeroOffset, 0.0F, 1.0F - oneOffset, 1.0F, 1.0F, 1.0F);
						case 2:
							return new AxisAlignedBB(zeroOffset, 0.0F, 0.0F, 1.0F, oneOffset, 1.0F);
						case 3:
							return new AxisAlignedBB(0.0F, 0.0F, zeroOffset, 1.0F, oneOffset, 1.0F);
						}
					case WEST:
						switch (piece) {
						case 1:
							return new AxisAlignedBB(0.0F, 0.0F, zeroOffset, oneOffset, 1.0F, 1.0F);
						case 2:
							return new AxisAlignedBB(0.0F, 0.0F, 0.0F, oneOffset, 1.0F - zeroOffset, 1.0F);
						case 3:
							return new AxisAlignedBB(0.0F, 0.0F, zeroOffset, 1.0F, oneOffset, 1.0F);
						}
					default:
						return Block.NULL_AABB;
					}
				} else {
					switch (state.getValue(BlockColorizerSlopedRotate.FACING)) {
					case EAST:
						switch (piece) {
						case 1:
							return new AxisAlignedBB(zeroOffset, 0.0F, 0.0F, 1.0F, 1.0F, oneOffset);
						case 2:
							return new AxisAlignedBB(zeroOffset, 1.0F - oneOffset, 0.0F, 1.0F, 1.0F, 1.0F);
						case 3:
							return new AxisAlignedBB(0.0F, zeroOffset, 0.0F, 1.0F, 1.0F, oneOffset);
						}
					case NORTH:
						switch (piece) {
						case 1:
							return new AxisAlignedBB(0.0F, 0.0F, 0.0F, oneOffset, 1.0F, 1.0F - zeroOffset);
						case 2:
							return new AxisAlignedBB(0.0F, zeroOffset, 0.0F, oneOffset, 1.0F, 1.0F);
						case 3:
							return new AxisAlignedBB(0.0F, zeroOffset, 0.0F, 1.0F, 1.0F, oneOffset);
						}
						return new AxisAlignedBB(0.0F, zeroOffset, 0.0F, 1.0F, 1.0F, oneOffset);
					case SOUTH:
						switch (piece) {
						case 1:
							return new AxisAlignedBB(zeroOffset, 0.0F, 1.0F - oneOffset, 1.0F, 1.0F, 1.0F);
						case 2:
							return new AxisAlignedBB(zeroOffset, 1.0F - oneOffset, 0.0F, 1.0F, 1.0F, 1.0F);
						case 3:
							return new AxisAlignedBB(0.0F, 1.0F - oneOffset, zeroOffset, 1.0F, 1.0F, 1.0F);
						}
					case WEST:
						switch (piece) {
						case 1:
							return new AxisAlignedBB(0.0F, 0.0F, zeroOffset, oneOffset, 1.0F, 1.0F);
						case 2:
							return new AxisAlignedBB(0.0F, zeroOffset, 0.0F, oneOffset, 1.0F, 1.0F);
						case 3:
							return new AxisAlignedBB(0.0F, 1.0F - oneOffset, zeroOffset, 1.0F, 1.0F, 1.0F);
						}
					default:
						return Block.NULL_AABB;
					}
				}
			}

			if (state.getBlock() == DecorBlocks.sloped_intersection) {
				if (state.getValue(BlockColorizerSlopedRotate.HALF) == EnumHalf.BOTTOM) {
					switch (state.getValue(BlockColorizerSlopedRotate.FACING)) {
					case EAST:
						switch (piece) {
						case 1:
							return new AxisAlignedBB(zeroOffset, 0.0F, 0.0F, 1.0F, oneOffset, 1.0F);
						case 2:
							return new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 1.0F - zeroOffset, oneOffset);
						}
					case NORTH:
						switch (piece) {
						case 1:
							return new AxisAlignedBB(0.0F, 0.0F, 0.0F, oneOffset, 1.0F - zeroOffset, 1.0F);
						case 2:
							return new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 1.0F - zeroOffset, oneOffset);
						}
					case SOUTH:
						switch (piece) {
						case 1:
							return new AxisAlignedBB(zeroOffset, 0.0F, 0.0F, 1.0F, oneOffset, 1.0F);
						case 2:
							return new AxisAlignedBB(0.0F, 0.0F, zeroOffset, 1.0F, oneOffset, 1.0F);
						}
					case WEST:
						switch (piece) {
						case 1:
							return new AxisAlignedBB(0.0F, 0.0F, 0.0F, oneOffset, 1.0F - zeroOffset, 1.0F);
						case 2:
							return new AxisAlignedBB(0.0F, 0.0F, zeroOffset, 1.0F, oneOffset, 1.0F);
						}
					default:
						return Block.NULL_AABB;
					}
				} else {
					switch (state.getValue(BlockColorizerSlopedRotate.FACING)) {
					case EAST:
						switch (piece) {
						case 1:
							return new AxisAlignedBB(zeroOffset, 1.0F - oneOffset, 0.0F, 1.0F, 1.0F, 1.0F);
						case 2:
							return new AxisAlignedBB(0.0F, zeroOffset, 0.0F, 1.0F, 1.0F, oneOffset);
						}
					case NORTH:
						switch (piece) {
						case 1:
							return new AxisAlignedBB(0.0F, zeroOffset, 0.0F, oneOffset, 1.0F, 1.0F);
						case 2:
							return new AxisAlignedBB(0.0F, zeroOffset, 0.0F, 1.0F, 1.0F, oneOffset);
						}
					case SOUTH:
						switch (piece) {
						case 1:
							return new AxisAlignedBB(zeroOffset, 1.0F - oneOffset, 0.0F, 1.0F, 1.0F, 1.0F);
						case 2:
							return new AxisAlignedBB(0.0F, 1.0F - oneOffset, zeroOffset, 1.0F, 1.0F, 1.0F);
						}
					case WEST:
						switch (piece) {
						case 1:
							return new AxisAlignedBB(0.0F, zeroOffset, 0.0F, oneOffset, 1.0F, 1.0F);
						case 2:
							return new AxisAlignedBB(0.0F, 1.0F - oneOffset, zeroOffset, 1.0F, 1.0F, 1.0F);
						}
					default:
						return Block.NULL_AABB;
					}
				}
			}

			if (state.getBlock() == DecorBlocks.corner) {
				switch (state.getValue(BlockColorizerSlopedRotate.FACING)) {
				case EAST:
					return new AxisAlignedBB(zeroOffset, 0.0F, 0.0F, 1.0F, 1.0F, oneOffset);
				case NORTH:
					return new AxisAlignedBB(0.0F, 0.0F, 0.0F, oneOffset, 1.0F, 1.0F - zeroOffset);
				case SOUTH:
					return new AxisAlignedBB(zeroOffset, 0.0F, 1.0F - oneOffset, 1.0F, 1.0F, 1.0F);
				case WEST:
					return new AxisAlignedBB(0.0F, 0.0F, zeroOffset, oneOffset, 1.0F, 1.0F);
				default:
					return Block.NULL_AABB;
				}
			}

		} else if (state.getBlock() instanceof BlockColorizerHedge) {
			if (state.getBlock() == DecorBlocks.pyramid) {
				switch (state.getValue(BlockColorizerFacing.FACING)) {
				case DOWN:
					return new AxisAlignedBB(zeroOffset * 0.5F, 1.0F - oneOffset * 0.68F, zeroOffset * 0.5F, 1.0F - zeroOffset * 0.5F, 1.0F, 1.0F - zeroOffset * 0.5F);
				case EAST:
					return new AxisAlignedBB(0.0F, zeroOffset * 0.5F, zeroOffset * 0.5F, oneOffset * 0.68F, 1.0F - zeroOffset * 0.5F, 1.0F - zeroOffset * 0.5F);
				case NORTH:
					return new AxisAlignedBB(zeroOffset * 0.5F, zeroOffset * 0.5F, 1.0F - oneOffset * 0.68F, 1.0F - zeroOffset * 0.5F, 1.0F - zeroOffset * 0.5F, 1.0F);
				case SOUTH:
					return new AxisAlignedBB(zeroOffset * 0.5F, zeroOffset * 0.5F, oneOffset * 0.68F, 1.0F - zeroOffset * 0.5F, 1.0F - zeroOffset * 0.5F, 0.0F);
				case UP:
					return new AxisAlignedBB(zeroOffset * 0.5F, 0.0F, zeroOffset * 0.5F, 1.0F - zeroOffset * 0.5F, oneOffset * 0.68F, 1.0F - zeroOffset * 0.5F);
				case WEST:
					return new AxisAlignedBB(1.0F, zeroOffset * 0.5F, zeroOffset * 0.5F, 1.0F - oneOffset * 0.68F, 1.0F - zeroOffset * 0.5F, 1.0F - zeroOffset * 0.5F);
				default:
					return Block.NULL_AABB;
				}
			}

			if (state.getBlock() == DecorBlocks.full_pyramid) {
				switch (state.getValue(BlockColorizerFacing.FACING)) {
				case DOWN:
					return new AxisAlignedBB(zeroOffset * 0.5F, 1.0F - oneOffset, zeroOffset * 0.5F, 1.0F - zeroOffset * 0.5F, 1.0F, 1.0F - zeroOffset * 0.5F);
				case EAST:
					return new AxisAlignedBB(0.0F, zeroOffset * 0.5F, zeroOffset * 0.5F, oneOffset, 1.0F - zeroOffset * 0.5F, 1.0F - zeroOffset * 0.5F);
				case NORTH:
					return new AxisAlignedBB(zeroOffset * 0.5F, zeroOffset * 0.5F, 1.0F, 1.0F - zeroOffset * 0.5F, 1.0F - zeroOffset * 0.5F, 1.0F - oneOffset);
				case SOUTH:
					return new AxisAlignedBB(zeroOffset * 0.5F, zeroOffset * 0.5F, 0.0F, 1.0F - zeroOffset * 0.5F, 1.0F - zeroOffset * 0.5F, oneOffset);
				case UP:
					return new AxisAlignedBB(zeroOffset * 0.5F, 0.0F, zeroOffset * 0.5F, 1.0F - zeroOffset * 0.5F, oneOffset, 1.0F - zeroOffset * 0.5F);
				case WEST:
					return new AxisAlignedBB(1.0F, zeroOffset * 0.5F, zeroOffset * 0.5F, 1.0F - oneOffset, 1.0F - zeroOffset * 0.5F, 1.0F - zeroOffset * 0.5F);
				default:
					return Block.NULL_AABB;
				}
			}

			if (state.getBlock() == DecorBlocks.sloped_post) {
				return Block.FULL_BLOCK_AABB;
			}

		}

		return Block.NULL_AABB;
	}

	public enum SlopeType {
		SlantedCorner("slanted_corner"), Corner("corner", 2), Slope("slope", 2), SlopedAngle("sloped_angle", 2), ObliqueSlope("oblique_slope", 3), SlopedIntersection("sloped_intersection", 2), Pyramid("pyramid", 2), FullPyramid("full_pyramid", 2), SlopedPost("sloped_post", 1);

		private final int numPieces;
		private final String name;

		private SlopeType(String name) {
			this(name, -1);
		}

		private SlopeType(String name, int numPieces) {
			this.name = name;
			this.numPieces = numPieces;
		}

		public String getName() {
			return name;
		}

		public int getNumPieces() {
			// Special override for SlantedAngles
			if (this == SlantedCorner)
				return DecorConfig.smoothness;
			else
				return numPieces;
		}
	}
}
