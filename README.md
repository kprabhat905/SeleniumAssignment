# SeleniumAssignment

For this assignment POM framework in selenium using Java as scripting language. Maven is used for dependency management and continuous development. TestNG is used to maintain test cases.

#Table of Content
1. Overview
2. Pre-requisites
3. Folder Structure
4. Execution steps
5. Key notes

OVERVIEW
--------------
SELENIUM is a free (open-source) automated testing framework used to validate web applications across different browsers and platforms.
TestNG is an automation testing framework in which NG stands for "Next Generation". TestNG is inspired from JUnit which uses the annotations (@). TestNG overcomes the disadvantages of JUnit and is designed to make end-to-end testing easy.

Maven is a powerful project / build management tool, based on the concept of a POM (Project Object Model) that includes project information and configuration information for Maven such as construction directory, source directory, dependency, test source directory, Goals, plugins, etc.

PRE-REQUISITES
--------------------
1. Check your system to see if you have the latest Java version installed. Also, ensure your JAVA_HOME environment to the location of the installed JDK.
Command: java -version

2. Maven 
a. Setting up Maven, 
	- Download Maven https://maven.apache.org/download.cgi.

b. Maven Installation
	- Unzip the distribution archive to the directory you wish to install Maven. I extracted maven to my Documents folder
	- Add Maven to the PATH.

c. Verify Maven was correctly installed.
Command: mvn –version

EXECUTION STEPS
---------------
1. Go to project path in terminal. For example: C:\Users\User-Name\eclipse-workspace\E2EUserJourney>
2. Run mvn clean
3. Run mvn compile
4. Run mvn test

KEY NOTES
-----------
1. Cross browser testing can be done by updating the testng.xml.
2. Extent reports are present in TestReports - Report directory after the execution
3. Earlier executions zip is present in TestReports - Archive directory after the execution
4. All the Selectors are present in AutomationXML-ObjectRepository.xml directory
5. Resources contains driver's exe and config file
