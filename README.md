# BillingProtector
BillingProtector is and android robust and small library whose aim is to check the device state & the purchases security. Its main purpose is to block a transition if the application has been modified or patched; it can also be used to prevent applications from being executed on an unprotected/insecure envoronment.

## Setup
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
    implementation 'com.github.AndreaCioccarelli:CryptoPrefs:1.1.2.0'
}
```