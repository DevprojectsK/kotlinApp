package koxcom.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import koxcom.models.ErrorMessage
import koxcom.models.User

//private  val users = arrayListOf<User>()

private  val users = mutableListOf(
    User(1, "Jeiner Casrto", 25, "tecjeiner@gmail.com"),
    User(2, "Cristaian Casrto", 9, "cris@gmail.com"),
)

private val message = ErrorMessage("No se encontro el registro")

fun Route.userRouting(){
    route("/user"){
        get {
            if (users.isNotEmpty()){
                call.respond(users)
            }else{
                call.respond(message)
            }
        }
        get ("{id?}"){
            val id = call.parameters["id"] ?: return@get call.respondText (
                "Id no encontrado",
                status = HttpStatusCode.BadRequest
            )

            val  user = users.find { it.id == id.toInt() } ?: return@get call.respondText(
                "Usuario no encontrado",
                status = HttpStatusCode.NotFound
            )

            call.respond(user)

        }
        post {
            val user = call.receive<User>()
            users.add(user)
            call.respondText(
                "Usuario creado correctamente",
                status = HttpStatusCode.Created
            )
        }

        delete ("{id?}") {
            val id = call.parameters["id"] ?: return@delete call.respondText (
                "Id no encontrado",
                status = HttpStatusCode.BadRequest
            )

            if (users.removeIf{it.id == id.toInt()}){
                call.respondText(
                    "usuario eliminado correctamente",
                    status = HttpStatusCode.Accepted
                )
            }else {
                call.respondText(
                    "Usuario no encontrado",
                    status = HttpStatusCode.NotFound
                )
            }
        }
    }
}