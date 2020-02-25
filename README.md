# CMPUT301-Assignment1

## Learning Objectives
Solve a problem by constructing a simple, interactive application using Android and Java.
<br>Document an object-oriented design in Unified Modeling Language.

## Problem Description
Consider the situation of someone who needs to monitor their blood pressure and heart rate data. Make a simple, attractive, intuitive, Android mobile app to track this data. Let us call this app: CardioBook.

Specifically, each measurement has the following fields:

date measured (presented in yyyy-mm-dd format)
<br>time measured (presented in hh:mm format)
<br>systolic pressure in mm Hg (non-negative integer)
<br>diastolic pressure in mm Hg (non-negative integer)
<br>heart rate in beats per minute (non-negative integer)
<br>comment (textual, up to 20 characters)
<br>Only the comment field may be left blank for a measurement.

The app should allow the user to:

show a list of measurements
<br>add a new measurement (which always appends to the bottom end of the list)
<br>view and edit the details of an existing measurement
<br>delete a measurement
<br>see unusual blood pressures highlighted or flagged
<br>Normal pressures are systolic between 90 and 140 and diastolic between 60 and 90.

The list need not show all the information for a measurement if space is limited. Minimally, each record in the list should show the date, systolic pressure, diastolic pressure, and heart rate.

The app must assist the user in proper data entry. For example, use appropriate user interface controls to enforce particular data types and avoid illegal values.

The app must be persistent. That is, exiting and fully stopping the app should not lose data.

## Deliverables
### Code Base:
Your complete source code and compiled binary, implementing the working app and its user interface, will be inspected and run by the TA. The Android Studio project and APK (Android Package Kit) binary must be included in the submission. Each class must contain comments describing its purpose, design rationale, and any outstanding issues.

### Video:
The video is a demonstration of the app. The video file must be included in the submission. The video is meant to show that the demonstration actions below actually work. No audio is needed. Maximum duration is 3 minutes. You may use the screen recording software in the labs (e.g., ffmpeg or simplescreenrecorder). Focus on just the screen of the app, not the whole desktop. For visual clarity, do not use a handheld camera.

### System Documentation:
Describe the structure of your app's object-oriented design using UML class diagram(s), saved as non-lossy image file(s). Focus on the most important classes that you designed and implemented. Add notes to describe the main responsibilities of these classes.ks)

## Demonstration Actions
Open the app from the launcher.
<br>Show the list of measurements, with no measurements so far. (This should be the initial screen.)
<br>Add a measurement with date 2020-02-01, time 22:00, systolic 126, diastolic 62, heart rate 52, and no comment.
<br>Show the list, with this measurement.
<br>View/edit this measurement to be systolic 106, and comment "resting".
<br>Show the list, with this updated measurement.
<br>Add a measurement with date 2020-02-02, time 23:00, systolic 85, diastolic 49, heart rate 60, comment "sitting"
<br>Show the list, with the two measurements.
<br>Add a measurement with date 2020-02-03, time 19:30, systolic 97, diastolic 63, heart rate 51, comment "laying"
<br>Show the list, with the three measurements.
<br>Delete the measurement dated 2020-02-02.
<br>Show the list, with the two remaining measurements.
<br>Exit and stop the app, showing in the running apps list that the app is no longer running.
<br>Open the app again.
<br>Show the list, with the two measurements.
<br>View the details of the 2020-02-01 measurement.
<br>View the details of the 2020-02-03 measurement.
