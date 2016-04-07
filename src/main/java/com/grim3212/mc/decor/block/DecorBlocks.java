package com.grim3212.mc.decor.block;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.grim3212.mc.core.part.IPartItems;
import com.grim3212.mc.core.util.NBTHelper;
import com.grim3212.mc.core.util.RecipeHelper;
import com.grim3212.mc.decor.GrimDecor;
import com.grim3212.mc.decor.item.DecorItems;
import com.grim3212.mc.decor.item.ItemFurniture;
import com.grim3212.mc.decor.item.ItemLantern;
import com.grim3212.mc.decor.util.BlockHelper;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class DecorBlocks implements IPartItems {

	public static Block calendar;
	public static Block wall_clock;
	public static Block light_bulb;
	public static Block pot;
	public static Block cage;
	public static Block chain;
	public static Block road;
	public static Block lantern;
	public static Block fancy_stone;
	public static Block craft_clay;
	public static Block craft_bone;
	public static Block counter;
	public static Block stool;
	public static Block chair;
	public static Block wall;
	public static Block table;
	public static Block fence;
	public static Block fence_gate;
	public static Block lamp_post_bottom;
	public static Block lamp_post_middle;
	public static Block lamp_post_top;

	public static IRecipe mossy;
	public static List<IRecipe> stone;
	public static List<IRecipe> chains;
	public static List<IRecipe> crafts = new ArrayList<IRecipe>();
	public static List<IRecipe> clocks;
	public static List<IRecipe> lights = new ArrayList<IRecipe>();

	@Override
	public void initItems() {
		calendar = (new BlockCalendar()).setHardness(1.0F).setStepSound(Block.soundTypeWood).setUnlocalizedName("calendar").setCreativeTab(GrimDecor.INSTANCE.getCreativeTab());
		wall_clock = new BlockWallClock().setHardness(0.75F).setStepSound(Block.soundTypeWood).setUnlocalizedName("wall_clock").setCreativeTab(GrimDecor.INSTANCE.getCreativeTab());
		light_bulb = (new BlockLightBulb()).setHardness(0.1F).setStepSound(Block.soundTypeGlass).setUnlocalizedName("light_bulb");
		lantern = (new BlockLantern().setHardness(0.1F).setLightLevel(0.9375F).setUnlocalizedName("lantern")).setCreativeTab(GrimDecor.INSTANCE.getCreativeTab());
		road = (new BlockRoad()).setHardness(0.5F).setResistance(10F).setStepSound(Block.soundTypeStone).setUnlocalizedName("road").setCreativeTab(GrimDecor.INSTANCE.getCreativeTab());
		fancy_stone = (new Block(Material.rock)).setHardness(0.5F).setResistance(10F).setStepSound(Block.soundTypeStone).setUnlocalizedName("fancy_stone").setCreativeTab(GrimDecor.INSTANCE.getCreativeTab());
		chain = (new BlockDecoration(Material.circuits, false)).setUnlocalizedName("chain").setCreativeTab(GrimDecor.INSTANCE.getCreativeTab());
		cage = (new BlockDecoration(Material.iron, true)).setHardness(0.8F).setResistance(5F).setUnlocalizedName("cage").setCreativeTab(GrimDecor.INSTANCE.getCreativeTab());
		pot = (new BlockPot()).setHardness(0.5F).setResistance(10F).setUnlocalizedName("pot").setCreativeTab(GrimDecor.INSTANCE.getCreativeTab());
		craft_clay = (new BlockCraftClay()).setUnlocalizedName("craft_clay").setCreativeTab(GrimDecor.INSTANCE.getCreativeTab());
		craft_bone = (new BlockCraftBone()).setUnlocalizedName("craft_bone").setCreativeTab(GrimDecor.INSTANCE.getCreativeTab());
		counter = (new BlockCounter()).setUnlocalizedName("counter");
		stool = (new BlockStool()).setUnlocalizedName("stool");
		chair = (new BlockChair()).setUnlocalizedName("chair");
		wall = (new BlockWall()).setUnlocalizedName("wall");
		fence = (new BlockFence()).setUnlocalizedName("fence");
		fence_gate = (new BlockFenceGate()).setUnlocalizedName("fence_gate");
		table = (new BlockTable()).setUnlocalizedName("table");
		lamp_post_bottom = (new BlockLampPost(false)).setUnlocalizedName("lamp_post_bottom");
		lamp_post_middle = (new BlockLampPost(false)).setUnlocalizedName("lamp_post_middle");
		lamp_post_top = new BlockLampPost(true).setUnlocalizedName("lamp_post_top");

		GameRegistry.registerBlock(calendar, "calendar");
		GameRegistry.registerBlock(wall_clock, "wall_clock");
		GameRegistry.registerBlock(light_bulb, "light_bulb");
		GameRegistry.registerBlock(table, ItemFurniture.class, "table");
		GameRegistry.registerBlock(counter, ItemFurniture.class, "counter");
		GameRegistry.registerBlock(stool, ItemFurniture.class, "stool");
		GameRegistry.registerBlock(chair, ItemFurniture.class, "chair");
		GameRegistry.registerBlock(wall, ItemFurniture.class, "wall");
		GameRegistry.registerBlock(fence, ItemFurniture.class, "fence");
		GameRegistry.registerBlock(lamp_post_bottom, "lamp_post_bottom");
		GameRegistry.registerBlock(lamp_post_middle, "lamp_post_middle");
		GameRegistry.registerBlock(lamp_post_top, "lamp_post_top");
		GameRegistry.registerBlock(fence_gate, ItemFurniture.class, "fence_gate");
		GameRegistry.registerBlock(lantern, ItemLantern.class, "lantern");
		GameRegistry.registerBlock(road, "road");
		GameRegistry.registerBlock(fancy_stone, "fancy_stone");
		GameRegistry.registerBlock(chain, "chain");
		GameRegistry.registerBlock(cage, "cage");
		GameRegistry.registerBlock(pot, "pot");
		GameRegistry.registerBlock(craft_clay, "craft_clay");
		GameRegistry.registerBlock(craft_bone, "craft_bone");
	}

	@Override
	public void addRecipes() {
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(calendar, 1), new Object[] { "##", "##", "##", '#', Items.paper }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(wall_clock, 1), new Object[] { "XIX", "IRI", "XIX", 'X', "plankWood", 'I', "ingotGold", 'R', "dustRedstone" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(wall_clock, 1), new Object[] { "XXX", "XRX", "XXX", 'X', "plankWood", 'R', Items.clock }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.clock, 1), new Object[] { wall_clock }));
		clocks = RecipeHelper.getLatestIRecipes(3);

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(light_bulb, 1, 0), new Object[] { "###", "#$#", " ! ", '#', "blockGlass", '$', Blocks.redstone_torch, '!', "ingotIron" }));
		lights.add(RecipeHelper.getLatestIRecipe());

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(craft_bone, 1), new Object[] { " # ", "###", "###", '#', Items.bone }));
		crafts.add(RecipeHelper.getLatestIRecipe());

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(lantern, 1, 0), new Object[] { " # ", "#X#", '#', Items.paper, 'X', "dustGlowstone" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(lantern, 1, 1), new Object[] { " # ", "#X#", '#', Items.bone, 'X', "dustGlowstone" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(lantern, 1, 2), new Object[] { " # ", "#X#", '#', "ingotIron", 'X', "dustGlowstone" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.mossy_cobblestone, 8), new Object[] { "###", "#X#", "###", '#', Blocks.cobblestone, 'X', Items.water_bucket }));
		mossy = RecipeHelper.getLatestIRecipe();

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(fancy_stone, 1), new Object[] { "###", "# #", "###", '#', "stone" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.stone, 8), new Object[] { "#", '#', fancy_stone }));
		stone = RecipeHelper.getLatestIRecipes(2);

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(cage, 2), new Object[] { "###", "# #", "###", '#', "ingotIron" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(chain, 2), new Object[] { "#", "#", '#', "ingotIron" }));
		chains = RecipeHelper.getLatestIRecipes(2);

		GameRegistry.addSmelting(Blocks.gravel, new ItemStack(road, 1), 0.15F);

		addFurnitureRecipe(stool, 4, "###", "I I");
		addFurnitureRecipe(counter, 4, "###", " I ");
		addFurnitureRecipe(table, 4, "###", "I I", "I I");
		addFurnitureRecipe(chair, 4, "#  ", "###", "I I");
		addFurnitureRecipe(wall, 4, "#", "#", "#");
		addFurnitureRecipe(fence, 4, "###", "#I#");
		addFurnitureRecipe(fence_gate, 4, "I#I", "III");
		addFurnitureRecipe(DecorItems.lamp_item, 1, "#G#", "###", " # ");
	}
	
	private void addFurnitureRecipe(Block furnType, int amount, String... pattern){
		addFurnitureRecipe(Item.getItemFromBlock(furnType), amount, pattern);
	}

	private void addFurnitureRecipe(Item furnType, int amount, String... pattern) {
		LinkedHashMap<Block, Integer> loadedBlocks = BlockHelper.getBlocks();
		Block[] blocks = loadedBlocks.keySet().toArray(new Block[loadedBlocks.keySet().size()]);

		for (int i = 0; i < blocks.length; i++) {
			if (loadedBlocks.get(blocks[i]) == 0) {
				ItemStack stack = new ItemStack(furnType, amount);
				NBTHelper.setInteger(stack, "blockID", Block.getIdFromBlock(blocks[i]));
				NBTHelper.setInteger(stack, "blockMeta", 0);
				GameRegistry.addRecipe(new ShapedOreRecipe(stack, new Object[] { pattern, '#', new ItemStack(blocks[i], 1, 0), 'I', "stickWood", 'G', "glowstone" }));
			} else {
				for (int j = 0; j < loadedBlocks.get(blocks[i]); j++) {
					ItemStack stack = new ItemStack(furnType, amount);
					NBTHelper.setInteger(stack, "blockID", Block.getIdFromBlock(blocks[i]));
					NBTHelper.setInteger(stack, "blockMeta", j);
					GameRegistry.addRecipe(new ShapedOreRecipe(stack, new Object[] { pattern, '#', new ItemStack(blocks[i], 1, j), 'I', "stickWood", 'G', "glowstone" }));
				}
			}
		}
	}
}
