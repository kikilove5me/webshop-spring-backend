package kotlinTest

data class User (val user: String){
    init {
        require(checkName(user)) {"invalid name: "}
    }

    private fun checkName(user: String):Boolean {
        return user.matches(Regex("\\d\\w+"))
    }

}

fun main() {
    val user1 = User("dda")

}