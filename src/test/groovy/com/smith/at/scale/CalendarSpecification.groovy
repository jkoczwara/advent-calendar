package com.smith.at.scale

import spock.lang.Specification

class CalendarSpecification extends Specification {

    String readFromFile(String fileName) {
        def resource = this.getClass()
                .getResource(fileName)
        if (resource == null) throw new IllegalArgumentException("File with name '$fileName' does not exists!")

        resource.text
    }

}
