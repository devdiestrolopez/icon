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
