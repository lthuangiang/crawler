package com.minhhop.crawler


import groovy.transform.ToString

enum EventLevel {DEBUG,INFO,WARN,ERROR}

@ToString(includeNames = true)
class EventLog {
    Date date
    EventLevel level
    String msg
    @Override
    String toString() {
        return "${date.format("MM/dd/yyyy HH:mm:ss")} : $level - ${msg}"
    }
}
