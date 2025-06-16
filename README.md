This is a Kotlin Multiplatform project targeting Android, Desktop.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.


Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…

Android Build APK
1. Copy the Apk file in to Tablet device 
2. double click file and change install permission 
3. then it will be installed

Install Windows using MSI file 
1. download the file to windows desktop
2. double click and follow the instruction
3. once completed you can able to open the installed APP

Descrition about the Solution 
created the Application with Kotlin Multiplatform compose UI with Shared logic 
converted the JSON file in to serializabe model class 
created view model class to get the list of menu category and its group menu items with price.
created view model for card with model data class.
used Navigation drawer with ElevatedCard for in design and Column and row. 
