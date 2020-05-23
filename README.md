# AQI-project



Maven AQI Calculator
Maven Central Build Status CodeFactor BCH compliance

Are you also looking for Node version ? npm version

Foreword
All formulas, constraints, and messages use in this project follows US EPA AQI Calculation Guideline and Regulations (which includes breakpoint table and calculation formulas) . Since each country issues different AQI calculation methods, using this package might be inappropriate, consider your usage.

This project is intended to community target for free use. The author is not associated with USA government, nor United States Environmental Protection Agency (a.k.a US EPA)

Highlights
Calculate AQI from raw concentration
The result from calculation includes Air Quality Index, Category, Color, Sensitive Groups of the pollutant and the corresponding Health Effects Statements and Cautionary Statements messages.
Support NowCast Concentration
You need to get messages in another language ? AQI Calculator now allows customizable messages at your own need.
Support The Following Pollutants
Pollutant	Scientific name	Unit of Measurement	Sensitive Groups	Regular Calculation Support	NowCast Support	Health Effects Statements	Cautionary Statements
PM10	10 μm Particle Pollutant	μg/m3	✔️	✔️	✔️	✔️	✔️
PM2.5	2.5 μm Particle Pollutant	μg/m3	✔️	✔️	✔️	✔️	✔️
O3	Ozone	ppb	✔️	✔️	✔️	✔️	✔️
CO	Carbon Monoxide	ppb	✔️	✔️	❌	✔️	✔️
SO2	Sulfur Dioxide	ppb	✔️	✔️	❌	✔️	✔️
NO2	Nitrogen Dioxide	ppb	✔️	✔️	❌	✔️	✔️
Installation
Using Maven Dependency
<!-- https://mvnrepository.com/artifact/com.github.thanglequoc/aqi-calculator -->
<dependency>
    <groupId>com.github.thanglequoc</groupId>
    <artifactId>aqi-calculator</artifactId>
    <version>1.3.1</version>
</dependency>
Or for other build tools like Gradle, SBT, Ivy,.. you may find it on Maven Central Repository

Using Plug-n-play jar file:
Grab the target jar in target-jar folder and add the jar to your project.

Quick Usage
For Regular AQI Calculation
Using AQIResult Object:
AQICalculator calculator = AQICalculator.getAQICalculatorInstance();
AQIResult result = calculator.getAQI(Pollutant.PM10, 99);
Now AQIResult store all the related information that you might need, query them by the following methods

The Air Quality Index (AQI)
result.getAQI();

73

The Concentration result.getConcentration();
For NowCast calculation, this will be the NowCast Concentration

99.0

The AQI Category result.getCategory();
Moderate

The color of this index result.getColor()
Yellow

The meaning of of this index result.getMeaning()
Air quality is acceptable; however, for some pollutants there may be a moderate health concern for a very small number of people who are unusually sensitive to air pollution

The sensitive groups of this pollutant result.getSensitiveGroups()
People with respiratory disease are the group most at risk

The health effects statements result.getHealthEffectsStatements();
Respiratory symptoms possible in unusually sensitive individuals; possible aggravation of heart or lung disease in people with cardiopulmonary disease and older adults

The cautionary statements
result.getCautionaryStatements();

Unusually sensitive people should consider reducing prolonged or heavy exertion

For NowCast AQI Calculation
/* Example Data for NowCast PM10 12h period - 64, 63, 72, 77, 65, 61, 70, 71, 64, 57, 58, 64 */
AQICalculator calculator = AQICalculator.getAQICalculatorInstance();

double[] data = { 64, 63, 72, 77, 65, 61, 70, 71, 64, 57, 58, 64 };
AQIResult result = calculator.getNowCastAQI(Pollutant.PM10, data);
System.out.println(result.getAQI());
57

The first value in the array is the avg value in the current hour, and each next element in the array represent the previous data of the hour before current hour.

If the hour doesn't have data, replace missing data in the hour with -1

Example NowCast Dataset for PM10: (have some missing data in hour)
Hour	Avg Concentration
14	64 ppb
13	63 ppb
12	----
11	77 ppb
10	65 ppb
9	----
8	70 ppb
7	71 ppb
6	----
5	57 ppb
4	58 ppb
3	64 ppb
Presume that you want to calculate NowCast AQI for PM10 at 14, the data array should be

double[] data = { 64, 63, -1, 77, 65, -1, 70, 71, -1, 57, 58, 64 };`
AQIResult result = calculator.getNowCastAQI(Pollutant.PM10, data);
result.getAQI();
56

Customize AQI Messages
The default text from AQI result is in English. However, you can easily override these messages in your own language. Just download the template files and make change on your own

Allowed customized messages resources:

General Messages
Sensitive Groups
Specific Messages
Example: Override with custom AQI message in German language. Simply done by enable custom message mode, and provide any of the override files path from your project classpath. Sample Settings

AQICalculator calculator = AQICalculator.getAQICalculatorInstance();

AQICustomSettings mySettings = new AQICustomSettings()
	.withCustomMessagesMode(true)
	.withGeneralMessageResourcePath("AQIResource/custom-aqi-general-messages_de.json")
	.withSensitiveGroupsResourcePath("AQIResource/custom-aqi-sensitive-groups_de.json")
	.withSpecificMessageResourcePath("AQIResource/custom-aqi-specific-messages_de.json");
calculator.applyCustomSettings(mySettings);

AQIResult aqiResult = calculator.getAQI(Pollutant.PM10, 99);
System.out.println(aqiResult.getCategory());
System.out.println(aqiResult.getMeaning());
System.out.println(aqiResult.getSensitiveGroups());
System.out.println(aqiResult.getHealthEffectsStatements());
System.out.println(aqiResult.getCautionaryStatements());
Outcome

Mäßig
Luftqualität ist akzeptabel; Bei einigen Schadstoffen kann es jedoch zu einer mäßigen Gesundheitsgefährdung für eine sehr kleine Anzahl von Personen kommen, die ungewöhnlich empfindlich auf Luftverschmutzung reagieren
Am stärksten gefährdet sind Menschen mit Atemwegserkrankungen
Atmungssymptome bei ungewöhnlich empfindlichen Personen möglich; Mögliche Verschlimmerung von Herz- oder Lungenerkrankungen bei Personen mit kardiopulmonaler Erkrankung und älteren Erwachsenen
Ungewöhnlich sensible Menschen sollten in Betracht ziehen, längere oder schwere Belastungen zu reduzieren
I'm not familiar with German, these messages is just the copy-paste result from Google Translate 🐑

General Messages
General Messages contains the category, meaning and color

[{
  "index": {
    "min": 0,
    "max": 50
  },
  "category": "<<Text in your language>>",
  "meaning": "<<Text in your language>>",
  "color": "<<Text in your language>>"
}] 
Download General Messages Resource File and custom it on your own. Then set this file path with withGeneralMessageResourcePath(<path_to_your_file>)

AQICustomSettings mySettings = new AQICustomSettings()
	.withCustomMessagesMode(true)
	.withGeneralMessageResourcePath("AQIResource/custom-aqi-general-messages_de.json");
calculator.applyCustomSettings(mySettings);
Sensitive Groups
Sensitive Groups File contains list of pollutant's sensitive groups

[{
    "code": "PM2.5",
    "sensitiveGroups": "<<Text in your language>>"
}]
Download Sensitive Groups Resource File and custom it on your own. Then set this file path with withSensitiveGroupsResourcePath(<path_to_your_file>)

AQICustomSettings mySettings = new AQICustomSettings()
	.withCustomMessagesMode(true)
	.withSensitiveGroupsResourcePath("AQIResource/custom-aqi-sensitive-groups_de.json");
calculator.applyCustomSettings(mySettings);
Specific Messages
Specific Messages contains Health Effects Statements and Cautionary Statements for each pollutant and each specific quality level

[{
    "code": "PM2.5",
    "aqiLevel": [{
        "index": {
            "min": 0,
            "max": 50
        },
        "category": "<<Text in your own language>>",
        "healthEffectsStatements": "<<Text in your own language>>",
        "cautionaryStatements": "<<Text in your own language>>"
    }]
}]
Download Specific Messages Resource File and custom it on your own. Then set this file path with withSpecificMessageResourcePath(<path_to_your_file>)

AQICustomSettings mySettings = new AQICustomSettings()
	.withCustomMessagesMode(true)
	.withSpecificMessageResourcePath("AQIResource/custom-aqi-specific-messages_de.json");
calculator.applyCustomSettings(mySettings);
Reset To Default Settings
Reset the calculator to default messages path settings with calculator.resetDefaultSettings();

AQI Calculation Tutorial
US EPA AQI Breakpoint
2017_07_20_15_22_21

Image from Wikipedia
Calculation Formula
The AQI is the highest value calculated for each pollutant as follows:

Identify the highest concentration among all of the monitors within each reporting area and truncate as follows:
2017_07_20_15_27_19

Using US EPA AQI Breakpoint, find the two breakpoints that contain the concentration.
Using AQI Equation , calculate the index.
Round the index to the nearest integer.  
2017_07_20_15_25_59

NowCast for PM and Ozone
The concentration of PM10, PM2.5 is so dynamic since wind can completely clean the air in less than 30 minutes, or a wildfire can raise the concentration with a very fast rate in an hour. So NowCast is introduced, it mainly focus on detect the average changing of the period hour and perform counter balancing.

2017_07_20_15_30_08

NowCast Rules
image

Handling Missing data
To compute a valid NowCast, you must have at least two of the most recent 3 hours

image

Extra Documents and Tools that you might needs
Air Quality Index (AQI) Basics

Air Now AQI Calculator: Concentration to AQI

Air Now NowCast Calculator

Daily and Hourly AQI - PM2.5 and PM10

Daily and Hourly AQI - Ozone

US EPA AQI Brochure

US EPA AQI Technical Assistance Document. I am have no idea why the section Health Effects Statements section is not presented in their latest document guideline. I managed to kept the one in 2016 that have this section

US EPA NowCast Overview

Demonstration images for NowCast in this tutorial are from US EPA NowCast Overview document
Contributors & Supports
Without these great contribution from these guys, this repository might not be as it is today. I would like to express my gratitude to

Marcus Hylton - US EPA Airnow Specialist
Code Contributors

Proudly powered by IntelliJ IDEA from JetBrains for open source project



