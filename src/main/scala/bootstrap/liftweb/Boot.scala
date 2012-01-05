package bootstrap.liftweb

import net.liftweb._
import net.liftweb.mapper.{DB,Schemifier,DefaultConnectionIdentifier,StandardDBVendor,MapperRules}

import http.{S,LiftRules, NotFoundAsTemplate, ParsePath}
import sitemap.{SiteMap, Menu, Loc}
import util.{ NamedPF,Props }

import example.travel.model.{Auction,Supplier,Customer,Bid,Order,OrderAuction}

class Boot {
  def boot {
  
    
  
    // where to search snippet
    LiftRules.addToPackages("example.travel")

    DB.defineConnectionManager(DefaultConnectionIdentifier, DBVendor)

    object DBVendor extends StandardDBVendor(
      Props.get("db.class").openOr("org.h2.Driver"),
      Props.get("db.url").openOr("jdbc:h2:database/chapter_3"),
      Props.get("db.user"),
      Props.get("db.pass"))

    LiftRules.unloadHooks.append(
      () => DBVendor.closeAllConnections_!())
    S.addAround(DB.buildLoanWrapper)


    // build sitemap


    val sitemap = List(
      Menu("Home") / "index" >> Loc.LocGroup("public"),
      Menu("Admin") / "admin" / "index" >> Loc.LocGroup("admin"),
      Menu("Suppliers") / "admin" / "suppliers" >> Loc.LocGroup("admin")
        submenus(Supplier.menus : _*)
    ) ::: Customer.menus
    
    LiftRules.uriNotFound.prepend(NamedPF("404handler"){
      case (req,failure) => NotFoundAsTemplate(
        ParsePath(List("exceptions","404"),"html",false,false))
    })
    
    LiftRules.setSiteMap(SiteMap(sitemap:_*))
    
    // set character encoding
    LiftRules.early.append(_.setCharacterEncoding("UTF-8"))


    // automatically create the tables
    Schemifier.schemify(true, Schemifier.infoF _, Bid, Auction, Supplier, Customer, Order, OrderAuction)
    
    
    
  }
}