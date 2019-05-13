# Hello Sensor - GYROLITE
Individual school project for MAMN01 - Advanced Interaction Design Hello Sensor. This app is called GYROLITE, it has the functions compass and accelerometer.


## Code review
This is what adjusments where made to existing code.

### Compass code
* Most of the basic code for the functionality of the compass has been retrieved from here: [Compass Code](https://www.wlsdevelop.com/index.php/en/blog?option=com_content&view=article&id=38)
* Code for accerelometer was not used.
* A low-pass filter was used from here: [Low-Pass filter](https://www.built.io/blog/applying-low-pass-filter-to-android-sensor-s-readings)
* Code was added for users to add their own degree input with error feedback if user tries to write outside the interval 0-360, also it was not possible to have any other input than numbers.
* I adjusted the code in order to add vibration for a set degree +/- 15 degrees. Also I added so the vibration won't repeat untill the user has moved the compass away from the set degree. As default the degree was set to 360, North.

### Accerelometer code
* Most of the basic code for the functionality of the compass has been retrieved from here: [Accerelometer Code](https://developer.android.com/reference/android/hardware/SensorManager.html)
* I adjusted the code such that a small notification would pop up when the phone was in a direction with the screen towards the users face.
* A low-pass filter was used from here: [Low-Pass filter](https://www.built.io/blog/applying-low-pass-filter-to-android-sensor-s-readings)

## GYROLITE preview
<p style="text-align:center;"><img src="https://github.com/obakanue/gyrolite-hello-sensor/blob/master/Pictures/Skärmavbild%202019-04-10%20kl.%2000.54.40.png?raw=true"  width=" 40%" height=" 40%"</p>
<p style="text-align:center;"><img src="https://github.com/obakanue/gyrolite-hello-sensor/blob/master/Pictures/Skärmavbild%202019-04-10%20kl.%2000.55.12.png?raw=true" width=" 40%" height=" 40%"</p>
<p style="text-align:center;"><img src="https://github.com/obakanue/gyrolite-hello-sensor/blob/master/Pictures/Skärmavbild%202019-04-10%20kl.%2000.57.04.png?raw=true" width=" 40%" height=" 40%"</p>
<p style="text-align:center;"><img src="https://github.com/obakanue/gyrolite-hello-sensor/blob/master/Pictures/Skärmavbild%202019-04-10%20kl.%2000.57.18.png?raw=true" width=" 40%" height=" 40%"></p>
