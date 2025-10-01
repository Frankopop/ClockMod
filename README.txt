# ClockMod (MC 1.19.2) [![](https://jitpack.io/v/Frankopop/ClockMod.svg)](https://jitpack.io/#Frankopop/ClockMod)

**License:** MIT  

---

## Overview

ClockMod adds a dynamic in-game clock overlay synchronized with the server.  
The mod allows players to see the current time directly on their screen without needing additional items or GUIs.

By default, the mod displays a simple, clean clock overlay. The clock is updated in real-time according to the server's in-game time, making it perfect for multiplayer servers or solo worlds where knowing the precise time is useful.

---

## Features

- ⏱ **Real-time server synchronization**: The clock reflects the server time accurately.  
- 🎛 **Customizable overlay**: Easily tweak position, size, and visibility of the clock.  
- ⚡ **Lightweight**: Minimal impact on performance.  
- 🔧 **Easy integration**: Works out-of-the-box on any Forge 1.19.2 setup.  

---

## Adding ClockMod to Your Project

Add **JitPack** to your `build.gradle` repositories:

```gradle
repositories {
    maven { url "https://jitpack.io" }
}
Add ClockMod as a dependency:

gradle
Copia codice
dependencies {
    implementation 'com.github.Frankopop:ClockMod:1.0-1.19.2'
}
Replace 1.0-1.19.2 with the tag/version you want to use if different.

Usage
Once installed, the clock appears on your HUD automatically when joining a server or starting a single-player world.
No additional configuration is required for default behavior.

Development Setup
If you want to develop or contribute to ClockMod:

Clone the repository:

bash
Copia codice
git clone https://github.com/Frankopop/ClockMod.git
cd ClockMod
Add run configuration properties to your Gradle build.gradle client and server blocks:

gradle
Copia codice
property 'mixin.env.remapRefMap', 'true'
property 'mixin.env.refMapRemappingFile', "${buildDir}/createSrgToMcp/output.srg"
Regenerate IDE run configurations:

bash
Copia codice
./gradlew genIntellijRuns   # For IntelliJ IDEA
./gradlew genEclipseRuns    # For Eclipse
./gradlew genVSCodeRuns     # For VSCode
Release Notes
1.0-1.19.2 – First public release

Dynamic clock overlay synchronized with server time.

Lightweight and ready for Forge 1.19.2.
