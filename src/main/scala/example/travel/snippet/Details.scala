package example.travel.snippet
import net.liftweb.util.Helpers._
import example.travel.lib.AuctionHelpers
import xml.Text
import example.travel.model.Auction
import net.liftweb.mapper.By
import net.liftweb.http.{S, StatefulSnippet}

class Details extends StatefulSnippet with AuctionHelpers {
  override def dispatch = {
    case "show" => show
    case "bid" => bid
  }


  val auction = Auction.find(
    By(Auction.id,S.param("id").map(
      _.toLong).openOr(0L)))

  def show = auction.map {
    single(_) &
      "#current_amount" #>
        <span>leadingBid.toString</span> &
      "#next_amount" #> <span>minimumBid.toString</span>
  } openOr("*" #> "That auction does not exist.")



  def bid = "*" #> Text("Not implemented")
}