# Appium Automation
### WIP

This project seeks to automate the app [The App](https://github.com/appium-pro/TheApp) using Appium and Java.

For the automation I choose to create each scenario in the code for study purposes.\
The Main branch will be changed while this project evolves, but in the end, it will contain other branches with the apps that I will work on. 

## **Prerequisites**
Android Studio, IntelliJ, Java 16+, Appium Server GUI and Appium Inspector.

## **Installation**
First install Android Studio, IntelliJ, Java 16+, Appium Server GUI and Appium Inspector to start the configuration.

## **Configuration**
On Android Studio, I choose to create an Android Virtual Device(AVD) with:
- name: appium
- Nexus 5
- Resolution: 1080x 1920
- API 28
- Target Pie Android 9.0 x86

On Appium Server GUI, I used the JSON on my configuration:
```
{
"platformName": "Android",
"appium:platformVersion": "9",
"appium:deviceName": "Android Emulator",
"appium:automationName": "UiAutomator2",
"appium:app": "https://github.com/cloudgrey-io/the-app/releases/download/v1.10.0/TheApp-v1.10.0.apk"
}
```

## **How to Use**
Open Android Studio and Run the AVD created, after that start the Appium server GUI and execute it with the JSON.

In Intellij, execute a scenario and watch the Android Device to see the automation Running.

To inspect Elements, open the Appium Inspector and click the elements you want to get the locators, it will show xpath, Accessibility ID, ID and so on.
**[TBD: insert an image for exemple]**

## **Contributing**
Please feel free to leave comments on my social media and email me to suggest improvements for this framework's performance and organization.

## **License**
Copyright © 2023 [Guilherme Granato](https://github.com/GfGranato) \
This project is MIT licensed

## **Author**
Guilherme Granato \
[![Linkedin](https://i.stack.imgur.com/gVE0j.png) LinkedIn](https://www.linkedin.com/in/guilherme-granato/)
[![GitHub](https://i.stack.imgur.com/tskMh.png) GitHub](https://github.com/GfGranato/)

## **Acknowledgments**
This project helped me to learn more about how to use cypress for E2E tests.
Feel free to use it in your own projects