package hu.arondev.uni.mobileprog.core.interactor

import hu.arondev.uni.mobileprog.core.data.UserRepository

class GetCurrentUser(private val userRepository: UserRepository) {
    suspend operator fun invoke() = userRepository.getCurrentUser()
}