# VideogamesApp Catalog

Aplicación Android desarrollada en Kotlin que permite consultar, buscar, editar y eliminar lógicamente un catálogo de videojuegos gratuitos usando la API pública de [FreeToGame](https://www.freetogame.com/api/).

---

## Funcionalidades

- Splash screen: descarga y almacenamiento local del catálogo.
- Búsqueda: filtrado por nombre o categoría.
- Detalle: visualización, edición y eliminación lógica de juegos.
- Persistencia local con Room.
- Navegación con Jetpack Compose.
- Arquitectura limpia (Clean Architecture + MVVM).
- Inyección de dependencias con Koin.

---

## Arquitectura

El proyecto está dividido en las siguientes capas:

- **data**: contiene el acceso a datos locales (Room) y remotos (Retrofit).
- **domain**: define los modelos y casos de uso del negocio.
- **presentation**: contiene las pantallas, ViewModels y la UI con Jetpack Compose.
- **di**: configuración de módulos de Koin.

---

## Decisiones Técnicas

- **Jetpack Compose** fue elegido para la interfaz declarativa moderna y eficiente.
- **Room** permite persistencia local estructurada y segura.
- **Koin** se utilizó por su facilidad de configuración para DI.
- **MVVM + Clean Architecture** permite desacoplamiento, testabilidad y mantenimiento sencillo.
- Se utiliza un modelo de datos limpio (`Game`) y mapeadores entre DTO, entidad y dominio.
- **Eliminación lógica** en lugar de física para mantener integridad de datos y permitir recuperación.

---

## Proximos pasos

- **Jetpack Compose** fue elegido para la interfaz declarativa moderna y eficiente.
- **Room** permite persistencia local estructurada y segura.
- **Koin** se utilizó por su facilidad de configuración para DI.
- **MVVM + Clean Architecture** permite desacoplamiento, testabilidad y mantenimiento sencillo.
- Se utiliza un modelo de datos limpio (`Game`) y mapeadores entre DTO, entidad y dominio.
- **Eliminación lógica** en lugar de física para mantener integridad de datos y permitir recuperación.

---

## Cómo correr el proyecto

1. Clona el repositorio.
2. Ábrelo en Android Studio.
3. Asegúrate de tener configurado un emulador o dispositivo físico.
4. Presiona **Run** y Listo!

---

## Requerimientos

- Android Studio Giraffe o más reciente
- Kotlin 1.9.23+
- SDK 34
- Internet para descargar la data

---

## Licencia

MIT License © 2025 KEAM