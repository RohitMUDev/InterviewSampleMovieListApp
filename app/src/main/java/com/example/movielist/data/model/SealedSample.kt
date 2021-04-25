package com.example.movielist.data.model

sealed class Employee

data class Manager(val name : String, val exp : String) : Employee()

data class Developer(val name :  String, val platform : String) :  Employee()
class Employees{

    var name : String  = ""
    get() {
        return field.toLowerCase()
    }

    set(value) {

        if(value.isEmpty()){
            field = "Not defined"
        }
    }


}

fun createDummy(){

    var empList :  ArrayList<Employee> = ArrayList()

    val manager1  : Manager = Manager(name = "test1", exp = "10")
    val manager2  : Manager = Manager(name = "test2", exp = "10")
    val developer1  : Developer = Developer(name = "test2", platform = "android")
    val developer2  : Developer = Developer(name = "test3", platform = "IOS")

    empList.add(manager1)
    empList.add(manager2)
    empList.add(developer1)
    empList.add(developer2)


}


fun checkItemType(empllist : List<Employee>){

  /*for(emp in empllist){

      when(emp){
          is Manager ->
          is Developer -> TODO()
      }
  }*/


}