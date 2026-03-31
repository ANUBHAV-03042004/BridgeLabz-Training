**Step-by-step procedure followed (Implementation Flow)**



* Converted Eclipse project to Maven



* Added JaCoCo Maven plugin



* Configured SonarQube properties in pom.xml



* Generated coverage via mvn verify



* Uploaded analysis via sonar:sonar



* Verified reports on SonarQube dashboard



* Re-ran analysis after code/test changes



* Observed coverage trend improvement





**From my JaCoCo report:**



* Instruction Coverage: 68%



* Branch Coverage: 39%



* Classes: 4



* Methods: 67



* Lines: 247



* Module: addressbook



**Classroom methodology / approach used Approach followed:**



* Test-first + Static Analysis driven development



* Write core functionality



* Add unit tests incrementally



* Measure coverage using JaCoCo



* Analyze quality using SonarQube



* Fix issues based on feedback



* Re-analyze and improve metrics
