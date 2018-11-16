# BillingProtector
[![Latest Release](https://jitpack.io/v/AndreaCioccarelli/BillingProtector.svg)](https://jitpack.io/#AndreaCioccarelli/BillingProtector)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/a5bcdb5592d042f1825457fb9fafb778)](https://www.codacy.com/app/cioccarelliandrea01/BillingProtector)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-BillingProtector-green.svg?style=flat)](https://android-arsenal.com/details/1/7289)
[![Language](https://img.shields.io/badge/language-kotlin-orange.svg)](https://github.com/AndreaCioccarelli/BillingProtector/blob/master/library/build.gradle)
[![Min sdk](https://img.shields.io/badge/minsdk-14-yellow.svg)](https://github.com/AndreaCioccarelli/BillingProtector/blob/master/library/build.gradle)
[![License](https://img.shields.io/hexpm/l/plug.svg)](https://github.com/AndreaCioccarelli/BillingProtector/blob/master/LICENSE)

BillingProtector is a tiny and robust library for Android, entirely written in Kotlin. Its main purpose is to check the end-user device state & to secure purchases. 
Plus, it allows you to block a transition process if the application has been hacked or patched, if the device environment is corrupted or if other suspicious conditions are detected; it can also be used to prevent your apps from being executed on an unprotected/insecure operative system, like Snapchat does with rooted devices.

### Backgrounding
Hacking android applications is way easier than how it is thought to be. Softwares like [Lucky Patcher](https://www.luckypatchers.com) are phenomenally written to bypass all your first-line defenses and to edit your app executable code, redirecting your purchases to the pirate app and not to the Google Play Store.
That's a violation of the Developer's product and business, since it won't just make you lose money but also users and credibility.
I've been implmenting by myself on every app of mine a complex and different security scheme each time. Then I decided to put everything together and to realize this project of crucial importance for someone's business, in parallel with [CryptoPrefs](https://github.com/AndreaCioccarelli/CryptoPrefs).
Remember that a skilled hacker will always find a way to crack your code. This library is a harsh barrier that will stop the 99% of the other kiddies.

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
    implementation 'com.github.AndreaCioccarelli:BillingProtector:1.0.2'
}
```

# Usage
BillingProtector has different functionalities and can be used for different purposes.
- Check for pirate applications on the end-user device
- List the found threads on the end-user device
- Determine if the device has root access without requesting it
- Determine root binary path in the operative system

### Initialization
```kotlin
val bp = BillingProtector(context)
```
The code showed above creates an instance of the BillingProtector class passing as only argument the context of an application/activity.
You don't need to destroy any references to that object in `onDestroy()` since it isn't attached to your context.

### Checking Root Access
```kotlin
if (bp.isRootInstalled()) {
    finish();
}
```

The method `isRootInstalled()` is going to execute a built-in unix program to determine if the binary `su`, the root permission controller, is present on the system and it will analyze the path of that binary. From this evaluation the result is returned under boolean type.
This method is safe at 100% for 3 reasons.
- **It's not thread-blocking**, because it executes as less shell commands as possible.
- **It doesn't require root permissions**. Most of the root-checking software analyzes root access presence by actually executing `su` and analyzing the command stdout and stderr. This is slow, unefficient, thread-blocking, it requires your app to ask for root permissions and, in some cases of incompatibility / broken installation, it will freeze your app causing an ANR.
- **The result is reliable**. Many root managers I've had the opportunity to study while building [Impactor](https://play.google.com/store/apps/details?id=com.andreacioccarelli.impactor) places the root binary in hidden directories, under different partitions and among other equally-named programs. This is messy and it can lead to wrong results, basing on the installation type, on the root manager internal mechanism, on the root installation type, and so on and so forth. This tiny trick is efficient and reliable, since it will always just pick the system-linked `su` command.


### Checking pirate software presence
```kotlin
if (bp.arePirateAppsInstalled()) {
    for (app in bp.getPirateAppsList()) {
        toast(app.packageName)
    }
    finish()
}
```
The method `arePirateAppsInstalled()` is a simple `for` cycle that iterates through every installed software to search if one of them matches with the packages bundled in the library.
The method `getPirateAppsList()` instread returns a list of `PirateApp`s, that you can easily show to the user, or open in the default Sytsem Settings App Viewer with the given package name, and asking him to uninstall.

**Warning:**
- Never store the value of `arePirateAppsInstalled()` in a variable. Always calculate it at runtime, because your app can be easily cracked with lucky patcher otherwise (Also if no user will probably have way know it, he'd have to open e.g. Lucky Patcher, patch your app, launch it, uninstall Lucky Patcher, wait you to get on the purchase page and then install it again to compleate the process).

### Getting root binary path
```kotlin
toast(bp.getRootBinatyPath())
```

This method returns the absolute path of the `su` binary, if present. If not, an empty string is returned.
To operate with this file you need root permission since it is surely placed in a protected device zone.
This method works also if you don't require root permission though.
