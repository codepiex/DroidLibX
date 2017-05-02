DroidLibX
=========

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.codepiex.droidlibx/droidlibx/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.codepiex.droidlibx/droidlibx)
[ ![Download](https://api.bintray.com/packages/eeshan-jamal/DroidMaven/droid-libx/images/download.svg) ](https://bintray.com/eeshan-jamal/DroidMaven/droid-libx/_latestVersion)


This project is intended for providing easy to use frameworks which makes the integration of Android's New/Existing features very fast with less code. 

### Featured API's

 - **PermissionRequestor:**  This class provides simple methods to request permission which require user approval before app use them. Just key-in the required arguments for the method including a listener which will respond based on user decision. **Note-** You must called onRequestPermissionsResult method of the PermissionRequestor instance from the Activity (in which you are using it) onRequestPermissionsResult callback in-order to receive listener callback. 
 
 ### Download

[Latest .aar file](https://repository.sonatype.org/service/local/artifact/maven/redirect?r=central-proxy&g=com.codepiex.droidlibx&a=droidlibx&p=aar&v=LATEST)

Maven:

```
<dependency>
  <groupId>com.codepiex.droidlibx</groupId>
  <artifactId>droidlibx</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>
```

Gradle:

```
compile 'com.codepiex.droidlibx:droidlibx:1.0.0'
```

### License

DroidLibX is released under the [Apache 2.0 license](LICENSE).
```
Copyright 2017 Eeshan Jamal

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
