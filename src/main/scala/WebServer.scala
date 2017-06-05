import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.stream.ActorMaterializer
import akka.http.scaladsl.server.Directives._

object WebServer {
  def main(args: Array[String]) {

    implicit val system = ActorSystem("my-system")
    implicit val materializer = ActorMaterializer()
    // needed for the future flatMap/onComplete in the end
    implicit val executionContext = system.dispatcher

    val route =
      path("hello") {
        get {
          complete(HttpEntity(ContentTypes.`application/json`, "{\"hello\":\"worldVlad\"}"))
        }
      }

    val bindingFuture = Http().bindAndHandle(route, "0.0.0.0", 8080)
  }
}