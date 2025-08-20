# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# Keep the sealed interface and all of its direct implementations
-keep interface io.github.devdiestrolopez.icon.core.android.IconResource { *; }
-keep class io.github.devdiestrolopez.icon.core.android.DrawableResource { *; }
-keep class io.github.devdiestrolopez.icon.core.android.ImageVectorResource { *; }

# Keep the data class members to ensure they aren't removed or renamed
-keepclassmembers class io.github.devdiestrolopez.icon.core.android.DrawableResource {
    public <init>(...);
    public int getId();
}
-keepclassmembers class io.github.devdiestrolopez.icon.core.android.ImageVectorResource {
    public <init>(...);
    public androidx.compose.ui.graphics.vector.ImageVector getVector();
}
