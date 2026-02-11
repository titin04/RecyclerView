# Dinosaurios App 

Aplicacin Android para gestionar una coleccin de dinosaurios, desarrollada como proyecto para 2 DAM (Programacin Multimedia).

##  Caractersticas

*   **Arquitectura**: MVVM (Model-View-ViewModel) + Clean Architecture.
*   **Inyeccin de Dependencias**: Hilt (Dagger).
*   **Autenticacin**: Firebase Authentication (Email/Password).
*   **Interfaz de Usuario**:
    *   Responsive y moderna.
    *   Uso de Fragments y Navigation Component.
    *   RecyclerView con diseo personalizado.
    *   Dialogs para crear, editar y eliminar.

##  Tecnologas Utilizadas

*   **Lenguaje**: Kotlin
*   **UI**: XML Layouts, ViewBinding
*   **Async**: Coroutines & Flow
*   **DI**: Hilt
*   **Backend**: Firebase (Auth)
*   **Arquitectura**: Multi-module (app module por ahora, separado en paquetes `data`, `domain`, `ui`).

##  Configuracin del Proyecto

Para ejecutar este proyecto, necesitas configurar Firebase:

1.  Crea un proyecto en [Firebase Console](https://console.firebase.google.com/).
2.  Aade una app Android con el paquete `com.example.dinosaurios`.
3.  Descarga el archivo `google-services.json`.
4.  Coloca el archivo en la carpeta `app/` del proyecto.
5.  Habilita el mtodo de autenticacin **Correo electrnico/Contrasea** en la consola de Firebase.

##  Estructura del Proyecto

*   `data`: Implementacin de repositorios y fuentes de datos.
*   `domain`: Lgica de negocio (Use Cases, Modelos, Interfaces de Repositorio).
*   `ui`: Fragmentos, Actividades, ViewModels y Adapters.
*   `di`: Mdulos de Hilt.

##  Notas
*   Los datos de los dinosaurios son persistentes solo en memoria localmente (de momento).
*   El login y registro son funcionales contra Firebase.
