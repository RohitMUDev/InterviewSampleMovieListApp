package com.example.movielist.util

class SampleFoodItems(builder : Builder) {




       class Builder{


           private var name : String? = null
           private var type : String? = null

           fun setName(name : String) :  Builder{

               this.name  = name;
               return  this
           }


           fun setType(type : String) : Builder{
               this.type = type
               return this
           }


           fun create() : SampleFoodItems{
               return SampleFoodItems(this)
           }


           fun getName() : String?{
               return this.name
           }


           fun getType() :  String?{
               return  this.type
           }

       }

}