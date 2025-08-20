# The Icon Project
The Icon project provides a modern, type-safe solution for handling icons in Android applications. It's composed of two distinct libraries to ensure a clean separation of concerns and maximize reusability. The `icon-core-android` library defines the core API, while `icon-compose-android` offers a ready-to-use Composable component for Jetpack Compose.

## ✨ Features

- **Unified API:** A single `IconResource` sealed interface to represent both vector drawables and Compose ImageVectors.

- **Type Safety:** Compile-time safety and IDE auto-completion by eliminating the need to pass raw resource IDs.

- **Seamless Integration:** A drop-in `Icon` composable that works gracefully with both types of icon resources.

- **Composable-first:** Built specifically for use with Jetpack Compose.

## 🚀 Setup
To use the libraries in your project, you'll need to add the Maven repository and declare the dependencies in your Gradle files.

### 1. Add the Maven Repository
Make sure `mavenCentral()` is listed in the `repositories` block in your project's top-level `settings.gradle.kts` file.

```kotlin
// settings.gradle.kts
dependencyResolutionManagement {
  repositories {
    google()
    mavenCentral()
  }
}
```

### 2. Add the Dependencies
You can use the libraries independently, but for most projects, you'll only need to import the `icon-compose-android` library.

```kotlin
// build.gradle.kts (module level)
dependencies {
  // (Optional) Only if the core IconResource and its data classes are needed.
  implementation("io.github.devdiestrolopez.icon.core:icon-core-android:1.0.0")

  // Automatically provides the public API of icon-core-android.
  implementation("io.github.devdiestrolopez.icon.compose:icon-compose-android:1.0.0")
}
```

## 📦 icon-core-android
This is the central API library. It provides the type-safe `IconResource` sealed interface and its implementations.

### How It Works
This library defines a simple but powerful API. It's a foundational component that establishes a common contract for icons across your application.

- **IconResource:** A sealed interface that ensures all possible icon types are known at compile time, enabling exhaustive when expressions.

- **DrawableResource:** Wraps a standard Android `@DrawableRes` integer ID.

- **ImageVectorResource:** Wraps a Compose ImageVector.

This separation allows other libraries (like `icon-compose-android`) to build components that can accept any `IconResource` type.

```kotlin
// Example of a function that accepts any IconResource
fun processIcon(icon: IconResource) {
  when (icon) {
    is DrawableResource -> // Handle drawable resource (icon.id)
    is ImageVectorResource -> // Handle ImageVector (icon.vector)
  }
}
```

## 🎨 icon-compose-android
This library provides a drop-in `Icon` composable for Jetpack Compose. It's built on top of the `icon-core-android` library and automatically handles rendering the different types of `IconResource`s you provide.

### How It Works
The library contains a single `Icon` composable that takes an `IconResource` parameter. It uses a when expression to determine the specific icon type and renders it using the appropriate Compose APIs (`painterResource()` for `DrawableResource` and `rememberVectorPainter()` for `ImageVectorResource`). All the rendering logic is handled internally, providing a clean and consistent API for your UI.

### Usage
The `Icon` composable is a direct replacement for the standard Compose Material 3 Icon component.

```kotlin
@Composable
fun MyScreen() {
  // Example with a drawable resource
  Icon(
    iconResource = DrawableResource(id = R.drawable.ic_my_icon),
    contentDescription = "A custom drawable icon"
  )
  
  // Example with a Compose ImageVector
  Icon(
    iconResource = ImageVectorResource(vector = Icons.Default.Favorite),
    contentDescription = "A favorite icon"
  )
}
```

## 🔗 Use with iconset-generator-plugin
These libraries can be used as standalone components. However, their full value is realized when used in conjunction with the `iconset-generator-plugin`. The plugin streamlines icon management by automatically generating type-safe code that can be used directly with these libraries.

### Plugin Setup
First, add the plugin to your project-level `build.gradle.kts` file.

```kotlin
// build.gradle.kts (project level)
plugins {
  id("io.github.devdiestrolopez.iconset.generator") version "1.0.0" apply false
}
```

Next, apply the plugin in the `build.gradle.kts` file of the specific module where the icon resources reside.

```kotlin
// build.gradle.kts (module level)
plugins {
  id("io.gihub.devdiestrolopez.iconset.generator")
}
```

Finally, you can customize the output package and file name for the generated code.

```kotlin
// build.gradle.kts (module level)
iconSet {
  // (Optional) The package where the generated class will be created.
  // By default, this is set to <your_module_package_name>
  outputPackage.set("com.example.app.ui.icons")
  
  // (Optional) The name of the generated Kotlin object.
  // By default, this is set to "IconSet"
  fileName.set("AppIcons")
}
```

### Generated Output & Usage
After running a Gradle build, a file similar to the one below will be generated in your build directory. This object can then be used directly with the `Icon` composable from the `icon-compose-android` library.

```kotlin
// Example generated file: build/generated/source/main/com/example/app/ui/icons/AppIcons.kt
package com.example.app.ui.icons

object AppIcons {
  val MyIcon: DrawableResource = DrawableResource(id = R.drawable.ic_my_icon)
  val AnotherIcon: DrawableResource = DrawableResource(id = R.drawable.ic_another_icon)
  // ... more icons
}
```

### Example Composable Usage:

```kotlin
@Composable
fun MyScreen() {
  Icon(
    iconResource = AppIcons.MyIcon,
    contentDescription = "My first icon",
    modifier = Modifier.size(48.dp)
  )

  Icon(
    iconResource = AppIcons.AnotherIcon,
    contentDescription = "My second icon",
    modifier = Modifier.size(48.dp)
  )
}
```

## 🤝 Contribution & Feedback

We welcome contributions and feedback! If you find a bug or have an idea for a new feature, please open an issue or submit a pull request on the project's repository.
