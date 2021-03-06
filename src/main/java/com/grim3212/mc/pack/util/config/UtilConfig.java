package com.grim3212.mc.pack.util.config;

import java.util.ArrayList;
import java.util.List;

import com.grim3212.mc.pack.core.config.GrimConfig;
import com.grim3212.mc.pack.util.GrimUtil;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.config.DummyConfigElement.DummyCategoryElement;
import net.minecraftforge.fml.client.config.IConfigElement;

public class UtilConfig extends GrimConfig {

	public static final String CONFIG_NAME = "util";
	public static final String CONFIG_GENERAL_NAME = "util.general";
	public static final String CONFIG_FUSRODAH_NAME = "util.fusrodah";
	public static final String CONFIG_DEBUG_NAME = "util.debug";
	public static final String CONFIG_PARTS_NAME = "util.subparts";
	public static final String CONFIG_AUTO_TORCH_NAME = "util.autotorch";

	// Sync to client
	public static double frd_power;
	public static double frd_lift;
	public static double fusrodahCooldown;
	public static int featherRate;

	// Client Only
	public static boolean soundEnabled;
	public static boolean useOldSound;
	public static int lightTolerance;
	public static int torchDistance;
	public static Property atEnabled;

	// Debug
	public static boolean showCollisionBoxes;

	// Subparts
	public static boolean subpartAutoItemReplacer;
	public static boolean subpartAutoTorch;
	public static boolean subpartChickenFeathers;
	public static boolean subpartDebug;
	public static boolean subpartDoubleDoors;
	public static boolean subpartFrozen;
	public static boolean subpartFusRoDah;
	public static boolean subpartGraves;
	public static boolean subpartInfiniteLava;
	public static boolean subpartTime;

	@Override
	public String name() {
		return CONFIG_NAME;
	}

	@Override
	public void syncSubparts() {
		subpartAutoItemReplacer = config.get(CONFIG_PARTS_NAME, "Enable SubPart auto item replacer", false).setRequiresMcRestart(true).getBoolean();
		subpartAutoTorch = config.get(CONFIG_PARTS_NAME, "Enable SubPart auto torch", true).setRequiresMcRestart(true).getBoolean();
		subpartChickenFeathers = config.get(CONFIG_PARTS_NAME, "Enable SubPart chicken feathers", true).setRequiresMcRestart(true).getBoolean();
		subpartDebug = config.get(CONFIG_PARTS_NAME, "Enable SubPart debug", false).setRequiresMcRestart(true).getBoolean();
		subpartDoubleDoors = config.get(CONFIG_PARTS_NAME, "Enable SubPart double doors", true).setRequiresMcRestart(true).getBoolean();
		subpartFrozen = config.get(CONFIG_PARTS_NAME, "Enable SubPart frozen", true).setRequiresMcRestart(true).getBoolean();
		subpartFusRoDah = config.get(CONFIG_PARTS_NAME, "Enable SubPart fusrodah", true).setRequiresMcRestart(true).getBoolean();
		subpartGraves = config.get(CONFIG_PARTS_NAME, "Enable SubPart graves", true).setRequiresMcRestart(true).getBoolean();
		subpartInfiniteLava = config.get(CONFIG_PARTS_NAME, "Enable SubPart infinite lava", false).setRequiresMcRestart(true).getBoolean();
		subpartTime = config.get(CONFIG_PARTS_NAME, "Enable SubPart time", true).setRequiresMcRestart(true).getBoolean();
	}

	@Override
	public void syncConfig() {
		syncSubparts();

		if (subpartFusRoDah) {
			frd_power = config.get(CONFIG_FUSRODAH_NAME, "Horizontal Pushing Force", 2.0D).getDouble();
			frd_lift = config.get(CONFIG_FUSRODAH_NAME, "Upward Pushing Force", 0.8D).getDouble();
			soundEnabled = config.get(CONFIG_FUSRODAH_NAME, "Sound Enabled", true).getBoolean();
			useOldSound = config.get(CONFIG_FUSRODAH_NAME, "Use Original FusRoDah Sound", false).getBoolean();
			fusrodahCooldown = config.get(CONFIG_FUSRODAH_NAME, "FusRoDah cooldown (seconds)", 5).getDouble();
		}

		if (subpartChickenFeathers)
			featherRate = config.get(CONFIG_GENERAL_NAME, "The spawn rate for chickens dropping feathers", 26000).getInt();

		if (subpartDebug)
			showCollisionBoxes = config.get(CONFIG_DEBUG_NAME, "Show collision boxes", false).getBoolean();

		if (subpartAutoTorch) {
			atEnabled = config.get(CONFIG_AUTO_TORCH_NAME, "AutoTorch enabled", false);

			// Tolerance is a slider
			lightTolerance = config.get(CONFIG_AUTO_TORCH_NAME, "AutoTorch Light Tolerance", 30, "AutoTorch Light Tolerance defines what the maximum light tolerance can be before another torch will be placed. So, if it is higher the more torches will be placed while lower will decrease the amount.", 0, 100).setConfigEntryClass(GrimUtil.proxy.getSliderClass()).getInt();

			// Distance cycles through possible values
			String d = config.get(CONFIG_AUTO_TORCH_NAME, "AutoTorch Torch Distance", "grimpack.util.autotorch.d0", "AutoTorch Distance allows you to specify where to place the torch. There are currently 4 options from under the player to three blocks in front of the player", new String[] { "grimpack.util.autotorch.d0", "grimpack.util.autotorch.d1", "grimpack.util.autotorch.d2", "grimpack.util.autotorch.d3" }).getString();
			torchDistance = d.equals("grimpack.util.autotorch.d1") ? 1 : d.equals("grimpack.util.autotorch.d2") ? 2 : d.equals("grimpack.util.autotorch.d3") ? 3 : 0;
		}

		super.syncConfig();
	}

	@Override
	public List<IConfigElement> getConfigItems() {
		List<IConfigElement> list = new ArrayList<IConfigElement>();
		if (subpartChickenFeathers)
			list.add(new DummyCategoryElement("utilGeneralCfg", "grimpack.util.cfg.general", new ConfigElement(GrimUtil.INSTANCE.getConfig().getCategory(CONFIG_GENERAL_NAME)).getChildElements()));
		if (subpartFusRoDah)
			list.add(new DummyCategoryElement("utilFusRoDahCfg", "grimpack.util.cfg.fusrodah", new ConfigElement(GrimUtil.INSTANCE.getConfig().getCategory(CONFIG_FUSRODAH_NAME)).getChildElements()));
		if (subpartAutoTorch)
			list.add(new DummyCategoryElement("utilAutoTorchCfg", "grimpack.util.cfg.autotorch", new ConfigElement(GrimUtil.INSTANCE.getConfig().getCategory(CONFIG_AUTO_TORCH_NAME)).getChildElements()));
		if (subpartDebug)
			list.add(new DummyCategoryElement("utilDebugCfg", "grimpack.util.cfg.debug", new ConfigElement(GrimUtil.INSTANCE.getConfig().getCategory(CONFIG_DEBUG_NAME)).getChildElements()));
		list.add(new DummyCategoryElement("utilSubPartCfg", "grimpack.util.cfg.subparts", new ConfigElement(GrimUtil.INSTANCE.getConfig().getCategory(CONFIG_PARTS_NAME)).getChildElements()));
		return list;
	}

	@Override
	public void readFromServer(PacketBuffer buffer) {
		subpartAutoItemReplacer = buffer.readBoolean();
		subpartAutoTorch = buffer.readBoolean();
		subpartChickenFeathers = buffer.readBoolean();
		subpartDebug = buffer.readBoolean();
		subpartDoubleDoors = buffer.readBoolean();
		subpartFrozen = buffer.readBoolean();
		subpartFusRoDah = buffer.readBoolean();
		subpartGraves = buffer.readBoolean();
		subpartInfiniteLava = buffer.readBoolean();
		subpartTime = buffer.readBoolean();

		if (subpartFusRoDah) {
			fusrodahCooldown = buffer.readDouble();
			frd_lift = buffer.readDouble();
			frd_power = buffer.readDouble();
		}
	}

	@Override
	public void writeToClient(PacketBuffer buffer) {
		buffer.writeBoolean(subpartAutoItemReplacer);
		buffer.writeBoolean(subpartAutoTorch);
		buffer.writeBoolean(subpartChickenFeathers);
		buffer.writeBoolean(subpartDebug);
		buffer.writeBoolean(subpartDoubleDoors);
		buffer.writeBoolean(subpartFrozen);
		buffer.writeBoolean(subpartFusRoDah);
		buffer.writeBoolean(subpartGraves);
		buffer.writeBoolean(subpartInfiniteLava);
		buffer.writeBoolean(subpartTime);

		if (subpartFusRoDah) {
			buffer.writeDouble(fusrodahCooldown);
			buffer.writeDouble(frd_lift);
			buffer.writeDouble(frd_power);
		}
	}
}
