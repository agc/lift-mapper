package example.travel.lib


import net.liftweb._,
common.{Full,Box,Loggable},
textile.TextileParser
import example.travel.model.Auction
import xml.NodeSeq
import xml.Text

import net.liftweb.util.Helpers._     // necesario para #>


trait AuctionHelpers extends Loggable {

  protected def many(auctions: List[Auction]) = auctions.map(a => single(a))

  protected def single(auction: Auction) =
    ".name *" #> auction.name &
      ".desc"   #> TextileParser.toHtml(auction.description) &
      "#winning_customer *" #> winningCustomer(auction) &
      "#travel_dates" #> auction.travelDates &
      "a [href]"      #> "/auction/%s".format(auction.id.toString)


  protected def winningCustomer(a: Box[Auction]): NodeSeq =
    Text(a.flatMap(_.winningCustomer.map(_.shortName)).openOr("Unknown"))

  protected def winningCustomer(a: Auction): NodeSeq =
    winningCustomer(Full(a))

  /*
  protected def leadingBid(a: Box[Auction]): Double =
    a.flatMap(_.currentAmount).openOr(0D)

  protected def minimumBid(a: Box[Auction]): Double =
    a.flatMap(_.nextAmount).openOr(0D)
    */

}
