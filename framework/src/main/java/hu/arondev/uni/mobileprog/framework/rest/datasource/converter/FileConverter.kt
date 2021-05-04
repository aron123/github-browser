package hu.arondev.uni.mobileprog.framework.rest.datasource.converter

import hu.arondev.uni.mobileprog.core.domain.File
import hu.arondev.uni.mobileprog.framework.rest.entity.FileEntity
import org.mapstruct.Mapper

@Mapper
interface FileConverter {
    fun convertToDomain(file: FileEntity): File
    fun convertToDomain(files: List<FileEntity>): List<File>
}