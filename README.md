<p> 
  <img src="./screenshots/cover.jpg" alt="cover">
</p>

[![Maven Central](https://img.shields.io/maven-central/v/io.github.chouaibmo/row-calendar.svg?color=blue)](https://search.maven.org/artifact/io.github.chouaibmo/row-calendar)
## 💡 Description

RowCalendar is a Compose Multiplatform library designed to offer a straightforward and user-friendly 
scrollable horizontal calendar component for both Android and iOS platforms.

## 📱 Supported platforms
this project supports the following platforms:
* Android
* iOS

Support for Browser and Desktop (Linux, macOS, Windows) is planned for future releases.
Please note that the library is under active development, and some features may not be available across all platforms.

## ⚙️ Setup
To integrate RowCalendar into your project, add the following dependencies to the commonMain source set of your shared module:

```kotlin
commonMain.dependencies {
    // Other dependencies ...
    implementation("io.github.chouaibmo:row-calendar:<latest_version>")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:<latest_version>")
}
```
**Note**: RowCalendar utilizes the `kotlinx-datetime` library, which must also be included in your shared module dependencies.



## 📱 Sample usage
### Basic usage
The following code snippet shows how to use the `RowCalendar` composable to display a simple row calendar 
by making use of the `DayCell` composable provided by the library to display each day in the calendar.
```kotlin
import io.chouaibmo.horizontalcalendar.RowCalendar
import io.chouaibmo.horizontalcalendar.components.DayCell

@Composable
fun RowCalendarSample() {
    RowCalendar(
        modifier = Modifier.height(100.dp),
        content = { date, isSelected, onClick ->
            DayCell(
                date = date,
                isSelected = isSelected,
                onClick = onClick,
                shape = RoundedCornerShape(20),
                modifier = Modifier.padding(6.dp)
            )

        }
    )
}
```

### Customization
If you prefer a more tailored approach that better aligns with your app's design and user experience, 
you can provide your own composable as shown in the following code snippet:
```kotlin
import io.chouaibmo.horizontalcalendar.RowCalendar

@Composable
fun RowCalendarSample() {
    RowCalendar(
        modifier = Modifier.height(100.dp),
        content = { date, isSelected, onClick ->
            // Your own custom composable
        }
    )
}
```

## 📸 Screenshots
<p>
  <img src="./screenshots/screenshot1.png" width="32%" alt="screenshot1">
  <img src="./screenshots/screenshot2.png" width="32%" alt="screenshot2">
  <img src="./screenshots/screenshot3.png" width="32%" alt="screenshot3">
</p>


## 🤝 Contribution

If you wish to contribute, please feel free to submit pull requests or issues to help improve RowCalendar.

## 💙 Find this repository useful?
If you find this library useful, please consider starring the repository and sharing it with others :star:

# License
```xml
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

