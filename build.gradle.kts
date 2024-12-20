// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

}
plugins {
    id ("com.android.application") version "8.1.0" apply false
    id ("com.android.library") version "7.4.2" apply false
    id ("org.jetbrains.kotlin.android") version "1.9.10" apply false
    id ("io.realm.kotlin") version "1.8.0" apply false
    id ("com.google.devtools.ksp") version "1.9.10-1.0.13" apply false
    id ("com.google.dagger.hilt.android") version "2.50" apply false
}