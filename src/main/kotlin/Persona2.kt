/*Actualizar el ejercicio 4.2 para añadir a la clase Persona el siguiente comportamiento:

Debe retornar un saludo con su nombre... saludar():String
Debe retornar si altura por encima de la media (solo si altura >= 1.75)... alturaEncimaMedia():Boolean
Debe retornar si peso por encima de la media (solo si peso >= 70)... pesoEncimaMedia():Boolean
Sería conveniente añadir también un método obtenerDescImc() para usar en obtenerDesc(), que implemente el retorno de la descripción del rango de tipo de imc al que equivale su cálculo.
Nota: (Mejora: Enum class en https://www.baeldung.com/kotlin/enum) * Si el IMC es menos de 18.5, se encuentra dentro del rango de "peso insuficiente". * Si el IMC está entre 18.5 y 24.9, se encuentra dentro del rango de "peso saludable". * Si el IMC está entre 25.0 y 29.9, se encuentra dentro del rango de "sobrepeso". * Si el IMC es 30.0 o superior, se encuentra dentro del rango de "obesidad".

Debe implementar también un método que muestre una descripción completa de la persona... obtenerDesc():String. Por ejemplo, este método mostrará por pantalla algo así:

"Julia con una altura de 1.72m (Por debajo de la media) y un peso 64.7kg (Por encima de la media) tiene un IMC de 21,87 (peso saludable)".
2. Crear en el main() una estructura de datos con 4 o 5 personas más, recorrer la estructura y por cada persona debe saludar y mostrar su descripción completa.
Finalmente, revisa el IDE e intenta actualizar el modificador de visibilidad de los métodos de tu clase cómo os estará indicando... veréis que los métodos que realmente no van a ser llamados desde fuera de la clase te recomienda que sean privados a la misma. De esta manera estamos encapsulando estos métodos para que se puedan utilizar solo desde dentro de la clase y no sean públicos.*/

class Persona2(var peso: Double, var altura: Double){
    var nombre: String = "Sin nombre"

    val imc: Double
        get() = calcularImc()

    init {
        require(nombre.isNotBlank()) {"Nombre no vacio"}
        require(peso > 0 && altura >0) {"El peso y/o la altura deben ser mayor que 0."}
    }

    constructor(nombre: String, peso: Double, altura: Double): this(peso, altura) {
        this.nombre = nombre
    }

    private fun calcularImc() = this.peso/(this.altura * this.altura)


    fun saludar(): String{
        return "Hola me llamo ${nombre}."
    }
    private fun alturaEncimaMedia(): Boolean {
        return altura >= 1.75
    }
    private fun pesoEncimaMedia(): Boolean {
        return peso >= 70
    }
    private fun obtenerDescImc(): String{
        return when (imc){
            in 0.0..18.5 -> "con un peso insuficiente."
            in 18.5..25.0 -> "con un peso óptimo."
            in 25.0..30.0 -> "con sobrepeso."
            else -> "con obesidad."
        }
    }
    fun obtenerDesc(): String {
        val descAltura = if (alturaEncimaMedia()) "por encima de la media." else "por debajo de la media."
        val descPeso = if (pesoEncimaMedia()) "por encima de la media." else "por debajo de la media."
        val descImc = obtenerDescImc()


        return "${this.nombre} con el peso de ${this.peso}kg $descPeso y la altura de ${this.altura}m $descAltura, tiene un imc de ${this.imc} $descImc\n"
    }
    override fun toString() = ""
}




fun main(){
    val personas = listOf(
        Persona2(57.0, 1.81),
        Persona2("Víctor", 79.0, 1.61),
        Persona2("David", 73.0, 1.71),
        Persona2(80.0, 1.67),
        Persona2("Raúl", 90.0, 1.92),
        Persona2("Fernando", 40.0, 1.61))

    for (persona in personas) {
        println(persona.saludar())
        println(persona.obtenerDesc())
    }
}