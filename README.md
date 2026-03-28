# Conversor de Moneda - TPO1

## Descripción de la App
Esta aplicación de Android permite realizar conversiones de moneda (Dólares a Euros y viceversa) de manera dinámica. El usuario puede ingresar una tasa de cambio personalizada y elegir el sentido de la conversión mediante botones de opción (RadioButtons). La interfaz es intuitiva y cuenta con validaciones para asegurar que se ingresen valores correctos antes de realizar el cálculo.

## Integrantes del Grupo
*   **Miranda Salmin, Ismael Ariel** - DNI: 44993241
*   **Quiroga, Maximo Tomas** - DNI: 44642650
*   **Zegarra, Juan Cruz** - DNI: 43057480

## Implementación de MVVM
El proyecto sigue el patrón de arquitectura **MVVM (Model-View-ViewModel)** para separar la lógica de negocio de la interfaz de usuario:

1.  **Model (Modelo):** Representado por la clase `ConversorService`. Contiene la lógica pura de cálculo matemático para la conversión de moneda.
2.  **ViewModel:** Implementado en `MainViewModel`. 
    *   Utiliza `MutableLiveData` para exponer los resultados, mensajes de error y estados de la UI (qué moneda está seleccionada).
    *   Gestiona la comunicación entre la Vista y el Modelo.
3.  **View (Vista):** La `MainActivity` se encarga exclusivamente de la representación visual.
    *   Utiliza **View Binding** para interactuar con los componentes del layout.
    *   **Observa** los cambios en los `LiveData` del ViewModel para actualizar los campos de texto y mostrar mensajes de error mediante `Toast` de forma reactiva.
    *   Notifica al ViewModel sobre las acciones del usuario (clics en botones o cambios en los RadioButtons).
