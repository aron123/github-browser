package hu.arondev.uni.mobileprog.core.interactor

import hu.arondev.uni.mobileprog.core.data.UserRepository
import hu.arondev.uni.mobileprog.core.domain.User

class SearchUsers(private val userRepository: UserRepository) {
    suspend operator fun invoke(username: String) = userRepository.searchUsersByUsername(username)
}