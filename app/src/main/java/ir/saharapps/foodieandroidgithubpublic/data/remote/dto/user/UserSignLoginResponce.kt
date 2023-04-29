package ir.saharapps.foodieapp.data.remote.dto.user


sealed class UserSignLoginResponse<T>(val data: T? = null) {
    class Authorized<T>(data: T? = null): UserSignLoginResponse<T>(data)
    class Unauthorized<T>: UserSignLoginResponse<T>()
    class UnKnownError<T>: UserSignLoginResponse<T>()
    class InternetConnection<T>: UserSignLoginResponse<T>()
    class ServerConnection<T>: UserSignLoginResponse<T>()
}