import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.implementation(dependencyName: Dependency) {
    add("implementation", dependencyName)
}
fun DependencyHandler.implementation(dependencyName: String) {
    add("implementation", dependencyName)
}
