package ru.netology.nmedia.dto

class ThousandView (val int:Int){
    var text: String = "0"
    var number: Int = int
    public fun getThousand()  {

        if(number <= 999){
            this.text  = number.toString()
        }
        else if(number in 1000..1099){
            this.text  = "1.0K"
        }else if(number in 1100..9999){
            val intPartNum:Int = number/1000
            val frPartNum:Int = (number%1000)/100
            this.text  = "${intPartNum}.${frPartNum}K"
        }
        else if(number in 10000..999999){
            val intPartNum:Int = number/1000
            this.text  = "${intPartNum}.0K"
        }
        else if(number in 1000000..Int.MAX_VALUE){
            val intPartNum:Int = number/1000000
            val frPartNum:Int = (number%1000000)/100000
            this.text  = "${intPartNum}.${frPartNum}M"
        }
    }
    operator fun inc():ThousandView{
        this.number++
        return this
    }
    operator fun dec():ThousandView{
        this.number--
        return this
    }
    override fun toString(): String {
        getThousand()
        return this.text
    }


}