



sealed class Routes (val route: String) {
    object HomeScreen: Routes("HomeScreen")
    object InterestScreen : Routes ("InterestScreen")

    object ProfileScreen : Routes ("ProfileScreen")

    object LoadingScreen: Routes ("LoadingScreen")

    object SignInScreen : Routes ("SignInScreen")

    object GPTScreen : Routes ("GPTScreen")

    object  NotificationScreen: Routes ("NotificationScreen")

    object  SubscriptionScreen : Routes ("SubscriptionScreen")

    object PaymentDetailsScreen : Routes ("PaymentDetailsScree")

    object PlanDetailScreen: Routes ("PlanDetailScreen")

    object InfoProfileScreen: Routes("InfoProfileScreen")
    object SecurityScreen: Routes("SecurityScreen")


    object RechDetailsScreen : Routes("RechDetailsScreen/{description}/{type_text}/{signature_date}/{publication_date}/{source}") {
        fun createRoute(description: String?, type_text: String?, signature_date: String?, publication_date: String?, source: String?) =
            "RechDetailsScreen/$description/$type_text/$signature_date/$publication_date/$source"
    }











}