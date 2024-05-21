



sealed class Routes (val route: String) {
    object HomeScreen: Routes("HomeScreen")
    object InterestScreen : Routes ("InterestScreen")

    object ProfileScreen : Routes ("ProfileScreen")

    object LoadingScreen: Routes ("LoadingScreen")

    object SignInScreen : Routes ("SignInScreen")


}