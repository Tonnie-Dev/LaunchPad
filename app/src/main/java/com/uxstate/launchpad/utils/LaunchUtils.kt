package com.uxstate.launchpad.utils

import com.uxstate.launchpad.domain.model.Launch
import com.uxstate.launchpad.domain.model.Mission
import com.uxstate.launchpad.domain.model.Pad
import com.uxstate.launchpad.domain.model.Provider
import com.uxstate.launchpad.domain.model.Rocket
import com.uxstate.launchpad.domain.model.Status
import java.util.Date


fun generateRandomIntId(): Int {

    return (100..999).random()
}

fun generateLoremIpsum(wordCount: Int): String {
    val loremText = listOf(
            "lorem", "ipsum", "dolor", "sit", "amet", "consectetur", "adipiscing", "elit",
            "sed", "do", "eiusmod", "tempor", "incididunt", "ut", "labore", "et", "dolore",
            "magna", "aliqua", "ut", "enim", "ad", "minim", "veniam", "quis", "nostrud",
            "exercitation", "ullamco", "laboris", "nisi", "ut", "aliquip", "ex", "ea", "commodo",
            "consequat", "duis", "aute", "irure", "dolor", "in", "reprehenderit", "in", "voluptate",
            "velit", "esse", "cillum", "dolore", "eu", "fugiat", "nulla", "pariatur", "excepteur",
            "sint", "occaecat", "cupidatat", "non", "proident", "sunt", "in", "culpa", "qui",
            "officia", "deserunt", "mollit", "anim", "id", "est", "laborum"
    )

    return (1..wordCount).joinToString(" ") { loremText.random() }
}


fun generateLaunch():Launch {

    return   Launch(
            id = generateRandomIntId(),
            name = "${generateLoremIpsum(2)} ${generateRandomIntId()}",
            mission = Mission(
                    name = "My Mission",
                    description = generateLoremIpsum(30),
                    type = ""
            ),
            imageUrl = "android.resource://",
            provider = Provider(id = generateRandomIntId(), name = "", type = ""),
            status = Status(name = "Name", abbrev = "TBD", description = ""),
            pad = Pad(
                    locationName = generateLoremIpsum(2),
                    latitude = "",
                    longitude = "", complex = "",
                    totalLaunchCount = 0,
                    totalLandingCount = 0,

                    ),
            startWindowDate = Date().getCurrentDateTime(),
            rocket = Rocket(name = "Rocket ${generateRandomIntId()}", family = generateLoremIpsum(1))
    )

}

fun generateLaunches(count:Int = 10):List<Launch>{

    return buildList {


        repeat(count){

            add(generateLaunch())
        }
    }


}

