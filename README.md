# DeviceIDUtil
Util class to generate unique ID for android device.

This is a simple application that implement a method to generate a unique ID in android device.
Since Android 10, Google has restricted application to get IMEI device, unless your apps has a special carrier permission.
otherwise you'll get java.lang.SecurityException error.

So this is an alternative solution if your app is using getDeviceId.
