# BillingProtector
[![Latest Release](https://jitpack.io/v/AndreaCioccarelli/BillingProtector.svg)](https://jitpack.io/#AndreaCioccarelli/BillingProtector)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/a5bcdb5592d042f1825457fb9fafb778)](https://www.codacy.com/app/cioccarelliandrea01/BillingProtector)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-BillingProtector-green.svg?style=flat)](https://android-arsenal.com/details/1/7289)
[![Language](https://img.shields.io/badge/language-kotlin-orange.svg)](https://github.com/AndreaCioccarelli/BillingProtector/blob/master/library/build.gradle)
[![Min sdk](https://img.shields.io/badge/minsdk-14-yellow.svg)](https://github.com/AndreaCioccarelli/BillingProtector/blob/master/library/build.gradle)
[![License](https://img.shields.io/hexpm/l/plug.svg)](https://github.com/AndreaCioccarelli/BillingProtector/blob/master/LICENSE)

BillingProtector is a tiny and robust library for Android, entirely written in Kotlin. Its main purpose is to check the android device state & to provide a way to execute purchases just when it is safe. 
Plus, it allows you to block a transition process if the application has been hacked or patched, if the device environment is corrupted or if any other suspicious conditions are detected; it can also be used to prevent your apps from being executed on an unprotected/insecure operative system, like Snapchat does with rooted devices.

### Backgrounding
Hacking android applications is way easier than how it is thought to be. Softwares like [Lucky Patcher](https://www.luckypatchers.com) are written to bypass all your first-line defenses and to edit your app executable code, redirecting your purchases to the pirate app, and *not* to the Google Play Store.
I've been implmenting by myself a complex and different security scheme each time, then I decided to put everything together and to realize this project, in parallel with [CryptoPrefs](https://github.com/AndreaCioccarelli/CryptoPrefs).
Remember that a skilled hacker will always find a way to crack your code. This library is a harsh barrier that will stop the 99% of the others.

### Bulletin
- At the end of December 2018, Lucky Patcher 8.0.0 was released, along with the possibility to randomize package name and make the app invisible from Google Play Protect and other defense systems.
The 5th of January, BillingProtector 1.1.0 update introduces support for custom package parameter matching and comes along with the ability of detecting every masked lucky patcher installation
- Lucky Patcher (As of March 2019) developed a technique which allowed to display its package label [using a different charset](https://twitter.com/ACioccarelli/status/1105249064147472385), avoiding normal string-based detction. 
BillingProtector 1.3.0 detects that version, the Lucky Patcher billing emulation server, the installer and the proxy used to bypass app purchase mechanism.
- Lucky Patcher (As of September 2019) Started randomizing package names of both installer and compiled application to avoid detection.
BillingProtector updates some internal detection features to achive a better match, and to detect possible furure versions bypasses before they are developed by actually reverse-engeninering them.

# Setup
BillingProtector uses [jitpack](https://jitpack.io/#AndreaCioccarelli/BillingProtector) as package repository.
To use it you need to add that line to your project build.gradle file:
```gradle
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```
And the dependency to your module build.gradle file:
```gradle
dependencies {
    implementation 'com.github.AndreaCioccarelli:BillingProtector:1.4.0'
}
```

# Usage
BillingProtector has different functionalities and can be used for different purposes.
- Checking for pirate applications on the end-user device
- Listing threats on the end-user device
- Determining whether the device is rooted
- Determining the root binary path in the file system

### Initialization
```kotlin
val bp = BillingProtector(context)
```
The code shown above creates an instance of the BillingProtector clas, passing as only argument the context of an application/activity.
You don't need to destroy any reference to that object in `onDestroy()` since it doesn't hook with your context.

### Checking Root Access
```kotlin
if (bp.isRootInstalled()) {
    finish()
}
```

The method `isRootInstalled()` is going to execute a built-in unix program to determine if the binary `su` (the root permission controller) is present on the system, and it will analyze the path of that binary. From this evaluation the result is returned.

This method is safe at 100% for 3 reasons.
- **It's not thread-blocking**, because it executes few, passive, fast and synchronous tasks
- **It doesn't ask for root permission**. Most of root-checking software analyzes root access presence by actually executing `su` and analyzing the command *stdout* and *stderr*. This is slow, unefficient, thread-blocking, it requires your app to ask for root permissions and, in some cases of incompatibility / broken installation, it will freeze your app.
- **The result is reliable**. Many root managers I've had the opportunity to study while building [Impactor](https://play.google.com/store/apps/details?id=com.andreacioccarelli.impactor) place the root binary in hidden directories, under different partitions and among other equally-named programs. This is messy and it can lead to wrong results, basing on the installation type, on the root manager internal mechanism, on the root installation version, and so on and so forth. This tiny hack is efficient and reliable, since it will always just pick the right system-wide `su` command.

### Checking pirate software presence
```kotlin
if (bp.arePirateAppsInstalled()) {
    for (app in bp.getPirateAppsList()) {
        toast(app.packageName)
    }
    finish()
}
```
The method `arePirateAppsInstalled()` is a simple iteration that goes through every installed package to check if one of them matches with the analysis metadata bundled in the library.
The method `getPirateAppsList()` returns a list of `PirateApp`s, that you can display to the user, launch in the system settings, retrive a specific package name or prompting to uninstall the selected software.

**Warning:**
- Never store the value of `arePirateAppsInstalled()` in a property. Always evaluate it at run-time, because your app can be cracked otherwise (Also if no user will probably have way know it, he'd have to open e.g. Lucky Patcher, patch your app, launch it, uninstall Lucky Patcher, wait you to get on the purchase page and then install it again to compleate the process), following a basic memory injection attack.

### Getting root binary path
```kotlin
bp.getRootBinatyPath()
```

This method returns the absolute path of the `su` binary, if present. If not, an empty string is returned.
To operate with this file you need root permission since it will be placed in a protected file system zone.
This method works if you don't require root permissions too.
