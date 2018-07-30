# BillingProtector (WIP)
BillingProtector is and android robust and small library whose aim is to check the device state & the purchases security. Its main purpose is to block a transition if the application has been modified or patched; it can also be used to prevent applications from being executed on an unprotected/insecure envoronment.

# Warning ⚠️
This API is subject to changes


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
	
}
```

## Usage
BillingProtector has some core functionalities. Let's see them in a list