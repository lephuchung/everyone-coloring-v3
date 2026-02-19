package com.example.everyonecoloringv3.core.di

import com.example.everyonecoloringv3.core.data.fake.InMemoryArtworkRepository
import com.example.everyonecoloringv3.core.data.fake.InMemoryColoringEngine
import com.example.everyonecoloringv3.core.data.fake.InMemoryPixelArtGenerationRepository
import com.example.everyonecoloringv3.core.data.fake.InMemorySessionRepository
import com.example.everyonecoloringv3.core.domain.coloring.engine.ColoringEngine
import com.example.everyonecoloringv3.core.domain.repository.ArtworkRepository
import com.example.everyonecoloringv3.core.domain.repository.PixelArtGenerationRepository
import com.example.everyonecoloringv3.core.domain.repository.SessionRepository
import com.example.everyonecoloringv3.core.domain.usecase.ApplyColorUseCase
import com.example.everyonecoloringv3.core.domain.usecase.GeneratePixelArtUseCase
import com.example.everyonecoloringv3.core.domain.usecase.GetArtworksUseCase
import com.example.everyonecoloringv3.core.domain.usecase.StartSessionUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideColoringEngine(): ColoringEngine = InMemoryColoringEngine()

    @Provides
    @Singleton
    fun provideSessionRepository(): SessionRepository = InMemorySessionRepository()

    @Provides
    @Singleton
    fun provideArtworkRepository(): ArtworkRepository = InMemoryArtworkRepository()

    @Provides
    @Singleton
    fun providePixelArtGenerationRepository(): PixelArtGenerationRepository =
        InMemoryPixelArtGenerationRepository()

    @Provides
    @Singleton
    fun provideApplyColorUseCase(
        engine: ColoringEngine,
        sessionRepository: SessionRepository
    ): ApplyColorUseCase = ApplyColorUseCase(engine, sessionRepository)

    @Provides
    @Singleton
    fun provideGetArtworksUseCase(artworkRepository: ArtworkRepository): GetArtworksUseCase =
        GetArtworksUseCase(artworkRepository)

    @Provides
    @Singleton
    fun provideStartSessionUseCase(sessionRepository: SessionRepository): StartSessionUseCase =
        StartSessionUseCase(sessionRepository)

    @Provides
    @Singleton
    fun provideGeneratePixelArtUseCase(
        pixelArtGenerationRepository: PixelArtGenerationRepository
    ): GeneratePixelArtUseCase = GeneratePixelArtUseCase(pixelArtGenerationRepository)
}
