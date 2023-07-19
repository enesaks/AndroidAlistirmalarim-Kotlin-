import java.util.HashMap
import java.util.StringJoiner



fun main(args: Array<String>) {

    println("Hello World!")

    //Veriables(Değişkenler)-Constans(Sabitler)

    var x = 5
    var y = 4
    println(x * y)

    var age = 35

    println(age / 7 * 5)

    age = 21
    println(age)

    val name = "enes"

    //name = "mehmet" val -> sabit

    //Defining
    var myInteger : Int

    //Initialize
    myInteger = 10
    //MyInteger = 10.5 veremeyiz!!

    //Double && Float

    val pi = 3.14 //Double
    val floatPi = 3.14f //Float

    //String

    var myString = "Hello Wolrd"
    var namee = "Enes"
    var surname = "Aksu"
    var fullname = namee + surname
    println(fullname)

    //Boolean

    var myBoolean = true


    //Conversion
    var number = 5

    var lonNumber = number.toLong()

    var input = "10"

    var inpoutINt = input.toInt()
    println(inpoutINt * 2)

    //Collections

    // Arrays

    val myArray = arrayOf("James","Kirk","Rob")
    println(myArray[0])

    val mynumberArray = arrayOf(1,2,3,5,4)

    val mixedArray = arrayOf(1,"bir")
    println(mixedArray[0])
    println(mixedArray[1])


    //ArrayList

    val myMusician = arrayListOf<String>("s","b")
    println(myMusician[0])
    myMusician.add("a")
    println(myMusician[2])
    myMusician.add(0,"c")
    println(myMusician[0])

    val myMixedArrayList = arrayListOf<Any>()
    myMixedArrayList.add("sdads")
    myMixedArrayList.add(2)

    //Set

    var myExxamplearray = arrayOf(1,1,2,3,4)

    println("First Element : ${myExxamplearray[0]}")
    println("Second Element : ${myExxamplearray[1]}")

    var mySet = setOf<Int>(1,1,2,3)
    println(mySet.size)

    //For Each
    mySet.forEach{ println(it) } //HEr bir değeri ekrana bastırır.


    //Map HahsMap

    //Key -Value
    val fruitHashMap = hashMapOf<String,Int>()
    fruitHashMap.put("banana",150)
    fruitHashMap.put("apple",100)

    println(fruitHashMap.get("apple"))

    val myHashMap = HashMap<String,String>()
    val myNewMap = hashMapOf<String,Int>("A" to 1,"B" to 2,"C" to 3)

    //Operators >,<,>=,<=,==,!=,&&,||

    //IF Else
    var myNumberAge = 54

    if (myNumberAge < 30){
        println("küçüktür 30 dan")
    }else if (myNumberAge >= 30 && myNumberAge < 40){
        println("30 la 40 arasında")
    }
    else {
        println("büyüktür 40 dan")
    }

    //Switch When
    var day = 3
    var dayString = "Mondey"

    when(day){
        1-> dayString = "Monday"

        2-> dayString = "Thuesday"

        3-> dayString = "Wednesday"

        else -> dayString =""
    }
    println(dayString)

    //Loops

    //For Loop

    var myArrayofnumber = arrayOf(1,2,3,4,5,6,7,8)

    for (number in myArrayofnumber){
        println(number)
    }

    for (i in myArrayofnumber.indices){
        println(myArrayofnumber[i])
    }

    for (b in 0..3){
        println(b)
    }

    //While Loop

    var j = 1

    while (j<=3){
        println(j)
        j++
    }



    //Function
    test()

    println(mySum(5,3))

}


//Function
fun test(){
    println("Test Function")
}

fun mySum(a : Int, b : Int) : Int{
   return a + b;
}