# Oso - notifications

## class Notification

the Notification class is a static class.
this prepares the reminder that will cause the questionnaire to repeat every two weeks.

**init()**
registers an alarm with the android OS.
it is okay to run multiple times.
the alarm is set with an alarm manager and will use AlarmReceiver.


**create()**
posts a notification to the user. the notification press is handled by NotificationReceiver.

**getNextTime()**
gets the SharedPreferences hour of day, within the next 24 hour block.


## class BootReceiver

BootReceiver gets activated when android boots.
it sets an alarm for the next time to check if it's questionnaire time.


## class AlarmReceiver

AlarmReceiver is activated when the alarm goes off.
if it is time for that,


## class NotificationReceiver

This is activated when the user presses on a notification.  it sends an action to ChatActivity which will initiate the questionnaire
