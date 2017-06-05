package model

import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape

import scala.concurrent.Future


case class Order(id: Long, description: String)

class OrderTable(tag: Tag) extends Table[Order](tag, "orders") {
  def id : Rep[Long] = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def description : Rep[String] = column[String]("description")


  override def * : ProvenShape[Order] = (id, description) <> (Order.tupled, Order.unapply)
}

