# parcial2Programa
este es un sistema para gestionar espacios recreativos en la universidad, realizar reservas y cancelar reservas de estudiantes es un ambiente recreativo.

estructura del proyecto
clases: EspacioRecreacional que cuenta con atributos de tipo, nombre, capacidad y los horarios y cuenta con un metodo para que todos los horarios que se estipularon siempre aparezcan libres para ser ocupados, aparte cuenta con metodos de reserva, cancelacion de reserva y mostrar la disponiblidad de horarios 

Estudiante esta clase tiene atributos como nombre e ID y permite a los estudiantes que reserven un espacio de recreacion 

GestionRecreacional esta clase permite registrar espacios, verificar la disponibilidad y cancelar reservas 

Y por ultimo esta clase main que es la clase principal del proyecto, donde se ejecuta todo el codigo y se pone en funcionamiento todo lo anterior, aparte nos muestra en pantalla el menu interactivo para los usuarios y que ellos decidan lo que quieren realizar, cuenta con un switch que evalua varias condiciones dependiendo de la accion que quiera realizar el usuario



Y el proyecto cuenta con importaciones de colecciones como java.util.Scanner, java.util.HashMap y java.util.ArrayList.