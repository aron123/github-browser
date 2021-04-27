package hu.arondev.uni.mobileprog.core.interactor

import hu.arondev.uni.mobileprog.core.data.UserRepository

class GetOneUser(private val userRepository: UserRepository) {
    suspend operator fun invoke(username: String) = userRepository.getOneUserByUsername(username)
}