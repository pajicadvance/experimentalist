plugins {
	id("mod-platform")
	id("net.neoforged.moddev.legacyforge")
	id("dev.kikugie.fletching-table") version "0.1.0-alpha.22"
	kotlin("jvm") version "2.2.10"
	id("com.google.devtools.ksp") version "2.2.10-2.0.2"
}

platform {
	loader = "forge"
	dependencies {
		required("minecraft") {
			forgeVersionRange = "[1.20,)"
		}
		required("forge") {
			forgeVersionRange = "[1,)"
		}
	}
}

fletchingTable {
	mixins.create("main") {
		mixin("default", "${prop("mod.id")}-forge.mixins.json")
	}
}

mixin {
	add(sourceSets["main"], "${prop("mod.id")}-forge.refmap.json")
	config("${prop("mod.id")}-forge.mixins.json")
}

legacyForge {
	version = property("deps.forge") as String
	validateAccessTransformers = true

	if (hasProperty("deps.parchment")) parchment {
		val (mc, ver) = (property("deps.parchment") as String).split(':')
		mappingsVersion = ver
		minecraftVersion = mc
	}

	runs {
		register("client") {
			client()
			gameDirectory = file("run/")
			ideName = "Forge Client (${stonecutter.active?.version})"
			programArgument("--username=Dev")
		}
		register("server") {
			server()
			gameDirectory = file("run/")
			ideName = "Forge Server (${stonecutter.active?.version})"
		}
	}

	mods {
		register(property("mod.id") as String) {
			sourceSet(sourceSets["main"])
		}
	}
}

repositories {
	maven("https://maven.parchmentmc.org") { name = "ParchmentMC" }
}

dependencies {
	annotationProcessor("org.spongepowered:mixin:0.8.5:processor")
	annotationProcessor("io.github.llamalad7:mixinextras-common:0.5.0")
	compileOnly("io.github.llamalad7:mixinextras-common:0.5.0")
	implementation("io.github.llamalad7:mixinextras-forge:0.5.0")
	jarJar("io.github.llamalad7:mixinextras-forge:0.5.0")
}

tasks.named("createMinecraftArtifacts") {
	dependsOn(tasks.named("stonecutterGenerate"))
}

tasks.named<Jar>("jar") {
	manifest {
		attributes(
			"MixinConfigs" to "${prop("mod.id")}-forge.mixins.json"
		)
	}
}
