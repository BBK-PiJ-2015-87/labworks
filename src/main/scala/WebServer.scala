import akka.Done
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.stream.ActorMaterializer
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import model.{Order, OrderTable}
import spray.json.DefaultJsonProtocol._

import scala.concurrent.Future
import slick.jdbc.PostgresProfile.api._
import slick.lifted.TableQuery

object WebServer {

  val orders = TableQuery[OrderTable]

  val db = Database.forConfig("pgdb")

  def fetchOrders(): Future[Seq[Order]] = db.run(orders.result)

  def fetchOrder(orderId: Long): Future[Option[Order]] = db.run(orders.filter(_.id === orderId).result.headOption)

  def saveOrder(order: Order): Future[Done] = ???

  implicit val itemFormat = jsonFormat2(Order)


  def main(args: Array[String]) {
    implicit val system = ActorSystem("my-system")
    implicit val materializer = ActorMaterializer()
    // needed for the future flatMap/onComplete in the end
    implicit val executionContext = system.dispatcher

    val route: Route =
      get {
        pathSingleSlash {
          complete(Order(1, "order numero one"))
        } ~
        pathPrefix("orders") {
          complete(fetchOrders())
        } ~
        pathPrefix("order" / LongNumber) { id =>
          val maybeOrder: Future[Option[Order]] = fetchOrder(id)

          onSuccess(maybeOrder) {
            case Some(order) => complete(order)
            case None       => complete(StatusCodes.NotFound)
          }
        }
      } ~
        post {
          path("order") {
            entity(as[Order]) { order =>
              val saved: Future[Done] = saveOrder(order)
              onComplete(saved) { done =>
                complete("order created")
              }
            }
          }
        }

    val bindingFuture = Http().bindAndHandle(route, "0.0.0.0", 8080)

    bindingFuture.failed.foreach { ex =>
      println(ex)
    }
  }
}