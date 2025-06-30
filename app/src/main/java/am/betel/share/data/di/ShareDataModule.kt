package am.betel.share.data.di

import am.betel.share.data.repository.ShareRepositoryImpl
import am.betel.share.data.usecase.ShareSongUseCaseImpl
import am.betel.share.domain.repository.ShareRepository
import am.betel.share.domain.usecase.ShareSongUseCase
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val shareDataModule = module {
    singleOf(::ShareRepositoryImpl) { bind<ShareRepository>() }
    singleOf(::ShareSongUseCaseImpl) { bind<ShareSongUseCase>() }
}