package koxcom.models
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id:Int,
    val name:String,
    val age:Int,
    val email:String
)


@Serializable
data class ErrorMessage(
    val message: String
)