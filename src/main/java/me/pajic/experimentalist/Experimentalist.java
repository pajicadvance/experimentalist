package me.pajic.experimentalist;

import me.pajic.experimentalist.platform.Platform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//? fabric {
import me.pajic.experimentalist.platform.fabric.FabricPlatform;
//?} neoforge {
/*import me.pajic.experimentalist.platform.neoforge.NeoforgePlatform;
 *///?}

@SuppressWarnings("LoggingSimilarMessage")
public class Experimentalist {

	public static final String MOD_ID = /*$ mod_id*/ "experimentalist";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	private static final Platform PLATFORM = createPlatformInstance();

	static Platform xplat() {
		return PLATFORM;
	}

	private static Platform createPlatformInstance() {
		//? fabric {
		return new FabricPlatform();
		//?} neoforge {
		/*return new NeoforgePlatform();
		 *///?}
	}
}
