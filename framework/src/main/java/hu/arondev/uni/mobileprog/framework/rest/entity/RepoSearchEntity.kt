package hu.arondev.uni.mobileprog.framework.rest.entity

data class RepoSearchEntity(
    val incomplete_results: Boolean = false,
    val items: List<RepoEntity> = listOf(),
    val total_count: Int = 0
)