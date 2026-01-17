package by.arsy.telegramdrivemessageconsumer

sealed class Command(val commandDescription: String) {

    val command: String
        get() = "/${commandDescription.lowercase()}"

    object OnLight : Command(commandDescription = "light_on")
    object OffLight : Command(commandDescription = "light_off")

}