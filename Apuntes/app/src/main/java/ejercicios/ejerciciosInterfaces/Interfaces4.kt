package ejerciciosInterfaces

interface PersonaCliente{
    val nombre:String
    val edad:Int

    fun presentarse(){
        println("Hola, soy $nombre y tengo $edad años")
    }
}
interface Cliente{
    val numeroCliente:Int

    fun realizarCompra()
}
class ClientePremium(
    override val nombre:String,
    override val edad:Int,
    override val numeroCliente:Int
):PersonaCliente, Cliente{
    override fun presentarse() {
        println("Cliente $numeroCliente está realizando una compra")
        println("Soy un cliente premium")
    }

    override fun realizarCompra() {
        println("Cliente $numeroCliente ha realizado su compra")
    }
}

fun main(){
    val clientePremium=ClientePremium("Carlos",30,12345)
    clientePremium.presentarse()
    clientePremium.realizarCompra()

    println()

    val clientePremium2=ClientePremium("José",25,123245)
    clientePremium2.presentarse()
    clientePremium2.realizarCompra()
}