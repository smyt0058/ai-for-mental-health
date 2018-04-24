# Oso
## notifications

### class Notification

the Notification class is a static class to handle various intent functions
there are receivers that will use it.


### class BootReceiver

BootReceiver gets activated when android boots.
it sets an alarm for the next time to check if it's questionnaire time.

    Notification.init()

this will also be called when ChatActivity opens so it must be
okay to run multiple times.

### class AlarmReceiver

AlarmReceiver receives the alarm set with the alarm manager.
if it is time for that,
if the chat activity is not running it creates a notification for the user.

    Notification.create()


the sequence of conditionals to go through when an alarm is received:

    is chat activity open?

    yes so initiate server questionnaire
        if online - initiate questionnaire (after saving context of current chat)
        if offline - show that, set alarm for next day
        set alarm for two weeks (or tomorrow, if that logic will test recency of questionnaire completion)

    no. chat activity not open
        if online - post notification
        if offline - post notification with: "next time you are online... "

        if notification accepted
            open chat activity
            initiate questionnaire with same as above
            (same as above, with offline check and reset alarm)

        if notification gets cancelled
            set alarm for tomorrow

