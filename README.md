# BillingProtector
BillingProtector is and android robust and small library aiming to check the device state & purchases security. 
Its main purpose is to block a transition if the application has been modified or patched; it can also be used to prevent applications from being executed on an unprotected/insecure envoronment.

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


## Initialization
Create an instance of the BillingProtector class passing as argument the context of your current activity/fragment
```kotlin
val bp = BillingProtector(baseContext)
```

## Check Root
```kotlin
val isRootInstalled = bp.isRootInstalled
```

## Check if pirate apps are installed
```kotlin
val isEnvSafe = bp.arePirateAppsInstalled
val list = bp.pirateAppsList
```
