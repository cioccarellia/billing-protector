# BillingProtector
[![Latest Release](https://jitpack.io/v/AndreaCioccarelli/BillingProtector.svg)](https://jitpack.io/#AndreaCioccarelli/BillingProtector)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/a5bcdb5592d042f1825457fb9fafb778)](https://www.codacy.com/app/cioccarelliandrea01/BillingProtector)
[![Language](https://img.shields.io/badge/language-kotlin-orange.svg)](https://github.com/AndreaCioccarelli/LogKit/blob/master/library/build.gradle)
[![Min sdk](https://img.shields.io/badge/minsdk-14-yellow.svg)](https://github.com/AndreaCioccarelli/LogKit/blob/master/library/build.gradle)
[![License](https://img.shields.io/hexpm/l/plug.svg)](https://github.com/AndreaCioccarelli/BillingProtector/blob/master/LICENSE)

BillingProtector is a Kotlin robust and tiny library aiming to check the host device state & purchases security. 
Its main purpose is to block a transition if the application has been modified or patched, if the device environment is corrupted or if other suspicious condition is detected; it can also be used to prevent applications from being executed on an unprotected/insecure operative system.

## Backgrounding
Hacking android applications is way easier than how it is thought to be. Softwares like [Lucky Patcher](https://www.luckypatchers.com) are phenomenally written to bypass all your first-line defenses and edit your app executable code, redirecting your purchases to the pirate app and not to the Google Play Store.
That's a violation of the Developer's product and of the Developer's business, since it won't just make you lose money but also users and credibility.
I've been implmenting by myself on every app of mine a complex and different security scheme each time. Then I decided to put everything together and to realize this project of crucial importance for someone's business, in parallel with [CryptoPrefs](https://github.com/AndreaCioccarelli/CryptoPrefs).
Remember that a skilled hacker will always find a way to crack your code. This library is a barrier that will stop the 99% of the other kiddies.

# Setup
BillingProtector uses jitpack as packages repository.
To use it you need to add the repository to your project build.gradle file:
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
    implementation 'com.github.AndreaCioccarelli:BillingProtector:1.0.0'
}
```

# Usage
BillingProtector has different functionalities and can be used for different purposes.
- Check for pirate applications on the end-user device
- List the found threads on the end-user device
- Determine if the device has root access without requesting it
- Determine root binary path in the operative system

## Initialization
Create an instance of the BillingProtector class passing as argument the context of your current application/activity
```kotlin
val bp = BillingProtector(context)
```

## Check Root
```kotlin
if (bp.isRootInstalled()) {
    finish();
}
```

## Check if pirate apps are installed
```kotlin
if (bp.arePirateAppsInstalled()) {
    for (app in bp.getPirateAppsList()) {
        toast(app.packageName)
    }
    finish()
}
```

## Get root binary path
```kotlin
toast(bp.getRootBinatyPath())
```
