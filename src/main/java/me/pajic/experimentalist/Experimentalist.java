package me.pajic.experimentalist;

import me.pajic.experimentalist.platform.Platform;

import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//? fabric {
import me.pajic.experimentalist.platform.fabric.FabricPlatform;
//?} neoforge {
/*import me.pajic.experimentalist.platform.neoforge.NeoforgePlatform;
*///?} forge {
/*import me.pajic.experimentalist.platform.forge.ForgePlatform;
*///?}

@SuppressWarnings("LoggingSimilarMessage")
public class Experimentalist {

	public static final String MOD_ID = /*$ mod_id*/ "experimentalist";
	public static final String MOD_VERSION = /*$ mod_version*/ "2.0.2";
	public static final String MOD_FRIENDLY_NAME = /*$ mod_name*/ "Experimentalist";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	private static final Platform PLATFORM = createPlatformInstance();

	public static Platform xplat() {
		return PLATFORM;
	}

	private static Platform createPlatformInstance() {
		//? fabric {
		return new FabricPlatform();
		//?} neoforge {
		/*return new NeoforgePlatform();
		*///?} forge {
		/*return new ForgePlatform();
		*///?}
	}

	public static ResourceLocation vanillaId(String path) {
		//? if 1.20.1
		//return new ResourceLocation(path);
		//? if > 1.20.1
		return ResourceLocation.withDefaultNamespace(path);
	}
}
